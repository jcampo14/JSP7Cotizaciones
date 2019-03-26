var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ui.utils.masks', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function($mdThemingProvider) {
  $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.html5Mode(true);
  // or
  $locationProvider.html5Mode({
    enabled: true,
    requireBase: false
  });
}]);

app.config(['$qProvider', function($qProvider) {
  $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller('Ctrl', [
  '$localstorage', '$consumeService', '$scope', '$timeout',
  '$http', '$q', '$filter', '$location', '$window',
  function($localstorage, $consumeService, $scope, $timeout,
    $http, $q, $filter, $location, $window) {
    $scope.titulo_formulario = "Grabación de Cotizaciones";

    /* Configurar DataTable */
    $scope.selectedTable = [];
    $scope.limitOptions = [5, 10, 15];
    $scope.options = {
      rowSelection: true,
      multiSelect: false,
      autoSelect: true,
      decapitate: false,
      largeEditDialog: false,
      boundaryLinks: false,
      limitSelect: true,
      pageSelect: true
    };
    $scope.query = {
      order: 'nombre',
      limit: 5,
      page: 1
    };
    $scope.onPaginate = function() {
      $scope.selected = [];
    };
    $scope.toggleLimitOptions = function() {
      $scope.limitOptions = $scope.limitOptions ? undefined : [5,
        10, 15
      ];
    };
    $scope.loadStuff = function() {
      $scope.promiseIncoterm = $timeout(function() {}, 2000);
    };
    $scope.logItem = function(item) {
      console.log(item.nombre, 'was selected');
    };
    $scope.logOrder = function(order) {
      console.log('order: ', order);
    };
    $scope.logPagination = function(page, limit) {
      console.log('page: ', page);
      console.log('limit: ', limit);
    };
    /* Iniciar la forma */
    $scope.init = function() {
      $scope.isLoading = true;
      $scope.tieneCostos = false;
      $scope.autocompleteTerceros.selectedItem = '';
      $scope.autocompleteArticulos.selectedItem = '';
      delete $scope.cotEnc;
      delete $scope.cotDet;
      delete $scope.selectedDetalle;
      /* Traemos los valores inciales */
      var promiseSecciones = $consumeService.get('cot-secciones?emp=' + $localstorage.get('global.empresa', null));
      var promiseIncoterms = $consumeService.get('incoterms?emp=' + $localstorage.get('global.empresa', null));
      var promiseCriterios = $consumeService.get('criterios?emp=' + $localstorage.get('global.empresa', null));
      var promiseidiomas = $consumeService.get('idiomas?emp=' + $localstorage.get('global.empresa', null));
      var promiseAgencias = $consumeService.get('agencias?emp=' + $localstorage.get('global.empresa', null));
      var promiseEmbalajes = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', null));
      var promiseParamFac = $consumeService.get('param-fac?emp=' + $localstorage.get('global.empresa', null));
      var promisePaises = $consumeService.get('paises?emp=' + $localstorage.get('global.empresa', null));
      var promiseVendedores = $consumeService.get('vendedor?emp=' + $localstorage.get('global.empresa', null));
      $q.all([promiseSecciones, promiseIncoterms, promiseCriterios, promiseidiomas, promiseAgencias,
        promiseEmbalajes, promiseParamFac, promisePaises, promiseVendedores
      ]).then(function(values) {
        $scope.secciones = values[0].data;
        $scope.incoterms = values[1].data;
        $scope.criterios = values[2].data;
        $scope.idiomas = values[3].data;
        $scope.agencias = values[4].data;
        $scope.embalajes = values[5].data;
        $scope.paramFac = values[6].data;
        $scope.paises = values[7].data;
        $scope.vendedores = values[8].data;
        /* Si es una consulta */
        var urlParams = $location.search();
        if (urlParams.params != null) {
          $scope.traerCotizacion(urlParams);
        } else {
          /* Scope de modelos para el json */
          $scope.cotEnc = {
            cEmp: $localstorage.get('global.empresa', null),
            usuario: $localstorage.get('global.usuario', null)
          };
          $scope.cotDet = [];
          $scope.selectedArticulo = {};
          $scope.isLoading = false;
        }
      });
    };

    /* Autocomplete */
    $scope.autocompleteTerceros = {
      isDisabled: false,
      noCache: false,
      selectedItem: "",
      searchText: "",
      selectItemChange: function(item) {
        if (item) {
          $scope.cotEnc.cSuc = {};
          $scope.cotEnc.contacto = {};
          $scope.cotEnc.nIde = item.nIde;
          if ($scope.cotEnc.iva == undefined) {
            $scope.cotEnc.iva = item.iva;
          }
        }
      },
      querySearch: function(query) {
        var requestConfig = {
          method: "GET",
          url: 'terceros?emp=' + $localstorage.get('global.empresa', null) + '&filter=' + escape(query),
          headers: {
            Authorization: window.localStorage.getItem('token.jsp7')
          }
        };
        return $http(requestConfig)
          .then(function(result) {
            return result.data.data;
          }, function(error) {
            console.log(error);
            swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
          });
      }
    };

    $scope.autocompleteArticulos = {
      isDisabled: false,
      noCache: true,
      selectedItem: "",
      searchText: "",
      selectItemChange: function(item) {
        if (item) {
          if ($scope.selectedDetalle == null) {
            $scope.selectedDetalle = {};
          }
          $scope.buscarPrecioVenta(item.cEmp, item);
          if (!item.descripcion) {
            $scope.traerDescripcionComercial(item.cEmp, item.cod, $scope.cotEnc.idioma);
          }
        }
      },
      querySearch: function(query2) {
        if (query2 == "") {
          $scope.selectedDetalle.precio_lista = "";
        } else {
          var requestConfig = {
            method: "GET",
            url: 'articulos?emp=' + $localstorage.get('global.empresa', null) +
              "&filter=" + query2.toUpperCase(),
            headers: {
              Authorization: window.localStorage.getItem('token.jsp7')
            }
          };
          return $http(requestConfig)
            .then(function(result) {
              return result.data.data;
            }, function(error) {
              console.log(error);
              swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
            });
        }
      }
    };

    $scope.$watch("autocompleteArticulos.selectedItem", function(newValue, oldValue) {
      if (newValue === oldValue) {
        return;
      } else {
        if ($scope.selectedDetalle != null) {
          if (!$scope.isDisabled) {
            $scope.selectedDetalle.nom = newValue != undefined ? newValue.nom : undefined;
          }
        } else {
          if (!$scope.isDisabled) {
            $scope.selectedDetalle = {
              nom: newValue != undefined ? newValue.nom : undefined
            }
          }
        }
      }
    });

    /* Cargar cotizacion para revision o modificación */
    $scope.traerCotizacion = function(urlParams) {
      var paramsDecode = JSON.parse(decodeURI(atob(urlParams.params)));
      var promiseCotizacion = $consumeService.get('cot-enc?emp=' + paramsDecode.cEmp + '&age=' + paramsDecode.cAgr +
        '&per=' + paramsDecode.per + '&numeroCot=' + paramsDecode.cot + '&rev=' + paramsDecode.rev);
      promiseCotizacion.then(function(result) {
        var dateIni = new Date(result.emi);
        var dateFin = new Date(result.ven);
        var timeDiff = Math.abs(dateIni - dateFin);
        var dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
        $scope.cotEnc = {
          "cEmp": result.cEmp,
          "cAgr": result.cAgr,
          "nIde": result.nIde,
          "criVenta": result.criterio,
          "idioma": result.idioma,
          "incoterm": result.codIncoterm,
          "cot": result.cot,
          "rev": result.rev,
          "idioma": result.idioma,
          "diasValidez": dayDifference,
          "modificar": paramsDecode.modificar,
          "origen": result.origen,
          "destino": result.pais,
          "despacho": result.despacho,
          "terminoPago": result.terminoPago,
          "tiempoEntrega": result.tiempoEntrega,
          "lugarDestino": result.lugarDestino,
          "obs": result.obs,
          "cVen": result.cVen,
          "embalaje": result.embalaje ? result.embalaje.cEmb : null
        };
        if (result.iva > 0) {
          $scope.cotEnc.iva = 'S';
        } else {
          $scope.cotEnc.iva = 'N';
        }
        $scope.cotDet = [];
        /* Buscamos el usuario grabado */
        var promiseGetVendedorByUsuario = $consumeService.get('vendedorByNit?emp=' + $localstorage.get('global.empresa', null) +
          '&nit=' + $scope.cotEnc.cVen);
        promiseGetVendedorByUsuario.then(function(result) {
          $scope.cotEnc.usuario = result.usuario;
        }, function(error) {
          console.log(error);
        })
        var promiseNit = $consumeService.get('tercerosByNit?emp=' + result.cEmp + '&nit=' +
          result.nIde);
        promiseNit.then(function(resultNit) {
          $scope.autocompleteTerceros.selectedItem = resultNit.data[0];
          var promiseContacto = $consumeService.get('cli-contacto?emp=' + $localstorage.get('global.empresa', null) +
            '&nit=' + $scope.cotEnc.nIde);
          promiseContacto.then(function(resultContacto) {
            $scope.contactos = resultContacto.data;
            $scope.cotEnc.contacto = result.contacto;
          });
          var promise = $consumeService.get('suc-cli?emp=' + $scope.cotEnc.cEmp +
            '&nit=' + $scope.cotEnc.nIde);
          promise.then(function(resultSuc) {
            $scope.sucursales = resultSuc.data;
            $scope.cotEnc.cSuc = result.codSuc;
            /* Cargamos los cargos/Costos adicionales */
            var promise = $consumeService.get('fac-costos-adicByMoneda?emp=' + $scope.cotEnc.cEmp +
              "&mon=" + $scope.cotEnc.criVenta.mon);
            promise.then(function(resultCargos) {
              $scope.costosAdic = resultCargos.data;
              if ($scope.costosAdic.length >= 1) {
                $scope.tieneCostos = true;
                index = 0;
                var indexCargos;
                while (index < result.cargos.length) {
                  indexCargos = 0;
                  while (indexCargos < $scope.costosAdic.length) {
                    if ($scope.costosAdic[indexCargos].codigo == result.cargos[index].codCosto) {
                      $scope.costosAdic[indexCargos].selected = true;
                      $scope.costosAdic[indexCargos].valor = result.cargos[index].valor;
                    }
                    indexCargos++;
                  }
                  index++;
                };
                $scope.disableCostoAdic();
              } else {
                $scope.tieneCostos = false;
              }
              $scope.isLoading = false;
            }, function(error) {
              console.log(error);
              swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
            });
          });
        });
        /* Cargamos el detalle */
        var index = 0;
        while (index < result.detalle.length) {
          var recordToAdd = {
            "cEmp": result.detalle[index].cEmp,
            "id": result.detalle[index].articulo,
            "cantidad": result.detalle[index].can,
            "precio_lista": result.detalle[index].lis,
            "precio_venta": result.detalle[index].ven,
            "descuento": result.detalle[index].pDes,
            "descripcion": result.detalle[index].inf7,
            "nom": result.detalle[index].nom
          };
          if ($scope.cotEnc.iva == 'S') {
            var ivaValue = {
              iva: {
                "cDes": result.detalle[index].articulo.idIva != undefined ? result.detalle[index].articulo.idIva.cDes : null,
                "pctj": result.detalle[index].articulo.idIva != undefined ? result.detalle[index].articulo.idIva.pctj : 0
              }
            };
          } else {
            var ivaValue = {
              iva: {
                "cDes": null,
                "pctj": 0
              }
            };
          }
          recordToAdd.iva = ivaValue.iva;
          $scope.cotDet.push(recordToAdd);
          index++;
        }
        /* Cargamos las secciones */
        index = 0;
        var indexSeccion;
        while (index < result.secciones.length) {
          indexSeccion = 0;
          while (indexSeccion < $scope.secciones.length) {
            if ($scope.secciones[indexSeccion].codSeccion == result.secciones[index].codSeccion) {
              $scope.secciones[indexSeccion].descripcion_final = result.secciones[index].descripcion;
              $scope.secciones[indexSeccion].etiqueta_final = result.secciones[index].etiqueta;
            }
            indexSeccion++;
          }
          index++;
        };
        $scope.$applyAsync();
      }, function(error) {
        console.log(error);
        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
      });
    };

    $scope.ivaChangeDocument = function() {
      if ($scope.cotEnc.iva == 'S') {
        var promiseArray = [];
        $scope.cotDet.forEach(function callback(currentValue, index, array) {
          var promiseObject = $consumeService.get('articulosByCod?emp=' + currentValue.cEmp + '&cod=' + currentValue.id.cod);
          promiseArray.push(promiseObject);
        });
        $scope.consumingService = true;
        $q.all(promiseArray).then(function(values) {
          $scope.cotDet.forEach(function callback(currentValue, index, array) {
            $scope.cotDet[index].iva = {
              "cDes": values[index].data[0].idIva != undefined ? values[index].data[0].idIva.cDes : null,
              "pctj": values[index].data[0].idIva != undefined ? values[index].data[0].idIva.pctj : 0
            };
          });
          $scope.consumingService = false;
        });
      } else {
        $scope.cotDet.forEach(function callback(currentValue, index, array) {
          $scope.cotDet[index].iva = {
            "cDes": null,
            "pctj": 0
          };
        });
      }
    };

    $scope.showSearch = function() {
      $scope.filter.show = true;
      $timeout(function() {
        document.getElementById("searchProspecto").focus();
      }, 10);
    };

    $scope.hideSearch = function() {
      $scope.filter.show = false;
      $scope.filter.search = '';
    };

    /* Limpiar el filtro del select */
    $scope.clearSelectFilter = function() {
      $scope.searchArticulo = '';
      $scope.searchTercero = '';
      $scope.searchCriterio = '';
      $scope.searchIncoterm = '';
      $scope.searchEmbajale = '';
      $scope.searchPais = '';
      $scope.searchContacto = '';
    };

    /* Funciones para los select */
    $scope.loadSeccionesDetalle = function(cEmp, codSeccion) {
      if ($scope.cotEnc.idioma != undefined) {
        var promise = $consumeService.get('cot-secciones-det-sinonimosByIdioma?emp=' + cEmp +
          '&seccion=' + codSeccion + "&idioma=" + $scope.cotEnc.idioma);
        promise.then(function(result) {
          $scope.secciones_det = result.data;
        }, function(error) {
          console.log(error);
          swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
        });
      } else {
        var promise = $consumeService.get('cot-secciones-det?emp=' + cEmp +
          '&seccion=' + codSeccion);
        promise.then(function(result) {
          $scope.secciones_det = result.data;
        }, function(error) {
          console.log(error);
          swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
        });
      }
    };

    $scope.cargarSucursales = function() {
      return $consumeService.get('suc-cli?emp=' + $scope.cotEnc.cEmp +
        '&nit=' + $scope.cotEnc.nIde).then(function(result) {
        $scope.sucursales = result.data;
      }, function(error) {
        console.log(error);
        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
      });
    };

    $scope.cargarContactos = function() {
      return $consumeService.get('cli-contacto?emp=' + $localstorage.get('global.empresa', null) +
        '&nit=' + $scope.cotEnc.nIde).then(function(result) {
        $scope.contactos = result.data;
      });
    };

    $scope.cargarCostosAdic = function() {
      $scope.consumingService = true;
      var promise = $consumeService.get('fac-costos-adicByMoneda?emp=' + $scope.cotEnc.cEmp +
        "&mon=" + $scope.cotEnc.criVenta.mon);
      promise.then(function(result) {
        $scope.costosAdic = result.data;
        if ($scope.costosAdic.length >= 1) {
          $scope.tieneCostos = true;
          $scope.disableCostoAdic();
        } else {
          $scope.tieneCostos = false;
        }
        $scope.consumingService = false;
      }, function(error) {
        console.log(error);
        $scope.consumingService = false;
        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
      });
    };

    $scope.disableCostoAdic = function() {
      if ($scope.paramFac[0].seguroAut == 'S') {
        $scope.costosAdic.forEach(function(element) {
          if (element.tipo == 'GS') {
            element.isDisabled = true;
          } else {
            element.isDisabled = false;
          }
        });
      }
    };

    $scope.$watch("cotDet.length", function(newValue, oldValue) {
      var sumDet = 0;
      if ($scope.paramFac != undefined) {
        if ($scope.paramFac[0].seguroAut == 'S') {
          if (newValue > 0) {
            $scope.cotDet.forEach(function(element) {
              sumDet = sumDet + (element.cantidad * element.precio_venta);
            }, this);
            $scope.costosAdic.forEach(function(element) {
              if (element.tipo == 'GS') {
                element.valor = sumDet * $scope.paramFac[0].porcSegAut / 100;
              }
            }, this);
          }
        }
      }
    });

    $scope.changeIva = function() {
      var iva = $filter('filter')($scope.ivas, {
        'cDes': $scope.selectedIva
      }, true);
      $scope.selectedDetalle.pctjIva = iva[0].pctj;
    };

    $scope.changePrecioVenta = function(form) {
      if ($scope.selectedDetalle.precio_lista != undefined && $scope.selectedDetalle.precio_lista != "") {
        var valArr = $scope.selectedDetalle.precio_lista * (1 + $scope.paramFac[0].incpArr / 100);
        var valAbj = $scope.selectedDetalle.precio_lista * (1 - $scope.paramFac[0].incpArr / 100);
        if ($scope.selectedDetalle.precio_venta > valArr || $scope.selectedDetalle.precio_venta < valAbj) {
          form.precioVenta.$setValidity('validationError', false);
        } else {
          form.precioVenta.$setValidity('validationError', true);
        }
      }
    };

    /* Asignamos la descripcion del textarea al modelo de las secciones */
    $scope.setDescripcion = function(value) {
      var seccion = value;
      seccion.descripcion_final = seccion.seleccionado.descripcion;
    };

    $scope.buscarPrecioVenta = function(empresa, articulo) {
      $scope.consumingService = true;
      if (articulo.idClase.afInvent == 'S') {
        var promise = $consumeService.get('precios?emp=' + empresa +
          "&cod=" + articulo.cod + "&cri=" + $scope.cotEnc.criVenta.cri);
        promise.then(function(result) {
          if (result.pVen == null) {
            $scope.selectedDetalle.tienePrecio = false;
          } else {
            $scope.selectedDetalle.tienePrecio = true;
            $scope.selectedDetalle.precio_lista = result.pVen;
          }
          $scope.consumingService = false;
        }, function(error) {
          console.log(error);
          $scope.consumingService = false;
          swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
        });
      } else {
        var promise = $consumeService.get('precioServicio?emp=' + empresa +
          "&cod=" + articulo.cod + "&mon=" + $scope.cotEnc.criVenta.mon);
        promise.then(function(result) {
          if (result.pVen == null) {
            $scope.selectedDetalle.tienePrecio = false;
          } else {
            $scope.selectedDetalle.tienePrecio = true;
            $scope.selectedDetalle.precio_lista = result.pVen;
          }
          $scope.consumingService = false;
        }, function(error) {
          console.log(error);
          $scope.consumingService = false;
          swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
        });
      }
    };

    $scope.traerDescripcionComercial = function(empresa, codigo, idioma) {
      $scope.consumingService = true;
      if (idioma == undefined) {
        var promise = $consumeService.get('articulo-descripcion?emp=' + empresa + '&cod=' +
          codigo + '&idioma=');
      } else {
        var promise = $consumeService.get('articulo-descripcion?emp=' + empresa + '&cod=' +
          codigo + '&idioma=' + idioma);
      }
      promise.then(function(result) {
        if (result.data.length > 0) {
          $scope.selectedDetalle.descripcion = result.data[0].descripcion;
        }
        $scope.consumingService = false;
      }, function(error) {
        $scope.consumingService = false;
        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
      });
    };

    /* Funciones */
    $scope.addItemDetalle = function(form) {
      if (form.$valid) {
        var o = $filter('filter')($scope.cotDet, {
          'id': {
            'cod': $scope.autocompleteArticulos.selectedItem.cod
          }
        }, true);
        if (o.length == 0) {
          var itemToAdd = {
            id: $scope.autocompleteArticulos.selectedItem,
            cEmp: $scope.autocompleteArticulos.selectedItem.cEmp,
            cantidad: $scope.selectedDetalle.cantidad,
            precio_lista: $scope.selectedDetalle.precio_lista,
            precio_venta: $scope.selectedDetalle.precio_venta,
            descuento: $scope.selectedDetalle.descuento == "" || $scope.selectedDetalle.descuento == undefined ? 0 : $scope.selectedDetalle.descuento,
            descripcion: $scope.selectedDetalle.descripcion,
            nom: $scope.selectedDetalle.nom
          };
          if ($scope.cotEnc.iva == 'S') {
            var ivaValue = {
              iva: {
                "cDes": $scope.autocompleteArticulos.selectedItem.idIva != undefined ? $scope.autocompleteArticulos.selectedItem.idIva.cDes : null,
                "pctj": $scope.autocompleteArticulos.selectedItem.idIva != undefined ? $scope.autocompleteArticulos.selectedItem.idIva.pctj : 0
              }
            };
          } else {
            var ivaValue = {
              iva: {
                "cDes": null,
                "pctj": 0
              }
            };
          }
          itemToAdd.iva = ivaValue.iva;
          $scope.cotDet.push(itemToAdd);
          delete $scope.autocompleteArticulos.selectedItem;
          $scope.selectedDetalle = null;
          form.$setPristine();
          form.$setUntouched();
        } else {
          swal("Mensaje JSP7", "El artículo ya ha sido ingresado en la cotización", "warning");
        }
      }
    };

    $scope.showEditarTable = function(item) {
      $scope.selectedDetalle = {};
      $scope.autocompleteArticulos.selectedItem = item.id;
      $scope.selectedDetalle.cantidad = item.cantidad;
      $scope.selectedDetalle.precio_lista = item.precio_lista;
      $scope.selectedDetalle.precio_venta = item.precio_venta;
      $scope.selectedDetalle.descuento = item.descuento;
      $scope.selectedDetalle.descripcion = item.descripcion;
      $scope.selectedDetalle.nom = item.nom;
      $scope.isDisabled = true;
      $scope.$applyAsync();
    };

    $scope.editItemDetalle = function(form) {
      if (form.$valid) {
        var index = 0;
        while (index < $scope.cotDet.length) {
          if ($scope.cotDet[index].id.cod == $scope.autocompleteArticulos.selectedItem.cod) {
            if (index > -1) {
              $scope.cotDet[index].cantidad = $scope.selectedDetalle.cantidad;
              $scope.cotDet[index].precio_lista = $scope.selectedDetalle.precio_lista;
              $scope.cotDet[index].precio_venta = $scope.selectedDetalle.precio_venta;
              $scope.cotDet[index].descuento = $scope.selectedDetalle.descuento;
              $scope.cotDet[index].descripcion = $scope.selectedDetalle.descripcion;
              $scope.cotDet[index].nom = $scope.selectedDetalle.nom;
              if ($scope.cotEnc.iva == 'S') {
                var ivaValue = {
                  iva: {
                    "cDes": $scope.autocompleteArticulos.selectedItem.idIva != undefined ? $scope.autocompleteArticulos.selectedItem.idIva.cDes : null,
                    "pctj": $scope.autocompleteArticulos.selectedItem.idIva != undefined ? $scope.autocompleteArticulos.selectedItem.idIva.pctj : 0
                  }
                };
              } else {
                var ivaValue = {
                  iva: {
                    "cDes": null,
                    "pctj": 0
                  }
                };
              }
              $scope.cotDet[index].iva = ivaValue.iva;
            }
          }
          index++;
        }
        $scope.selectedDetalle = {};
        $scope.selectedTable = [];
        delete $scope.autocompleteArticulos.selectedItem;
        form.$setPristine();
        form.$setUntouched();
        $scope.isDisabled = false;
      }
    };

    $scope.deleteItemDetalle = function() {
      var index = 0;
      while (index < $scope.cotDet.length) {
        if ($scope.cotDet[index].id.cod == $scope.autocompleteArticulos.selectedItem.cod) {
          if (index > -1) {
            $scope.cotDet.splice(index, 1);
            delete $scope.selectedDetalle;
            $scope.autocompleteArticulos.selectedItem = '';
            $scope.isDisabled = false;
          }
        }
        index++;
      }
    };

    $scope.clearEditarTable = function(item) {
      delete $scope.selectedDetalle;
      $scope.autocompleteArticulos.selectedItem = '';
      delete $scope.selectedIva;
      $scope.isDisabled = false;
    };

    $scope.grabarCotizacion = function() {
      if ($scope.EncForm.$valid) {
        /* Buscamos las secciones */
        var secciones = [];
        var index = 0;
        while (index < $scope.secciones.length) {
          if (($scope.secciones[index].descripcion_final != null || $scope.secciones[index].descripcion_final != "") &&
            ($scope.secciones[index].etiqueta_final != null || $scope.secciones[index].etiqueta_final != "")) {
            var itemSeccion = {
              "cEmp": $scope.secciones[index].cEmp,
              "codSeccion": $scope.secciones[index].codSeccion,
              "descripcionFinal": $scope.secciones[index].descripcion_final,
              "etiquetaFinal": $scope.secciones[index].etiqueta_final
            };
            secciones.push(itemSeccion);
          }
          index++;
        };
        /* Buscamos el detalle */
        var detalle = [];
        index = 0;
        while (index < $scope.cotDet.length) {
          var itemDetalle = {
            "cEmp": $scope.cotDet[index].cEmp,
            "cod": $scope.cotDet[index].id.cod,
            "cantidad": $scope.cotDet[index].cantidad,
            "precioLista": $scope.cotDet[index].precio_lista == null ? $scope.cotDet[index].precio_venta : $scope.cotDet[index].precio_lista,
            "precioVenta": $scope.cotDet[index].precio_venta,
            "descuento": $scope.cotDet[index].descuento,
            "codIva": $scope.cotDet[index].iva.cDes,
            "descripcion": $scope.cotDet[index].descripcion,
            "nom": $scope.cotDet[index].nom
          };
          detalle.push(itemDetalle);
          index++;
        };
        /* Buscamos los costos */
        var costos = [];
        index = 0;
        if ($scope.costosAdic) {
          while (index < $scope.costosAdic.length) {
            if ($scope.costosAdic[index].selected && ($scope.costosAdic[index].valor != null || $scope.costosAdic[index].valor != "")) {
              var itemCostos = {
                "cEmp": $scope.costosAdic[index].cEmp,
                "idFacCostosAdic": $scope.costosAdic[index].codigo,
                "valor": $scope.costosAdic[index].valor
              };
              costos.push(itemCostos);
            }
            index++;
          };
        }

        /* Armamos el Request */
        var requestBody = {
          "cEmp": $scope.cotEnc.cEmp,
          "cAgr": $scope.cotEnc.cAgr,
          "nIde": $scope.cotEnc.nIde,
          "criVenta": $scope.cotEnc.criVenta.cri,
          "cSuc": $scope.autocompleteTerceros.selectedItem.suc == 'S' ? $scope.cotEnc.cSuc : undefined,
          "idioma": $scope.cotEnc.idioma,
          "usuario": $scope.cotEnc.usuario,
          "usuarioElabora": $localstorage.get('global.usuario', null),
          "diasValidez": $scope.cotEnc.diasValidez,
          "embalaje": $scope.cotEnc.embalaje,
          "cot": $scope.cotEnc.cot,
          "rev": $scope.cotEnc.rev,
          "modificar": $scope.cotEnc.modificar,
          "origen": $scope.cotEnc.origen,
          "destino": $scope.cotEnc.destino,
          "iva": $scope.cotEnc.iva,
          "incoterm": $scope.cotEnc.incoterm,
          "terminoPago": $scope.cotEnc.terminoPago,
          "tiempoEntrega": $scope.cotEnc.tiempoEntrega,
          "lugarDestino": $scope.cotEnc.lugarDestino,
          "despacho": $scope.cotEnc.despacho,
          "contacto": $scope.cotEnc.contacto,
          "obs": $scope.cotEnc.obs,
          "secciones": secciones,
          "detalle": detalle,
          "costos": costos
        };
        swal({
          title: "Mensaje JSP7", //Bold text
          text: "¿Desea guardar la cotización?", //light text
          type: 'question',
          showCancelButton: true,
          confirmButtonText: 'Aceptar',
          cancelButtonText: 'Cancelar',
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          showLoaderOnConfirm: true,
          preConfirm: (promise) => {
            return $http({
              method: "POST",
              url: "cotizacion",
              headers: {
                'Content-Type': 'application/json',
                Authorization: window.localStorage.getItem('token.jsp7')
              },
              data: requestBody
            }).then(function(result) {
              return result.data;
            }, function(error) {
              console.log(error);
              swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
            });
          },
          allowOutsideClick: () => !swal.isLoading()
        }).then((result) => {
          if (result.value) {
            if (result.value.success == true) {
              swal({
                title: 'Mensaje JSP7',
                text: result.value.message,
                type: 'success',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Aceptar'
              }).then((resultConfirm) => {
                if (resultConfirm.value) {
                  //$scope.init();
                  // Ejecutamos el reporte de impresion
                  var promise = $consumeService.get('runJReports?reportName=com.aspsols.cotizaciones.reportes.Cotizaciones_Print');
                  promise.then(function(resultReport) {
                    var urlToExecute = resultReport.data + "&createPl=T" + "&c_emp=" + $localstorage.get('global.empresa', null) +
                      "&c_agr=" + $scope.cotEnc.cAgr + "&cot=" + result.value.numCot + "&rev=" + result.value.numRev +
                      "&per=" + result.value.perCot + "&ver_totales=" + "N" +
                      "&paramform=NO";
                    $window.location.href = urlToExecute;
                    //$window.location.href = 'cotizaciones.html';
                  });
                }
              });
            } else {
              swal("Mensaje JSP7", result.value.message, "warning");
            }
          }
        });
      }
    };

    /* Iniciamos el formulario */
    $scope.init();

  }
]);

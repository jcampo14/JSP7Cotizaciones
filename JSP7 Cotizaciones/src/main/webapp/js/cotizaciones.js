'use strict';
var app = angular.module('App', ['ngMaterial', 'md.data.table',
    'App.utils', 'ui.utils.masks', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog',
    '$mdEditDialog', '$q', '$filter',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, $mdEditDialog,
        $q, $filter) {

        /** Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

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
        $scope.onPaginate = function () {
            $scope.selected = [];
        };
        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5,
                10, 15];
        };
        $scope.loadStuff = function () {
            $scope.promiseIncoterm = $timeout(function () {
            }, 2000);
        };
        $scope.logItem = function (item) {
            console.log(item.nombre, 'was selected');
        };
        $scope.logOrder = function (order) {
            console.log('order: ', order);
        };
        $scope.logPagination = function (page, limit) {
            console.log('page: ', page);
            console.log('limit: ', limit);
        };
        /* Iniciar la forma */
        $scope.init = function () {
            $scope.isLoading = true;
            $scope.tieneCostos = false;
            $scope.autocompleteTerceros.selectedItem = '';
            $scope.autocompleteArticulos.selectedItem = '';
            delete $scope.cot_enc;
            delete $scope.cot_det;
            delete $scope.selectedDetalle;
            /* Traemos los valores inciales */
            var promiseSecciones = $consumeService.get('cot-secciones/?emp=' + $localstorage.get('global.empresa', null));
            var promiseIncoterms = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', null));
            var promiseCriterios = $consumeService.get('criterios/?emp=' + $localstorage.get('global.empresa', null));
            var promiseidiomas = $consumeService.get('idiomas/?emp=' + $localstorage.get('global.empresa', null));
            var promiseAgencias = $consumeService.get('agencias/?emp=' + $localstorage.get('global.empresa', null));
            var promiseEmbalajes = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', null));
            var promiseParamFac = $consumeService.get('param-fac/?emp=' + $localstorage.get('global.empresa', null));
            $q.all([promiseSecciones, promiseIncoterms, promiseCriterios, promiseidiomas, promiseAgencias,
                promiseEmbalajes, promiseParamFac]).then(function (values) {
                    $scope.secciones = values[0].data;
                    $scope.incoterms = values[1].data;
                    $scope.criterios = values[2].data;
                    $scope.idiomas = values[3].data;
                    $scope.agencias = values[4].data;
                    $scope.embalajes = values[5].data;
                    $scope.paramFac = values[6].data;
                    // Agregamos Idioma vacio (IDIOMAS)
                    var idiomaNull = {
                        "cEmp": $localstorage.get('global.empresa', null),
                        "idioma": "",
                        "version": 0
                    };
                    $scope.idiomas.unshift(idiomaNull);
                    $scope.isLoading = false;
                });
            /* Scope de modelos para el json */
            $scope.cot_enc = {
                cEmp: $localstorage.get('global.empresa', null),
                cAgr: null,
                nIde: null,
                criVenta: null,
                cSuc: null,
                idioma: null,
                incoterm: null,
                diasValidez: null,
                embalaje: null
            };
            $scope.cot_det = [];
            $scope.selectedArticulo = {};
        };

        /* Autocomplete */
        $scope.autocompleteTerceros = {
            isDisabled: false,
            noCache: false,
            selectedItem: "",
            searchText: "",
            selectItemChange: function (item) {
                if (item) {
                    $scope.cot_enc.nIde = item.nIde;
                }
                console.log('Actual: ' + $scope.cot_enc.nIde + '\nSeleccionado: ' + $scope.autocompleteTerceros.selectedItem);
            },
            querySearch: function (query) {
                return $http.get('terceros/?emp=' + $localstorage.get('global.empresa', null) + '&filter=' + escape(query))
                    .then(function (result) {
                        return result.data.data;
                    }, function error(error) {
                        console.log(error);
                    });
            }
        };

        $scope.autocompleteArticulos = {
            isDisabled: false,
            noCache: true,
            selectedItem: "",
            searchText: "",
            selectItemChange: function (item) {
                if (item) {
                    $scope.selectedArticulo = item;
                    $scope.buscarPrecioVenta(item.cEmp, item.cod);
                    $scope.$applyAsync;
                }
            },
            querySearch: function (query2) {
                if (query2.length >= 3) {
                    var deferred = $q.defer();
                    var promise = $consumeService.get('articulos/?emp=' + $localstorage.get('global.empresa', null)
                        + "&filter=" + query2.toUpperCase());
                    promise.then(function (result) {
                        deferred.resolve(result.data);
                    });
                    return deferred.promise;
                } else {
                    return [];
                }
            }
        };

        /* Iniciamos el formulario */
        $scope.init();

        /* Limpiar el filtro del select */
        $scope.clearSelectFilter = function () {
            $scope.searchArticulo = '';
            $scope.searchTercero = '';
            $scope.searchCriterio = '';
            $scope.searchIncoterm = '';
            $scope.searchEmbajale = '';
        };

        /* Funciones para los select */
        $scope.loadSeccionesDetalle = function (cEmp, codSeccion) {
            var promise = $consumeService.get('cot-secciones-det/?emp=' + cEmp
                + '&seccion=' + codSeccion);
            promise.then(function (result) {
                $scope.secciones_det = result.data;
            });
        };

        $scope.cargarSucursales = function () {
            var promise = $consumeService.get('suc-cli/?emp=' + $scope.cot_enc.cEmp
                + '&nit=' + $scope.cot_enc.nIde);
            promise.then(function (result) {
                $scope.sucursales = result.data;
            });
        };

        $scope.cargarCostosIncoterm = function () {
            var promise = $consumeService.get('incoterm-fac-costos-adic/?emp=' + $scope.cot_enc.cEmp
                + '&incoterm=' + $scope.cot_enc.incoterm);
            promise.then(function (result) {
                $scope.costosAdic = result.data;
                if ($scope.costosAdic.length > 1) {
                    $scope.tieneCostos = true;
                } else {
                    $scope.tieneCostos = false;
                }
            });
        };

        $scope.changeIva = function () {
            var iva = $filter('filter')($scope.ivas, { 'cDes': $scope.selectedIva }, true);
            $scope.selectedDetalle.pctjIva = iva[0].pctj;
        };

        $scope.changePrecioVenta = function (form) {
            var valArr = $scope.selectedDetalle.precio_lista * (1 + $scope.paramFac[0].incpArr / 100);
            var valAbj = $scope.selectedDetalle.precio_lista * (1 - $scope.paramFac[0].incpArr / 100);
            if ($scope.selectedDetalle.precio_venta > valArr || $scope.selectedDetalle.precio_venta < valAbj) {
                form.precioVenta.$setValidity('validationError', false);
            } else {
                form.precioVenta.$setValidity('validationError', true);
            }
        };

        /* Asignamos la descripcion del textarea al modelo de las secciones */
        $scope.setDescripcion = function (value) {
            var seccion = value;
            seccion.descripcion_final = seccion.seleccionado.descripcion;
        };

        /* Funciones */
        $scope.addItemDetalle = function (form) {
            if (form.$valid) {
                var o = $filter('filter')($scope.cot_det, { 'cod': $scope.selectedArticulo.cod }, true);
                if (o.length == 0) {
                    var itemToAdd = {
                        id: $scope.autocompleteArticulos.selectedItem,
                        cEmp: $scope.autocompleteArticulos.selectedItem.cEmp,
                        cod: $scope.autocompleteArticulos.selectedItem.cod,
                        nom: $scope.autocompleteArticulos.selectedItem.nom,
                        cantidad: $scope.selectedDetalle.cantidad,
                        precio_lista: $scope.selectedDetalle.precio_lista,
                        precio_venta: $scope.selectedDetalle.precio_venta,
                        descuento: $scope.selectedDetalle.descuento
                    };
                    if ($scope.autocompleteTerceros.selectedItem.iva == 'S') {
                        var ivaValue = {
                            iva: {
                                "cDes": $scope.autocompleteArticulos.selectedItem.idIva.cDes,
                                "pctj": $scope.autocompleteArticulos.selectedItem.idIva.pctj
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
                    $scope.cot_det.push(itemToAdd);
                    delete $scope.autocompleteArticulos.selectedItem;
                    delete $scope.selectedDetalle;
                    form.$setPristine();
                    form.$setUntouched();
                } else {
                    swal("Mensaje JSP7", "El artículo ya ha sido ingresado en la cotización", "warning");
                }
            }
        };

        $scope.editItemDetalle = function (form) {
            if (form.$valid) {
                var index = 0;
                while (index < $scope.cot_det.length) {
                    if ($scope.cot_det[index].cod == $scope.selectedArticulo.cod) {
                        if (index > -1) {
                            $scope.cot_det[index].cantidad = $scope.selectedDetalle.cantidad;
                            $scope.cot_det[index].precio_lista = $scope.selectedDetalle.precio_lista;
                            $scope.cot_det[index].precio_venta = $scope.selectedDetalle.precio_venta;
                            $scope.cot_det[index].descuento = $scope.selectedDetalle.descuento;
                            if ($scope.autocompleteTerceros.selectedItem.iva == 'S') {
                                var ivaValue = {
                                    iva: {
                                        "cDes": $scope.autocompleteArticulos.selectedItem.idIva.cDes,
                                        "pctj": $scope.autocompleteArticulos.selectedItem.idIva.pctj
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
                            $scope.cot_det[index].iva = ivaValue.iva;
                            delete $scope.selectedDetalle;
                            delete $scope.autocompleteArticulos.selectedItem;
                            form.$setPristine();
                            form.$setUntouched();
                            $scope.isDisabled = false;
                        }
                    }
                    index++;
                }
            }
        };

        $scope.deleteItemDetalle = function () {
            var index = 0;
            while (index < $scope.cot_det.length) {
                if ($scope.cot_det[index].cod == $scope.selectedArticulo.cod) {
                    if (index > -1) {
                        $scope.cot_det.splice(index, 1);
                        delete $scope.selectedDetalle;
                        $scope.autocompleteArticulos.selectedItem = '';
                        $scope.isDisabled = false;
                    }
                }
                index++;
            }
        };

        $scope.buscarPrecioVenta = function (empresa, codigo) {
            $scope.selectedDetalle = {};
            var promise = $consumeService.get('precios/?emp=' + empresa
                + "&cod=" + codigo + "&cri=" + $scope.cot_enc.criVenta.cri);
            promise.then(function (result) {
                if (result.pVen == null) {
                    swal("Mensaje JSP7", "El artículo no tiene precio de lista", "warning");
                } else {
                    $scope.selectedDetalle.precio_lista = result.pVen;
                    $scope.selectedIva = $scope.selectedArticulo.idIva.cDes;
                    $scope.selectedDetalle.pctjIva = $scope.selectedArticulo.idIva.pctj;
                }
            });
        };

        $scope.showEditarTable = function (item) {
            $scope.selectedDetalle = {};
            $scope.autocompleteArticulos.selectedItem = item.id;
            $scope.selectedDetalle.cantidad = item.cantidad;
            $scope.selectedDetalle.precio_lista = item.precio_lista;
            $scope.selectedDetalle.precio_venta = item.precio_venta;
            $scope.selectedDetalle.descuento = item.descuento;
            $scope.selectedIva = item.iva.cDes;
            $scope.selectedDetalle.pctjIva = item.iva.pctj;
            $scope.isDisabled = true;
        };

        $scope.clearEditarTable = function (item) {
            delete $scope.selectedDetalle;
            $scope.autocompleteArticulos.selectedItem = '';
            delete $scope.selectedIva;
            $scope.isDisabled = false;
        };

        $scope.grabarCotizacion = function () {
            swal({
                title: "Mensaje JSP7", //Bold text
                text: "¿Desea guardar la cotización?", //light text
                type: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.value) {
                    /* Buscamos las secciones */
                    var secciones = [];
                    var index = 0;
                    while (index < $scope.secciones.length) {
                        if ($scope.secciones[index].descripcion_final != null) {
                            var itemSeccion = {
                                "cEmp": $scope.secciones[index].cEmp,
                                "codSeccion": $scope.secciones[index].codSeccion,
                                "descripcionFinal": $scope.secciones[index].descripcion_final
                            };
                            secciones.push(itemSeccion);
                        }
                        index++;
                    };
                    /* Buscamos el detalle */
                    var detalle = [];
                    index = 0;
                    while (index < $scope.cot_det.length) {
                        var itemDetalle = {
                            "cEmp": $scope.cot_det[index].cEmp,
                            "cod": $scope.cot_det[index].cod,
                            "cantidad": $scope.cot_det[index].cantidad,
                            "precioLista": $scope.cot_det[index].precio_lista,
                            "precioVenta": $scope.cot_det[index].precio_venta,
                            "descuento": $scope.cot_det[index].descuento,
                            "codIva": $scope.cot_det[index].iva.cDes
                        };
                        detalle.push(itemDetalle);
                        index++;
                    };
                    /* Buscamos los costos */
                    var costos = [];
                    index = 0;
                    while (index < $scope.costosAdic.length) {
                        if ($scope.costosAdic[index].valor != null) {
                            var itemCostos = {
                                "cEmp": $scope.costosAdic[index].cEmp,
                                "idFacCostosAdic": $scope.costosAdic[index].idFacCostosAdic,
                                "valor": $scope.costosAdic[index].valor
                            };
                            costos.push(itemCostos);
                        }
                        index++;
                    };
                    /* Armamos el Request */
                    var requestBody = {
                        "cEmp": $scope.cot_enc.cEmp,
                        "cAgr": $scope.cot_enc.cAgr,
                        "nIde": $scope.cot_enc.nIde,
                        "criVenta": $scope.cot_enc.criVenta.cri,
                        "cSuc": $scope.cot_enc.cSuc,
                        "idioma": $scope.cot_enc.idioma,
                        "usuario": $localstorage.get('global.usuario', null),
                        "diasValidez": $scope.cot_enc.diasValidez,
                        "embalaje": $scope.cot_enc.embalaje,
                        "iva": $scope.autocompleteTerceros.selectedItem.iva,
                        "secciones": secciones,
                        "detalle": detalle,
                        "costos": costos
                    };
                    /* Consumimos el servicio */
                    var configRequest = {
                        method: "POST",
                        url: "cotizacion/",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: requestBody
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            swal("Mensaje JSP7", result.message, "success");
                            $scope.init();
                        } else {
                            swal("Mensaje JSP7", result.message, "warning");
                        }
                    });
                }
            });
        };

        $scope.logIva = function () {
            console.log($scope.selectedIva);
        };
    }]);
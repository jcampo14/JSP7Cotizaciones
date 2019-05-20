var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function($mdThemingProvider) {
  'use strict';
  $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('Ctrl',
  function($localstorage, $scope, $http, $window, $mdDialog, $consumeService) {
    $scope.titulo_formulario = "Consulta de Cotizaciones";

    $scope.init = function() {
      /* Configurar DataTable */
      $scope.selected = [];
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
        order: '-cot',
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

      $scope.searchParam = "BY-PER";
      $scope.param = {
        perIni: toCharDate(new Date()),
        perFin: toCharDate(new Date())
      };

    };

    function toCharDate(date) {
      if (date.getMonth() < 10) {
        return date.getFullYear() + "0" + (date.getMonth() + 1);
      } else {
        return date.getFullYear() + "" + (date.getMonth() + 1);
      }
    };

    $scope.getRecordsByPer = function() {
      if ($scope.formBuscarPeriodo.$valid) {
        //$scope.queryData = [];
        console.log('page: ', $scope.query.page);
        console.log('limit: ', $scope.query.limit);
        var requestConfig = {
          method: "GET",
          url: "cot-encByPeriodo?emp=" + $localstorage.get("global.empresa", null) + "&perIni=" +
            $scope.param.perIni + "&perFin=" + $scope.param.perFin + "&page=" + $scope.query.page +
            "&size=" + $scope.query.limit + "&order=" + $scope.query.order,
          headers: {
            Authorization: window.localStorage.getItem('token.jsp7')
          }

        };
        $scope.promise = $http(requestConfig);
        $scope.promise.then(function(result) {
          $scope.queryData = result.data;
        });
      }
    };

    $scope.getRecordsByCliente = function() {
      //$scope.queryData = [];
      console.log('page: ', $scope.query.page);
      console.log('limit: ', $scope.query.limit);
      var requestConfig = {
        method: "GET",
        url: "cot-encByCliente?emp=" + $localstorage.get("global.empresa", null) + "&cliente=" +
          $scope.param.cliente + "&page=" + $scope.query.page +
          "&size=" + $scope.query.limit + "&order=" + $scope.query.order,
        headers: {
          Authorization: window.localStorage.getItem('token.jsp7')
        }
      };
      $scope.promise = $http(requestConfig);
      $scope.promise.then(function(result) {
        $scope.queryData = result.data;
      });
    };

    $scope.getRecordsByNumero = function() {
      //$scope.queryData = [];
      console.log('page: ', $scope.query.page);
      console.log('limit: ', $scope.query.limit);
      var requestConfig = {
        method: "GET",
        url: "cot-encByNumero?emp=" + $localstorage.get("global.empresa", null) + "&numeroCot=" +
          $scope.param.numeroCot + "&page=" + $scope.query.page +
          "&size=" + $scope.query.limit + "&order=" + $scope.query.order,
        headers: {
          Authorization: window.localStorage.getItem('token.jsp7')
        }
      };
      $scope.promise = $http(requestConfig);
      $scope.promise.then(function(result) {
        $scope.queryData = result.data;
      });
    };

    $scope.getRecords = function() {
      if ($scope.searchParam == "BY-CLIENTE") {
        $scope.getRecordsByCliente();
      } else if ($scope.searchParam == "BY-PER") {
        $scope.getRecordsByPer();
      } else if ($scope.searchParam == "BY-NUMERO") {
        $scope.getRecordsByNumero();
      }
    };

    $scope.llamarFormulario = function(accion) {
      if ($scope.selected.length == 1) {
        /* Codificar BASE64 */
        var jsonToSend = {
          "cEmp": $scope.selected[0].cEmp,
          "per": $scope.selected[0].per,
          "cAgr": $scope.selected[0].cAgr,
          "cot": $scope.selected[0].cot,
          "rev": $scope.selected[0].rev,
          "modificar": accion
        };
        var params = encodeURI(btoa(JSON.stringify(jsonToSend)));
        $window.location.href = 'cotizaciones.html?params=' + params;
      } else {
        swal("Mensaje JSP7", "Debe seleccionar 1 registro.", "warning");
      }
    };

    $scope.autocompleteTerceros = {
      isDisabled: false,
      noCache: false,
      selectedItem: "",
      searchText: "",
      selectItemChange: function(item) {
        if (item) {
          $scope.param.cliente = item.nIde;
        }
      },
      querySearch: function(query) {
        var requestConfig = {
          method: "GET",
          url: "terceros?emp=" + $localstorage.get('global.empresa', null) + "&filter=" + escape(query),
          headers: {
            Authorization: window.localStorage.getItem('token.jsp7')
          }
        }
        return $http(requestConfig)
          .then(function(result) {
            return result.data.data;
          }, function(error) {
            console.log(error);
            swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
          });
      }
    };

    $scope.convertirAPedido = function(ev) {
      $scope.dialog = {
        titulo: "Convertir Cotización a Pedido"
      };
      $mdDialog.show({
          controller: ConvertirAPedidoController,
          templateUrl: 'dialog1.tmpl.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          scope: $scope,
          preserveScope: true,
          clickOutsideToClose: true,
          fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
        })
        .then(function(action) {
          // $scope.status = 'You said the information was "' + answer + '".';
        }, function() {
          // $scope.status = 'You cancelled the dialog.';
        });
    };

    function ConvertirAPedidoController($scope, $mdDialog) {
      $scope.record = {};
      $scope.hide = function() {
        $mdDialog.hide();
      };
      $scope.cancel = function() {
        $mdDialog.cancel();
      };
      $scope.action = function(action) {
        if (action == 'OK') {
          if ($scope.selected.length == 1) {
            var requestBody = {
              "cEmp": $scope.selected[0].cEmp,
              "per": $scope.selected[0].per,
              "cAgr": $scope.selected[0].cAgr,
              "cot": $scope.selected[0].cot,
              "rev": $scope.selected[0].rev,
              "codSuc": $scope.record.cSuc
            };
            swal({
              title: "Mensaje JSP7", //Bold text
              text: "¿Desea convertir la cotización en pedido?", //light text
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
                  url: "cotizacionAPedido",
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
                      $mdDialog.hide(action);
                      $scope.init();
                    }
                  });
                } else {
                  swal("Mensaje JSP7", result.value.message, "warning");
                  $mdDialog.hide(action);
                }
              }
            });
          } else {
            swal("Mensaje JSP7", "Debe seleccionar un registro.", "warning");
          }
        } else {
          $mdDialog.hide(action);
        }
      };
    };

    $scope.cargarSucursales = function() {
      return $consumeService.get('suc-cli?emp=' + $scope.selected[0].cEmp +
        '&nit=' + $scope.selected[0].cliente.nIde).then(function(result) {
        $scope.sucursales = result.data;
      }, function(error) {
        console.log(error);
        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
      });
    };

    $scope.copiarCotizacion = function() {
      if ($scope.selected.length == 1) {
        var requestBody = {
          "cEmp": $scope.selected[0].cEmp,
          "per": $scope.selected[0].per,
          "cAgr": $scope.selected[0].cAgr,
          "cot": $scope.selected[0].cot,
          "rev": $scope.selected[0].rev,
          "usuarioElabora": $localstorage.get('global.usuario', null)
        };
        swal({
          title: "Mensaje JSP7", //Bold text
          text: "¿Desea copiar la cotización?", //light text
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
              url: "copiarCotizacion",
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
                  $scope.init();
                }
              });
            } else {
              swal("Mensaje JSP7", result.value.message, "warning");
            }
          }
        });
      } else {
        swal("Mensaje JSP7", "Debe seleccionar un registro.", "warning");
      }
    };

    $scope.imprimir = function() {
      swal({
        title: "Mensaje JSP7", //Bold text
        text: "¿Desea imprimir la cotización?", //light text
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si',
        cancelButtonText: 'No'
      }).then((result) => {
        if (result.value) {
          var promise = $consumeService.get('runJReports?reportName=com.aspsols.cotizaciones.reportes.Cotizaciones_Print');
          promise.then(function(result) {
            var urlToExecute = result.data + "&createPl=T" + "&c_emp=" + $localstorage.get('global.empresa', null) +
              "&c_agr=" + $scope.selected[0].cAgr + "&cot=" + $scope.selected[0].cot + "&rev=" + $scope.selected[0].rev +
              "&per=" + $scope.selected[0].per + "&ver_totales=" + "N" +
              "&paramform=NO";
            $window.location.href = urlToExecute;
          });
        }
      });
    };

    $scope.init();
  });

var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog) {
        'use strict';        
        $scope.titulo_formulario = "Definición de Secciones de la Cotización";

        $scope.selectedSeccion = [];
        $scope.selectedSinonimo = [];

        $scope.limitOptions = [5, 10, 15];

        $scope.optionsMaster = {
            rowSelection: true,
            multiSelect: false,
            autoSelect: true,
            decapitate: false,
            largeEditDialog: false,
            boundaryLinks: false,
            limitSelect: true,
            pageSelect: true
        };

        $scope.optionsDetail = {
            rowSelection: true,
            multiSelect: true,
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

        /* Mas configuraciones del data table */

        $scope.onPaginate = function () {
            $scope.selectedSeccion = [];
            $scope.selectedSinonimo = [];
        };

        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5,
                10, 15];
        };

        $scope.loadStuff = function () {
            $scope.promiseSinonimo = $timeout(function () {
            }, 500);
        };

        $scope.clearTableDetail = function (item) {
            $scope.promiseSinonimo = $timeout(function () {
                $scope.selectedSinonimo = [];
                $scope.queryCotSeccionesSinonimo = [];
            }, 500);
        };

        $scope.showDataDetail = function (item) {
            $scope.selectedSinonimo = [];
            $scope.promiseSinonimo = $consumeService.get('cot-secciones-sinonimos?emp=' + $localstorage.get('global.empresa', '00')
                + "&seccion=" + item.codSeccion);
            $scope.promiseSinonimo.then(function (result) {
                $scope.queryCotSeccionesSinonimo = result;
            });
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

        $scope.selectedSeccion = [];
        $scope.promiseSeccion = $consumeService.get('cot-secciones?emp=' + $localstorage.get('global.empresa', '00'));
        $scope.promiseSeccion.then(function (result) {
            $scope.queryCotSecciones = result;
        });        

        /* Dialogo Adicionar */
        $scope.showAdd = function (ev) {
            $scope.dialog = { titulo: "Agregar Registro" };
            if ($scope.selectedSeccion.length == 1) {
                $mdDialog.show({
                    controller: AddDialogController,
                    templateUrl: 'manageSinonimo.tmpl.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    scope: $scope,
                    preserveScope: true,
                    clickOutsideToClose: true,
                    fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                })
                    .then(function (action) {
                        // $scope.status = 'You said the information was "' + answer + '".';
                    }, function () {
                        // $scope.status = 'You cancelled the dialog.';
                    });
            }
        };

        function AddDialogController($scope, $mdDialog) {
            $scope.tableRecord = {
                cEmp: $localstorage.get('global.empresa', '00'),
                version: 0,
                codSeccion: $scope.selectedSeccion[0].codSeccion,
                descripcion: null,
                idioma: null,
            };
            $scope.model = {
                isDisabled: false
            };
            $scope.loadIdiomas = function () {
                var promise = $consumeService.get('idiomas?emp=' + $localstorage.get('global.empresa', '00'));
                promise.then(function (result) {
                    $scope.idiomas = result.data;
                });
            };
            $scope.hide = function () {
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.tableRecord.descripcion == null || $scope.tableRecord.idioma == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "cot-secciones-sinonimos",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.tableRecord
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promiseSinonimo = $consumeService.get('cot-secciones-sinonimos?emp=' + $localstorage.get('global.empresa', '00')
                                    + "&seccion=" + $scope.selectedSeccion[0].codSeccion);
                                $scope.promiseSinonimo.then(function (result) {
                                    $scope.queryCotSeccionesSinonimo = result;
                                    swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                });
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                            $mdDialog.hide(action);
                        });
                    }
                } else {
                    $mdDialog.hide(action);
                }
            };
        };

        /** Eliminar */
        $scope.showDelete = function () {
            if ($scope.selectedSinonimo.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 registro", "warning");
            } else {
                if ($scope.selectedSeccion.length == 0) {
                    swal("Mensaje JSP7", "Debe seleccionar una sección", "warning");
                } else {
                    swal({
                        title: "Mensaje JSP7", //Bold text
                        text: "¿Desea Eliminar el/los registro(s)?", //light text
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Aceptar',
                        cancelButtonText: 'Cancelar'
                    }).then((result) => {
                        if (result.value) {
                            var configRequest = {
                                method: "DELETE",
                                url: "cot-secciones-sinonimos",
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                data: { list: $scope.selectedSinonimo }
                            };
                            var promise = $consumeService.post(configRequest);
                            promise.then(function (result) {
                                if (result.success == true) {
                                    $scope.promiseSinonimo = $consumeService.get('cot-secciones-sinonimos?emp=' + $localstorage.get('global.empresa', '00')
                                        + "&seccion=" + $scope.selectedSeccion[0].codSeccion);
                                    $scope.promiseSinonimo.then(function (result) {
                                        $scope.queryCotSeccionesSinonimo = result;
                                        swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                    });
                                } else {
                                    swal("Mensaje JSP7", result.message, "error");
                                }
                            });
                        }
                    });                    
                }
            }
        };

    }]);
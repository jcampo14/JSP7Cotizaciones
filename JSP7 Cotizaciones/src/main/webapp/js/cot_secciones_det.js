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

        /** Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Definición de Conceptos para las Secciones";

        $scope.selectedSeccion = [];
        $scope.selectedDetalle = [];
        $scope.selectedIdioma = [];

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
            $scope.selectedDetalle = [];
            $scope.selectedIdioma = [];
        };

        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5,
                10, 15];
        };

        $scope.loadStuff = function () {
            var promise = $timeout(function () {
            }, 500);
        };

        $scope.clearTableDetail = function (item) {
            $scope.promiseDetalle = $timeout(function () {
                $scope.selectedDetalle = [];
                $scope.queryCotSeccionesDetalle = [];
                $scope.selectedIdioma = [];
                $scope.queryCotSeccionesDetSinonimo = [];
            }, 500);            
        };

        $scope.showDataDetail = function (item) {
            $scope.selectedIdioma = [];
            $scope.queryCotSeccionesDetSinonimo = [];
            $scope.selectedDetalle = [];
            $scope.promiseDetalle = $consumeService.get('cot-secciones-det?emp=' + $localstorage.get('global.empresa', '00')
                + "&seccion=" + item.codSeccion);
            $scope.promiseDetalle.then(function (result) {
                $scope.queryCotSeccionesDetalle = result;
            });
        };

        $scope.clearTableIdioma = function (item) {
            $scope.promiseSinonimo = $timeout(function () {
                $scope.selectedIdioma = [];
                $scope.queryCotSeccionesDetSinonimo = [];
            }, 500);
        };

        $scope.showDataTableIdioma = function (item) {
            $scope.selectedIdioma = [];
            $scope.promiseSinonimo = $consumeService.get('cot-secciones-det-sinonimos?emp=' + $localstorage.get('global.empresa', '00')
                + "&seccion=" + item.codSeccion + "&codVal=" + item.codVal);
            $scope.promiseSinonimo.then(function (result) {
                $scope.queryCotSeccionesDetSinonimo = result;
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

        $scope.promiseSeccion = $consumeService.get('cot-secciones?emp=' + $localstorage.get('global.empresa', '00'));
        $scope.promiseSeccion.then(function (result) {
            $scope.queryCotSecciones = result;
        });

        $scope.showAddDetalle = function (ev) {
            $scope.dialog = { titulo: "Agregar Registro" };
            if ($scope.selectedSeccion.length == 1) {
                $mdDialog.show({
                    controller: AddDetalleDialogCtrl,
                    templateUrl: 'manageDetalle.tmpl.html',
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

        function AddDetalleDialogCtrl($scope, $mdDialog) {
            $scope.seccion_detalle = {
                cEmp: $localstorage.get('global.empresa', '00'),
                codSeccion: $scope.selectedSeccion[0].codSeccion,
                codVal: null,
                descripcion: null,
                version: 0
            };
            $scope.model = {
                isDisabled: false
            };
            $scope.hide = function () {
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.seccion_detalle.descripcion == null || $scope.seccion_detalle.codVal == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "cot-secciones-det",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.seccion_detalle
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promiseDetalle = $consumeService.get('cot-secciones-det?emp=' + $localstorage.get('global.empresa', '00')
                                    + "&seccion=" + $scope.selectedSeccion[0].codSeccion);
                                $scope.promiseDetalle.then(function (result) {
                                    $scope.queryCotSeccionesDetalle = result;
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

        $scope.showDeteleDetalle = function () {
            if ($scope.selectedDetalle.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 registro", "warning");
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
                            url: "cot-secciones-det",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: { list: $scope.selectedDetalle }
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promiseDetalle = $consumeService.get('cot-secciones-det?emp=' + $localstorage.get('global.empresa', '00')
                                    + "&seccion=" + $scope.selectedSeccion[0].codSeccion);
                                $scope.promiseDetalle.then(function (result) {
                                    $scope.queryCotSeccionesDetalle = result;
                                    swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                });
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                        });
                    }
                });
            }
        };

        $scope.showAddIdioma = function (ev) {
            $scope.dialog = { titulo: "Agregar Registro" };
            if ($scope.selectedSeccion.length == 1) {
                $mdDialog.show({
                    controller: AddIdiomaDialogCtrl,
                    templateUrl: 'manageIdioma.tmpl.html',
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

        function AddIdiomaDialogCtrl($scope, $mdDialog) {
            $scope.detalle_idioma = {
                cEmp: $localstorage.get('global.empresa', '00'),
                codSeccion: $scope.selectedDetalle[0].codSeccion,
                codVal: $scope.selectedDetalle[0].codVal,
                idioma: null,
                descripcion: null,
                version: 0
            };
            $scope.model = {
                isDisabled: false
            };
            $scope.hide = function () {
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.detalle_idioma.descripcion == null || $scope.detalle_idioma.idioma == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "cot-secciones-det-sinonimos",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.detalle_idioma
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promiseSinonimo = $consumeService.get('cot-secciones-det-sinonimos?emp=' + $localstorage.get('global.empresa', '00')
                                    + "&seccion=" + $scope.selectedDetalle[0].codSeccion + "&codVal=" + $scope.selectedDetalle[0].codVal);
                                $scope.promiseSinonimo.then(function (result) {
                                    $scope.queryCotSeccionesDetSinonimo = result;
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
            $scope.loadIdiomas = function () {
                var promise = $consumeService.get('idiomas?emp=' + $localstorage.get('global.empresa', '00'));
                promise.then(function (result) {
                    $scope.idiomas = result.data;
                });
            };
        };

        $scope.showDeleteIdioma = function () {
            if ($scope.selectedIdioma.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 registro", "warning");
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
                            url: "cot-secciones-det-sinonimos",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: { list: $scope.selectedIdioma }
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promiseSinonimo = $consumeService.get('cot-secciones-det-sinonimos?emp=' + $localstorage.get('global.empresa', '00')
                                    + "&seccion=" + $scope.selectedIdioma[0].codSeccion + "&codVal=" + $scope.selectedIdioma[0].codVal);
                                $scope.promiseSinonimo.then(function (result) {
                                    $scope.queryCotSeccionesDetSinonimo = result;
                                    swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                });
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                        });
                    }
                });                
            }
        };

    }]);
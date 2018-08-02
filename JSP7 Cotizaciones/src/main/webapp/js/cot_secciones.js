var app = angular.module('App', ['ngMaterial', 'md.data.table', 'oitozero.ngSweetAlert', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert) {
        'use strict';
        /** Simulamos el Login */
        $localstorage.set('CEmp', '01');
        $localstorage.set('Username', 'ADMIN');

        $scope.model = {
            isDisabled: true
        };

        $scope.titulo_formulario = "Definición de Secciones de la Cotización";

        $scope.limitOptions = [5, 10, 15];

        $scope.options = {
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

        $scope.getTiposSec = [
            {
                codigo: "ENC",
                nombre: "Encabezado de documento"
            },
            {
                codigo: "PIE",
                nombre: "Pie de documento"
            }
        ];

        $scope.selected = [];
        $scope.promise = $consumeService.get('WSCotizaciones/listCotSecciones?emp=' + $localstorage.get('CEmp', '00'));
        $scope.promise.then(function (result) {
            $scope.queryCotSecciones = result;
        });

        /* Dialogo Adicionar */
        $scope.showAdd = function (ev) {
            $scope.dialog = { titulo: "Agregar Registro" };
            $mdDialog.show({
                controller: AddDialogController,
                templateUrl: 'addCotSeccion.tmpl.html',
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
        };

        function AddDialogController($scope, $mdDialog) {

            $scope.cotSeccionesRecord = {
                CEmp: $localstorage.get('CEmp', '00'),
                version: 0,
                codSeccion: null,
                nombreSec: null,
                tipoSeccion: null,
                orden: null
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
                    if ($scope.cotSeccionesRecord.codSeccion == null || $scope.cotSeccionesRecord.nombreSec == null
                        || $scope.cotSeccionesRecord.tipoSeccion == null || $scope.cotSeccionesRecord.orden == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "WSCotizaciones/insertCotSeccion",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.cotSeccionesRecord
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.response.success == true) {
                                $scope.promise = $consumeService.get('WSCotizaciones/listCotSecciones?emp=' + $localstorage.get('CEmp', '00'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.queryCotSecciones = result;
                                });
                                swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                            } else {
                                swal("Mensaje JSP7", result.response.message, "error");
                            }
                            $mdDialog.hide(action);
                        });
                    }
                } else {
                    $mdDialog.hide(action);
                }
            };
        };

        $scope.showEdit = function (ev) {
            if ($scope.selected.length == 1) {
                $mdDialog.show({
                    controller: EditDialogController,
                    templateUrl: 'addCotSeccion.tmpl.html',
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
            } else {
                swal("Mensaje JSP7", "Debe seleccionar 1 registro para editar", "warning");
            }
            $scope.dialog = { titulo: "Editar Registro" };
        };

        function EditDialogController($scope, $mdDialog) {
            $scope.cotSeccionesRecord = $scope.selected[0];
            $scope.model = {
                isDisabled: true
            };
            $scope.hide = function () {
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.cotSeccionesRecord.codSeccion == null || $scope.cotSeccionesRecord.nombreSec == null
                        || $scope.cotSeccionesRecord.tipoSeccion == null || $scope.cotSeccionesRecord.orden == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "WSCotizaciones/updateCotSeccion",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.cotSeccionesRecord
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.response.success == true) {
                                $scope.promise = $consumeService.get('WSCotizaciones/listCotSecciones?emp=' + $localstorage.get('CEmp', '00'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.queryCotSecciones = result;
                                });
                                swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                            } else {
                                swal("Mensaje JSP7", result.response.message, "error");
                            }
                            $mdDialog.hide(action);
                        });
                    }
                } else {
                    $mdDialog.hide(action);
                }
            };
        };

        /* Borrar registro */
        $scope.showDelete = function () {
            if ($scope.selected.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 elemento para borrar.", "warning");
            } else {
                var vm = this;
                vm.confirm = function () {
                    SweetAlert.swal({
                        title: "Mensaje JSP7", //Bold text
                        text: "¿Desea Eliminar el/los registro(s)?", //light text
                        type: "warning", //type -- adds appropiriate icon
                        showCancelButton: true, // displays cancel btton
                        cancelButtonText: "Cancelar",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Aceptar",
                        closeOnConfirm: true, //do not close popup after click on confirm, usefull when you want to display a subsequent popup
                        closeOnCancel: true
                    },
                        function (isConfirm) { //Function that triggers on user action.
                            if (isConfirm) {
                                var dataToSend = angular.toJson({ cotSecciones: $scope.selected });
                                var configRequest = {
                                    method: "POST",
                                    url: "WSCotizaciones/deleteCotSeccion",
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    data: dataToSend
                                };
                                $scope.promiseDeleteIncoterm = $consumeService.post(configRequest);
                                $scope.promiseDeleteIncoterm.then(function (result) {
                                    if (result.response.success == true) {
                                        $scope.promise = $consumeService.get('WSCotizaciones/listCotSecciones?emp=' + $localstorage.get('CEmp', '00'));
                                        $scope.promise.then(function (result) {
                                            $scope.selected = [];
                                            $scope.queryCotSecciones = result;
                                        });
                                        swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                    } else {
                                        swal("Mensaje JSP7", result.response.message, "error");
                                    }
                                });
                            }
                        });
                };
                vm.confirm();
            }
        };

    }]);
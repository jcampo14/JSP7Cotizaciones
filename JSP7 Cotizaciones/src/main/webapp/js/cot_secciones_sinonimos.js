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
            $scope.promiseIncoterm = $timeout(function () {
                $scope.selectedSinonimo = [];
                $scope.queryCotSeccionesSinonimo = [];
            }, 500);
        };

        $scope.showDataDetail = function (item) {
            $scope.selectedSinonimo = [];
            $scope.promiseSinonimo = $consumeService.get('WSCotizaciones/listCotSeccionesSinonimos?emp=' + $localstorage.get('CEmp', '00')
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
        $scope.promiseSeccion = $consumeService.get('WSCotizaciones/listCotSecciones?emp=' + $localstorage.get('CEmp', '00'));
        $scope.promiseSeccion.then(function (result) {
            $scope.queryCotSecciones = result;
        });

        $scope.showDetail = function ($event, record) {
            $scope.selectedSinonimo = [];
            $scope.promiseSinonimo = $consumeService.get('WSCotizaciones/listCotSeccionesSinonimos?emp=' + $localstorage.get('CEmp', '00')
                + "&seccion=" + record.codSeccion);
            $scope.promiseSinonimo.then(function (result) {
                $scope.queryCotSeccionesSinonimo = result;
            });
        };

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
                CEmp: $localstorage.get('CEmp', '00'),
                version: 0,
                codSeccion: $scope.selectedSeccion[0].codSeccion,
                descripcion: null,
                idioma: null,
            };
            $scope.model = {
                isDisabled: false
            };
            $scope.loadIdiomas = function () {
                var promise = $consumeService.get('WSCotizaciones/listIdiomas?emp=' + $localstorage.get('CEmp', '00'));
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
                            url: "WSCotizaciones/insertCotSeccionSinonimo",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.tableRecord
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.response.success == true) {
                                $scope.promiseSinonimo = $consumeService.get('WSCotizaciones/listCotSeccionesSinonimos?emp=' + $localstorage.get('CEmp', '00')
                                    + "&seccion=" + $scope.selectedSeccion[0].codSeccion);
                                $scope.promiseSinonimo.then(function (result) {
                                    $scope.queryCotSeccionesSinonimo = result;
                                    swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                });
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

        /** Eliminar */
        $scope.showDelete = function () {
            if ($scope.selectedSinonimo.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 registro", "warning");
            } else {
                if ($scope.selectedSeccion.length == 0) {
                    swal("Mensaje JSP7", "Debe seleccionar una sección", "warning");
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
                                    var configRequest = {
                                        method: "POST",
                                        url: "WSCotizaciones/deleteCotSeccionSinonimo",
                                        headers: {
                                            'Content-Type': 'application/json'
                                        },
                                        data: { cotSeccionesSinonimos: $scope.selectedSinonimo }
                                    };
                                    var promise = $consumeService.post(configRequest);
                                    promise.then(function (result) {
                                        if (result.response.success == true) {
                                            $scope.promiseSinonimo = $consumeService.get('WSCotizaciones/listCotSeccionesSinonimos?emp=' + $localstorage.get('CEmp', '00')
                                                + "&seccion=" + $scope.selectedSeccion[0].codSeccion);
                                            $scope.promiseSinonimo.then(function (result) {
                                                $scope.queryCotSeccionesSinonimo = result;
                                                swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                            });
                                        } else {
                                            swal("Mensaje JSP7", result.response.message, "error");
                                        }
                                    });
                                }
                            });
                    };
                    vm.confirm();
                }
            }
        };

    }]);
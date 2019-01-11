var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('embalajeController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog) {
        'use strict';
        $scope.titulo_formulario = "Definición de Embalajes";

        $scope.init = function () {
            /* Parametros del data table */
            $scope.selected = [];
            $scope.selectedSinonimo = [];
            $scope.limitOptions = [5, 10, 15];

            $scope.options = {
                rowSelection: true,
                multiSelect: false,
                autoSelect: false,
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

            $scope.querySinonimo = {
                order: 'idioma',
                limit: 5,
                page: 1
            };

            /* Mas configuraciones del data table */
            $scope.toggleLimitOptions = function () {
                $scope.limitOptions = $scope.limitOptions ? undefined : [5,
                    10, 15];
            };

            $scope.loadStuff = function () {
                $scope.promise = $timeout(function () {

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

            $scope.onPaginate = function () {
                $scope.selected = [];
                $scope.$applyAsync();
            };

            /* LLenar la tabla */
            $scope.promise = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', '01'));
            $scope.promise.then(function (result) {
                // this is only run after getData() resolves
                $scope.dataEmbalaje = result;
                $scope.$applyAsync();
            });
        };

        /* Borrar registro */
        $scope.delete = function () {
            if ($scope.selected.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 elemento para borrar.", "warning");
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
                        var dataToSend = angular.toJson({ list: $scope.selected });
                        var configRequest = {
                            method: "DELETE",
                            url: "embalajes",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: dataToSend
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promise = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', '01'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.dataEmbalaje = result;
                                    swal("Mensaje JSP7", "¡Registro borrado exitosamente!", "success");
                                });
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                        });
                    }
                });
            }
        };

        /* Dialogo Editar */
        $scope.showEdit = function (ev) {
            if ($scope.selected.length == 1) {
                $mdDialog.show({
                    controller: EditDialogController,
                    templateUrl: 'dialog1.tmpl.html',
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
                swal("Mensaje JSP7", "Debe seleccionar 1 elemento para editar.", "warning");
            }
        };

        function EditDialogController($scope, $mdDialog) {
            $scope.dialogTitle = "Editar Embalaje";

            $scope.isDisabled = true;

            $scope.editEmbalaje = $scope.selected[0];

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.record.nombre == null || $scope.record.nombre == '') {
                        swal("Mensaje JSP7", "El nombre del embalaje esta vacio.", "error");
                        $scope.promise = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', '01'));
                        $scope.promise.then(function (result) {
                            $scope.selected = [];
                            $scope.dataEmbalaje = result;
                        });
                    } else {

                        var configRequest = {
                            method: "PUT",
                            url: "embalajes",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.record
                        };
                        $scope.promiseEditEmbalaje = $consumeService.post(configRequest);

                        $scope.promiseEditEmbalaje.then(function (result) {
                            if (result.success == true) {
                                $scope.promise = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', '01'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.dataEmbalaje = result;
                                });
                                swal("Mensaje JSP7", "¡Embalaje actualizado exitosamente!", "success");
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                            $mdDialog.hide(action);
                        });
                    }
                } else {
                    $mdDialog.hide(action);
                }
            }
        };

        /* Dialogo Adicionar */
        $scope.showAdd = function (ev) {
            $mdDialog.show({
                controller: AddDialogController,
                templateUrl: 'dialog1.tmpl.html',
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
            $scope.dialogTitle = "Agregar Embalaje";

            $scope.isDisabled = false;

            $scope.record = {
                cEmp: $localstorage.get('global.empresa', '01'),
                cEmb: null,
                version: 0,
                nombre: null
            };

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.record.cEmb == null || $scope.record.nombre == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "embalajes",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.record
                        };
                        var promise = $consumeService.post(configRequest);

                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promise = $consumeService.get('embalajes?emp=' + $localstorage.get('global.empresa', '01'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.dataEmbalaje = result;
                                });
                                swal("Mensaje JSP7", "¡Embalaje guardado exitosamente!", "success");
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                            $mdDialog.hide(action);
                        });
                    }
                } else {
                    $mdDialog.hide(action);
                }
            }
        };

        $scope.showSinonimo = function () {
            $scope.promiseSinonimo = $consumeService.get('EmbalajeSinonimos?emp=' + $localstorage.get('global.empresa', '01')
                + '&emb=' + $scope.selected[0].cEmb);
            $scope.promiseSinonimo.then(function (result) {
                $scope.selectedSinonimo = [];
                $scope.dataSinonimo = result;
            });
        };

        $scope.clearSinonimo = function () {
            $scope.selectedSinonimo = [];
            $scope.dataSinonimo = [];
        };

        $scope.loadIdiomas = function () {
            return $consumeService.get('idiomas?emp=' + $localstorage.get('global.empresa', null)).then(function (result) {
                $scope.idiomas = result.data;
            });
        };

        $scope.showAddSinonimo = function (ev) {
            if ($scope.selected.length == 1) {
                $mdDialog.show({
                    controller: AddSinonimoController,
                    templateUrl: 'dialog2.tmpl.html',
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
                swal("Mensaje JSP7", "Debe seleccionar un embalaje.", "warning");
            }
        };

        function AddSinonimoController($scope, $mdDialog) {
            $scope.dialogTitle = "Agregar Idioma al Embalaje";

            $scope.isDisabled = false;

            $scope.record = {
                cEmp: $localstorage.get('global.empresa', null),
                codEmb: $scope.selected[0].cEmb
            };

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.action = function (action) {
                if (action == 'OK') {
                    if ($scope.record.codEmb == null || $scope.record.descripcion == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "EmbalajeSinonimos",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.record
                        };
                        var promise = $consumeService.post(configRequest);

                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.showSinonimo();
                                swal("Mensaje JSP7", "¡Embalaje guardado exitosamente!", "success");
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                            $mdDialog.hide(action);
                        });
                    }
                } else {
                    $mdDialog.hide(action);
                }
            }
        };

        $scope.deleteSinonimo = function () {
            if ($scope.selectedSinonimo.length == 0) {
                swal("Mensaje JSP7", "Debe seleccionar al menos 1 elemento para borrar.", "warning");
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
                        var dataToSend = $scope.selectedSinonimo;
                        var configRequest = {
                            method: "DELETE",
                            url: "EmbalajeSinonimos",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: dataToSend
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.showSinonimo();
                                swal("Mensaje JSP7", "¡Registro borrado exitosamente!", "success");
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                        });
                    }
                });
            }
        };

        /* Iniciamos el formulario */
        $scope.init();

    }]);

var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('incotermController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog) {
        'use strict';
        /* Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Definición de Incoterms";

        /* LLenar la tabla */
        $scope.promise = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', '00'));
        $scope.promise.then(function (result) {
            // this is only run after getData() resolves        
            $scope.query_incoterm = result;
            $scope.$applyAsync();
        });
        /* Parametros del data table */
        $scope.selected = [];
        $scope.limitOptions = [5, 10, 15];

        $scope.options = {
            rowSelection: true,
            multiSelect: true,
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
                            url: "incoterms/",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: dataToSend
                        };
                        $scope.promiseDeleteIncoterm = $consumeService.post(configRequest);
                        $scope.promiseDeleteIncoterm.then(function (result) {
                            if (result.success == true) {
                                $scope.promise = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', '00'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.query_incoterm = result;
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

        /* Dialogo Editar */
        $scope.showEdit = function (ev) {
            if ($scope.selected.length == 1) {
                $mdDialog.show({
                    locals: { dataToPass: $scope.selected[0] },
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

        function EditDialogController($scope, $mdDialog, dataToPass) {
            $scope.editIncoterm = angular.copy(dataToPass);

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.action = function (action) {
                if (action == 'OK') {
                    var configRequest = {
                        method: "PUT",
                        url: "incoterms/",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.editIncoterm
                    };
                    $scope.promiseEditIncoterm = $consumeService.post(configRequest);

                    $scope.promiseEditIncoterm.then(function (result) {
                        if (result.success == true) {
                            $scope.promise = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', '00'));
                            $scope.promise.then(function (result) {
                                $scope.selected = [];
                                $scope.query_incoterm = result;
                            });
                            swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                        $mdDialog.hide(action);
                    });
                } else {
                    $mdDialog.hide(action);
                }
            }
        }

        /* Dialogo Adicionar */
        $scope.showAdd = function (ev) {
            $mdDialog.show({
                controller: AddDialogController,
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
        };

        function AddDialogController($scope, $mdDialog) {

            $scope.addIncoterm = {
                cEmp: $localstorage.get('global.empresa', '00'),
                codIncoterm: null,
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
                    if ($scope.addIncoterm.codIncoterm == null || $scope.addIncoterm.nombre == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "incoterms/",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.addIncoterm
                        };
                        $scope.promiseAddIncoterm = $consumeService.post(configRequest);

                        $scope.promiseAddIncoterm.then(function (result) {
                            if (result.success == true) {
                                $scope.promise = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', '00'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.query_incoterm = result;
                                });
                                swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
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
        }

    }]);    
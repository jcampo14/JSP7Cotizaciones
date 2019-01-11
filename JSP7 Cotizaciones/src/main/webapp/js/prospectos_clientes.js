var app = angular.module('App', ['ngMaterial', 'md.data.table',
    'App.utils', 'ui.utils.masks', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('Ctrl', function ($localstorage, $consumeService, $scope, $timeout,
    $mdDialog) {
    $scope.titulo_formulario = "Actualización de Prospectos de Clientes";
    $scope.init = function () {
        $scope.filter = {};
        $scope.selected = [];
        $scope.selectedContacto = [];
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
        $scope.queryContactos = {
            order: 'perCargo',
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
        $scope.promise = $consumeService.get('prospectos?emp=' + $localstorage.get('global.empresa', null));
        $scope.promise.then(function (result) {
            // this is only run after getData() resolves
            $scope.queryClientes = result;
            $scope.$applyAsync();
        });
        var promiseZonas = $consumeService.get('zonas?emp=' + $localstorage.get('global.empresa', null));
        promiseZonas.then(function (result) {
            $scope.zonas = result.data;
        }, function (error) {
            swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
        });
    };

    $scope.showSearch = function () {
        $scope.filter.show = true;
        $timeout(function () {
            document.getElementById("searchProspecto").focus();
        }, 10);

    };

    $scope.hideSearch = function () {
        $scope.filter.show = false;
    };

    $scope.showContactos = function () {
        $scope.promiseContactos = $consumeService.get('cli-contacto?emp=' + $localstorage.get('global.empresa', null) +
            '&nit=' + $scope.selected[0].nIde).then(function (result) {
                $scope.queryContactos = result;
            });
    };

    $scope.clearContactos = function () {
        $scope.queryContactos = [];
        $scope.selectedContacto = [];
    };

    /* Dialogo Agregar */
    $scope.showAdd = function (ev) {
        $mdDialog.show({
            controller: addDialogCtrl,
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

    function addDialogCtrl($scope, $mdDialog) {
        $scope.record = {
            "cEmp": $localstorage.get('global.empresa', null)
        };
        $scope.dialogConfig = {
            "disableId": false,
            "title": "Agregar Prospecto"
        };
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.action = function (action, form) {
            if (action == true) {
                if (form.$valid) {
                    var configRequest = {
                        method: "POST",
                        url: "prospectos",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.record
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success) {
                            swal("Mensaje JSP7", "¡Datos guardados exitosamente!", "success");
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                        $mdDialog.hide(action);
                        $scope.init();
                    }, function (error) {
                        console.log(error);
                        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
                    });
                }
            } else {
                $mdDialog.hide(action);
            }
        }
    };

    $scope.showEdit = function (ev) {
        $mdDialog.show({
            controller: editDialogCtrl,
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

    function editDialogCtrl($scope, $mdDialog) {
        $scope.record = $scope.selected[0];
        $scope.dialogConfig = {
            "disableId": false,
            "title": "Agregar Prospecto"
        };
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.action = function (action, form) {
            if (action == true) {
                if (form.$valid) {
                    var configRequest = {
                        method: "PUT",
                        url: "prospectos",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.record
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success) {
                            swal("Mensaje JSP7", "¡Datos guardados exitosamente!", "success");
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                        $mdDialog.hide(action);
                        $scope.init();
                    }, function (error) {
                        console.log(error);
                        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
                    });
                }
            } else {
                $mdDialog.hide(action);
            }
        }
    };

    $scope.showDelete = function () {
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
                    var dataToSend = angular.toJson($scope.selected[0]);
                    var configRequest = {
                        method: "DELETE",
                        url: "prospectos",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: dataToSend
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            swal("Mensaje JSP7", "¡Registro borrado exitosamente!", "success");
                            $scope.init();
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                    });
                }
            });
        }
    };

    $scope.showAddContacto = function (ev) {
        if ($scope.selected.length == 1) {
            $mdDialog.show({
                controller: addContactoDialogCtrl,
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

        }
    };

    function addContactoDialogCtrl($scope, $mdDialog) {
        $scope.dialogConfig = {
            "disableId": false,
            "title": "Agregar Contacto"
        };

        $scope.record = {
            "cEmp": $localstorage.get('global.empresa', null),
            "nit": $scope.selected[0].nIde
        };

        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.action = function (action, form) {
            if (action == true) {
                if (form.$valid) {
                    var configRequest = {
                        method: "POST",
                        url: "cli-contacto",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.record
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success) {
                            swal("Mensaje JSP7", "¡Datos guardados exitosamente!", "success");
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                        $scope.showContactos();
                        $mdDialog.hide(action);
                    }, function (error) {
                        console.log(error);
                        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
                    });
                }
            } else {
                $mdDialog.hide(action);
            }
        }
    };

    $scope.showDeleteContacto = function () {
        if ($scope.selectedContacto.length == 0) {
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
                    var dataToSend = $scope.selectedContacto;
                    var configRequest = {
                        method: "DELETE",
                        url: "cli-contacto",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: dataToSend
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            swal("Mensaje JSP7", "¡Registro borrado exitosamente!", "success");
                            $scope.showContactos();
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                    });
                }
            });
        }
    };

    $scope.init();
});

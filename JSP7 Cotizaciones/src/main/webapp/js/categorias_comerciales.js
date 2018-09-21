var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', function ($localstorage, $consumeService, $scope, $timeout,
    $window, $http, $mdDialog) {
    /* Simulamos el Login */
    $localstorage.set('global.empresa', '01');
    $localstorage.set('global.usuario', 'ADMIN');

    $scope.init = function () {
        $scope.titulo_formulario = "Definición de Categorias Comerciales";
        $scope.isLoading = true;
        var promise = $http.get('categorias-comerciales/?emp=' + $localstorage.get('global.empresa', null));
        promise.then(function (result) {
            $scope.categorias = result.data.data;
            $scope.isLoading = false;
            $scope.$applyAsync();
        }, function (error) {
            console.log(error);
            swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
        });
        $scope.categoriaComercial = {};
    };

    $scope.clickIcon = function (value) {
        $scope.categorias
        $(value.srcElement).parent().toggleClass('expanded')
            .closest('li').find('ul:first').toggleClass('show-effect');
    };

    $scope.clickOption = function (item) {
        $scope.categoriaComercial.cEmp = item.cEmp;
        $scope.categoriaComercial.codCatPadre = item.codCat;
        $scope.categoriaComercial.nombrePadre = item.nombreCat;
    };

    $scope.clearSelected = function () {
        $scope.categoriaComercial = {};
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
            "cEmp": $localstorage.get('global.empresa', null),
            "codCatPadre": $scope.categoriaComercial.codCatPadre
        };
        $scope.dialogConfig = {
            "disableId": true,
            "title": "Agregar Categoria"
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
                        url: "categorias-comerciales/",
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
                            swal("Mensaje JSP7", result.message, "success");
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

    /* Dialogo Editar */
    $scope.showEdit = function (ev) {
        if ($scope.categoriaComercial.codCatPadre) {
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
        } else {
            swal("Mensaje JSP7", "Debe seleccionar 1 elemento para editar.", "warning");
        }
    };

    function editDialogCtrl($scope, $mdDialog) {
        $scope.record = {
            "cEmp": $localstorage.get('global.empresa', null),
            "codCat": $scope.categoriaComercial.codCatPadre,
            "nombreCat": $scope.categoriaComercial.nombrePadre
        };
        $scope.dialogConfig = {
            "disableId": true,
            "title": "Editar Categoria"
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
                        url: "categorias-comerciales/",
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
                            swal("Mensaje JSP7", result.message, "success");
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

    /* Borrar registro */
    $scope.showDelete = function () {
        if ($scope.categoriaComercial == 0) {
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
                    var dataToSend = angular.toJson({
                        "cEmp": $scope.categoriaComercial.cEmp,
                        "codCat": $scope.categoriaComercial.codCatPadre
                    });
                    var configRequest = {
                        method: "DELETE",
                        url: "categorias-comerciales/",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: dataToSend
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            $scope.promise = $consumeService.get('categorias-comerciales/?emp=' + $localstorage.get('global.empresa', '01'));
                            $scope.promise.then(function (result) {
                                $scope.selected = [];
                                $scope.query_incoterm = result;
                                $scope.init();
                                swal("Mensaje JSP7", "¡Registro borrado exitosamente!", "success");
                            });
                        } else {
                            swal("Mensaje JSP7", result.message, "error");
                        }
                    }, function (error) {
                        console.log(error);
                        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
                    });
                }
            });
        }
    }; 

    $scope.init();

});


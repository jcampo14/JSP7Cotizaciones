var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('Ctrl', function ($localstorage, $consumeService, $scope, $timeout,
    $window, $mdDialog, $q) {
    $scope.titulo_formulario = "Actualización de Descripciones Comerciales";

    /* Parametros del data table */
    $scope.selected = [];
    $scope.selectedDetail = [];
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

    $scope.init = function () {
        $scope.query = {
            order: 'nom',
            limit: 5,
            page: 1,
            filter: ''
        };
        $scope.queryDetail = {
            order: 'idioma',
            limit: 5,
            page: 1
        };
        $scope.filter = {};
        $scope.getDataToTable();
        var promiseIdiomas = $consumeService.get('idiomas?emp=' + $localstorage.get('global.empresa', null));
        $q.all([promiseIdiomas]).then(function (values) {
            $scope.idiomas = values[0].data;
        });
    };

    $scope.getDataToTable = function () {
        if ($scope.query.filter.length >= 3 || $scope.query.filter.length == 0) {
            $scope.promise = $consumeService.get('articulosPagina?emp=' + $localstorage.get('global.empresa', null)
                + '&filter=' + $scope.query.filter.toUpperCase() + '&page=' + $scope.query.page + '&size=' + $scope.query.limit);
            $scope.promise.then(function (result) {
                $scope.queryData = result;
            }, function (error) {
                swal('Mensaje JSP7', error.data.message, 'error');
            });
        }
    };

    $scope.showSearch = function () {
        $scope.filter.show = true;
        $scope.$applyAsync();
        $timeout(function () {
            document.querySelector("#filter-textfield").focus();
        }, 500);
    };

    $scope.hideSearch = function () {
        $scope.query.filter = '';
        $scope.filter.show = false;
        $scope.getDataToTable();
    };

    $scope.showDataDetail = function (item) {
        $scope.promiseDetail = $consumeService.get('/articulo-descripcionByCod?emp=' + item.cEmp
            + '&cod=' + item.cod);
        $scope.promiseDetail.then(function (result) {
            $scope.queryDataDetail = result;
        }, function (error) {
            swal('Mensaje JSP7', error.message, 'error');
        });
    };

    $scope.cleardataDetail = function (item) {
        $scope.promiseDetail = $timeout(function () {
            $scope.selectedDetail = [];
            $scope.queryDataDetail = [];
        }, 100);
    };

    /* Dialogo Adicionar */
    $scope.showAdd = function (ev) {
        $mdDialog.show({
            controller: addDialogController,
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

    function addDialogController($scope, $mdDialog) {
        $scope.dialogConfig = {
            title: "Agregar Descripción"
        };
        $scope.model = {
            isDisabled: false
        };
        $scope.record = {
            "cEmp": $scope.selected[0].cEmp,
            "cod": $scope.selected[0].cod
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
                        url: "articulo-descripcion",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.record
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            $scope.showDataDetail($scope.selected[0]);
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
        };
    };

    $scope.showEdit = function (ev) {
        if ($scope.selectedDetail.length == 0) {
            swal("Mensaje JSP7", "Debe seleccionar al menos 1 elemento para editar.", "warning");
        } else {
            $mdDialog.show({
                controller: editDialogController,
                templateUrl: 'dialog1.tmpl.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                scope: $scope,
                preserveScope: true,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
            }).then(function (action) {
                // $scope.status = 'You said the information was "' + answer + '".';
            }, function () {
                // $scope.status = 'You cancelled the dialog.';
            });
        };
    };

    function editDialogController($scope, $mdDialog) {
        $scope.record = angular.copy($scope.selectedDetail[0]);
        $scope.dialogConfig = {
            title: "Editar Descripción"
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
        $scope.action = function (action, form) {
            if (action == true) {
                if (form.$valid) {
                    var configRequest = {
                        method: "POST",
                        url: "articulo-descripcion",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.record
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            $scope.showDataDetail($scope.selected[0]);
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
        };
    };

    $scope.showDelete = function () {
        if ($scope.selectedDetail.length == 0) {
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
                    var dataToSend = angular.toJson({ list: $scope.selectedDetail });
                    var configRequest = {
                        method: "DELETE",
                        url: "articulo-descripcion",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: dataToSend
                    };
                    var promise = $consumeService.post(configRequest);
                    promise.then(function (result) {
                        if (result.success == true) {
                            $scope.showDataDetail($scope.selected[0]);
                            swal("Mensaje JSP7", "¡Registro(s) borrado(s) exitosamente!", "success");
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

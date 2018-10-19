var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('incotermCostosAdicController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog) {
        'use strict';        
        $scope.titulo_formulario = "Asociación de Costos Adicionales a Incoterms";

        $scope.selectedCostosAdic = [];
        $scope.selectedIncoterm = [];

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

        $scope.query = {
            order: 'nombre',
            limit: 5,
            page: 1
        };

        /* Mas configuraciones del data table */

        $scope.onPaginate = function () {
            $scope.selectedIncoterm = [];
            $scope.selectedCostosAdic = [];
        };

        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5,
                10, 15];
        };

        $scope.loadStuff = function () {
            $scope.promiseIncoterm = $timeout(function () {

            }, 500);
        };

        $scope.logItem = function (item) {
            console.log(item.nombre, 'was selected');
        };

        $scope.showDataDetail = function (item) {
            $scope.promiseCostosAdic = $consumeService.get('incoterm-fac-costos-adic?emp=' + $localstorage.get('global.empresa', '00')
                + '&incoterm=' + item.codIncoterm); //$scope.query_incoterm.data[fIndex].codigo);
            $scope.promiseCostosAdic.then(function (result) {
                $scope.selectedCostosAdic = [];
                $scope.queryCostosAdic = result;
            });
        };

        $scope.clearTableDetail = function (item) {
            $scope.promiseCostosAdic = $timeout(function () {
                $scope.selectedCostosAdic = [];
                $scope.queryCostosAdic = [];
            }, 500);
        };

        $scope.logOrder = function (order) {
            console.log('order: ', order);
        };

        $scope.logPagination = function (page, limit) {
            console.log('page: ', page);
            console.log('limit: ', limit);
        };

        /** Llenamos la tabla */
        $scope.promiseIncoterm = $consumeService.get('incoterms?emp=' + $localstorage.get('global.empresa', '00'));
        $scope.promiseIncoterm.then(function (result) {
            $scope.selectedIncoterm = [];
            $scope.query_incoterm = result;
            $scope.$applyAsync();
        });       

        /* Dialogos */
        $scope.showAddCostoAdic = function (ev) {
            $mdDialog.show({
                controller: AddDialogController,
                templateUrl: 'addCostosAdic.tmpl.html',
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

            $scope.addCostoAdic = {
                cEmp: $scope.selectedIncoterm[0].cEmp,
                codigo: null,
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
                    if ($scope.selectedIncoterm.length == 0) {
                        swal("Mensaje JSP7", "Debe seleccionar un incoterm", "error");
                    } else {
                        if ($scope.addCostoAdic.codigo == null) {
                            swal("Mensaje JSP7", "Faltan datos por llenar", "error");
                        } else {
                            var configRequest = {
                                method: "POST",
                                url: "incoterm-fac-costos-adic",
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                data: {
                                    cEmp: $scope.addCostoAdic.cEmp,
                                    idFacCostosAdic: $scope.addCostoAdic.codigo,
                                    idIncoterm: $scope.selectedIncoterm[0].codIncoterm
                                }
                            };
                            var promise = $consumeService.post(configRequest);
                            promise.then(function (result) {
                                if (result.success == true) {
                                    $scope.promiseCostosAdic = $consumeService.get('incoterm-fac-costos-adic?emp=' + $scope.selectedIncoterm[0].cEmp
                                        + '&incoterm=' + $scope.selectedIncoterm[0].codIncoterm);
                                    $scope.promiseCostosAdic.then(function (result) {
                                        $scope.selectedCostosAdic = [];
                                        $scope.queryCostosAdic = result;
                                        $scope.$applyAsync();
                                    });
                                    swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                                } else {
                                    swal("Mensaje JSP7", result.message, "error");
                                }
                                $mdDialog.hide(action);
                            });
                        }
                    }
                } else {
                    $mdDialog.hide(action);
                }
            };

            $scope.loadCostosAdic = function () {
                var promise = $consumeService.get('fac-costos-adic?emp=' + $localstorage.get('global.empresa', '00'));
                promise.then(function (result) {
                    $scope.costosAdic = result.data;
                    $scope.$applyAsync();
                });
            };
        };

        $scope.deleteCostoAdic = function () {
            if ($scope.selectedCostosAdic.length == 0) {
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
                        var dataToSend = angular.toJson({ list: $scope.selectedCostosAdic });
                        var configRequest = {
                            method: "DELETE",
                            url: "incoterm-fac-costos-adic",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: dataToSend
                        };
                        var promise = $consumeService.post(configRequest);
                        promise.then(function (result) {
                            if (result.success == true) {
                                $scope.promiseCostosAdic = $consumeService.get('incoterm-fac-costos-adic?emp=' + $scope.selectedIncoterm[0].cEmp
                                        + '&incoterm=' + $scope.selectedIncoterm[0].codIncoterm);
                                    $scope.promiseCostosAdic.then(function (result) {
                                        $scope.selectedCostosAdic = [];
                                        $scope.queryCostosAdic = result;
                                        $scope.$applyAsync();
                                    });
                                swal("Mensaje JSP7", "¡Transacción exitosa!", "success");
                            } else {
                                swal("Mensaje JSP7", result.message, "error");
                            }
                        });
                    }
                });                
            }
        };

    }]);        
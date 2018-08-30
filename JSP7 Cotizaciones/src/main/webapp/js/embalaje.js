var app = angular.module('App', ['ngMaterial', 'md.data.table', 'oitozero.ngSweetAlert', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('embalajeController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert) {
        'use strict';
        /* Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');        

        $scope.titulo_formulario = "Definición de Embalajes";

        /* LLenar la tabla */
        $scope.promise = $consumeService.get('embalajes/?emp=' + $localstorage.get('global.empresa', '01'));
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
                var vm = this;
                vm.confirm = function () {
                    swal({
                        title: "Mensaje JSP7", //Bold text
                        text: "¿Desea Eliminar el registro?", //light text
                        type: "warning", //type -- adds appropiriate icon
                        showCancelButton: true, // displays cancel btton
                        cancelButtonText: "Cancelar",
                        confirmButtonColor: "#2196f3",
                        confirmButtonText: "Aceptar",
                        closeOnConfirm: true, //do not close popup after click on confirm, usefull when you want to display a subsequent popup
                        closeOnCancel: true
                    },
                        function (isConfirm) { //Function that triggers on user action.
                            if (isConfirm) {
                                var dataToSend = angular.toJson({ list: $scope.selected });
                                var configRequest = {
                                    method: "DELETE",
                                    url: "embalajes/",
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    data: dataToSend
                                };
                                $scope.promiseDeleteEmbalaje = $consumeService.post(configRequest);
                                $scope.promiseDeleteEmbalaje.then(function (result) {
                                    if (result.success == true) {
                                        $scope.promise = $consumeService.get('embalajes/?emp=' + $localstorage.get('global.empresa', '01'));
                                        $scope.promise.then(function (result) {
                                            $scope.selected = [];
                                            $scope.query_incoterm = result;
                                            swal("Mensaje JSP7","¡Registro borrado exitosamente!", "success");
                                        });                                        
                                    } else {
                                        swal("Mensaje JSP7",result.message, "error");
                                    }
                                });                                                             
                            }                        
                        });
                };
                vm.confirm();
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
            
        	$scope.editEmbalaje = $scope.selected[0];
        	
            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.action = function (action) {
                if (action == 'OK') {
                	if($scope.editEmbalaje.nombre == null ||  $scope.editEmbalaje.nombre == ''){
                		swal("Mensaje JSP7","El nombre del embalaje esta vacio.", "error");
                		$scope.promise = $consumeService.get('embalajes/?emp=' + $localstorage.get('global.empresa', '01'));
                        $scope.promise.then(function (result) {
                            $scope.selected = [];
                            $scope.query_incoterm = result;
                        });
                	}else{
                		
                    var configRequest = {
                        method: "PUT",
                        url: "embalajes/",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: $scope.editEmbalaje
                    };
                    $scope.promiseEditEmbalaje = $consumeService.post(configRequest);

                    $scope.promiseEditEmbalaje.then(function (result) {
                        if (result.success == true) {
                            $scope.promise = $consumeService.get('embalajes/?emp=' + $localstorage.get('global.empresa', '01'));
                            $scope.promise.then(function (result) {
                                $scope.selected = [];
                                $scope.query_incoterm = result;
                            });
                            swal("Mensaje JSP7","¡Embalaje actualizado exitosamente!", "success");
                        } else {
                            swal("Mensaje JSP7",result.message, "error");
                        }
                        $mdDialog.hide(action);
                    });
                }
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

            $scope.addEmbalaje = {
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
                    if ($scope.addEmbalaje.cEmb == null || $scope.addEmbalaje.nombre == null) {
                        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
                    } else {
                        var configRequest = {
                            method: "POST",
                            url: "embalajes/",
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: $scope.addEmbalaje
                        };
                        $scope.promiseAddEmbalaje = $consumeService.post(configRequest);

                        $scope.promiseAddEmbalaje.then(function (result) {
                            if (result.success == true) {
                                $scope.promise = $consumeService.get('embalajes/?emp=' + $localstorage.get('global.empresa', '01'));
                                $scope.promise.then(function (result) {
                                    $scope.selected = [];
                                    $scope.query_incoterm = result;
                                });
                                swal("Mensaje JSP7","¡Embalaje guardado exitosamente!", "success");
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
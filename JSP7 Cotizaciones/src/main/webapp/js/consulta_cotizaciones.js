var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$mdDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $mdDialog) {
        'use strict';
        /* Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Consulta de Cotizaciones";

        /* Parametros del data table */
        $scope.selected = [];
        $scope.limitOptions = [5, 10, 15];

        $scope.options = {
            rowSelection: false,
            multiSelect: true,
            autoSelect: false,
            decapitate: false,
            largeEditDialog: false,
            boundaryLinks: false,
            limitSelect: true,
            pageSelect: true
        };

        $scope.query = {
            order: 'cot',
            limit: 5,
            page: 1
        };

        $scope.queryRev = {
            order: '-numeroRev',
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
        $scope.promise = $consumeService.get('cot-enc-list?emp=' + $localstorage.get('global.empresa', null)
            + '&ven=' + $localstorage.get('global.usuario', null));
        $scope.promise.then(function (result) {
            // this is only run after getData() resolves        
            $scope.cotizacionData = result;
            $scope.$applyAsync();
        });

        /* Traer Revisiones */
        $scope.mostrarRevisiones = function (item) {
            $mdDialog.show({
                locals: { dataToPass: $scope.selected[0] },
                controller: DialogController,
                templateUrl: 'dialog1.tmpl.html',
                parent: angular.element(document.body),
                //targetEvent: ev,
                scope: $scope,
                preserveScope: true,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
            }).then(function (action) {
                // $scope.status = 'You said the information was "' + answer + '".';
            }, function () {
                // $scope.status = 'You cancelled the dialog.';
            });
            $scope.promiseRevisiones = $consumeService.get('cot-enc-rev?emp=' + item.cEmp
                + '&age=' + item.cAgr + '&per=' + item.per + '&numeroCot=' + item.cot);
            $scope.promiseRevisiones.then(function (result) {
                $scope.revisionesData = result;
            });
        };

        function DialogController($scope, $mdDialog) {
            $scope.hide = function () {
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
            $scope.action = function (action) {
                if (action == 'OK') {
                } else {
                    $mdDialog.hide(action);
                }
            };
            $scope.agregarRevDocument = function (item) {
                /* Codificar BASE64 */   
                var jsonToSend = {
                    "cEmp": item.cEmp,
                    "per": item.per,
                    "cAgr": item.cAgr,
                    "cot": item.cot,
                    "rev": item.numeroRev,
                    "modificar": 'N'
                };
                var params = encodeURI(btoa(JSON.stringify(jsonToSend)));
                $window.location.href = '/cotizaciones.html?params=' + params;
            };
            $scope.modificarRevDocument = function (item) {
                /* Codificar BASE64 */   
                var jsonToSend = {
                    "cEmp": item.cEmp,
                    "per": item.per,
                    "cAgr": item.cAgr,
                    "cot": item.cot,
                    "rev": item.numeroRev,
                    "modificar": 'S'
                };
                var params = encodeURI(btoa(JSON.stringify(jsonToSend)));
                $window.location.href = '/cotizaciones.html?params=' + params;
            };
        };

    }]);    
var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog) {
        'use strict';
        /* Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Consulta de Cotizaciones";

        /* LLenar la tabla */
        $scope.promise = $consumeService.get('cot-enc-list/?emp=' + $localstorage.get('global.empresa',null)
            + '&ven=' + $localstorage.get('global.usuario',null));
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



    }]);    
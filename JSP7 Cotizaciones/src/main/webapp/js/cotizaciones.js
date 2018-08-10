var app = angular.module('App', ['ngMaterial', 'md.data.table', 'oitozero.ngSweetAlert', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    '$mdEditDialog',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert, $mdEditDialog) {
        'use strict';

        // scope.$watch('isLoading', function (val) {
        //     $scope.isLoading = val;
        // });
        
        $scope.isLoading = true;

        /** Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Grabación de Cotizaciones";

        $scope.limitOptions = [5, 10, 15];

        $scope.options = {
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
            $scope.selected = [];
        };

        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5,
                10, 15];
        };

        $scope.loadStuff = function () {
            $scope.promiseIncoterm = $timeout(function () {

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

        /* Scope de modelos para el json */
        $scope.cot_enc = {
            cEmp: $localstorage.get('global.empresa',null)
        };

        $scope.cot_secciones = [];

        /* Traemos las Secciones de la cotización */
        $scope.selected = [];
       var promise = $consumeService.get('cot-secciones/?emp=' + $localstorage.get('global.empresa', null));
        promise.then(function (result) {
            $scope.secciones = result.data;            
        }).finally(function(){
            $scope.isLoading = false;
        });

        /* Funciones para los select */
        $scope.loadSeccionesDetalle = function(cEmp, codSeccion){
            var promise = $consumeService.get('cot-secciones-det/?emp=' + cEmp
                + '&seccion=' + codSeccion);
            promise.then(function(result){
                $scope.secciones_det = result.data;
            });
        };

        $scope.setDescripcion = function(index, value){
            $scope.secciones[index].descripcion_final = value;
            console.log($scope.secciones);
        };

        $scope.imprimir = function(){
            console.log('Log: ', $scope.secciones);
        };        

        /* Dialogos para editar */
        /*
        $scope.TableEditNombreSec = function (event, record) {
            event.stopPropagation();
            var promise = $mdEditDialog.small({
                modelValue: record.nombreSec,
                placeholder: 'Digite un valor',
                save: function (input) {
                    record.nombreSec = input.$modelValue;
                },
                targetEvent: event,
                validators: {
                    'md-maxlength': 30
                }
            });

            promise.then(function (ctrl) {
                var input = ctrl.getInput();

                input.$viewChangeListeners.push(function () {
                    input.$setValidity('test', input.$modelValue !== 'test');
                });
            });
        };
        */
    }]);
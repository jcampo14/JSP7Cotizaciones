'use strict';
var app = angular.module('App', ['ngMaterial', 'md.data.table', 'oitozero.ngSweetAlert',
    'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    '$mdEditDialog', '$q', '$filter',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert, $mdEditDialog,
        $q, $filter) {

        $scope.isLoading = true;
        

        /** Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Grabación de Cotizaciones";

        /* Configurar DataTable */
        $scope.selectedTable = [];
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
            cEmp: $localstorage.get('global.empresa', null),
            nIde: null,
            criVenta: null,
        };
        $scope.cot_det = [];
        $scope.selectedArticulo = {};

        /* Consumimos los servicios para los datos del inicio del formulario */
        $scope.selected = [];
        var promiseSecciones = $consumeService.get('cot-secciones/?emp=' + $localstorage.get('global.empresa', null));
        var promiseArticulos = $consumeService.get('articulos/?emp=' + $localstorage.get('global.empresa', null));
        var promiseIncoterms = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', null));
        $q.all([promiseSecciones, promiseArticulos, promiseIncoterms]).then(function (values) {
            $scope.secciones = values[0].data;
            $scope.articulos = values[1].data;
            $scope.incoterms = values[2].data;
            $scope.isLoading = false;
        });

        /* Limpiar el filtro del select */
        $scope.clearArticulosFilter = function () {
            $scope.searchArticulo = '';
        };

        /* Buscar el textFileld dentro del select para evitar buscar sin el filtro */
        angular.element(document.querySelector('input.demo-header-searchbox')).on('keydown', function (ev) {
            ev.stopPropagation();
        });

        /* Funciones para los select */
        $scope.loadSeccionesDetalle = function (cEmp, codSeccion) {
            var promise = $consumeService.get('cot-secciones-det/?emp=' + cEmp
                + '&seccion=' + codSeccion);
            promise.then(function (result) {
                $scope.secciones_det = result.data;
            });
        };

        /* Agisnamos la descripcion del textarea al modelo de las secciones */
        $scope.setDescripcion = function (index, value) {
            $scope.secciones[index].descripcion_final = value;
            console.log($scope.secciones);
        };

        /* Funciones */
        $scope.addItemDetalle = function () {
            var o = $filter('filter')($scope.cot_det, { 'cod': $scope.selectedArticulo.cod }, true);
            if (o.length == 0) {
                $scope.cot_det.push($scope.selectedArticulo);
                $scope.selectedArticulo = {};
            }else{
                swal("Mensaje JSP7", "El artículo ya ha sido ingresado en la cotización", "error");
            }
            console.log($scope.secciones);
        };
    }]);
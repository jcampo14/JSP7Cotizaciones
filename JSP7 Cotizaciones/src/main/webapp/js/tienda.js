var app = angular.module('App', ['ngMaterial', 'oitozero.ngSweetAlert', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('tiendaController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert) {

    	$scope.titulo="Soluciones Agroindustriales";
    	$scope.items=[{name : "Inicio"}, 
			   {name : "Nosotros"},
			   {name : "Contacto"}];
    	
    	$scope.ordenados = ['Opcion 1','Opcion 2','Opcion 3','Opcion 4','Opcion 5','Opcion 6'];
    	
    	$scope.currentUser = "John Campo";
    	$scope.filtrados = [{name : "Equipos Rurales"}, 
						   	{name : "Repuestos"},
						   	{name : "Consumibles"},
						   	{name : "Equipos Rurales"}, 
						   	{name : "Repuestos"},
						   	{name : "Consumibles"},
						   	{name : "Equipos Rurales"}, 
						   	{name : "Repuestos"},
						   	{name : "Consumibles"},
						   	{name : "Equipos Rurales"}, 
						   	{name : "Repuestos"},
						   	{name : "Consumibles"},
						   	{name : "Equipos Rurales"}, 
						   	{name : "Repuestos"}];
    	
    	$scope.stars=['1','2','3','4','5'];
    	$scope.products=['1','2','3','4','5'];
        $scope.credentials = {};

        /* Traemos las empresas para setear en el select */
//        $scope.loadCompanies = function () {
//            var promise = $consumeService.get('WSCotizaciones/listCompanies');
//            promise.then(function (result) {
//                // this is only run after getData() resolves        
//                $scope.companies = result.data;
//                $scope.$applyAsync();
//            });
//        };
//
//        $scope.iniciarSesion = function () {
//            if ($scope.credentials.username == null || $scope.credentials.password == null
//                || $scope.credentials.company == null) {
//                swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
//            } else {
//                var promise = $consumeService.get("WSCotizaciones/login?usuario="
//                    + $scope.credentials.username + "&clave=" + $scope.credentials.password
//                    + "&emp=" + $scope.credentials.company);
//                promise.then(function (result) {
//                    if (result.response.success == true) {
//                        $localstorage.set('global.empresa', $scope.credentials.company);
//                        $localstorage.set('global.usuario', $scope.credentials.username);
//                        /* Seteamos el nombre de usuario */
//                        $localstorage.set('global.nombreUsuario', result.response.message);
//
//                        $window.location.href = 'menu.html';
//                    } else {
//                        swal("Mensaje JSP7", result.message, "error");
//                    }
//                });
//            }
//        }

    }]);
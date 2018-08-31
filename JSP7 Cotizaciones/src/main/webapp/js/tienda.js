var app = angular.module('App', ['ngMaterial', 'oitozero.ngSweetAlert', 'App.utils','ngMap']);

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
    	
    	$localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

    	$scope.promise = $consumeService.get('tienda/?emp=' + $localstorage.get('global.empresa', '01') + '&rama=D' + '&imgprincipal=S');
    	
    	$scope.promise.then(function (result) {
            $scope.products = result;
            $scope.$applyAsync();
        });
    		
    	$scope.titulo="Soluciones Agroindustriales";
    	$scope.items=[{name : "Inicio",
    					state:true}, 
					  {name : "Nosotros",
						state:false}, 
					   {name : "Contacto",
						state:false}
					];
    	
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
    	
    	$scope.footerDescription="is simply dummy text of the printing and typesetting industry. " +
    			"Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.";
    	
    	$scope.stars=['1','2','3','4','5'];
    	$scope.topStars=['1','2','3','4','5'];
    	$scope.arrows = ['<','>'];
    	$scope.pages=['1','2','3','4','5'];
//    	$scope.products=['1','2','3','4','5','6','7','8','9'];
    	$scope.topProducts=['1','2','3','4','5'];
        $scope.credentials = {};
        
        $scope.hoverp = function() {
	
        }
        
        var refreshId = setInterval(function() { 
        	if ($('.producto').length!=0) { 
                $('.producto').hover(
        	        	function() {
        	        		 $( this ).find('.product-icons').fadeIn();
        	        		 $( this ).css('box-shadow','0px 0px 20px 5px rgba(0,0,0,0.4)');
        	        		 $( this ).find('.product-footer').css('padding','0');
        	        	}, function() {
        	        		 $( this ).find('.product-icons').fadeOut();
        	        		 $( this ).css('box-shadow','none');
        	        		 $( this ).find('.product-footer').css('padding','0 10px');
        	        	}
        	        );
        	clearInterval(refreshId); 
        	} 
        	}, 3000); 
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

var app = angular.module('App', ['ngMaterial', 'ui.bootstrap','oitozero.ngSweetAlert', 'App.utils','ngMap']);

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
    	$scope.currentPage = 1;
    	$scope.numContPage = 9;
    	$scope.maxSize = 9;
    	$scope.filterProducts = [];
        
    	$scope.init = function (){
    		$scope.promise = $consumeService.get('tienda?emp=' + $localstorage.get('global.empresa', '01') + '&rama=D' + '&imgprincipal=S');
        	
        	$scope.promise.then(function (result) {
                $scope.products = result;
                $scope.paintProducts();
                $scope.$applyAsync();
            });
    	}
    	
    	$scope.init();
    	
    	$scope.paintProducts = function(){
    		
    		$scope.numContForPages = function () {
    		    return Math.ceil($scope.products.count / $scope.numContPage);
    		  };
        	  
    		  $scope.$watch('currentPage + numPerPage', function() {
      		    $scope.begin = (($scope.currentPage - 1) * $scope.numContPage);
      		    $scope.end = $scope.begin + $scope.numContPage;
      		    
      		    $scope.filterProducts = $scope.products.data.slice($scope.begin, $scope.end);
      		    $scope.hoverProducts();
      		    $scope.numContForPages();
      		    
      		    $scope.begin = $scope.begin + 1;
      		    
      		    if($scope.end > $scope.products.count){
      		    	$scope.end = $scope.products.count;
      		    }else{
      		    	$scope.end = $scope.end;
      		    }
      		    
      		  });
    	}
    	
    	$scope.getImgUrl = function(productoo){
    		$scope.img = this.product.url;
    		$scope.precio = this.product.precio;
    		$scope.codigo = this.product.codigo;
    		
    		if($scope.img != null || $scope.img != undefined){
    			return $('#product-image-'+$scope.codigo+'-'+$scope.precio).css('background-image', 'url('+$scope.img+')');
    		}else{
    			return $('#product-image-'+$scope.codigo+'-'+$scope.precio).css('background-image','url(img/logoproductdefault.png)');
    		}
    	}
    	
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
    	$scope.topProducts=['1','2','3','4','5'];
        $scope.credentials = {};
        
        $scope.hoverProducts = function (){
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
        }


    }]);

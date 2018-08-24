var app = angular.module('App', ['ngMaterial', 'oitozero.ngSweetAlert', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('loginStoreController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert) {

        $scope.validateUser = {
        		user: '',
        		password: '',
        		sesion: 'true',
        		empresa: '01'
        };

        /* Traemos las empresas para setear en el select */
        $scope.loadCompanies = function () {
            var promise = $consumeService.get('WSCotizaciones/listCompanies');
            promise.then(function (result) {
                // this is only run after getData() resolves        
                $scope.companies = result.data;
                $scope.$applyAsync();
            });
        };

        $scope.iniciarSesion = function () {
            if ($scope.validateUser.user == "" || $scope.validateUser.password == ""
                || $scope.validateUser.empresa == "") {
                swal("Error", "Los campos estan vacios", "error");
            } else {
                var promise = $consumeService.get("/login?usuario="
                    + $scope.validateUser.user + "&clave=" + $scope.validateUser.password
                    + "&emp=" + $scope.validateUser.empresa);
                promise.then(function (result) {
                    if (result.response.success == true) {
                        $localstorage.set('global.empresa', $scope.credentials.company);
                        $localstorage.set('global.usuario', $scope.credentials.username);
                        /* Seteamos el nombre de usuario */
                        $localstorage.set('global.nombreUsuario', result.response.message);

                        $window.location.href = 'menu.html';
                    } else {
                        swal("Mensaje JSP7", result.message, "error");
                    }
                });
            }
        }

    }]);
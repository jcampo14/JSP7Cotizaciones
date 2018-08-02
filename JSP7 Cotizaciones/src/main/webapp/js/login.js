var app = angular.module('App', ['ngMaterial', 'oitozero.ngSweetAlert', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('loginController', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert) {

        $scope.titulo_formulario = "Centro de Autenticación JSP7";

        $scope.credentials = {};

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
            if ($scope.credentials.username == null || $scope.credentials.password == null
                || $scope.credentials.company == null) {
                swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
            } else {
                var promise = $consumeService.get("WSCotizaciones/login?usuario="
                    + $scope.credentials.username + "&clave=" + $scope.credentials.password
                    + "&emp=" + $scope.credentials.company);
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
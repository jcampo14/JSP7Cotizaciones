var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', function ($localstorage, $consumeService, $scope, $timeout,
    $window, $mdDialog) {
        $scope.init = function(){
            
        }
});
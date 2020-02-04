var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function($mdThemingProvider) {
  'use strict';
  $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('Ctrl', function($localstorage, $consumeService, $scope, $q, $window) {
  $scope.init = function() {
    $scope.executingReport = false;
  };

  $scope.runReport = function() {
    var urlToExecute = "GetClientesDataBase" + "?empresa=" + $localstorage.get('global.empresa', null);
    $window.location.href = urlToExecute;
  };

  $scope.init();

});

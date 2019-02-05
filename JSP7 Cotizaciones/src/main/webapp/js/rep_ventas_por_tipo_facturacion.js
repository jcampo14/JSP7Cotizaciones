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
    if ($scope.mainForm.$valid) {
      $scope.executingReport = true;
      var promise = $consumeService.get('runJReports?reportName=com.aspsols.cotizaciones.reportes.informeDeVentasPorTipoFacturacion');
      promise.then(function(result) {
        var urlToExecute = result.data + "&createPl=T" + "&empresa=" + $localstorage.get('global.empresa', null) +
          "&periodo=" + $scope.param.per + "&paramform=NO";
        $window.location.href = urlToExecute;
      });
    }
  };

  $scope.init();

});

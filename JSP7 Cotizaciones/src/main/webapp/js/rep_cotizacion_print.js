var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('Ctrl', function ($localstorage, $consumeService, $scope, $q, $window) {
    $scope.init = function () {
        var promiseAgencias = $consumeService.get('agencias?emp=' + $localstorage.get('global.empresa', null));
        $q.all([promiseAgencias]).then(function (values) {
            $scope.agencias = values[0].data;
        });
        $scope.param = {
            ocultarTotales: 'N'
        };
    };

    $scope.runReport = function () {
        if ($scope.mainForm.$valid) {
            var promise = $consumeService.get('runJReports?reportName=com.aspsols.cotizaciones.reportes.Cotizaciones_Print');
            promise.then(function (result) {
                var urlToExecute = result.data + "&createPl=T" + "&c_emp=" + $localstorage.get('global.empresa', null)
                    + "&c_agr=" + $scope.param.cAgr + "&cot=" + $scope.param.cot + "&rev=" + $scope.param.rev
                    + "&per=" + $scope.param.per + "&ver_totales=" + $scope.param.ocultarTotales
                    + "&paramform=NO";
                $window.location.href = urlToExecute;
            });
        }
    };

    $scope.init();
});

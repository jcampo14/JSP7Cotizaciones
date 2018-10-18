var app = angular.module('App', ['ngMaterial', 'md.data.table', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl',
    function ($localstorage, $scope, $http, $window) {
        /** Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');
        $scope.titulo_formulario = "Consulta de Cotizaciones";

        $scope.init = function () {
            /* Configurar DataTable */
            $scope.selected = [];
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
                order: '-cot',
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

            $scope.searchParam = "BY-PER";
            $scope.param = {
                perIni: toCharDate(new Date()),
                perFin: toCharDate(new Date())
            };

        };

        function toCharDate(date) {
            return date.getFullYear() + "" + (date.getMonth() + 1);
        };

        $scope.searchByPer = function () {
            $scope.getRecordsByPer($scope.query.page, $scope.query.limit);
        };

        $scope.searchByCliente = function () {
            $scope.getRecordsByCliente($scope.query.page, $scope.query.limit);
        };

        $scope.getRecordsByPer = function () {
            if ($scope.formBuscarPeriodo.$valid) {
                console.log('page: ', $scope.query.page);
                console.log('limit: ', $scope.query.limit);
                var requestConfig = {
                    method: "GET",
                    url: "cot-encByPeriodo?emp=" + $localstorage.get("global.empresa", null) + "&perIni="
                        + $scope.param.perIni + "&perFin=" + $scope.param.perFin + "&page=" + $scope.query.page
                        + "&size=" + $scope.query.limit + "&order=" + $scope.query.order
                };
                $scope.promise = $http(requestConfig);
                $scope.promise.then(function (result) {
                    $scope.queryData = result.data;
                });
            }
        };

        $scope.getRecordsByCliente = function () {
            console.log('page: ', $scope.query.page);
            console.log('limit: ', $scope.query.limit);
            var requestConfig = {
                method: "GET",
                url: "cot-encByCliente?emp=" + $localstorage.get("global.empresa", null) + "&cliente="
                    + $scope.param.cliente + "&page=" + $scope.query.page
                    + "&size=" + $scope.query.limit + "&order=" + $scope.query.order
            };
            $scope.promise = $http(requestConfig);
            $scope.promise.then(function (result) {
                $scope.queryData = result.data;
            });
        };

        $scope.getRecords = function () {
            if ($scope.searchParam == "BY-CLIENTE") {
                $scope.getRecordsByCliente();
            } else if ($scope.searchParam == "BY-PER") {
                $scope.getRecordsByPer();
            }
        };

        $scope.llamarFormulario = function (accion) {
            if ($scope.selected.length == 1) {
                /* Codificar BASE64 */
                var jsonToSend = {
                    "cEmp": $scope.selected[0].cEmp,
                    "per": $scope.selected[0].per,
                    "cAgr": $scope.selected[0].cAgr,
                    "cot": $scope.selected[0].cot,
                    "rev": $scope.selected[0].rev,
                    "modificar": accion
                };
                var params = encodeURI(btoa(JSON.stringify(jsonToSend)));
                $window.location.href = '/cotizaciones.html?params=' + params;
            } else {
                swal("Mensaje JSP7", "Debe seleccionar 1 registro.", "warning");
            }
        };

        $scope.autocompleteTerceros = {
            isDisabled: false,
            noCache: false,
            selectedItem: "",
            searchText: "",
            selectItemChange: function (item) {
                if (item) {
                    $scope.param.cliente = item.nIde;
                }
            },
            querySearch: function (query) {
                return $http.get('terceros?emp=' + $localstorage.get('global.empresa', null) + '&filter=' + escape(query))
                    .then(function (result) {
                        return result.data.data;
                    }, function (error) {
                        console.log(error);
                        swal("Mensaje JSP7", error.data.status + " - " + error.data.error, "error");
                    });
            }
        };

        $scope.init();
    });
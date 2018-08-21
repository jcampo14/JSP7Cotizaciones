'use strict';
var app = angular.module('App', ['ngMaterial', 'md.data.table', 'oitozero.ngSweetAlert',
    'App.utils', 'ui.utils.masks']);

app.config(['$mdThemingProvider', function ($mdThemingProvider) {
    'use strict';
    $mdThemingProvider.theme('default').primaryPalette('blue');
}]);

app.controller('Ctrl', [
    '$localstorage', '$consumeService',
    '$scope', '$timeout', '$window',
    '$http', '$mdDialog', 'SweetAlert',
    '$mdEditDialog', '$q', '$filter',
    function ($localstorage, $consumeService, $scope, $timeout,
        $window, $http, $mdDialog, SweetAlert, $mdEditDialog,
        $q, $filter) {

        /** Simulamos el Login */
        $localstorage.set('global.empresa', '01');
        $localstorage.set('global.usuario', 'ADMIN');

        $scope.titulo_formulario = "Grabación de Cotizaciones";

        /* Configurar DataTable */
        $scope.selectedTable = [];
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
            order: 'nombre',
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
        /* Iniciar la forma */
        $scope.init = function () {
            $scope.isLoading = true;
            delete $scope.cot_enc;
            delete $scope.cot_det;
            delete $scope.selectedDetalle;
            /* Traemos los valores inciales */
            var promiseSecciones = $consumeService.get('cot-secciones/?emp=' + $localstorage.get('global.empresa', null));
            var promiseArticulos = $consumeService.get('articulos/?emp=' + $localstorage.get('global.empresa', null));
            var promiseIncoterms = $consumeService.get('incoterms/?emp=' + $localstorage.get('global.empresa', null));
            var promiseCriterios = $consumeService.get('criterios/?emp=' + $localstorage.get('global.empresa', null));
            var promiseTerceros = $consumeService.get('terceros/?emp=' + $localstorage.get('global.empresa', null));
            var promiseidiomas = $consumeService.get('idiomas/?emp=' + $localstorage.get('global.empresa', null));
            var promiseAgencias = $consumeService.get('agencias/?emp=' + $localstorage.get('global.empresa', null));
            $q.all([promiseSecciones, promiseArticulos, promiseIncoterms, promiseCriterios, promiseTerceros,
                promiseidiomas, promiseAgencias]).then(function (values) {
                    $scope.secciones = values[0].data;
                    $scope.articulos = values[1].data;
                    $scope.incoterms = values[2].data;
                    $scope.criterios = values[3].data;
                    $scope.terceros = values[4].data;
                    $scope.idiomas = values[5].data;
                    $scope.agencias = values[6].data;
                    $scope.isLoading = false;
                });
            /* Scope de modelos para el json */
            $scope.cot_enc = {
                cEmp: $localstorage.get('global.empresa', null),
                cAgr: null,
                nIde: null,
                criVenta: null,
                cSuc: null,
                idioma: null
            };
            $scope.cot_det = [];
            $scope.selectedArticulo = {};
        };
        /* Iniciamos el formulario */
        $scope.init();

        /* Limpiar el filtro del select */
        $scope.clearSelectFilter = function () {
            $scope.searchArticulo = '';
            $scope.searchTercero = '';
            $scope.searchCriterio = '';
            $scope.searchIncoterm = '';
        };

        /* Funciones para los select */
        $scope.loadSeccionesDetalle = function (cEmp, codSeccion) {
            var promise = $consumeService.get('cot-secciones-det/?emp=' + cEmp
                + '&seccion=' + codSeccion);
            promise.then(function (result) {
                $scope.secciones_det = result.data;
            });
        };

        $scope.cargarSucursales = function () {
            var promise = $consumeService.get('suc-cli/?emp=' + $scope.cot_enc.cEmp
                + '&nit=' + $scope.cot_enc.nIde);
            promise.then(function (result) {
                $scope.sucursales = result.data;
            });
        }

        /* Agisnamos la descripcion del textarea al modelo de las secciones */
        $scope.setDescripcion = function (index, value) {
            $scope.secciones[index].descripcion_final = value;
        };

        /* Funciones */
        $scope.addItemDetalle = function () {
            if ($scope.selectedDetalle.cantidad == null || $scope.selectedDetalle.precio_lista == null
                || $scope.selectedArticulo.cod == null || $scope.selectedDetalle.descuento == null
                || $scope.selectedDetalle.precio_venta == null) {
                swal("Mensaje JSP7", "El artículo ya ha sido ingresado en la cotización", "warning");
            } else {
                var o = $filter('filter')($scope.cot_det, { 'cod': $scope.selectedArticulo.cod }, true);
                if (o.length == 0) {
                    var itemToAdd = {
                        id: $scope.selectedArticulo,
                        cEmp: $scope.selectedArticulo.cEmp,
                        cod: $scope.selectedArticulo.cod,
                        nom: $scope.selectedArticulo.nom,
                        cantidad: $scope.selectedDetalle.cantidad,
                        precio_lista: $scope.selectedDetalle.precio_lista,
                        precio_venta: $scope.selectedDetalle.precio_venta,
                        descuento: $scope.selectedDetalle.descuento
                    };
                    $scope.cot_det.push(itemToAdd);
                    delete $scope.selectedDetalle;
                    delete $scope.selectedArticulo;
                } else {
                    swal("Mensaje JSP7", "El artículo ya ha sido ingresado en la cotización", "warning");
                }
            }
        };

        $scope.editItemDetalle = function () {
            var index = 0;
            while (index < $scope.cot_det.length) {
                if ($scope.cot_det[index].cod == $scope.selectedArticulo.cod) {
                    if (index > -1) {
                        $scope.cot_det[index].cantidad = $scope.selectedDetalle.cantidad;
                        $scope.cot_det[index].precio_lista = $scope.selectedDetalle.precio_lista;
                        $scope.cot_det[index].precio_venta = $scope.selectedDetalle.precio_venta;
                        $scope.cot_det[index].descuento = $scope.selectedDetalle.descuento;
                        delete $scope.selectedDetalle;
                        delete $scope.selectedArticulo;
                        $scope.isDisabled = false;
                    }
                }
                index++;
            }
        };

        $scope.deleteItemDetalle = function () {
            var index = 0;
            while (index < $scope.cot_det.length) {
                if ($scope.cot_det[index].cod == $scope.selectedArticulo.cod) {
                    if (index > -1) {
                        $scope.cot_det.splice(index, 1);
                        delete $scope.selectedDetalle;
                        delete $scope.selectedArticulo;
                        $scope.isDisabled = false;
                    }
                }
                index++;
            }
        };

        $scope.buscarPrecioVenta = function (empresa, codigo) {
            $scope.selectedDetalle = {};
            var promise = $consumeService.get('precios/?emp=' + empresa
                + "&cod=" + codigo + "&cri=" + $scope.cot_enc.criVenta);
            promise.then(function (result) {
                if (result.pVen == null) {
                    swal("Mensaje JSP7", "El artículo no tiene precio de lista", "warning");
                } else {
                    $scope.selectedDetalle.precio_lista = result.pVen;
                }

            });
        };

        $scope.showEditarTable = function (item) {
            $scope.selectedDetalle = {};
            $scope.selectedArticulo = item.id;
            $scope.selectedDetalle.cantidad = item.cantidad;
            $scope.selectedDetalle.precio_lista = item.precio_lista;
            $scope.selectedDetalle.precio_venta = item.precio_venta;
            $scope.selectedDetalle.descuento = item.descuento;
            $scope.isDisabled = true;
        };

        $scope.clearEditarTable = function (item) {
            delete $scope.selectedDetalle;
            delete $scope.selectedArticulo;
            $scope.isDisabled = false;
        };

        $scope.grabarCotizacion = function () {
            /* Buscamos las secciones */
            var secciones = [];
            var index = 0;
            while (index < $scope.secciones.length) {
                if ($scope.secciones[index].descripcion_final != null) {
                    var itemSeccion = {
                        "cEmp": $scope.secciones[index].cEmp,
                        "codSeccion": $scope.secciones[index].codSeccion,
                        "descripcionFinal": $scope.secciones[index].descripcion_final
                    };
                    secciones.push(itemSeccion);
                }
                index++;
            };
            /* Buscamos el detalle */
            var detalle = [];
            index = 0;
            while (index < $scope.cot_det.length) {
                var itemDetalle = {
                    "cEmp": $scope.cot_det[index].cEmp,
                    "cod": $scope.cot_det[index].cod,
                    "cantidad": $scope.cot_det[index].cantidad,
                    "precioLista": $scope.cot_det[index].precio_lista,
                    "precioVenta": $scope.cot_det[index].precio_venta,
                    "descuento": $scope.cot_det[index].descuento
                };
                detalle.push(itemDetalle);
                index++;
            };
            /* Armamos el Request */
            var requestBody = {
                "cEmp": $scope.cot_enc.cEmp,
                "cAgr": $scope.cot_enc.cAgr,
                "nIde": $scope.cot_enc.nIde,
                "criVenta": $scope.cot_enc.criVenta,
                "cSuc": $scope.cot_enc.cSuc,
                "idioma": $scope.cot_enc.idioma,
                "secciones": secciones,
                "detalle": detalle
            };
            /* Consumimos el servicio */
            var configRequest = {
                method: "POST",
                url: "cotizacion/",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestBody
            };
            var promise = $consumeService.post(configRequest);
            promise.then(function (result) {
                swal("Mensaje JSP7", result.message, "warning");
                $scope.init();
            });
        };
    }]);
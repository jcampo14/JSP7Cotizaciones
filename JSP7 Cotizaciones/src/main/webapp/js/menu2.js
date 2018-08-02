var app = angular.module('StarterApp', ['ngMaterial', 'ngMdIcons', 'ngCookies']);

app.controller('AppCtrl', function ($scope, $http, $sce, $mdBottomSheet, $mdSidenav, $mdDialog, $cookies) {
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();
    };

    $scope.usuario = 'Firstname Lastname';
    $scope.mail = 'email@domainname.com';
    $scope.tabs = [];
    /*
    $http.get('WSminsaludpdit/getReportUrl')
        .then(function successCallback(response) {
            $scope.urlReportes = response.data.url;
        });
        */

    $scope.trustSrc = function (src) {
        return $sce.trustAsResourceUrl(src);
    }

    $scope.menu = [{ "menuid": 2, "title": "Configuración", "expand": false, "icon": "fa fa-cogs", "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 4, "title": "Tipo Punto Atención", "expand": false, "icon": "fa fa-angle-right", "link": "incoterm.html", "permisos": "ACEB" }, { "menuid": 9, "title": "Infraestructura", "expand": false, "icon": "fa fa-angle-right", "link": "paramInfra.xhtml", "permisos": "ACEB" }, { "menuid": 44, "title": "Unidad", "expand": false, "icon": "fa fa-angle-right", "link": "paramUnidad.xhtml", "permisos": "ACEB" }, { "menuid": 25, "title": "Equipamiento", "expand": false, "icon": "fa fa-angle-right", "link": "paramEquipamiento.xhtml", "permisos": "ACEB" }, { "menuid": 6, "title": "Tipo equipamiento", "expand": false, "icon": "fa fa-angle-right", "link": "paramTipoEquip.xhtml", "permisos": "ACEB" }, { "menuid": 7, "title": "Tipo localización", "expand": false, "icon": "fa fa-angle-right", "link": "paramTipoLoc.xhtml", "permisos": "ACEB" }, { "menuid": 24, "title": "Localización", "expand": false, "icon": "fa fa-angle-right", "link": "paramLoc.xhtml", "permisos": "ACEB" }, { "menuid": 5, "title": "Red Salud", "expand": false, "icon": "fa fa-angle-right", "link": "paramRedSalud.xhtml", "permisos": "ACEB" }, { "menuid": 74, "title": "Tipo Establecimiento", "expand": false, "icon": null, "link": "param_tipo_establecimiento.xhtml", "permisos": "ACEB" }, { "menuid": 46, "title": "Establecimiento Salud", "expand": false, "icon": "fa fa-hospital-o", "link": "paramEstablecimiento.xhtml", "permisos": "ACEB" }, { "menuid": 79, "title": "Paises", "expand": false, "icon": null, "link": "param_pais.xhtml", "permisos": "ACEB" }, { "menuid": 48, "title": "===================", "expand": false, "icon": null, "link": null, "permisos": "ACEB" }, { "menuid": 32, "title": "Estructura Institucional", "expand": false, "icon": "fa fa-angle-right", "link": "ParamTipoEst.xhtml", "permisos": "ACEB" }, { "menuid": 41, "title": "Instituciones", "expand": false, "icon": "fa fa-black-tie", "link": "paramEstruct.xhtml", "permisos": "ACEB" }, { "menuid": 49, "title": "=====================", "expand": false, "icon": null, "link": null, "permisos": "ACEB" }, { "menuid": 26, "title": "Parentesco", "expand": false, "icon": "fa fa-angle-right", "link": "param_parentesco.xhtml", "permisos": "ACEB" }, { "menuid": 27, "title": "Nivel Educativo", "expand": false, "icon": "fa fa-angle-right", "link": "param_nivel_educ.xhtml", "permisos": "ACEB" }, { "menuid": 28, "title": "Estado Civil", "expand": false, "icon": "fa fa-angle-right", "link": "param_estado_civil.xhtml", "permisos": "ACEB" }, { "menuid": 29, "title": "Profesion", "expand": false, "icon": "fa fa-angle-right", "link": "paramProfesion.xhtml", "permisos": "ACEB" }, { "menuid": 39, "title": "Ingreso", "expand": false, "icon": "fa fa-angle-right", "link": "param_ingresos.xhtml", "permisos": "ACEB" }, { "menuid": 50, "title": "=====================", "expand": false, "icon": null, "link": null, "permisos": "ACEB" }, { "menuid": 42, "title": "Tipo Formulario", "expand": false, "icon": "fa fa-angle-right", "link": "param_tipoForm.xhtml", "permisos": "ACEB" }, { "menuid": 43, "title": "Tipo Respuesta", "expand": false, "icon": "fa fa-angle-right", "link": "param_tipoRespuesta.xhtml", "permisos": "ACEB" }, { "menuid": 45, "title": "Formularios", "expand": false, "icon": "fa fa-book", "link": "gestion_forms.xhtml", "permisos": "ACEB" }, { "menuid": 51, "title": "=================", "expand": false, "icon": null, "link": null, "permisos": "ACEB" }, { "menuid": 30, "title": "Arbol Tipo Equipamiento", "expand": false, "icon": "fa fa-angle-right", "link": "paramTipoEquipArbol.xhtml", "permisos": "ACEB" }, { "menuid": 31, "title": "Arbol Tipo localización", "expand": false, "icon": "fa fa-angle-right", "link": "paramTipoLocArbol.xhtml", "permisos": "ACEB" }, { "menuid": 34, "title": "Arbol Tipo Estructura", "expand": false, "icon": "fa fa-angle-right", "link": "paramTipoEstrucArbol.xhtml", "permisos": "ACEB" }, { "menuid": 33, "title": "Menu", "expand": false, "icon": "fa fa-align-justify", "link": "param_menu.xhtml", "permisos": "ACEB" }] }, { "menuid": 11, "title": "Gestion de Usuarios", "expand": false, "icon": "fa fa-users", "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 37, "title": "Gestion de Usuarios", "expand": false, "icon": "fa fa-clipboard", "link": "gestion_usr.xhtml", "permisos": "ACEB" }, { "menuid": 61, "title": "Gestion de roles", "expand": false, "icon": "fal fa-angle-right", "link": "gestion_roles.xhtml", "permisos": "ACEB" }] }, { "menuid": 8, "title": "Gestion Punto atención", "expand": false, "icon": "fa fa-hospital-o", "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 38, "title": "Gestion de Punto de Atención", "expand": false, "icon": null, "link": "tipoPA.xhtml", "permisos": "ACEB" }, { "menuid": 78, "title": "Gestion A.G.I.", "expand": false, "icon": null, "link": "param_agi.xhtml", "permisos": "ACEB" }] }, { "menuid": 10, "title": "Beneficiario", "expand": false, "icon": "fa fa-child", "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 12, "title": "Gestión Beneficiario", "expand": false, "icon": "fa fa-id-card-o", "link": "gestion_beneficiarios.xhtml", "permisos": "ACEB" }, { "menuid": 40, "title": "Seguimiento", "expand": false, "icon": "fa fa-book", "link": "seg_benef.xhtml", "permisos": "ACEB" }, { "menuid": 63, "title": "Asistencia sin control", "expand": false, "icon": null, "link": "asistencia_sin_plan.xhtml", "permisos": "ACEB" }] }, { "menuid": 13, "title": "Tx/Rx Archivos", "expand": false, "icon": "fa fa-paper-plane-o", "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 14, "title": "Envio", "expand": false, "icon": "fa fa-angle-right", "link": "paramExpregional.xhtml", "permisos": "ACEB" }, { "menuid": 15, "title": "Recepción", "expand": false, "icon": "fa fa-angle-right", "link": "ascz", "permisos": "ACEB" }] }, { "menuid": 16, "title": "Consultas", "expand": false, "icon": "fa fa-question-circle", "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 69, "title": "Informes", "expand": false, "icon": "FA FA-ANGLE-RIGHT", "link": "param_reportes2.xhtml", "permisos": "ACEB" }, { "menuid": 76, "title": "Ficha supervición", "expand": false, "icon": "FA FA-ANGLE-RIGHT", "link": "com.aspsols.minsaludpdit.reportes.Ficha_de_Supervision", "permisos": "ACEB" }, { "menuid": 75, "title": "Asistencia brigadista", "expand": false, "icon": "FA FA-ANGLE-RIGHT", "link": "com.aspsols.minsaludpdit.reportes.Asistencia_Brigadistas", "permisos": "ACEB" }, { "menuid": 77, "title": "Centros infantiles", "expand": false, "icon": "FA FA-ANGLE-RIGHT", "link": "com.aspsols.minsaludpdit.reportes.Asistencia_Centros", "permisos": "ACEB" }] }, { "menuid": 80, "title": "Evaluación", "expand": false, "icon": null, "link": null, "permisos": "ACEB", "submenu": [{ "menuid": 81, "title": "Evaluación", "expand": false, "icon": null, "link": "paramEvaluacion.xhtml", "permisos": "ACEB" }] }];
    $scope.$applyAsync();
    $('.loading-panel').fadeOut(1000);

    /** Asignamos el menuId */

    /*
    $http.get('WSminsaludpdit/usuario')
        .then(function successCallback(response) {
            var datos = response.data;
            if (datos.cod == 0) {
                $scope.persona = datos;
                $scope.usuario = datos.persoapnombres + " " + datos.persoappaterno;
                $scope.mail = datos.persoemail;
                var warname = window.location.pathname.split('/')[1];
                //$scope.socket = new WebSocket("ws://localhost:8080/MinSaludPdit/WSNotificacion?usuario=" + datos.usuid);
                $scope.socket = new WebSocket("ws://" + window.location.host + "/" + warname + "/WSNotificacion?usuario=" + datos.usuid);
                $scope.socket.onopen = function () {
                }
                $scope.socket.onmessage = function (res) {
                    var msn = JSON.parse(res.data);
                    if (msn.accion == "salir") {
                        $scope.cerrarSession();
                    }
                }
                $scope.socket.onerror = function () {
                    console.log("error");
                }

                $http.get('WSminsaludpdit/menu?usuario=' + datos.usuid)
                    .then(function successCallback(response) {

                        $scope.menu = response.data;
                        $('.loading-panel').fadeOut(1000);
                    }, function errorCallback(response) {
                        window.location = "login.html";
                    });
            } else {
                alert(datos.msn);
                window.location = "login.html";

            }
        }, function errorCallback(response) {
            window.location = "login.html";
        });
        */
    $scope.selectedIndex = 0;
    $scope.expand = function (index) {
        if ($scope.menu[index].submenu) {
            $scope.menu[index].expand = !$scope.menu[index].expand;

        } else {
            $scope.expandTwo($scope.menu[index]);
        }
    }

    $scope.cerrarSession = function () {
        $http.get('WSminsaludpdit/cerrar')
            .then(function successCallback(response) {
                window.location = "login.html";

            }, function errorCallback(response) {
                window.location = "login.html";
            });


    }
    $scope.expandTwo = function (aux) {
        /*var search = $.grep($scope.tabs, function (b,i) {
          return b.menuid === aux.menuid;
        });*/
        var tab = 0;
        for (var t in $scope.tabs) {
            var aux2 = $scope.tabs[t];
            if (aux2.menuid === aux.menuid) {
                tab = t;
            }
        }
        if (tab === 0) {
            var url = aux.link;
            if (url.length > 0) {
                // if (url.indexOf("com.aspsols.minsaludpdit.reportes.") > -1) {
                //     url = $scope.urlReportes + url;
                // } else {
                //     if (aux.permisos) {
                //         url += "?permisos=" + aux.permisos;
                //     }
                // }
                $scope.tabs.push({
                    title: aux.title,
                    contentUrl: url,
                    close: true,
                    menuid: 1
                });
                $scope.selectedIndex += 1
                $scope.$applyAsync();
            }
        } else {
            $scope.selectedIndex = tab;
        }
    }

    $scope.eliminar = function (index) {
        $scope.tabs.splice(index, 1);
        /* Cerramos la sesion http de la forma */
        $cookies.put("PRUEBA", "PRUEBAVALUE");
        var idSessionForm = $cookies.getAll();
        $http.get('MinSaludPditLogout')
            .then(function successCallback(response) {
            }, function errorCallback(response) {

            });
    }

    // $scope.tabs = [{
    //     title: "inicio",
    //     contentUrl: "inicio.html",
    //     close: false,
    //     id: 0
    // }];

    $scope.alert = '';
    $scope.showListBottomSheet = function ($event) {
        $scope.alert = '';
        $mdBottomSheet.show({
            template: '<md-bottom-sheet class="md-list md-has-header"> <md-subheader>Settings</md-subheader> <md-list> <md-item ng-repeat="item in items"><md-item-content md-ink-ripple flex class="inset"> <a flex aria-label="{{item.name}}" ng-click="listItemClick($index)"> <span class="md-inline-list-icon-label">{{ item.name }}</span> </a></md-item-content> </md-item> </md-list></md-bottom-sheet>',
            controller: 'ListBottomSheetCtrl',
            targetEvent: $event
        }).then(function (clickedItem) {
            $scope.alert = clickedItem.name + ' clicked!';
        });
    };

});


function DialogController($scope, $mdDialog) {
    $scope.hide = function () {
        $mdDialog.hide();
    };
    $scope.cancel = function () {
        $mdDialog.cancel();
    };
    $scope.answer = function (answer) {
        $mdDialog.hide(answer);
    };
};

app.directive('userAvatar', function () {
    return {
        replace: true,
        template: '<svg class="user-avatar" viewBox="0 0 128 128" height="64" width="64" pointer-events="none" display="block" > <path fill="#FF8A80" d="M0 0h128v128H0z"/> <path fill="#FFE0B2" d="M36.3 94.8c6.4 7.3 16.2 12.1 27.3 12.4 10.7-.3 20.3-4.7 26.7-11.6l.2.1c-17-13.3-12.9-23.4-8.5-28.6 1.3-1.2 2.8-2.5 4.4-3.9l13.1-11c1.5-1.2 2.6-3 2.9-5.1.6-4.4-2.5-8.4-6.9-9.1-1.5-.2-3 0-4.3.6-.3-1.3-.4-2.7-1.6-3.5-1.4-.9-2.8-1.7-4.2-2.5-7.1-3.9-14.9-6.6-23-7.9-5.4-.9-11-1.2-16.1.7-3.3 1.2-6.1 3.2-8.7 5.6-1.3 1.2-2.5 2.4-3.7 3.7l-1.8 1.9c-.3.3-.5.6-.8.8-.1.1-.2 0-.4.2.1.2.1.5.1.6-1-.3-2.1-.4-3.2-.2-4.4.6-7.5 4.7-6.9 9.1.3 2.1 1.3 3.8 2.8 5.1l11 9.3c1.8 1.5 3.3 3.8 4.6 5.7 1.5 2.3 2.8 4.9 3.5 7.6 1.7 6.8-.8 13.4-5.4 18.4-.5.6-1.1 1-1.4 1.7-.2.6-.4 1.3-.6 2-.4 1.5-.5 3.1-.3 4.6.4 3.1 1.8 6.1 4.1 8.2 3.3 3 8 4 12.4 4.5 5.2.6 10.5.7 15.7.2 4.5-.4 9.1-1.2 13-3.4 5.6-3.1 9.6-8.9 10.5-15.2M76.4 46c.9 0 1.6.7 1.6 1.6 0 .9-.7 1.6-1.6 1.6-.9 0-1.6-.7-1.6-1.6-.1-.9.7-1.6 1.6-1.6zm-25.7 0c.9 0 1.6.7 1.6 1.6 0 .9-.7 1.6-1.6 1.6-.9 0-1.6-.7-1.6-1.6-.1-.9.7-1.6 1.6-1.6z"/> <path fill="#E0F7FA" d="M105.3 106.1c-.9-1.3-1.3-1.9-1.3-1.9l-.2-.3c-.6-.9-1.2-1.7-1.9-2.4-3.2-3.5-7.3-5.4-11.4-5.7 0 0 .1 0 .1.1l-.2-.1c-6.4 6.9-16 11.3-26.7 11.6-11.2-.3-21.1-5.1-27.5-12.6-.1.2-.2.4-.2.5-3.1.9-6 2.7-8.4 5.4l-.2.2s-.5.6-1.5 1.7c-.9 1.1-2.2 2.6-3.7 4.5-3.1 3.9-7.2 9.5-11.7 16.6-.9 1.4-1.7 2.8-2.6 4.3h109.6c-3.4-7.1-6.5-12.8-8.9-16.9-1.5-2.2-2.6-3.8-3.3-5z"/> <circle fill="#444" cx="76.3" cy="47.5" r="2"/> <circle fill="#444" cx="50.7" cy="47.6" r="2"/> <path fill="#444" d="M48.1 27.4c4.5 5.9 15.5 12.1 42.4 8.4-2.2-6.9-6.8-12.6-12.6-16.4C95.1 20.9 92 10 92 10c-1.4 5.5-11.1 4.4-11.1 4.4H62.1c-1.7-.1-3.4 0-5.2.3-12.8 1.8-22.6 11.1-25.7 22.9 10.6-1.9 15.3-7.6 16.9-10.2z"/> </svg>'
    };
});

app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('blue-grey', {
            'default': '800',
            'hue-1': '50'
        })
        .accentPalette('indigo');

});

function recorrerArbol(json) {
    var type;    
    var resultado;
    for (var i = 0; json.length; i++) {
        type = typeof json[i].submenu;        
        if (type != "undefined") {
            resultado = true;
        } else {
            resultado = recorrerArbol(json[i].submenu);
        }
    }
    return resultado;
};
var demo = angular.module('MenuJSP7', ['ngMaterial', 'ngMdMultiLevelMenu', 'ngMdBadge', 'ngRoute', 'hc.marked', 'App.utils']);

demo.config(['menuProvider', function (menuProvider) {
    menuProvider.items('primary', [
        {
            id: 'item-0',
            label: 'Documentation',
            icon: 'fas fa-award',
            link: 'incoterm.html',
            //            color: 'blue'
        },
        {
            id: 'item-1',
            label: 'Item 1: 3',
            icon: 'fas fa-address-book',
            items: [{
                id: 'item-1.1',
                label: 'Item 1.1',
                link: 'demo/views/item-1-1',
                icon: 'fas fa-award'
            }, {
                label: 'Item 1.2',
                link: 'demo/views/item-1-2',
                icon: 'fas fa-award'
            }]
        }, {
            id: 'item-2',
            label: 'Item 2',
            link: 'demo/views/item-2',
            icon: 'fas fa-award',
            badge: 3
        }, {
            label: 'Item 3',
            icon: 'fas fa-award',
            items: [{
                label: 'Item 3.1',
                link: 'demo/views/item-3-1',
                icon: 'fas fa-award'
            }, {
                label: 'Item 3.2',
                icon: 'fas fa-award',
                items: [{
                    label: 'Item 3.2.1',
                    link: 'demo/views/item-3-2-1',
                    icon: 'fas fa-award'
                }, {
                    label: 'Item 3.2.2',
                    icon: 'fas fa-award',
                    items: [{
                        label: 'Item 3.2.2.1',
                        link: 'demo/views/item-3-2-2-1',
                        icon: 'fas fa-award'
                    }]
                }]
            }]
        }, {
            id: 'item-4',
            label: 'Item 4',
            link: 'demo/views/item-4',
            icon: 'fas fa-award'
        }]);

}]);

demo.config(['markedProvider', function (markedProvider) {
    markedProvider.setOptions({
        gfm: true,
        tables: true,
        highlight: function (code, language) {
            if (!language) {
                language = 'bash';
            } else if (language == 'html') {
                language = 'markup';
            }
            return Prism.highlight(code, Prism.languages[language]);
        }
    });
}]);

demo.controller('MenuJSP7', ['$scope', '$menu', '$mdSidenav', '$mdBottomSheet', '$sce', '$localstorage'
    , function ($scope, $menu, $mdSidenav, $mdBottomSheet, $sce, $localstorage) {

        $scope.breadcrumb = $menu.breadcrumb();
        //$scope.count = $menu.get('item-2').badge;
        $scope.tabs = [];
        $scope.selectedIndex = 0;

        $scope.usuario = {
            username: $localstorage.get('global.usuario', null),
            empresa: $localstorage.get('global.empresa', null),
            nombre: $localstorage.get('global.nombreUsuario', null)
        };

        $scope.toggle = function () {
            $mdSidenav('left').toggle();
            // $scope.icon = $scope.icon == 'menu' ? 'close' : 'menu';
            if ($mdSidenav('left').isOpen()) {
                $scope.icon = 'close';
            } else {
                $scope.icon = 'menu';
            }
        };

        $scope.menu = function () {
            $mdBottomSheet.show({
                templateUrl: 'bottom-sheet-template',
                controller: function ($scope) { }
            });
        };

        $menu.callback(function (item) {
            console.log('You are going to', item.link);
            $scope.toggle();
            $mdBottomSheet.hide();
            /** Llamado del tab */
            var tab = 0;
            for (var t in $scope.tabs) {
                var aux2 = $scope.tabs[t];
                if (aux2.id == item.id) {
                    tab = t;
                }
            }
            if (tab === 0) {
                var url = item.link;
                if (url.length > 0) {
                    $scope.tabs.push({
                        title: item.label,
                        contentUrl: url,
                        close: true,
                        id: item.id
                    });
                    $scope.selectedIndex += 1
                    $scope.$applyAsync();
                }
            } else {
                $scope.selectedIndex = tab;
            }
        });

        $mdSidenav('left', true).then(function (instance) {
            $scope.icon = 'menu';
            instance.onClose(function () {
                $scope.icon = 'menu';
            });
        });

        $scope.$watch('count', function (value) {
            //$menu.get('item-2').badge = $scope.count;
            //$menu.get('item-1').label = 'Item 1: ' + $scope.count;
        });

        $scope.$watch('breadcrumb', function (value) {
            $menu.breadcrumb(value);
        });

        $scope.eliminar = function (index) {
            $scope.tabs.splice(index, 1);
            /* Cerramos la sesion http de la forma */
            // $cookies.put("PRUEBA", "PRUEBAVALUE");
            // var idSessionForm = $cookies.getAll();
            // $http.get('MinSaludPditLogout')
            //     .then(function successCallback(response) {
            //     }, function errorCallback(response) {
            //     });
        };

        $scope.trustSrc = function (src) {
            return $sce.trustAsResourceUrl(src);
        }

    }]);

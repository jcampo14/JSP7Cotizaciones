var app = angular.module('App', ['ngMaterial', 'App.utils', 'ngMessages', 'ngAnimate']);

app.config(['$mdThemingProvider', function($mdThemingProvider) {
  'use strict';
  $mdThemingProvider.theme('default').primaryPalette('green');
}]);

app.controller('loginController',
  function($localstorage, $consumeService, $scope,
    $window, $http) {

    $scope.titulo_formulario = "Centro de Autenticación JSP7";

    $scope.credentials = {};

    $scope.loggingIn = false;

    /* Traemos las empresas para setear en el select */
    $scope.loadCompanies = function() {
      var requestConfig = {
        method: "GET",
        url: "companies",
        headers: {
          'Content-Type': 'application/json'
        }
      };
      return $http(requestConfig).then(function(result) {
        // this is only run after getData() resolves
        $scope.companies = result.data.data;
        $scope.$applyAsync();
      });
    };

    $scope.iniciarSesion = function() {
      if ($scope.credentials.username == null || $scope.credentials.password == null ||
        $scope.credentials.company == null) {
        swal("Mensaje JSP7", "Faltan campos por llenar.", "error");
      } else {
        $scope.loggingIn = true;
        /* Traemos el token */
        var dataToAuth = {
          username: $scope.credentials.username,
          password: $scope.credentials.password
        }
        var requestConfig = {
          method: "POST",
          url: "auth",
          headers: {
            'Content-Type': 'application/json'
          },
          data: dataToAuth
        };
        var promiseToken = $http(requestConfig);
        promiseToken.then(function(result) {
          /* Guardamos el Token */
          $localstorage.set('token.jsp7', result.data.authToken);
          /* Logeo de JSP7 */
          var requestConfig = {
            method: "GET",
            url: "loginService?usuario=" +
              $scope.credentials.username + "&clave=" + $scope.credentials.password +
              "&emp=" + $scope.credentials.company,
            headers: {
              Authorization: window.localStorage.getItem('token.jsp7')
            }
          };
          var promise = $http(requestConfig);
          promise.then(function(result) {
            if (result.data.success == true) {
              $localstorage.set('global.empresa', $scope.credentials.company);
              $localstorage.set('global.usuario', $scope.credentials.username);

              /* Seteamos el nombre de usuario */
              $localstorage.set('global.nombreUsuario', result.data.message);
              /* Navegamos al Menu */
              $scope.loggingIn = false;
              $window.location.href = 'menu.html';
            } else {
              swal("Mensaje JSP7", result.data.message, "error");
            }
          }, function(error) {
            $scope.loggingIn = false;
            console.log(error);
          });
        }, function(error) {
          $scope.loggingIn = false;
          if (error.data.status == 401) {
            swal("¡Error!", "Usuario y/o clave inválidos.", "error");
          }
          console.log(error);
        });
      }
    }
  });

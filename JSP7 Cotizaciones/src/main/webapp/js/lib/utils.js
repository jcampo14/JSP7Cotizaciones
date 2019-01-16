var app = angular.module("App.utils", []);

app.factory('$localstorage', ['$window', function($window) {
    return {
      set: function(key, value) {
        window.localStorage.setItem(key, value);
      },
      get: function(key, defaultValue) {
        return window.localStorage.getItem(key) || defaultValue;
      },
      setObject: function(key, value) {
        window.localStorage.setItem(key, JSON.stringify(value));
      },
      getObject: function(key) {
        return JSON.parse(window.localStorage.getItem(key) || '{}');
      },
      clear: function(){
        window.localStorage.removeItem("token.jsp7");
        window.localStorage.removeItem("global.empresa");
        window.localStorage.removeItem("global.usuario");
        window.localStorage.removeItem("global.nombreUsuario");        
      }
    }
  }]);

  app.factory('$consumeService',['$http', function($http){
    return {
        get: function(url){
            var requestConfig = {
              method: "GET",
              url: url,
              headers: {
                Authorization: window.localStorage.getItem('token.jsp7')
              }
            }
            var promise = $http(requestConfig)
            .then(function (result) {
                var datos = result.data;
                return datos;
            });
            return promise;
        },
        post: function(configJson){
            configJson.headers = {
              'Content-Type': 'application/json',
              'Authorization': window.localStorage.getItem('token.jsp7')
            }
            var promise = $http(configJson).then(function successCallback(response) {
                return response.data;
            }, function errorCallback(response) {
                return response.data;
            });
            return promise;
        }
    }
  }]);

  app.directive('forceSelectFocus', function() {
    return {
      restrict: 'A',
      require: ['^^mdSelect', '^ngModel'],
      link: function(scope, element, controller) {
        scope.$watch(function () {
          let foundElement = element;
          while (!foundElement.hasClass('md-select-menu-container')) {
            foundElement = foundElement.parent();
          }
          return foundElement.hasClass('md-active');
        }, function (newVal) {
          if (newVal) {
            console.log(controller[1]);
              element.focus();
          }
        })
      }
    }
  });

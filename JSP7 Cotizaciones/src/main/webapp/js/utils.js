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
      }
    }
  }]);

  app.factory('$consumeService',['$http', function($http){
    return {
        get: function(url){
            var promise = $http.get(url)
            .then(function (result) {
                var datos = result.data;
                return datos;
            });
            return promise;
        },
        post: function(configJson){
            var promise = $http(configJson).then(function successCallback(response) {                        
                return response.data;
            }, function errorCallback(response) {
                return response.data;                        
            });
            return promise;
        }
    }
  }]);
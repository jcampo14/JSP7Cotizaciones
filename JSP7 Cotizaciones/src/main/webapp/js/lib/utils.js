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

  app.directive('numberInput', function($filter) {
  return {
    require: 'ngModel',
    link: function(scope, elem, attrs, ngModelCtrl) {

      ngModelCtrl.$formatters.push(function(modelValue) {
        return setDisplayNumber(modelValue, true);
      });

      // it's best to change the displayed text using elem.val() rather than
      // ngModelCtrl.$setViewValue because the latter will re-trigger the parser
      // and not necessarily in the correct order with the changed value last.
      // see http://radify.io/blog/understanding-ngmodelcontroller-by-example-part-1/
      // for an explanation of how ngModelCtrl works.
      ngModelCtrl.$parsers.push(function(viewValue) {
        setDisplayNumber(viewValue);
        return setModelNumber(viewValue);
      });

      // occasionally the parser chain doesn't run (when the user repeatedly
      // types the same non-numeric character)
      // for these cases, clean up again half a second later using "keyup"
      // (the parser runs much sooner than keyup, so it's better UX to also do it within parser
      // to give the feeling that the comma is added as they type)
      elem.bind('keyup focus', function() {
        setDisplayNumber(elem.val());
      });

      function setDisplayNumber(val, formatter) {
        var valStr, displayValue;

        if (typeof val === 'undefined') {
          return 0;
        }

        valStr = val.toString();
        displayValue = valStr.replace(/,/g, '').replace(/[A-Za-z]/g, '');
        displayValue = parseFloat(displayValue);
        displayValue = (!isNaN(displayValue)) ? displayValue.toString() : '';

        // handle leading character -/0
        if (valStr.length === 1 && valStr[0] === '-') {
          displayValue = valStr[0];
        } else if (valStr.length === 1 && valStr[0] === '0') {
          displayValue = '';
        } else {
          displayValue = $filter('number')(displayValue);
        }

        // handle decimal
        if (!attrs.integer) {
          if (displayValue.indexOf('.') === -1) {
            if (valStr.slice(-1) === '.') {
              displayValue += '.';
            } else if (valStr.slice(-2) === '.0') {
              displayValue += '.0';
            } else if (valStr.slice(-3) === '.00') {
              displayValue += '.00';
            }
          } // handle last character 0 after decimal and another number
          else {
            if (valStr.slice(-1) === '0') {
              displayValue += '0';
            }
          }
        }

        if (attrs.positive && displayValue[0] === '-') {
          displayValue = displayValue.substring(1);
        }

        if (typeof formatter !== 'undefined') {
          return (displayValue === '') ? 0 : displayValue;
        } else {
          elem.val((displayValue === '0') ? '' : displayValue);
        }
      }

      function setModelNumber(val) {
        var modelNum = val.toString().replace(/,/g, '').replace(/[A-Za-z]/g, '');
        modelNum = parseFloat(modelNum);
        modelNum = (!isNaN(modelNum)) ? modelNum : 0;
        if (modelNum.toString().indexOf('.') !== -1) {
          modelNum = Math.round((modelNum + 0.00001) * 100) / 100;
        }
        if (attrs.positive) {
          modelNum = Math.abs(modelNum);
        }
        return modelNum;
      }
    }
  };
});

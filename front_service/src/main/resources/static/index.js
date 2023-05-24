(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'carts/carts.html',
                controller: 'cartsController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.masterMarketUser) {
            try {
                let jwt = $localStorage.masterMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.masterMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.masterMarketUser) {
                $http.defaults.headers.common
                    .Authorization = 'Bearer ' + $localStorage.masterMarketUser.token;
            }
        }
    }
})();

angular.module('market').controller('indexController', function ($scope, $http, $localStorage) {
        $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/authorization/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.masterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };



    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    }
    $scope.clearUser = function () {
        delete $localStorage.masterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    }

    $scope.isUserLoggedIn = function () {
        if ($localStorage.masterMarketUser) {
            return true;
        } else return false;
    };

});
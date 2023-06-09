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
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .when('/order_pay/:orderId', {
                templateUrl: 'order_pay/order_pay.html',
                controller: 'orderPayController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.marchMarketUser) {
            try {
                let jwt = $localStorage.marchMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.marchMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            // if ($localStorage.marchMarketUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marchMarketUser.token;
            // }
        }
        if (!$localStorage.marketGuestCartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate_uuid')
                .then(function successCallback(response) {
                    $localStorage.marketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('market').controller('indexController', function ($scope, $http, $location, $localStorage) {
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
        $location.path('/');
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
angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/core/auth', $scope.user)
            .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.masterMarketUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                }
            )
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

    $scope.authCheck = function (){
        $http.get('http://localhost:5555/core/auth_check')
            .then(function (response) {
                alert(response.data.value);
            });
    }

    if ($localStorage.masterMarketUser) {
        try {
            let jwt = $localStorage.masterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp){
                console.log('Token is expired')
                delete $localStorage.masterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.masterMarketUser.token;
    }


    $scope.fillTable = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.increaseQuantity = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/inc/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }
    $scope.decreaseQuantity = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/dec/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:5555/core/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }
    $scope.removeFromCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/clear/').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function (productId) {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                alert("Order created ! ")
            });
    }

    $scope.fillTable();
    $scope.loadCart();
});
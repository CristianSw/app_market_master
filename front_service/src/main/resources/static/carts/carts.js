angular.module('market').controller('cartsController', function ($scope, $http, $localStorage) {
      $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.increaseQuantity = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marketGuestCartId + '/inc/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }
    $scope.decreaseQuantity = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marketGuestCartId + '/dec/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.removeFromCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marketGuestCartId + '/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/'+ $localStorage.marketGuestCartId + '/clear/').then(function (response) {
            $scope.loadCart();
        });
    }

    // $scope.createOrder = function (productId) {
    //     $http.post('http://localhost:5555/core/api/v1/orders')
    //         .then(function (response) {
    //             alert("Order created ! ")
    //             $scope.loadCart();
    //         });
    // }
    $scope.disabledCheckOut = function () {
        alert("To create order you need to login or register !");
    }

    $scope.checkOut = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            $scope.loadCart();
            $scope.orderDetails = null
        });
    };

    $scope.loadCart();
});
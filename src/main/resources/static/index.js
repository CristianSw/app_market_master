angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };
    $scope.clearCart = function () {
        $http.delete('http://localhost:8189/market/api/v1/cart')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.increaseQuantity = function (id){
        $http.get('http://localhost:8189/market/api/v1/cart/inc/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }
    $scope.decreaseQuantity = function (productId){
        $http.get('http://localhost:8189/market/api/v1/cart/dec/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.fillTable();
    $scope.loadCart();
});
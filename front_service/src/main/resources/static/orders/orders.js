angular.module('market').controller('ordersController', function ($scope, $http) {
    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/orders/')
            .then(function (response) {
                $scope.orders = response.data;
            });
    };

    $scope.goToPay = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }

    $scope.loadOrders();
});
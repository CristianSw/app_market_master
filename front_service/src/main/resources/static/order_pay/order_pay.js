angular.module('market').controller('orderPayController', function ($scope, $http, $location, $localStorage, $routeParams) {
    $scope.loadOrder = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders/',
            method: "GET"
        }).then(function (response) {
                $scope.orders = response.data;
                $scope.renderPaymentButtons();
            });
    };
    $scope.renderPaymentButtons = function (){
        paypal.Buttons({
            createOrder: function (data,actions){
                return fetch("http://localhost5555/core/api/v1/paypal/create" + $scope.order.id,{
                    method: "POST",
                    headers: {
                        'content-type': 'application/json'
                    }
                }).then(function (response){
                    return response.text();
                });
            },
            onApprove: function (data, actions){
                return fetch("http://localhost5555/core/api/v1/paypal/capture" + data.orderID,{
                    method: "POST",
                    headers: {
                        'content-type': 'application/json'
                    }
                }).then(function (response){
                    response.text().then(msg => alert(msg));
                });
            },
            onCancel: function (data) {
                console.log("Order cancelled: " + data);
            },
            onError: function (err) {
                console.log(err);
            }
        }).render('#paypal-buttons');
    }

    $scope.loadOrder();
});
angular.module('market').controller('registrationController', function ($scope, $http, $location, $localStorage) {
    $scope.funcRegister = function () {
        $http.post('http://localhost:5555/authorization/register', $scope.reguser)
            .then(function (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.masterMarketUser = {username: $scope.reguser.username, token: response.data.token};
                    $localStorage.reguser = null;
                    $location.path("/");

                }

                $scope.orders = response.data;
            });
    };
});
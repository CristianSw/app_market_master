angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {
      $scope.fillTable = function (pageIndex = 1) {
          $http({
              url:'http://localhost:5555/core/api/v1/products',
              method:'GET',
              params:{
                  p: pageIndex
              }
          }).then(function (response) {
              $scope.productsPage = response.data;
              $scope.generatePagesList($scope.productsPage.totalPages);
          });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marketGuestCartId + '/add/' + productId)
            .then(function (response) {
                // $scope.loadCart();
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

    $scope.generatePagesList = function (totalPages){
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }
    $scope.fillTable();
});
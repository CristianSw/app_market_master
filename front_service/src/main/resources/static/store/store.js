angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {
      $scope.loadProducts = function (pageIndex = 1) {
          $http({
              url:'http://localhost:5555/core/api/v1/products',
              method:'GET',
              params:{
                  p: pageIndex,
                  title_part: $scope.filter ? $scope.filter.title_part : null,
                  min_price: $scope.filter ? $scope.filter.min_price : null,
                  max_price: $scope.filter ? $scope.filter.max_price : null
              }
          }).then(function (response) {
              $scope.ProductsPage = response.data;
              $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
          });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
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
                $scope.loadProducts();
            });
    }


    $scope.loadProducts();
});
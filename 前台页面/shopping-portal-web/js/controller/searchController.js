search_app.controller('search_controller', function($scope, search_service, $http) {

	var baseUrl = 'http://localhost:8899/search-ms/';

	$scope.search = function() {

		$http.post(baseUrl + 'search', $scope.searchMap).success(function(response) {
			alert("123")
			$scope.resultMap = response /* response是json数据 { rows:[{},{},{}] }*/

		})

	}

})
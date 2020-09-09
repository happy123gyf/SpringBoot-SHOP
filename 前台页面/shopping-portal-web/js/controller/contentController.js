app.controller("contentController", function($scope, contentService) {
	$scope.contentList = [];
	// 根据分类ID查询广告的方法:
	$scope.findByCategoryId = function(categoryId) {
		contentService.findByCategoryId(categoryId).success(function(response) {
			alert("123")
			$scope.contentList[categoryId] = response;
		});
	}

});
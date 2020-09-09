app.service("contentService", function($http) {
	var URL = "http://localhost:8899/content-ms/";
	this.findByCategoryId = function(categoryId) {
		return $http.get(URL + "/findContentsByCategoryId/" + categoryId);
	}
});
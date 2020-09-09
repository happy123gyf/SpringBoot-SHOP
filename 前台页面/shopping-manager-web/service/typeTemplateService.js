typeTemplate_app.service('typeTemplate_service', function($http) {

	var baseUrl = 'http://localhost:8899/content-ms/';
	var managerUrl = 'http://localhost:8899/manager-ms/';

	this.selectBrandMap = function() {

		return $http.get(managerUrl + 'selectBrandMap')
	}

	this.selectSpecMap = function() {
		
		return $http.get(managerUrl + 'selectSpecMap')
	}

	this.deleteManyTypeTemplate = function(selectIds) {
		return $http.get(baseUrl + 'deleteManyTypeTemplate?ids=' + selectIds)
	}

	this.findByPageTypeTemplate = function(pageNum, pageSize) {
		return $http.get(baseUrl + 'findByPageTypeTemplate?pageNum=' + pageNum + '&pageSize=' + pageSize)
	}

})
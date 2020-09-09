app.service('bankService', function($http) {

	var manager_url = 'http://localhost:8899/manager-ms/';

	this.queryAllBrand = function() {

		return $http.get(manager_url + 'queryAllBrand');

	}

	this.deleteManyBrand = function(selectIds) {
		return $http.get(manager_url + "deleteManyBrand?ids=" + selectIds)
	};

	this.queryBrandById = function(brand_id) {

		return $http.get(manager_url + "queryBrandById/" + brand_id)
	}

	this.findByPage = function(pageNum, pageSize) {
		return $http.get(manager_url + 'findByPage?pageNum=' + pageNum + '&pageSize=' + pageSize)
	}

	this.createBrand = function(entity) {
		return $http.post(manager_url + "createBrand", entity)
	}
	
	this.updateBrand = function(entity) {
		return $http.post(manager_url + "updateBrand", entity)
	}

})
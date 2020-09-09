specification_app.service('specificationService', function($http) {

	var manager_url = 'http://localhost:8899/manager-ms/';

	this.queryAllSpecification = function() {

		return $http.get(manager_url + 'queryAllSpecification');

	}

	this.deleteManySpecification = function(selectIds) {
		return $http.get(manager_url + "deleteManySpecification?ids=" + selectIds)
	};

	this.querySpecificationById = function(spec_id) {

		return $http.get(manager_url + "querySpecificationById/" + spec_id)
	}

	this.findByPageSpecification = function(pageNum, pageSize) {
		return $http.get(manager_url + 'findByPageSpecification?pageNum=' + pageNum + '&pageSize=' + pageSize)
	}

	this.createSpecification = function(entity) {
		return $http.post(manager_url + "createSpecification", entity)
	}
	
	this.updateSpecification = function(entity) {
		return $http.post(manager_url + "updateSpecification", entity)
	}
	
	this.findSpecificationByName = function(specName){
	return	$http.get(manager_url + 'findSpecificationByName?specName=' + specName)		
	}
	
	
	

})
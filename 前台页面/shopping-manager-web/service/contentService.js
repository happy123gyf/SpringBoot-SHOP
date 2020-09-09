content_app.service('contentService', function($http) {

	var baseUrl = 'http://localhost:8899/content-ms/';

	this.queryAllContentCategory = function() {
		return $http.get(baseUrl + 'queryAllContentCategory')

	}

	this.createContent = function(entity) {
		return $http.post(baseUrl + 'createContent', entity)
	}

	this.updateContent = function(entity) {
		return $http.post(baseUrl + 'updateContent', entity)

	}

	this.deleteManyContent = function(ids) {

		return $http.get(baseUrl + "deleteManyContent?ids=" + ids)
	}

	this.updataStartContentStatus = function(ids) {
		return $http.get(baseUrl + 'updataStartContentStatus?ids=' + ids)

	}

	this.updataShieldContentStatus = function(ids) {
		return $http.get(baseUrl + 'updataShieldContentStatus?ids=' + ids)

	}

	this.findByPageContent = function(pageNum, pageSize) {

		return $http.get(baseUrl + 'findByPageContent?pageNum=' + pageNum + '&pageSize=' + pageSize)
	}

	this.queryContentById = function(content_id) {
		return $http.get(baseUrl + 'queryContentById/' + content_id)
	}

	this.uploadFile = function() {
		//表单对象
		var formData = new FormData();
		//对象追加一个file
		formData.append("file", file.files[0])

		return $http({
			method: 'post',
			url: baseUrl + 'uploadFile',
			data: formData,
			headers: {
				'Content-Type': undefined
			},
			transformRequest: angular.identity
		})
	}

})
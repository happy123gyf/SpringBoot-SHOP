app.service("uploadService", function($http) {
	var contentUrl = 'http://localhost:8899/content-ms/';

	this.uploadFile = function() {
		//表单对象
		var formData = new FormData();
		//对象追加一个file
		formData.append("file", file.files[0])

		return $http({
			method: 'post',
			url: contentUrl + 'uploadFile',
			data: formData,
			headers: {
				'Content-Type': undefined
			},
			transformRequest: angular.identity
		})
	}
});
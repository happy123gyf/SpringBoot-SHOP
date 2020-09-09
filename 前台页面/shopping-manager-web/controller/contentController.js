content_app.controller('content_controller', function($scope, contentService) {

	$scope.status = ['无效', '有效'];

	$scope.selectIds = [];

	//初始化广告类型
	$scope.loadContentCategory = function() {
		contentService.queryAllContentCategory().success(function(response) {
			$scope.categoryList = response
		})
	}

	//保存广告
	$scope.save = function() {

		var retuObj = null;

		if($scope.entity.id != null) {
			retuObj = contentService.updateContent($scope.entity);
		} else {
			retuObj = contentService.createContent($scope.entity);
		}

		retuObj.success(function(response) {
			alert(response.message)
			$scope.reloadList();
		})

	}

	//点击checkbox加入按钮
	$scope.updatselection = function(event, content_id) {
		if(event.target.checked == true) {

			$scope.selectIds.push(content_id);
		} else {
			var index = $scope.selectIds.indexOf(content_id);
			$scope.selectIds.splice(index, 1);
		}

	}

	//批量删除广告
	$scope.delete = function() {
		if(confirm("确定删除所选新闻吗?")) {
			contentService.deleteManyContent($scope.selectIds).success(function(response) {
				if(response.code == 200) {
					$scope.reloadList()
				} else {
					alert(response.message);
				}

			})
		}

	}

	//是否开启可用状态
	$scope.startStatus = function() {

		if(confirm("确定开启有效吗")) {
			contentService.updataStartContentStatus($scope.selectIds).success(function(response) {
				if(response.code == 200) {
					$scope.reloadList();
				} else {
					alert(response.message);
				}
			})

		}
	}

	//是否开启屏蔽状态
	$scope.shieldStatus = function() {
		if(confirm("确定开启屏蔽吗")) {
			contentService.updataShieldContentStatus($scope.selectIds).success(function(response) {

				if(response.code == 200) {
					$scope.reloadList();
				} else {
					alert(response.message);
				}
			})

		}
	}

	//根据id查询一个广告
	$scope.findOneContent = function(content_id) {
		contentService.queryContentById(content_id).success(function(response) {
			$scope.entity = response;

		})

	}

	//分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法 ,并且首次加载也会触发
	$scope.paginationConf = {
		currentPage: 1,
		totalItems: 5,
		itemsPerPage: 2,
		perPageOptions: [5, 10, 20, 30, 40, 50],
		onChange: function() {
			$scope.reloadList();
		}
	};

	$scope.reloadList = function() {
		$scope.findByPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
	}
	$scope.findByPage = function(pageNum, pageSize) {
		contentService.findByPageContent(pageNum, pageSize).success(function(response) {
			$scope.contentList = response.rows;
			$scope.paginationConf.totalItems = response.total;

		})

	}

	//文件上传
	$scope.uploadFile = function() {

		contentService.uploadFile().success(function(response) {

			if(response.code == 200) {
				$scope.entity.pic = response.message;
			} else {
				alert(response.message);
			}

		})
	}
})
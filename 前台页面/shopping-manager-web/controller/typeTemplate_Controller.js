typeTemplate_app.controller('typeTemplate_controller', function($scope, typeTemplate_service) {

	//查询品牌map json数据
	$scope.findBrandList = function() {
		typeTemplate_service.selectBrandMap().success(function(response) {
			
			$scope.brandList = {
				data: response
			}
		})

	}

	//查询规格map json数据
	$scope.findSpecList = function() {
		typeTemplate_service.selectSpecMap().success(function(response) {

			$scope.specList = {
				data: response
			}
		})

	}

	//增加扩展属性行
	$scope.addTableRow = function() {

		$scope.entity.customAttributeItems.push({});
	}
	//删除扩展属性行
	$scope.deleTableRow = function(index) {

		$scope.entity.customAttributeItems.splice(index, 1);
	}

	$scope.selectIds = [];
	//判断checkbox勾选状态
	$scope.updateselection = function(event, typeTemId) {
		if(event.target.checked == true) {

			$scope.selectIds.push(typeTemId)
		} else {

			var index = $scope.selectIds.indexOf(typeTemId);
			$scope.selectIds.slice(index, 1)
		}

	}

	//批量删除
	$scope.delete = function() {
		if(confirm("确定批量删除商品类型模板吗?")) {

			typeTemplate_service.deleteManyTypeTemplate($scope.selectIds).success(function(response) {
				if(response.code == 200) {
					$scope.reloadList()
				} else {
					alert(response.message)
				}
			})

		}

	}

	//分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法 ,并且首次加载也会触发
	$scope.paginationConf = {
		currentPage: 1,
		totalItems: 15,
		itemsPerPage: 3,
		perPageOptions: [5, 10, 20, 30, 40, 50],
		onChange: function() {
			$scope.reloadList();
		}
	};

	$scope.reloadList = function() {
		$scope.findByPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
	}
	$scope.findByPage = function(pageNum, pageSize) {
		typeTemplate_service.findByPageTypeTemplate(pageNum, pageSize).success(function(response) {

			$scope.typeTemplateList = response.rows;
			$scope.paginationConf.totalItems = response.total;

		})
	}

})
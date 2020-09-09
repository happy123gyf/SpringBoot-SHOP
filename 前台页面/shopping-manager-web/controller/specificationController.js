specification_app.controller('specification_controller', function($scope, specificationService) {

	//初始化查询所有规格
	$scope.loadSpecification = function() {

		specificationService.queryAllSpecification().success(function(response) {
			$scope.specList = response;
		})

	};

	//新增或更新规格
	$scope.save = function() {

		var resObj = null;
		if($scope.entity.specification.id != null) {
			resObj = specificationService.updateSpecification($scope.entity)
		} else {
			resObj = specificationService.createSpecification($scope.entity)
		}

		resObj.success(function(response) {
			if(response.code == 200) {
				$scope.reloadList();
			} else {
				alert(response.message);
			}

		})

	};

	$scope.selectAll = false; //复选框状态
	//判断checkbox的事件
	$scope.selectIds = [];
	$scope.updatselection = function(event, spec_id) {
		alert(spec_id)
		if(event.target.checked == true) {

			$scope.selectIds.push(spec_id);
		} else {
			var index = $scope.selectIds.indexOf(spec_id);
			$scope.selectIds.splice(index, 1);

		}

	}

	//批量删除数组
	$scope.delete = function() {
		if(confirm("确定要批量删除吗?")) {
			specificationService.deleteManySpecification($scope.selectIds).success(function(response) {
				if(response.code == 200) {
					alert("批量删除成功!")
					$scope.reloadList();
				} else {
					alert(response.message);
				}

			})
		}

	}

	//根据id查找规格
	$scope.findOneSpec = function(spec_id) {

		specificationService.querySpecificationById(spec_id).success(function(response) {
			$scope.entity = response;

		})

	};

	//分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法 ,并且首次加载也会触发
	$scope.paginationConf = {
		currentPage: 1,
		totalItems: 15,
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

		specificationService.findByPageSpecification(pageNum, pageSize).success(function(response) {

			$scope.specList = response.rows;
			$scope.paginationConf.totalItems = response.total;

		})
	}

	//按名称查询
	$scope.specName = "";
	$scope.chaxun = function(specName) {
		specificationService.findSpecificationByName(specName).success(function(response) {

			$scope.specList = response;
		})
	}

	//追加一行
	$scope.addTableRow = function() {

		$scope.entity.specificationOptionList.push({});

	}

	//删除一行
	$scope.dropTableRow = function(index) {

		$scope.entity.specificationOptionList.splice(index, 1);

	}

})
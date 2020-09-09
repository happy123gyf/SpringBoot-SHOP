app.controller('brand_controller', function($scope, bankService) {

	//初始化查询所有品牌
	$scope.loadBrand = function() {

		bankService.queryAllBrand().success(function(response) {
			$scope.brandList = response;
		})

	};

	//新增或更新品牌
	$scope.save = function() {
		var resObj = null;
		if($scope.entity.id != null) {
			resObj = bankService.updateBrand($scope.entity)
		} else {
			resObj = bankService.createBrand($scope.entity)
		}

		resObj.success(function(response) {
			if(response.code == 200) {
				$scope.reloadList();
			} else {
				alert(response.message);
			}

		})

	};


	 $scope.selectAll=false; //复选框状态
	//判断checkbox的事件
	$scope.selectIds = [];
	$scope.updatselection = function(event, brand_id) {
		if(event.target.checked == true) {		
			$scope.selectIds.push(brand_id);
		} else {
			var index = $scope.selectIds.indexOf(brand_id);
			$scope.selectIds.splice(index, 1);
			
		}

	}

	//批量删除数组
	$scope.delete = function() {
		if(confirm("确定要批量删除吗?")) {
			bankService.deleteManyBrand($scope.selectIds).success(function(response) {
				if(response.code == 200) {
					alert("批量删除成功!")
					$scope.reloadList();
				} else {
					alert(response.message);
				}

			})
		}

	}

	//根据id查找品牌
	$scope.findOneBrand = function(brand_id) {

		bankService.queryBrandById(brand_id).success(function(response) {
			$scope.entity = response;

		})

	};

	//分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法 ,并且首次加载也会触发
	$scope.paginationConf = {
		currentPage: 1,
		totalItems: 15,
		itemsPerPage: 8,
		perPageOptions: [5, 10, 20, 30, 40, 50],
		onChange: function() {
			$scope.reloadList();
		}
	};

	$scope.reloadList = function() {
		$scope.findByPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
	}
	$scope.findByPage = function(pageNum, pageSize) {

		bankService.findByPage(pageNum, pageSize).success(function(response) {

			$scope.brandList = response.rows;
			$scope.paginationConf.totalItems = response.total;

		})
	}
	
	
	
   

	

})
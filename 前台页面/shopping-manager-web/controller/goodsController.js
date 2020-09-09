goods_app.controller('goods_controller', function($scope, $http, goods_service) {

	var manager_url = 'http://localhost:8899/manager-ms/';

	$scope.loadAllGoods = function() {
		$http.get(manager_url + 'findAllGoods').success(function(response) {
			$scope.goodsList = response

		})
	}

	// 显示状态
	$scope.status = ["未审核", "审核通过", "审核未通过", "关闭"];

	$scope.itemCatList = [];
	// 显示分类:
	$scope.findItemCatList = function() {

		$http.get(manager_url + 'findAllItemCat').success(function(response) {
			for(var i = 0; i < response.length; i++) {

				$scope.itemCatList[response[i].id] = response[i].name;
			}
		});
	}

	//checkbox勾选
	$scope.selectids = [];
	$scope.updateSelection = function(event, goodsid) {

		if(event.target.checked == true) {
			$scope.selectids.push(goodsid);
		} else {
			var index = $scope.selectids.indexOf(goodsid);
			$scope.selectids.splice(index, 1)
		}

	}

	//根据ids批量删除
	$scope.deleteManyItemCat = function() {
		if(confirm("确定批量删除吗")) {

			$http.get(manager_url + 'deleteManyGoods/' + $scope.selectids).success(function(response) {
				if(response.code == 200) {
					$scope.reloadList();
				} else {
					alert(response.message)
				}

			})

		}

	}

	// 审核的方法:
	$scope.updateStatus = function(status) {
		$http.post(manager_url + '/updateGoodsStatus/' + $scope.selectids + '/' + status).success(function(response) {
			if(response.code == 200) {
				/*	$scope.selectids.splice(0, $scope.selectids.length)*/
				/*清空数组*/
				$scope.selectids = [];
				$scope.reloadList();
			} else {
				alert(response.message);
			}
		})

	}

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
 												
		$http.get(manager_url+'findGoodsByPage?pageNum='+pageNum+'&pageSize='+pageSize).success(function(response) {
			
			$scope.goodsList  = response.rows;
			$scope.paginationConf.totalItems = response.total;

		})
	}

})
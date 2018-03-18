app.controller('LoanBillMngCtrl', ['$rootScope','$scope', '$http', '$state', '$stateParams', 'toaster', 
                              function($rootScope,$scope, $http, $state, $stateParams, toaster) {

	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	CommonMathUtils.init.call(this, $scope,$http,toaster);
	
	$scope.params = $rootScope.conditon||{'page':1,'page.size':10};
	$scope.result = {};
	
	//计算盈利
	$scope.countProfitAmount = function(data) {
		var countFlag = false;
		if(data.businessType == 1) {//进行中=(-1)*返点
			if($scope.isNotNull(data.rebatePoint)) {
				data.profitAmount = -1 * data.rebatePoint;
				countFlag = true;
			}
		}else if(data.businessType == 2) {//续期=收款金额-返点
			if($scope.isNotNull(data.incomeAmount) 
					&& $scope.isNotNull(data.rebatePoint)) {
				data.profitAmount = $scope.floatSub(data.incomeAmount,
						data.rebatePoint);
				countFlag = true;
			}
		}else if(data.businessType == 3
				||data.businessType == 4
				||data.businessType == 6) {//全款/逾期还款/提前全款=收款金额-到手额度-返点
			if($scope.isNotNull(data.incomeAmount) 
					&& $scope.isNotNull(data.rebatePoint) 
					&& $scope.isNotNull(data.incomeLimie)) {
				data.profitAmount = $scope.floatSub($scope.floatSub(
						data.incomeAmount,
						data.incomeLimie), 
						data.rebatePoint);
				countFlag = true;
			}
		}else if(data.businessType == 7) {//逾期=负（到手额度+返点）
			if($scope.isNotNull(data.rebatePoint) 
					&& $scope.isNotNull(data.incomeLimie)) {
				data.profitAmount = -1 * $scope.floatAdd(data.incomeLimie, 
						data.rebatePoint);
				countFlag = true;
			}
		}
		
		if(!countFlag) {
			data.profitAmount = "";
		}
	}
	
	//查询
	$scope.queryResult = function() {
		
		$scope.postRequest({url: "/console/loanBill/loanBillList.do", data: $.param($scope.params)}, function(resp){
			$scope.result = resp;
			if($scope.result.data) {
				$($scope.result.data).each(function(i, data){
					$scope.countProfitAmount(data);
					//预期，催收table展示变换颜色
					if(data.businessType == 7) {
						data.showColorStyle = {"background-color" : "yellow"};
					}else if(data.businessType == 8) {
						data.showColorStyle = {"background-color" : "red"};
					}
				});
			}
			$scope.paginationConf.totalItems = resp.totalElements;
		});
    }
	$scope.queryResult();
	
	//删除
	$scope.goDelete = function(data) {
		$scope.commonConfirm("确定删除吗", function(){
			$scope.postRequest({url: "/console/loanBill/delLoanBill.do?id="+data.id, data: ''}, function(resp){
				$scope.toaster_success();
				$scope.queryResult();
			});
		});
    }
	
	//分页
	$scope.commonPaginationConf();
	
	//跳转编辑页面
	$scope.goEdit = function(data) {
		$state.go('app.loanBill_edit', {id:data.id});
	}
}]);
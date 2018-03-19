app.controller('LoanBillMngCtrl', ['$rootScope','$scope', '$http', '$state', '$stateParams', 'toaster', 
                              function($rootScope,$scope, $http, $state, $stateParams, toaster) {

	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	CommonMathUtils.init.call(this, $scope,$http,toaster);
	CommonDateTimeUtils.init.call(this, $scope,$http,toaster);
	
	$scope.params = $rootScope.conditon||{'page':1,'page.size':10};
	$scope.params.showMoreCondition = false;
	$scope.params.dateType = 0;
	$scope.result = {};
	
	/**
	 * 显示拒绝理由
	 */
	$scope.showRejectReason = function(msg) {
		layer.alert(msg);
	}
	
	/**
	 * 选择日期范围
	 */
	$scope.changeDateRange = function() {
		var range = $scope.params.dateRange;
		var begin = new Date();
		//当前日期
		var current = $scope.DateToStr(begin);
		//范围内的月份
		var rangeStartMonth = new Date(begin.setMonth(begin.getMonth()-range));
		//月的第一天
		var monthFirstDay = $scope.DateToStr(rangeStartMonth).substring(0, 8) + "01";
		$scope.params.startDate = monthFirstDay;
		if(range == 0) {//最近一个月
			$scope.params.endDate = current;
		}else {
			//当前月 - 1天
			var rangeEndMonth = $scope.DateToStr(new Date()).substring(0, 8) + "01";
			$scope.params.endDate = $scope.AddDays(rangeEndMonth, -1);
		}
	}
	
	/**
	 * 格式化日期
	 */
	$scope.formatParamsDate = function(source, name) {
		if($scope.isNotNull(source)) {
			if(toString.apply(source) == '[object Date]') {
				$scope.params[name] = $scope.DateToStr(source);
			}else if(typeof source == 'number'){
				$scope.params[name] = $scope.DateToStr(new Date(source));
			}
		}
	}
	
	/**
	 * 计算盈利
	 */
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
					if(data.delFlag == 1) {
						data.showColorStyle = {"background-color" : "#9B9D9E"};
					}
					debugger;
					//按钮展示控制
					data.deleteShow = false;//删除操作
					data.updateShow = false;//编辑操作
					data.alreadyDeleteShow = false;//已删除
					data.receiveShow = false;//领取操作
					data.addIncomeRecordShow = false;//添加收款记录
					data.queryIncomeRecordShow = false;//查询收款记录
					data.alreadyReceiveShow = false;//已领取
					if($scope.app.settings.loginUser.type == 0) {
						//管理员可以删除、编辑
						data.deleteShow = true;
						data.updateShow = true;
						data.addIncomeRecordShow = true;
						data.queryIncomeRecordShow = true;
					}else if($scope.app.settings.loginUser.type == 2 && data.provideId == $scope.app.settings.loginUser.id) {
						//财务且放款人员是自己，可以删除
						data.deleteShow = true;
						data.updateShow = true;
					}else if($scope.app.settings.loginUser.type == 1 && data.salesmanId == $scope.app.settings.loginUser.id) {
						//业务员可以修改自己的单子
						data.updateShow = true;
					}else if($scope.app.settings.loginUser.type == 4 
							&& !$scope.isNotNull(data.callmanId)
							&& data.businessType == 8) {
						//催收员可以领取未被领取的该催收的单子
						data.receiveShow = true;
					}else if($scope.app.settings.loginUser.type == 4 
							&& data.callmanId == $scope.app.settings.loginUser.id
							&& data.businessType == 8){
						//催收员是自己，可以添加、查看收款记录
						data.addIncomeRecordShow = true;
						data.queryIncomeRecordShow = true;
					} 
					//如果已被删除，只显示“已删除”
					if(data.delFlag == 1) {
						data.deleteShow = false;
						data.updateShow = false;
						data.receiveShow = false;
						data.alreadyReceiveShow = false;
						data.addIncomeRecordShow = false;
						data.queryIncomeRecordShow = false;
						data.alreadyDeleteShow = true;
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
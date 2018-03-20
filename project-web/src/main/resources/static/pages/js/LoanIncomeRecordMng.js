app.controller('LoanIncomeRecordMngCtrl', ['$rootScope','$scope', '$http', '$state', '$stateParams', 'toaster', 
                              function($rootScope,$scope, $http, $state, $stateParams, toaster) {

	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	debugger;
	$scope.params = $rootScope.conditon||{'page':1,'page.size':10};
	$scope.params.loanBillId = $stateParams.loanBillId||'';
	$scope.result = {};
	
	//查询
	$scope.queryResult = function() {
		
		$scope.postRequest({url: "/console/loanIncomeRecord/loanIncomeRecordList.do", data: $.param($scope.params)}, function(resp){
			$scope.result = resp;
			$scope.paginationConf.totalItems = resp.totalElements;
		});
    }
	$scope.queryResult();
	
	//删除
	$scope.goDelete = function(data) {
		$scope.commonConfirm("确定删除吗", function(){
			$scope.postRequest({url: "/console/loanIncomeRecord/delLoanIncomeRecord.do?id="+data.id, data: ''}, function(resp){
				$scope.toaster_success();
				$scope.queryResult();
			});
		});
    }
	
	//分页
	$scope.commonPaginationConf();
	
	//跳转编辑页面
	$scope.goAdd = function(data) {
		debugger;
		$state.go('app.loanIncomeRecord_edit', {loanBillId:$scope.params.loanBillId});
	}
}]);
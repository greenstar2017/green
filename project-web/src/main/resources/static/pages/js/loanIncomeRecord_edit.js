app.controller('LoanIncomeRecordEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster', 
                                    function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	CommonDateTimeUtils.init.call(this, $scope,$http,toaster);
	debugger;
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	$scope.loanBillId = $stateParams.loanBillId||'';
	
	$scope.postRequest({url: "/console/loanIncomeRecord/loanIncomeRecordInfo.do", data: $.param($scope.data)}, function(resp){
		$scope.data = resp.data;
		$scope.data.loanBillId = $scope.loanBillId;
	});
	
	$scope.cancel = function(){
		$state.go('app.loanIncomeRecordMng', {loanBillId:$scope.loanBillId});
	}
	
	/**
	 * 格式化日期
	 */
	$scope.changeDateFormatToStr = function(source, name) {
		if($scope.isNotNull(source)) {
			if(toString.apply(source) == '[object Date]') {
				$scope.data[name] = $scope.DateToStr(source);
			}else if(typeof source == 'number'){
				$scope.data[name] = $scope.DateToStr(new Date(source));
			}
		}
	}
	
	$scope.save = function(){
		$scope.postRequest({url: "/console/loanIncomeRecord/saveLoanIncomeRecord.do", data: $.param($scope.data)}, function(resp){
			if(resp.state == 0) {
				$scope.toaster_success();
				setTimeout(function(){
					$state.go('app.loanIncomeRecordMng', {loanBillId:$scope.loanBillId});
				}, 500);
			}
		}, function(resp){
			$scope.toaster_error(resp.message);
		})
	}
	
}]);

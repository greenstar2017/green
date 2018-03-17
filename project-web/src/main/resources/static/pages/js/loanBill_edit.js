app.controller('LoanBillEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster', 
                                    function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	
	if(undefined != $scope.data.id && $scope.data.id != '') {
		
		$scope.postRequest({url: "/console/loanBill/loanBillInfo.do", data: $.param($scope.data)}, function(resp){
			$scope.data = resp.data;
		});
	}else {
	}
	
	$scope.cancel = function(){
		$state.go('app.loanBillMng');
	}
	
	$scope.save = function(){
		$scope.postRequest({url: "/console/loanBill/saveLoanBill.do", data: $.param($scope.data)}, function(resp){
			if(resp.state == 0) {
				$scope.toaster_success();
				setTimeout(function(){
					$state.go('app.loanBillMng');
				}, 500);
			}
		}, function(resp){
			$scope.toaster_error(resp.message);
		})
	}
	
}]);

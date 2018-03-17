app.controller('SystemAccountEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster', 
                                    function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	
	if(undefined != $scope.data.id && $scope.data.id != '') {
		
		$scope.postRequest({url: "/console/systemAccount/systemAccountInfo.do", data: $.param($scope.data)}, function(resp){
			$scope.data = resp.data;
			$scope.data.type = 0;
			delete $scope.data.createTime;
		});
	}else {
		$scope.data.type = 0;
	}
	
	$scope.cancel = function(){
		$state.go('app.systemAccount');
	}
	
	$scope.save = function(){
		$scope.postRequest({url: "/console/systemAccount/saveSystemAccount.do", data: $.param($scope.data)}, function(resp){
			if(resp.state == 0) {
				$scope.toaster_success();
				setTimeout(function(){
					$state.go('app.systemAccount');
				}, 500);
			}
		}, function(resp){
			$scope.toaster_error(resp.message);
		})
	}
	
}]);

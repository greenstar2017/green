app.controller('TravelFundMngCtrl', ['$rootScope','$scope', '$http', '$state', '$stateParams', 'toaster', 
                                     function($rootScope,$scope, $http, $state, $stateParams, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.params = $rootScope.conditon||{'page':1,'page.size':10};
	$scope.params.parentId = $stateParams.parentId||0;
	$scope.result = {};
	
	$scope.queryResult = function() {
		
		$scope.postRequest({url: "/console/travelFund/travelFundList.do", data: $.param($scope.params)}, function(resp){
			$scope.result = resp;
			$scope.paginationConf.totalItems = resp.totalElements;
		});
    }
	$scope.queryResult();
	
	$scope.goDelete = function(data) {
		
		$scope.postRequest({url: "/console/travelFund/delTravelFund?id="+data.id, data: ''}, function(resp){
			$scope.toaster_success();
			$scope.queryResult();
		});
    }
	
	
	$scope.paginationConf = {
            currentPage: $scope.params['page'],
            totalItems: 0,
            itemsPerPage: $scope.params['page.size'],
            pagesLength: $scope.params['page.size'],
            perPageOptions: [10, 15, 20, 25, 30, 50],
            onChange: function(){
            	if(($scope.paginationConf.currentPage != 0 && $scope.params['page'] != $scope.paginationConf.currentPage) || 
            			$scope.params['page.size'] != $scope.paginationConf.itemsPerPage) {//防止初始化时被调用
            		$scope.params['page'] = $scope.paginationConf.currentPage;
        			$scope.params['page.size'] = $scope.paginationConf.itemsPerPage;
            		$scope.queryResult();
            	}
            }
	};
	
	$scope.goEdit = function(data) {
		$state.go('app.travelFund_edit', {id:data.id, parentId:$scope.params.parentId});
	}
	
	$scope.goHistory = function(data) {
		$state.go('app.travelFund', {parentId:data.id});
	}
}]);
app.controller('WorksMngCtrl', ['$rootScope','$scope', '$http', '$state', '$stateParams', 'toaster', 
                                function($rootScope,$scope, $http, $state, $stateParams, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.params = $rootScope.conditon||{'page':1,'page.size':10};
	$scope.result = {};
	
	$scope.stateList = [{key:1,val:"建成"},{key:2,val:"建造中"},{key:3,val:"设计阶段"},{key:4,val:"提案"}];
	$scope.stateEnList = [{key:1,val:"Completed"},{key:2,val:"Under construction"},{key:3,val:"Design in-progress"},{key:4,val:"Proposal"}];
	
	$scope.queryResult = function() {
		$scope.postRequest({url: "/console/works/worksList.do", data: $.param($scope.params)}, function(resp){
			$scope.result = resp;
			$scope.paginationConf.totalItems = resp.totalElements;
		});
    }
	$scope.queryResult();
	
	$scope.goDelete = function(data) {
		$scope.postRequest({url: "/console/works/delWorks?id="+data.id, data: ''}, function(resp){
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
		$state.go('app.works_edit', {id:data.id});
	}
}]);
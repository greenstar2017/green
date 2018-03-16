app.controller('ICCA_TravelFundEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster',  
                                           function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	$scope.lang = 'ch';
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.chgLang = function(lang) {
		$scope.lang = lang;
		$scope.data.desc = encodeURIComponent($("#desc").html()||'');
		setTimeout(function(){
			$("#descEn").html(decodeURIComponent($scope.data.descEn||''));
		}, 500);
		$scope.bingLinkedMenuEvent();
		$('html,body').animate({scrollTop: '0px'}, 800);
	}
	
	$scope.bingLinkedMenuEvent = function(){
		setTimeout(function(){
			angular.element(".linkedMenu").bind('click', function (event) {
				event.stopPropagation();
			});
		}, 1000);
	}
	$(document).ready(function(){
		$scope.bingLinkedMenuEvent();
	});
	
	
	
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	$scope.data.parentId = $stateParams.parentId||0;
	
	if(undefined != $scope.data.id && $scope.data.id != '') {
		$scope.postRequest({url: "/console/travelFund/travelFundInfo.do", data: $.param($scope.data)}, function(resp){
			$scope.data = resp.data;
			delete $scope.data.createTime;
			$("#desc").html(decodeURIComponent($scope.data.desc||''));
		});
	}
	
	$scope.cancel = function(){
		$state.go('app.travelFund', {parentId:$scope.data.parentId});
	}
	
	$scope.save = function(){
		$scope.data.descEn = encodeURIComponent($("#descEn").html()||'');
		$scope.postRequest({url: "/console/travelFund/saveTravelFund.do", data: $.param($scope.data)}, function(resp){
			if(resp.state == 0) {
				$scope.toaster_success();
				setTimeout(function(){
					$state.go('app.travelFund');
				}, 500);
			}
		}, function(resp){
			$scope.toaster_error();
		})
	}
}]);

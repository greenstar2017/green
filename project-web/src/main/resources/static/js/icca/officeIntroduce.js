app.controller('OfficeIntroduceMngCtrl', ['$rootScope','$scope', '$http', '$state', '$stateParams', 'toaster',
                                          function($rootScope,$scope, $http, $state, $stateParams, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	
	$scope.result = {};
	$scope.tabIndex = 0;
	$scope.lang = 'ch';
	
	$scope.chgLang = function(lang) {
		$scope.data["officeIntroduce"+$scope.tabIndex].desc = encodeURIComponent($("#desc").html()||'');
		$scope.tabIndex = 0;
		$scope.lang = lang;
		setTimeout(function(){
			$("#descEn").html(decodeURIComponent($scope.data["officeIntroduce"+$scope.tabIndex].descEn||''));
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
	
	$scope.initData = function(){
		$scope.tabIndex = 0;
		$scope.lang = 'ch';
		$scope.postRequest({url: "/console/officeIntroduce/officeIntroduceList.do", data: ''}, function(resp){
			$scope.data = resp.data||{};
			delete $scope.data.officeIntroduce0.createTime;
			delete $scope.data.officeIntroduce1.createTime;
			delete $scope.data.officeIntroduce2.createTime;
			delete $scope.data.officeIntroduce3.createTime;
			delete $scope.data.officeIntroduce4.createTime;
			
			$("#desc").html(decodeURIComponent($scope.data["officeIntroduce0"].desc||''));
			$scope.bingLinkedMenuEvent();
			$('html,body').animate({scrollTop: '0px'}, 800);
		});
	}
	
	$scope.initData();
	
	$scope.selectData = function(tabIndex) {
		
		var filed = "desc";
		if($scope.lang != 'ch') {
			filed = "descEn";
		}
		
		$scope.data["officeIntroduce"+$scope.tabIndex][filed] = encodeURIComponent($("#"+filed).html()||'');
		
		$scope.tabIndex = tabIndex;
		
		$("#"+filed).html(decodeURIComponent($scope.data["officeIntroduce"+$scope.tabIndex][filed]||''));
	}
	
	
	$scope.save = function(){
		$scope.data["officeIntroduce"+$scope.tabIndex].descEn = encodeURIComponent($("#descEn").html()||'');
		var saveData = {};
		$scope.setData(saveData, "officeIntroduce0", $scope.data.officeIntroduce0);
		$scope.setData(saveData, "officeIntroduce1", $scope.data.officeIntroduce1);
		$scope.setData(saveData, "officeIntroduce2", $scope.data.officeIntroduce2);
		$scope.setData(saveData, "officeIntroduce3", $scope.data.officeIntroduce3);
		$scope.setData(saveData, "officeIntroduce4", $scope.data.officeIntroduce4);
		$scope.postRequest({url: "/console/officeIntroduce/saveOfficeIntroduce.do", data: $.param(saveData)}, function(resp){
			$scope.data = resp.data||{};
			delete $scope.data.createTime;
			$("#desc").html(decodeURIComponent($scope.data.desc||''));
			$scope.toaster_success();
			
			$scope.initData();
		});
	}
	
	$scope.setData = function(object, field, target){
		target = target||{};
		object[field + ".id"] = target.id||"";
		object[field + ".desc"] = target.desc||"";
        object[field + ".descEn"] = target.descEn||"";
	}
}]);
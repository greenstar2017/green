'use strict';

function CommonRequest(){};

CommonRequest.AngularBaseCtrl = function ( $scope,$http,toaster ) {
	
	$scope.toaster_success = function() {
		toaster.pop('success',
				'提示',
				'操作成功');
	};
	$scope.toaster_warn = function(msg) {
		toaster.pop('warning',
				'消息',
				msg);
	};
	$scope.toaster_error = function(msg) {
		if(undefined == msg || '' == msg) {
			msg = '系统异常';
		}
		toaster.pop('error',
				'消息',
				msg);
	};
  
  $scope.postRequest = function(options, callback, failcallback){
		$http({
			method: 'POST' ,
			url: options.url ,
			data: options.data, // pass in data as strings
			headers: { 'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8; ajax' }
		}).success( function (resp) {
			if(resp.state == 301) {
				$scope.toaster_warn("未登录或者登录超时");
				setTimeout(function(){
					window.location.href = "/login";
				}, 500);
			}else if(resp.state == 0){
				if(undefined != callback) {
					callback(resp);
				}
			}else {
				if(undefined != failcallback) {
					failcallback(resp);
				}else {
					$scope.toaster_error(resp.message);
				}
			}
		}).error(function(data) {
			if(data.state == 301) {
				$scope.toaster_warn("未登录或者登录超时");
				setTimeout(function(){
					window.location.href = "/login";
				}, 500);
			}else {
				$scope.toaster_error(data.message);
			}
		});
	}
};

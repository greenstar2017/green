'use strict';

function CommonRequest(){};

CommonRequest.AngularBaseCtrl = function ( $scope,$http,toaster ) {
	
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
				var message = resp.message||"登录状态已过期，请重新登录";
				$scope.toaster_warn(message);
				setTimeout(function(){
					window.location.href = "/login.html";
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
				var message = data.message||"登录状态已过期，请重新登录";
				$scope.toaster_warn(message);
				setTimeout(function(){
					window.location.href = "/login.html";
				}, 500);
			}else {
				$scope.toaster_error(data.message);
			}
		});
	}
};

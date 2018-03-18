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
	
	/**
	 * 确认弹框
	 */
	$scope.commonConfirm = function(title, delYesFun, noFun) {
		var inx = layer.confirm(title, {
            btn: ['确定','取消'], //按钮
            scrollbar: false,
            shadeClose:true
        }, delYes);
        function delYes(){
            layer.close(inx);
            if(delYesFun) {
            	delYesFun();
            }
        }
	}
	
	/**
	 * 分页参数
	 */
	$scope.commonPaginationConf = function() {
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
	}
  
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
  
  //判断非空
  $scope.isNotNull = function(val) {
	  if(undefined != val && val.length != 0) {
		  return true;
	  }
	  return false;
  }
};

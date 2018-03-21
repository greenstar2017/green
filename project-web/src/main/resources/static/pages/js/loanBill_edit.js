app.controller('LoanBillEditCtrl', ['$scope', '$http', '$stateParams', '$state', '$filter', 'toaster', 
                                    function($scope, $http, $stateParams, $state, $filter, toaster) {
	
	CommonRequest.AngularBaseCtrl.call( this, $scope,$http,toaster);
	CommonMathUtils.init.call(this, $scope,$http,toaster);
	CommonDateTimeUtils.init.call(this, $scope,$http,toaster);
	
	$scope.lastChangeBusinessTypeVal = "";//记录最新修改的业务类型的值
	$scope.initBusinessTypeVal = "";//记录初始的业务类型的值，用作判断【续期】是否提示“自动生成一条下款记录”
	$scope.data = {};
	$scope.data.id = $stateParams.id||'';
	
	//获取基本信息
	$scope.postRequest({url: "/console/loanBill/loanBillInfo.do", data: $.param($scope.data)}, function(resp){
		$scope.data = resp.data;
		$scope.initData();
	});

	/**
	 * 格式化日期
	 */
	$scope.changeDateFormatToStr = function(source, name) {
		if($scope.isNotNull(source)) {
			if(toString.apply(source) == '[object Date]') {
				$scope.data.loanBill[name] = $scope.DateToStr(source);
			}else if(typeof source == 'number'){
				$scope.data.loanBill[name] = $scope.DateToStr(new Date(source));
			}
		}
	}
	
	//初始化数据
	$scope.initData = function() {
		//格式化日期
		$scope.changeDateFormatToStr($scope.data.loanBill.createTime, "createTime");
		$scope.changeDateFormatToStr($scope.data.loanBill.expireTime, "expireTime");
		$scope.changeDateFormatToStr($scope.data.loanBill.paybackTime, "paybackTime");
		$scope.changeDateFormatToStr($scope.data.loanBill.rebatePointDate, "rebatePointDate");
		
		//保留上一次修改业务类型的值
		$scope.lastChangeBusinessTypeVal = $scope.data.loanBill.businessType;
		//记录初始的业务类型的值，用作判断【续期】是否提示“自动生成一条下款记录”
		$scope.initBusinessTypeVal = $scope.data.loanBill.businessType;
		//拒绝状态，可以填写拒绝理由
		if($scope.data.loanBill.loanStatus == 2) {
			$("#rejectReasonId").removeAttr("readonly");
		}
		//催收类型，只允许催收人员添加收款记录
		if($scope.data.loanBill.businessType == 8) {
			$("#incomeAmountId").attr("disabled", true);
		}
		if($scope.app.settings.loginUser.type == 1) {//业务员不可以修改到期日期
			$("#expireTimeCalendarId").hide();
			$("#rebatePointWayId").attr("disabled", true);
		}else {
			$("#expireTimeCalendarId").show();
			$("#rebatePointWayId").removeAttr("disabled");
		}
		
		//催收人员不可以修改数据，可以添加收款记录
		if($scope.app.settings.loginUser.type == 4) {
			$("input").attr("disabled", true);
			$("select").attr("disabled", true);
			$("#saveId").hide();
		}
	}
	
	//计算盈利
	$scope.countProfitAmount = function() {
		var countFlag = false;
		var data = $scope.data.loanBill;
		if(data.businessType == 1) {//进行中=(-1)*返点
			if($scope.isNotNull(data.rebatePoint) && $scope.isNotNull(data.rebatePointDate)) {
				data.profitAmount = -1 * data.rebatePoint;
				countFlag = true;
			}
		}else if(data.businessType == 2) {//续期=收款金额-返点
			if($scope.isNotNull(data.incomeAmount) 
					&& $scope.isNotNull(data.rebatePoint)) {
				data.profitAmount = $scope.floatSub(data.incomeAmount,
						data.rebatePoint);
				countFlag = true;
			}
		}else if(data.businessType == 3
				||data.businessType == 4
				||data.businessType == 6
				||data.businessType == 8) {//全款/逾期还款/提前全款=收款金额-到手额度-返点
			if($scope.isNotNull(data.incomeAmount) 
					&& $scope.isNotNull(data.rebatePoint) 
					&& $scope.isNotNull(data.incomeLimie)) {
				data.profitAmount = $scope.floatSub($scope.floatSub(
						data.incomeAmount,
						data.incomeLimie), 
						data.rebatePoint);
				countFlag = true;
			}
		}else if(data.businessType == 7) {//逾期=负（到手额度+返点）
			if($scope.isNotNull(data.rebatePoint) 
					&& $scope.isNotNull(data.incomeLimie)) {
				data.profitAmount = -1 * $scope.floatAdd(data.incomeLimie, 
						data.rebatePoint);
				countFlag = true;
			}
		}
		
		if(!countFlag) {
			data.profitAmount = "";
		}
	}
	
	//修改业务类型
	$scope.changeBusinessType = function() {
		//审核中不可以修改业务类型
		if($scope.data.loanBill.loanStatus == 0) {
			layer.msg("下款状态为【审核中】不可以修改业务类型");
			$scope.data.loanBill.businessType = $scope.lastChangeBusinessTypeVal;
			return false;
		}
		//保留上一次修改业务类型的值
		$scope.lastChangeBusinessTypeVal = $scope.data.loanBill.businessType;
		//计算盈利
		$scope.countProfitAmount();
		//催收状态不可以修改收款金额
		if($scope.data.loanBill.businessType == 8) {
			$("#incomeAmountId").attr("disabled", true);
		}else {
			$("#incomeAmountId").removeAttr("disabled");
		}
		//续期状态提示“自动生成一条下款记录”
		if($scope.data.loanBill.businessType == 2 && $scope.initBusinessTypeVal != 2) {
			layer.alert("【续期】类型：保存后系统将自动创建新的下款单");
		}
	}
	
	//监听借贷人下拉选择
	$scope.changeLender = function() {
		$($scope.data.lenderList).each(function(i, data){
			if(data.id == $scope.data.loanBill.lenderId) {
				$scope.data.loanBill.lenderName = data.lenderName||"";
				$scope.data.loanBill.lenderIdentity = data.lenderIdentity||"";
			}
		});
	}
	//选择催收人员
	$scope.changeCallman = function() {
		$($scope.data.csUserList).each(function(i, data){
			if(data.id == $scope.data.loanBill.callmanId) {
				$scope.data.loanBill.callmanName = data.name||"";
			}
		});
	}
	//选择放款人员
	$scope.changeProvideman = function() {
		$($scope.data.cwUserList).each(function(i, data){
			if(data.id == $scope.data.loanBill.provideId) {
				$scope.data.loanBill.provideName = data.name||"";
			}
		});
	}
	//选择收款人员
	$scope.changeIncomeName = function() {
		$($scope.data.cwUserList).each(function(i, data){
			if(data.id == $scope.data.loanBill.incomeId) {
				$scope.data.loanBill.incomeName = data.name||"";
			}
		});
	}
	//修改利息的值
	$scope.changeInterest = function() {
		if($scope.isNotNull($scope.data.loanBill.loanLimit) && $scope.isNotNull($scope.data.loanBill.incomeLimie)) {
			$scope.data.loanBill.interest = $scope.floatSub($scope.data.loanBill.loanLimit, $scope.data.loanBill.incomeLimie);
		}else {
			$scope.data.loanBill.interest = "";
		}
		//计算盈利
		$scope.countProfitAmount();
	}
	//修改到期日
	$scope.changeExpireTime = function() {
		if($scope.isNotNull($scope.data.loanBill.period) && $scope.isNotNull($scope.data.loanBill.createTime)) {
			$scope.data.loanBill.expireTime = $scope.AddDays($scope.data.loanBill.createTime, $scope.data.loanBill.period);
		}else {
			$scope.data.loanBill.expireTime = "";
		}
	}
	//修改下款状态
	$scope.changeLoanStatus = function() {
		if($scope.data.loanBill.loanStatus == 0) {//审核中 业务类型为待进行
			$scope.data.loanBill.businessType = 0;
		}else if($scope.data.loanBill.loanStatus == 2) {//拒绝状态
			$("#rejectReasonId").removeAttr("readonly");
		}else {
			$("#rejectReasonId").attr("readonly", true);
		}
	}
	
	$scope.cancel = function(){
		$state.go('app.loanBillMng');
	}
	
	$scope.save = function(){
		//判断返点和返点日同时存在
		if($scope.isNotNull($scope.data.loanBill.rebatePoint) 
				&& $scope.data.loanBill.rebatePoint != 0 
				&& !$scope.isNotNull($scope.data.loanBill.rebatePointDate)) {
			layer.msg("输入返点金额后，请选择返点日！");
			return false;
		}
		if((!$scope.isNotNull($scope.data.loanBill.rebatePoint) || $scope.data.loanBill.rebatePoint == 0)
				&& $scope.isNotNull($scope.data.loanBill.rebatePointDate)) {
			layer.msg("选择返点日后，请输入返点金额！");
			return false;
		}
		//除了催收类型，填了还款金额必须选择还款日
		if($scope.data.loanBill.businessType != 8 
				&& (!$scope.isNotNull($scope.data.loanBill.incomeAmount) 
				|| $scope.data.loanBill.incomeAmount != 0)
				&& !$scope.isNotNull($scope.data.loanBill.paybackTime)) {
			layer.msg("输入收款金额后，请选择还款日！");
			return false;
		}
		$scope.postRequest({url: "/console/loanBill/saveLoanBill.do", data: $.param($scope.data.loanBill)}, function(resp){
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

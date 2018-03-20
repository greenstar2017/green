package com.green.service;

import com.baomidou.mybatisplus.service.IService;
import com.green.entity.LoanIncomeRecord;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-20
 */
public interface LoanIncomeRecordService extends IService<LoanIncomeRecord> {

	/**
	 * 获取收款金额
	 * 
	 * @param loanBillId
	 * @return
	 */
	Double getIncomeRecord(Integer loanBillId);
}

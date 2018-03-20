package com.green.service;

import com.green.entity.LoanBill;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 入款信息 服务类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-17
 */
public interface LoanBillService extends IService<LoanBill> {

	/**
	 * 回写收款记录到下款单
	 * 
	 * @param loanBillId
	 * @return
	 */
	boolean updateLoanIncomeByRecord(int loanBillId);
}

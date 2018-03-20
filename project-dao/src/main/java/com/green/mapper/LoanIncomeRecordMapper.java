package com.green.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.green.entity.LoanIncomeRecord;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-20
 */
public interface LoanIncomeRecordMapper extends BaseMapper<LoanIncomeRecord> {

	/**
	 * 获取收款金额
	 * 
	 * @param loanBillId
	 * @return
	 */
	Double getIncomeRecord(Integer loanBillId);

}
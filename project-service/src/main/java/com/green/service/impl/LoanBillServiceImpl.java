package com.green.service.impl;

import java.math.BigDecimal;

import com.green.entity.LoanBill;
import com.green.mapper.LoanBillMapper;
import com.green.service.LoanBillService;
import com.green.service.LoanIncomeRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入款信息 服务实现类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-17
 */
@Service
public class LoanBillServiceImpl extends ServiceImpl<LoanBillMapper, LoanBill> implements LoanBillService {

	@Autowired
	private LoanIncomeRecordService loanIncomeRecordService;
	
	@Override
	public boolean updateLoanIncomeByRecord(int loanBillId) {
		double amount = loanIncomeRecordService.getIncomeRecord(loanBillId);
		LoanBill loanBill = new LoanBill();
		loanBill.setId(loanBillId);
		loanBill.setIncomeAmount(BigDecimal.valueOf(amount));
		return this.updateById(loanBill);
	}
	
}

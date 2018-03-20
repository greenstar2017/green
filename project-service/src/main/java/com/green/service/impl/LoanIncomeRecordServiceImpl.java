package com.green.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.green.entity.LoanIncomeRecord;
import com.green.mapper.LoanIncomeRecordMapper;
import com.green.service.LoanIncomeRecordService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-20
 */
@Service
public class LoanIncomeRecordServiceImpl extends ServiceImpl<LoanIncomeRecordMapper, LoanIncomeRecord> implements LoanIncomeRecordService {

	@Autowired
	private LoanIncomeRecordMapper mapper;
	
	@Override
	public Double getIncomeRecord(Integer loanBillId) {
		Double amount = mapper.getIncomeRecord(loanBillId);
		if(null == amount) {
			return Double.valueOf(0);
		}else {
			return amount;
		}
	}
	
}

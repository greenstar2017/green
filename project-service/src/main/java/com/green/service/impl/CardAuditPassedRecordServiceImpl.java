package com.green.service.impl;

import com.green.entity.CardAuditPassedRecord;
import com.green.mapper.CardAuditPassedRecordMapper;
import com.green.service.CardAuditPassedRecordService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 卡券审核通过记录表 服务实现类
 * </p>
 *
 * @author yuanhualiang
 * @since 2017-12-03
 */
@Service
public class CardAuditPassedRecordServiceImpl extends ServiceImpl<CardAuditPassedRecordMapper, CardAuditPassedRecord> implements CardAuditPassedRecordService {

	@Autowired
	private CardAuditPassedRecordMapper mapper;
	
	@Override
	public Page<CardAuditPassedRecord> selectPageTest() {
		Page<CardAuditPassedRecord> page = new Page<>(1, 2);
		page.setRecords(mapper.selectListTest(page));
		return page;
	}
	
}

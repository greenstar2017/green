package com.green.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.green.entity.CardAuditPassedRecord;

/**
 * <p>
 * 卡券审核通过记录表 服务类
 * </p>
 *
 * @author yuanhualiang
 * @since 2017-12-03
 */
public interface CardAuditPassedRecordService extends IService<CardAuditPassedRecord> {
	
	Page<CardAuditPassedRecord> selectPageTest();
}

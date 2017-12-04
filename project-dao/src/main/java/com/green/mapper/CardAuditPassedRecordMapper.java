package com.green.mapper;

import java.util.List;

import com.green.entity.CardAuditPassedRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 卡券审核通过记录表 Mapper 接口
 * </p>
 *
 * @author yuanhualiang
 * @since 2017-12-03
 */
public interface CardAuditPassedRecordMapper extends BaseMapper<CardAuditPassedRecord> {

	List<CardAuditPassedRecord> selectListTest(Pagination page);
}
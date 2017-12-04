package com.green;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.green.entity.CardAuditPassedRecord;
import com.green.service.CardAuditPassedRecordService;

/**
 * @author yuanhualiang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
public class UnitTest {
	
	@Autowired
	private CardAuditPassedRecordService cardAuditPassedRecordService;
	
	@Test
	public void testList() {
		Wrapper<CardAuditPassedRecord> wrapper = new EntityWrapper<>();
		List<CardAuditPassedRecord> dataList = cardAuditPassedRecordService.selectList(wrapper);
		System.out.println(dataList.size());
	}
	
	@Test
	public void testPage() {
		Wrapper<CardAuditPassedRecord> wrapper = new EntityWrapper<>();
		Page<CardAuditPassedRecord> page = new Page<>(1, 5);
		cardAuditPassedRecordService.selectPage(page, wrapper);
		System.out.println(page.getTotal() + ":" + page.getPages());
	}
	
	@Test
	public void testMapperPage() {
		Page<CardAuditPassedRecord> page = cardAuditPassedRecordService.selectPageTest();
		System.out.println(page.getSize() + ":" + page.getPages());
	}
}

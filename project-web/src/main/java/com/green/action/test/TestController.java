/**
 * 
 */
package com.green.action.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.green.entity.CardAuditPassedRecord;
import com.green.response.RestObject;
import com.green.service.CardAuditPassedRecordService;

/**
 * @author yuanhualiang
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private CardAuditPassedRecordService cardAuditPassedRecordService;

	@ResponseBody
	@RequestMapping(value="/a", method = RequestMethod.GET)
	public RestObject add() {
		Page<CardAuditPassedRecord> page = cardAuditPassedRecordService.selectPageTest();
		return RestObject.newOk("OK", page.getRecords(), page.getTotal());
	}
}

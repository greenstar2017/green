/**
 *
 * yuanhualiang
 */
package com.green.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.green.common.BeanUtils;
import com.green.common.DateTimeUtils;
import com.green.constants.DelFlagEnum;
import com.green.dto.LoanIncomeRecordForm;
import com.green.entity.LoanIncomeRecord;
import com.green.entity.UserAccount;
import com.green.response.RestObject;
import com.green.service.LoanBillService;
import com.green.service.LoanIncomeRecordService;

/**
 * @author yuanhualiang
 *
 * 收款记录控制类
 */
@Controller
@RequestMapping("/console/loanIncomeRecord")
public class LoanIncomeRecordController extends BaseController {

	@Autowired
	private LoanIncomeRecordService loanIncomeRecordService;
	
	@Autowired
	private LoanBillService loanBillService;

	/**
	 * 查找系统用户信息
	 * 
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loanIncomeRecordList")
	public RestObject loanIncomeRecordList(
			HttpServletRequest request,
			HttpServletResponse response, Integer loanBillId,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize) {

		Wrapper<LoanIncomeRecord> wrapper = new EntityWrapper<>();
		wrapper.eq("del_flag", DelFlagEnum.UN_DEL.getKey());
		wrapper.eq("loan_bill_id", loanBillId);
		wrapper.orderBy("id", false);
		Page<LoanIncomeRecord> page = new Page<LoanIncomeRecord>(pageNo, pageSize);
		page = loanIncomeRecordService.selectPage(page, wrapper);

		return RestObject.newOk("查询成功", page.getRecords(), page.getTotal());
	}

	/**
	 * 添加/修改系统用户信息
	 * 
	 * @param request
	 * @param response
	 * @param loanIncomeRecordForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveLoanIncomeRecord")
	public RestObject saveLoanIncomeRecord(HttpServletRequest request, UserAccount userAccount, 
			HttpServletResponse response, @Valid LoanIncomeRecordForm loanIncomeRecordForm) {
		LoanIncomeRecord loanIncomeRecord = new LoanIncomeRecord();
		boolean flag = false;
		if (null != loanIncomeRecordForm.getId()) {
			try {
				loanIncomeRecord = loanIncomeRecordService.selectById(loanIncomeRecordForm.getId());
				BeanUtils.copyPropertiesExclude(loanIncomeRecordForm, loanIncomeRecord,
						new String[] { "incomeTime"}, false);
				if (StringUtils.isNotBlank(loanIncomeRecordForm.getIncomeTime())) {
					loanIncomeRecord.setIncomeTime(DateTimeUtils.parseDateTime(
							loanIncomeRecordForm.getIncomeTime(), "yyyy-MM-dd"));
				} else {
					loanIncomeRecord.setIncomeTime(null);
				}
				flag = loanIncomeRecordService.updateById(loanIncomeRecord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				BeanUtils.copyPropertiesExclude(loanIncomeRecordForm, loanIncomeRecord,
						new String[] { "incomeTime"}, false);
				if (StringUtils.isNotBlank(loanIncomeRecordForm.getIncomeTime())) {
					loanIncomeRecord.setIncomeTime(DateTimeUtils.parseDateTime(
							loanIncomeRecordForm.getIncomeTime(), "yyyy-MM-dd"));
				} else {
					loanIncomeRecord.setIncomeTime(null);
				}
				loanIncomeRecord.setDelFlag(DelFlagEnum.UN_DEL.getKey());
				loanIncomeRecord.setCreatorName(userAccount.getName());
				loanIncomeRecord.setCreatorId(userAccount.getId());
				flag = loanIncomeRecordService.insert(loanIncomeRecord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (flag) {
			//回写下款单收款金额
			loanBillService.updateLoanIncomeByRecord(loanIncomeRecordForm.getLoanBillId());
			
			return RestObject.newOk("保存成功");
		} else {
			return RestObject.newError("保存失败");
		}
	}

	/**
	 * 删除系统用户信息
	 * 
	 * @param request
	 * @param response
	 * @param loanIncomeRecordForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delLoanIncomeRecord")
	public RestObject delLoanIncomeRecord(HttpServletRequest request,
			HttpServletResponse response, @Null(message = "ID不能为空") int id) {
		boolean flag = false;
		LoanIncomeRecord loanIncomeRecord = loanIncomeRecordService.selectById(id);
		loanIncomeRecord.setDelFlag(DelFlagEnum.DONE_DEL.getKey());
		flag = loanIncomeRecordService.updateById(loanIncomeRecord);

		if (flag) {
			//回写下款单收款金额
			loanBillService.updateLoanIncomeByRecord(loanIncomeRecord.getLoanBillId());
			return RestObject.newOk("删除成功");
		} else {
			return RestObject.newError("删除失败");
		}
	}

	/**
	 * 基本信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loanIncomeRecordInfo")
	public RestObject loanIncomeRecordInfo(HttpServletRequest request,
			HttpServletResponse response, Integer id, UserAccount userAccount) {
		LoanIncomeRecord loanIncomeRecord = new LoanIncomeRecord();
		if(null != id) {
			loanIncomeRecord = loanIncomeRecordService.selectById(id);
		}else {
			loanIncomeRecord.setCreatorId(userAccount.getId());
			loanIncomeRecord.setCreatorName(userAccount.getName());
		}
		return RestObject.newOk("基本信息", loanIncomeRecord);
	}
}

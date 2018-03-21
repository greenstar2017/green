/**
 *
 * yuanhualiang
 */
package com.green.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.green.constants.AccountTypeEnum;
import com.green.constants.LenderStatusEnum;
import com.green.constants.LoanBusinessTypeEnum;
import com.green.constants.LoanDelFlagEnum;
import com.green.constants.LoanStatusEnum;
import com.green.dto.LoanBillForm;
import com.green.entity.Lender;
import com.green.entity.LoanBill;
import com.green.entity.UserAccount;
import com.green.response.RestObject;
import com.green.service.LenderService;
import com.green.service.LoanBillService;
import com.green.service.UserAccountService;

/**
 * @author yuanhualiang
 *
 *         借贷人控制类
 */
@Controller
@RequestMapping("/console/loanBill")
public class LoanBillController extends BaseController {

	@Autowired
	private LoanBillService loanBillService;

	@Autowired
	private LenderService lenderService;

	@Autowired
	private UserAccountService userAccountService;

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
	@RequestMapping("/loanBillList")
	public RestObject loanBillList(
			HttpServletRequest request,
			HttpServletResponse response,
			LoanBillForm loanBillForm,
			UserAccount userAccount,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize) {

		Wrapper<LoanBill> wrapper = new EntityWrapper<>();
		wrapper.and("1=1 ");
		// 查询时间范围
		if (null != loanBillForm.getDateType()) {
			String field = "create_time";// 录入日
			if (1 == loanBillForm.getDateType()) {// 到款日
				field = "expire_time";
			} else if (2 == loanBillForm.getDateType()) {// 到款日
				field = "payback_time";
			} else if (3 == loanBillForm.getDateType()) {// 返点日
				field = "rebate_point_date";
			}
			if (StringUtils.isNotEmpty(loanBillForm.getStartDate())
					&& StringUtils.isNotEmpty(loanBillForm.getEndDate())) {
				wrapper.and(" DATE_FORMAT(" + field
						+ ", '%Y-%m-%d') >= {0} and DATE_FORMAT(" + field
						+ ", '%Y-%m-%d')<= {1}", loanBillForm.getStartDate(),
						loanBillForm.getEndDate());
			} else if (StringUtils.isNotEmpty(loanBillForm.getStartDate())) {
				wrapper.and(" DATE_FORMAT(" + field + ", '%Y-%m-%d') >= {0} ",
						loanBillForm.getStartDate());
			} else if (StringUtils.isNotEmpty(loanBillForm.getEndDate())) {
				wrapper.and(" DATE_FORMAT(" + field + ", '%Y-%m-%d') >= {0} ",
						loanBillForm.getEndDate());
			}
		}
		if (StringUtils.isNotEmpty(loanBillForm.getLoanCode())) {
			wrapper.like("loan_code", loanBillForm.getLoanCode());
		}
		if (StringUtils.isNotEmpty(loanBillForm.getLenderName())) {
			wrapper.like("lender_name", loanBillForm.getLenderName());
		}
		if (StringUtils.isNotEmpty(loanBillForm.getLenderIdentity())) {
			wrapper.like("lender_identity", loanBillForm.getLenderIdentity());
		}
		if (null != loanBillForm.getLoanStatus()) {
			wrapper.eq("loan_status", loanBillForm.getLoanStatus());
		}
		if (StringUtils.isNotEmpty(loanBillForm.getSalesmanName())) {
			wrapper.like("salesman_name", loanBillForm.getSalesmanName());
		}
		if (StringUtils.isNotEmpty(loanBillForm.getProvideName())) {
			wrapper.like("provide_name", loanBillForm.getProvideName());
		}
		if (StringUtils.isNotEmpty(loanBillForm.getIncomeName())) {
			wrapper.like("income_name", loanBillForm.getIncomeName());
		}
		if (StringUtils.isNotEmpty(loanBillForm.getCallmanName())) {
			wrapper.like("callman_name", loanBillForm.getCallmanName());
		}
		if (null != loanBillForm.getBusinessType()) {
			wrapper.eq("business_type", loanBillForm.getBusinessType());
		}
		if (null != loanBillForm.getDelFlag()) {
			wrapper.eq("del_flag", loanBillForm.getDelFlag());
		}
		// 催收员看到所有未被领取以及催收员是自己的单子
		if (AccountTypeEnum.CS.getKey() == userAccount.getType()) {
			wrapper.and("(callman_id is null or callman_id = {0})",
					userAccount.getId());
		}
		wrapper.orderBy("id", false);
		Page<LoanBill> page = new Page<LoanBill>(pageNo, pageSize);
		page = loanBillService.selectPage(page, wrapper);

		return RestObject.newOk("查询成功", page.getRecords(), page.getTotal());
	}

	/**
	 * 添加/修改系统用户信息
	 * 
	 * @param request
	 * @param response
	 * @param loanBillForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveLoanBill")
	public RestObject saveLoanBill(HttpServletRequest request,
			UserAccount userAccount, HttpServletResponse response,
			@Valid LoanBillForm loanBillForm) {
		LoanBill loanBill = new LoanBill();
		boolean flag = false;
		if (null != loanBillForm.getId()) {
			try {
				loanBill = loanBillService.selectById(loanBillForm.getId());
				BeanUtils.copyPropertiesExclude(loanBillForm, loanBill,
						new String[] { "createTime", "expireTime",
								"paybackTime", "rebatePointDate" }, false);
				if (StringUtils.isNotBlank(loanBillForm.getCreateTime())) {
					loanBill.setCreateTime(DateTimeUtils.parseDateTime(
							loanBillForm.getCreateTime(), "yyyy-MM-dd"));
				} else {
					loanBill.setCreateTime(null);
				}
				if (StringUtils.isNotBlank(loanBillForm.getExpireTime())) {
					loanBill.setExpireTime(DateTimeUtils.parseDateTime(
							loanBillForm.getExpireTime(), "yyyy-MM-dd"));
				} else {
					loanBill.setExpireTime(null);
				}
				if (StringUtils.isNotBlank(loanBillForm.getPaybackTime())) {
					loanBill.setPaybackTime(DateTimeUtils.parseDateTime(
							loanBillForm.getPaybackTime(), "yyyy-MM-dd"));
				} else {
					loanBill.setPaybackTime(null);
				}
				if (StringUtils.isNotBlank(loanBillForm.getRebatePointDate())) {
					loanBill.setRebatePointDate(DateTimeUtils.parseDateTime(
							loanBillForm.getRebatePointDate(), "yyyy-MM-dd"));
				} else {
					loanBill.setRebatePointDate(null);
				}
				loanBill.setModifyId(userAccount.getId());
				loanBill.setModifyName(userAccount.getName());
				loanBill.setModifyTime(new Date());
				flag = loanBillService.updateAllColumnById(loanBill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				BeanUtils.copyPropertiesExclude(loanBillForm, loanBill,
						new String[] { "createTime", "expireTime",
								"paybackTime", "rebatePointDate" }, false);
				if (StringUtils.isNotBlank(loanBillForm.getCreateTime())) {
					loanBill.setCreateTime(DateTimeUtils.parseDateTime(
							loanBillForm.getCreateTime(), "yyyy-MM-dd"));
				}
				if (StringUtils.isNotBlank(loanBillForm.getExpireTime())) {
					loanBill.setExpireTime(DateTimeUtils.parseDateTime(
							loanBillForm.getExpireTime(), "yyyy-MM-dd"));
				}
				if (StringUtils.isNotBlank(loanBillForm.getPaybackTime())) {
					loanBill.setPaybackTime(DateTimeUtils.parseDateTime(
							loanBillForm.getPaybackTime(), "yyyy-MM-dd"));
				}
				if (StringUtils.isNotBlank(loanBillForm.getRebatePointDate())) {
					loanBill.setPaybackTime(DateTimeUtils.parseDateTime(
							loanBillForm.getRebatePointDate(), "yyyy-MM-dd"));
				} else {
					loanBill.setRebatePointDate(null);
				}
				
				flag = loanBillService.insert(loanBill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (flag) {
			//续期自动生成新的下款单
			if(LoanBusinessTypeEnum.XUQI.getKey() == loanBill.getBusinessType()) {
				createNewLoanBill(loanBill, userAccount);
			}
			return RestObject.newOk("保存成功");
		} else {
			return RestObject.newError("保存失败");
		}
	}

	/**
	 * 生成订单号
	 * 
	 * @return
	 */
	private String genenteCode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			result += random.nextInt(10);
		}
		return "sm" + newDate + result;
	}

	/**
	 * 删除系统用户信息
	 * 
	 * @param request
	 * @param response
	 * @param loanBillForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delLoanBill")
	public RestObject delLoanBill(HttpServletRequest request,
			HttpServletResponse response, @Null(message = "ID不能为空") int id, UserAccount userAccount) {
		boolean flag = false;
		LoanBill loanBill = loanBillService.selectById(id);
		loanBill.setDelFlag(LoanDelFlagEnum.DISABLED.getKey());
		loanBill.setModifyId(userAccount.getId());
		loanBill.setModifyName(userAccount.getName());
		loanBill.setModifyTime(new Date());
		flag = loanBillService.updateById(loanBill);

		if (flag) {
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
	@RequestMapping("/loanBillInfo")
	public RestObject loanBillInfo(HttpServletRequest request,
			HttpServletResponse response, Integer id, UserAccount userAccount) {
		Map<String, Object> resultData = new HashMap<String, Object>();
		LoanBill loanBill = new LoanBill();
		if (null != id) {
			loanBill = loanBillService.selectById(id);
		} else {
			init(loanBill, userAccount);
		}
		resultData.put("loanBill", loanBill);

		// 查询借贷人信息
		Wrapper<Lender> lwrapper = new EntityWrapper<>();
		lwrapper.eq("status", LenderStatusEnum.ENABLED.getKey());
		lwrapper.eq("creator_id", userAccount.getId());
		List<Lender> lenderList = lenderService.selectList(lwrapper);
		resultData.put("lenderList", lenderList);

		// 查询财务、催收人员
		Wrapper<UserAccount> uwrapper = new EntityWrapper<>();
		uwrapper.eq("status", LenderStatusEnum.ENABLED.getKey());
		uwrapper.and(" type in ({0}, {1})", AccountTypeEnum.CW.getKey(),
				AccountTypeEnum.CS.getKey());
		List<UserAccount> userAccountList = userAccountService
				.selectList(uwrapper);

		// 财务/催收人员
		List<UserAccount> cwUserList = new ArrayList<UserAccount>();
		List<UserAccount> csUserList = new ArrayList<UserAccount>();
		for (UserAccount ua : userAccountList) {
			if (AccountTypeEnum.CW.getKey() == ua.getType()) {
				cwUserList.add(ua);
			} else if (AccountTypeEnum.CS.getKey() == ua.getType()) {
				csUserList.add(ua);
			}
		}
		resultData.put("cwUserList", cwUserList);
		resultData.put("csUserList", csUserList);

		return RestObject.newOk("基本信息", resultData);
	}

	/**
	 * @param loanBillForm
	 * @param userAccount
	 */
	private void init(LoanBill loanBill, UserAccount userAccount) {
		loanBill.setCreateTime(new Date());
		loanBill.setSalesmanId(userAccount.getId());
		loanBill.setSalesmanName(userAccount.getName());
		loanBill.setBusinessType(LoanBusinessTypeEnum.WAITING.getKey());
		loanBill.setLoanStatus(LoanStatusEnum.AUDITING.getKey());
		loanBill.setRebatePoint(BigDecimal.ZERO);
		loanBill.setParentId(0);
		loanBill.setRenewalTimes(0);
		loanBill.setDelFlag(LoanDelFlagEnum.ENABLED.getKey());
		loanBill.setLoanCode(genenteCode());
	}
	
	/**
	 * 预期类型，创建一条新的入款单
	 * 
	 * @param loanBill
	 */
	private void createNewLoanBill(LoanBill loanBill, UserAccount userAccount) {
		
		LoanBill newLoanBill = new LoanBill();
		init(newLoanBill, userAccount);
		
		newLoanBill.setLenderId(loanBill.getLenderId());
		newLoanBill.setLenderIdentity(loanBill.getLenderIdentity());
		newLoanBill.setLenderName(loanBill.getLenderName());
		newLoanBill.setLoanLimit(loanBill.getLoanLimit());
		newLoanBill.setIncomeLimie(loanBill.getIncomeLimie());
		newLoanBill.setInterest(loanBill.getInterest());
		newLoanBill.setPeriod(loanBill.getPeriod());
		
		int renewTimes = loanBill.getRenewalTimes();
		renewTimes = renewTimes + 1;
		
		newLoanBill.setLoanCode(loanBill.getLoanCode() + "_" + renewTimes);
		newLoanBill.setParentId(loanBill.getParentId());
		
		boolean flag = loanBillService.insert(newLoanBill);
		if(flag) {
			//更新续期次数
			LoanBill parentLoanBill = new LoanBill();
			parentLoanBill.setId(loanBill.getId());
			parentLoanBill.setRenewalTimes(renewTimes);
			loanBillService.updateById(parentLoanBill);
		}
		
	}

	/**
	 * 催收员领取单子
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param userAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/receiveLoanBill")
	public RestObject receiveLoanBill(HttpServletRequest request,
			HttpServletResponse response, Integer id, UserAccount userAccount) {
		LoanBill loanBill = new LoanBill();
		loanBill.setId(id);
		loanBill.setCallmanId(userAccount.getId());
		loanBill.setCallmanName(userAccount.getName());
		
		boolean flag = loanBillService.updateById(loanBill);
		if (flag) {
			return RestObject.newOk("领取成功");
		} else {
			return RestObject.newError("领取失败");
		}
	}
}

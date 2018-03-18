/**
 *
 * yuanhualiang
 */
package com.green.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.green.annotations.UserId;
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
 * 借贷人控制类
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
			HttpServletResponse response, @UserId long userId,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize) {

		Wrapper<LoanBill> wrapper = new EntityWrapper<>();
		wrapper.eq("del_flag", LoanDelFlagEnum.ENABLED.getKey());
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
	public RestObject saveLoanBill(HttpServletRequest request, UserAccount userAccount, 
			HttpServletResponse response, @Valid LoanBillForm loanBillForm) {
		LoanBill loanBill = new LoanBill();
		boolean flag = false;
		if (null != loanBillForm.getId()) {
			try {
				BeanUtils.copyPropertiesExclude(loanBillForm, loanBill, 
						new String[]{"createTime", "expireTime", "paybackTime"});
				if(StringUtils.isNotBlank(loanBillForm.getCreateTime())) {
					loanBill.setCreateTime(DateTimeUtils.parseDateTime(loanBillForm.getCreateTime(), "yyyy-MM-dd"));
				}
				if(StringUtils.isNotBlank(loanBillForm.getExpireTime())) {
					loanBill.setExpireTime(DateTimeUtils.parseDateTime(loanBillForm.getExpireTime(), "yyyy-MM-dd"));
				}
				if(StringUtils.isNotBlank(loanBillForm.getPaybackTime())) {
					loanBill.setPaybackTime(DateTimeUtils.parseDateTime(loanBillForm.getPaybackTime(), "yyyy-MM-dd"));
				}
				flag = loanBillService.updateById(loanBill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				BeanUtils.copyPropertiesExclude(loanBillForm, loanBill, 
						new String[]{"createTime", "expireTime", "paybackTime"});
				if(StringUtils.isNotBlank(loanBillForm.getCreateTime())) {
					loanBill.setCreateTime(DateTimeUtils.parseDateTime(loanBillForm.getCreateTime(), "yyyy-MM-dd"));
				}
				if(StringUtils.isNotBlank(loanBillForm.getExpireTime())) {
					loanBill.setExpireTime(DateTimeUtils.parseDateTime(loanBillForm.getExpireTime(), "yyyy-MM-dd"));
				}
				if(StringUtils.isNotBlank(loanBillForm.getPaybackTime())) {
					loanBill.setPaybackTime(DateTimeUtils.parseDateTime(loanBillForm.getPaybackTime(), "yyyy-MM-dd"));
				}
				loanBill.setDelFlag(LoanDelFlagEnum.ENABLED.getKey());
				flag = loanBillService.insert(loanBill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (flag) {
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
	 * @param loanBillForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delLoanBill")
	public RestObject delLoanBill(HttpServletRequest request,
			HttpServletResponse response, @Null(message = "ID不能为空") int id) {
		boolean flag = false;
		LoanBill loanBill = loanBillService.selectById(id);
		loanBill.setDelFlag(LoanDelFlagEnum.DISABLED.getKey());
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
		if(null != id) {
			loanBill = loanBillService.selectById(id);
		}else {
			init(loanBill, userAccount);
		}
		resultData.put("loanBill", loanBill);
		
		//查询借贷人信息
		Wrapper<Lender> lwrapper = new EntityWrapper<>();
		lwrapper.eq("status", LenderStatusEnum.ENABLED.getKey());
		lwrapper.eq("creator_id", userAccount.getId());
		List<Lender> lenderList = lenderService.selectList(lwrapper);
		resultData.put("lenderList", lenderList);
		
		//查询财务、催收人员
		Wrapper<UserAccount> uwrapper = new EntityWrapper<>();
		uwrapper.eq("status", LenderStatusEnum.ENABLED.getKey());
		uwrapper.and(" type in ({0}, {1})", AccountTypeEnum.CW.getKey(), AccountTypeEnum.CS.getKey());
		List<UserAccount> userAccountList = userAccountService.selectList(uwrapper);
		
		//财务/催收人员
		List<UserAccount> cwUserList = new ArrayList<UserAccount>();
		List<UserAccount> csUserList = new ArrayList<UserAccount>();
		for(UserAccount ua : userAccountList) {
			if(AccountTypeEnum.CW.getKey() == ua.getType()) {
				cwUserList.add(ua);
			}else if(AccountTypeEnum.CS.getKey() == ua.getType()) {
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
	}
}

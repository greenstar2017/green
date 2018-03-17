/**
 *
 * yuanhualiang
 */
package com.green.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;

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
import com.green.constants.AccountStatusEnum;
import com.green.constants.LenderStatusEnum;
import com.green.dto.LenderForm;
import com.green.entity.Lender;
import com.green.entity.UserAccount;
import com.green.response.RestObject;
import com.green.service.LenderService;

/**
 * @author yuanhualiang
 *
 * 借贷人控制类
 */
@Controller
@RequestMapping("/console/lender")
public class LenderController extends BaseController {

	@Autowired
	private LenderService lenderService;

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
	@RequestMapping("/lenderList")
	public RestObject lenderList(
			HttpServletRequest request,
			HttpServletResponse response, @UserId long userId,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize) {

		Wrapper<Lender> wrapper = new EntityWrapper<>();
		wrapper.eq("status", AccountStatusEnum.ENABLED.getKey());
		wrapper.eq("creator_id", userId);
		Page<Lender> page = new Page<Lender>(pageNo, pageSize);
		page = lenderService.selectPage(page, wrapper);

		return RestObject.newOk("查询成功", page.getRecords(), page.getTotal());
	}

	/**
	 * 添加/修改系统用户信息
	 * 
	 * @param request
	 * @param response
	 * @param lenderForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveLender")
	public RestObject saveLender(HttpServletRequest request, UserAccount userAccount, 
			HttpServletResponse response, @Valid LenderForm lenderForm) {
		Lender lender = new Lender();
		boolean flag = false;
		if (null != lenderForm.getId()) {
			try {
				BeanUtils.copyProperties(lenderForm, lender);
				lender.setUpdateTime(new Date());
				if(null != lenderService.checkExistLender(lender)) {
					return RestObject.newError("借贷人身份证已经存在，创建人【" + lender.getCreator() + "】");
				}
				flag = lenderService.updateById(lender);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				BeanUtils.copyProperties(lenderForm, lender);
				lender.setStatus(LenderStatusEnum.ENABLED.getKey());
				lender.setCreator(userAccount.getName());
				lender.setCreatorId(userAccount.getId());
				lender.setCreateTime(new Date());
				lender.setUpdateTime(new Date());
				if(null != lenderService.checkExistLender(lender)) {
					return RestObject.newError("借贷人身份证已经存在，创建人【" + lender.getCreator() + "】");
				}
				flag = lenderService.insert(lender);
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
	 * @param lenderForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delLender")
	public RestObject delLender(HttpServletRequest request,
			HttpServletResponse response, @Null(message = "ID不能为空") int id) {
		boolean flag = false;
		Lender lender = lenderService.selectById(id);
		lender.setStatus(AccountStatusEnum.DISABLED.getKey());
		flag = lenderService.updateById(lender);

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
	@RequestMapping("/lenderInfo")
	public RestObject lenderInfo(HttpServletRequest request,
			HttpServletResponse response, @Null(message = "ID不能为空") int id) {
		Lender lender = lenderService.selectById(id);
		return RestObject.newOk("基本信息", lender);
	}
}

package com.green.service;

import com.baomidou.mybatisplus.service.IService;
import com.green.entity.Lender;

/**
 * <p>
 * 借贷人信息 服务类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-17
 */
public interface LenderService extends IService<Lender> {

	/**
	 * 检查借贷人是否已经存在
	 * 
	 * @param lender
	 * @return
	 */
	Lender checkExistLender(Lender lender);

}

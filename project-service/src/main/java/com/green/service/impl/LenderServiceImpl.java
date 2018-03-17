package com.green.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.green.constants.AccountStatusEnum;
import com.green.entity.Lender;
import com.green.entity.UserAccount;
import com.green.mapper.LenderMapper;
import com.green.service.LenderService;

/**
 * <p>
 * 借贷人信息 服务实现类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-17
 */
@Service
public class LenderServiceImpl extends ServiceImpl<LenderMapper, Lender>
		implements LenderService {

	@Override
	public Lender checkExistLender(Lender lender) {
		String identity = lender.getLenderIdentity();
		Wrapper<Lender> wrapper = new EntityWrapper<Lender>();
		wrapper.eq("lender_identity", identity);
		if (null != lender.getId()) {
			wrapper.ne("id", lender.getId());
		}
		wrapper.eq("status", AccountStatusEnum.ENABLED.getKey());
		lender = this.selectOne(wrapper);
		return lender;
	}

}

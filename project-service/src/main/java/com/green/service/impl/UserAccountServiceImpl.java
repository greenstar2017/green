package com.green.service.impl;

import com.green.constants.AccountStatusEnum;
import com.green.entity.UserAccount;
import com.green.mapper.UserAccountMapper;
import com.green.service.UserAccountService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-16
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

	@Override
	public boolean checkExistAccount(UserAccount userAccount) {
		String account = userAccount.getAccount();
		Wrapper<UserAccount> wrapper = new EntityWrapper<UserAccount>();
		wrapper.eq("account", account);
		if(null != userAccount.getId()) {
			wrapper.ne("id", userAccount.getId());
		}
		wrapper.eq("status", AccountStatusEnum.ENABLED.getKey());
		int count = this.selectCount(wrapper);
		return count > 0;
	}
	
}

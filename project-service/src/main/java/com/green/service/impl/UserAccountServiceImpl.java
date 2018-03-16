package com.green.service.impl;

import com.green.entity.UserAccount;
import com.green.mapper.UserAccountMapper;
import com.green.service.UserAccountService;
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
	
}

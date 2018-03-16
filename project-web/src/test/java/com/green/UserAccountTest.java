/**
 *
 * yuanhualiang
 */
package com.green;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.green.entity.UserAccount;
import com.green.service.UserAccountService;

/**
 * @author yuanhualiang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
public class UserAccountTest extends TestCase {

	@Autowired
	private UserAccountService userAccountService;
	
	@Test
	public void addUser() {
		UserAccount userAccount = new UserAccount();
		userAccount.setAccount("yewuyuan001");
		userAccount.setName("业务员001");
		userAccount.setStatus(0);
		userAccount.setType(1);
		
		userAccountService.insert(userAccount);
	}
}

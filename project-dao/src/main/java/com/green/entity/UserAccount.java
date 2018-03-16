package com.green.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-16
 */
@TableName("user_account")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 账号
     */
	private String account;
	/**
	 * 密码
	 */
	private String password;
    /**
     * 名称
     */
	private String name;
    /**
     * 账号类型 参考accountTypeEnum
     */
	private Integer type;
    /**
     * 有效状态 0-有效 1-无效 参考AccountStatusEnum
     */
	private Integer status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

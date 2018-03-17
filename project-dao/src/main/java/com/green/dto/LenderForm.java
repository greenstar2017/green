/**
 *
 * yuanhualiang
 */
package com.green.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @author yuanhualiang
 *
 */
public class LenderForm {

	/**
     * 借贷人ID
     */
	private Integer id;
    /**
     * 借贷人姓名
     */
	@NotBlank(message = "姓名不能为空")
	@Length(max = 30, message = "姓名长度不超过30个字符")
	private String lenderName;
    /**
     * 借贷人身份证号码
     */
	@NotBlank(message = "身份证号码不能为空")
	@Length(max = 30, message = "身份证号码长度不超过30个字符")
	private String lenderIdentity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLenderName() {
		return lenderName;
	}
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	public String getLenderIdentity() {
		return lenderIdentity;
	}
	public void setLenderIdentity(String lenderIdentity) {
		this.lenderIdentity = lenderIdentity;
	}
	
}

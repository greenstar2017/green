/**
 *
 * yuanhualiang
 */
package com.green.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author yuanhualiang
 * 
 * 收款记录
 */
public class LoanIncomeRecordForm {

	/**
     * 收款记录ID
     */
	private Integer id;
    /**
     * 下款单ID
     */
	private Integer loanBillId;
    /**
     * 添加人ID
     */
	private Integer creatorId;
    /**
     * 添加人名称
     */
	private String creatorName;
    /**
     * 收款日
     */
	@NotBlank(message = "收款日不能为空")
	private String incomeTime;
    /**
     * 收款金额
     */
	@DecimalMax(value = "1000000000", message = "收款金额长度不超过10位")
	private BigDecimal incomeAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLoanBillId() {
		return loanBillId;
	}
	public void setLoanBillId(Integer loanBillId) {
		this.loanBillId = loanBillId;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getIncomeTime() {
		return incomeTime;
	}
	public void setIncomeTime(String incomeTime) {
		this.incomeTime = incomeTime;
	}
	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	
}

package com.green.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-20
 */
@TableName("loan_income_record")
public class LoanIncomeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收款记录ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 下款单ID
     */
	@TableField("loan_bill_id")
	private Integer loanBillId;
    /**
     * 添加人ID
     */
	@TableField("creator_id")
	private Integer creatorId;
    /**
     * 添加人名称
     */
	@TableField("creator_name")
	private String creatorName;
    /**
     * 收款日
     */
	@TableField("income_time")
	private Date incomeTime;
    /**
     * 收款金额
     */
	@TableField("income_amount")
	private BigDecimal incomeAmount;
    /**
     * 删除标志
     */
	@TableField("del_flag")
	private Integer delFlag;


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

	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}

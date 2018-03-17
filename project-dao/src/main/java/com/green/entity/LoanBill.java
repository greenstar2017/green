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
 * 入款信息
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-17
 */
@TableName("loan_bill")
public class LoanBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入款ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 借贷人名称
     */
	@TableField("lender_name")
	private String lenderName;
    /**
     * 借贷人身份证
     */
	@TableField("lender_identity")
	private String lenderIdentity;
    /**
     * 下款途径 参考LoanWayEnum
     */
	@TableField("loan_way")
	private Integer loanWay;
    /**
     * 下款额度
     */
	@TableField("loan_limit")
	private BigDecimal loanLimit;
    /**
     * 到手额度
     */
	@TableField("income_limie")
	private BigDecimal incomeLimie;
    /**
     * 利息
     */
	private BigDecimal interest;
    /**
     * 周期
     */
	private Integer period;
    /**
     * 放款人名称
     */
	@TableField("provide_name")
	private String provideName;
    /**
     * 放款人ID
     */
	@TableField("provide_id")
	private Integer provideId;
    /**
     * 业务员名称
     */
	@TableField("salesman_name")
	private String salesmanName;
    /**
     * 业务员ID
     */
	@TableField("salesman_id")
	private Integer salesmanId;
    /**
     * 催收员名称
     */
	@TableField("callman_name")
	private String callmanName;
    /**
     * 催收员ID
     */
	@TableField("callman_id")
	private Integer callmanId;
    /**
     * 中介名称
     */
	@TableField("agency_name")
	private String agencyName;
    /**
     * 返点
     */
	@TableField("rebate_point")
	private BigDecimal rebatePoint;
    /**
     * 返点途径 参考LoanRebatePointWay
     */
	@TableField("rebate_point_way")
	private Integer rebatePointWay;
    /**
     * 录入日期
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 到期日
     */
	@TableField("expire_time")
	private Date expireTime;
    /**
     * 还款日
     */
	@TableField("payback_time")
	private Date paybackTime;
    /**
     * 业务类型 参考LoanBusinessTypeEnum
     */
	@TableField("business_type")
	private Integer businessType;
    /**
     * 收款金额
     */
	@TableField("income_amount")
	private BigDecimal incomeAmount;
	/**
	 * 收款人名字
	 */
	@TableField("income_name")
	private String incomeName;
	/**
	 * 收款人ID
	 */
	@TableField("income_id")
	private BigDecimal incomeId;
    /**
     * 收款平台 参考LoanWayEnum
     */
	@TableField("income_platform")
	private Integer incomePlatform;
    /**
     * 下款状态 参考LoanStatusEnum
     */
	@TableField("loan_status")
	private Integer loanStatus;
    /**
     * 拒绝理由
     */
	@TableField("reject_reason")
	private String rejectReason;
    /**
     * 更新时间
     */
	@TableField("modify_time")
	private Date modifyTime;
    /**
     * 更新人名称
     */
	@TableField("modify_name")
	private String modifyName;
    /**
     * 更新人ID
     */
	@TableField("modify_id")
	private Integer modifyId;
    /**
     * 删除状态 参考LoanDelFlagEnum
     */
	@TableField("del_flag")
	private Integer delFlag;


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

	public Integer getLoanWay() {
		return loanWay;
	}

	public void setLoanWay(Integer loanWay) {
		this.loanWay = loanWay;
	}

	public BigDecimal getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(BigDecimal loanLimit) {
		this.loanLimit = loanLimit;
	}

	public BigDecimal getIncomeLimie() {
		return incomeLimie;
	}

	public void setIncomeLimie(BigDecimal incomeLimie) {
		this.incomeLimie = incomeLimie;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getProvideName() {
		return provideName;
	}

	public void setProvideName(String provideName) {
		this.provideName = provideName;
	}

	public Integer getProvideId() {
		return provideId;
	}

	public void setProvideId(Integer provideId) {
		this.provideId = provideId;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getCallmanName() {
		return callmanName;
	}

	public void setCallmanName(String callmanName) {
		this.callmanName = callmanName;
	}

	public Integer getCallmanId() {
		return callmanId;
	}

	public void setCallmanId(Integer callmanId) {
		this.callmanId = callmanId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public BigDecimal getRebatePoint() {
		return rebatePoint;
	}

	public void setRebatePoint(BigDecimal rebatePoint) {
		this.rebatePoint = rebatePoint;
	}

	public Integer getRebatePointWay() {
		return rebatePointWay;
	}

	public void setRebatePointWay(Integer rebatePointWay) {
		this.rebatePointWay = rebatePointWay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getPaybackTime() {
		return paybackTime;
	}

	public void setPaybackTime(Date paybackTime) {
		this.paybackTime = paybackTime;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public BigDecimal getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(BigDecimal incomeId) {
		this.incomeId = incomeId;
	}

	public Integer getIncomePlatform() {
		return incomePlatform;
	}

	public void setIncomePlatform(Integer incomePlatform) {
		this.incomePlatform = incomePlatform;
	}

	public Integer getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(Integer loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public Integer getModifyId() {
		return modifyId;
	}

	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}

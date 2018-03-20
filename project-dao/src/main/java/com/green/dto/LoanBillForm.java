/**
 *
 * yuanhualiang
 */
package com.green.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author yuanhualiang
 * 下款信息
 */
public class LoanBillForm {
	/**
	 * 下款ID
	 */
	private Integer id;
	/**
     * 借贷人ID
     */
	private Integer lenderId;
	/**
	 * 下款编号
	 */
	private String loanCode;
	/**
	 * 借贷人名称
	 */
	@NotBlank(message = "借贷人名称不能为空")
	@Length(max = 30, message = "借贷人名称长度不超过30个字符")
	private String lenderName;
	/**
	 * 借贷人身份证
	 */
	@NotBlank(message = "借贷人身份证不能为空")
	@Length(max = 30, message = "借贷人身份证长度不超过30个字符")
	private String lenderIdentity;
	/**
	 * 下款途径 参考LoanWayEnum
	 */
	private Integer loanWay;
	/**
	 * 下款额度
	 */
	@DecimalMax(value = "1000000000", message = "下款额度长度不超过10位")
	private BigDecimal loanLimit;
	/**
	 * 到手额度
	 */
	@DecimalMax(value = "1000000000", message = "到手额度长度不超过10位")
	private BigDecimal incomeLimie;
	/**
	 * 利息
	 */
	@DecimalMax(value = "1000000000", message = "利息长度不超过10位")
	private BigDecimal interest;
	/**
	 * 周期
	 */
	@Max(value = 1000000000, message = "周期长度不超过10位")
	private Integer period;
	/**
	 * 放款人名称
	 */
	@Length(max = 30, message = "放款人名称长度不超过30位")
	private String provideName;
	/**
	 * 放款人ID
	 */
	private Integer provideId;
	/**
	 * 业务员名称
	 */
	@Length(max = 30, message = "业务员名称长度不超过30位")
	private String salesmanName;
	/**
	 * 业务员ID
	 */
	private Integer salesmanId;
	/**
	 * 催收员名称
	 */
	@Length(max = 30, message = "催收员名称长度不超过30位")
	private String callmanName;
	/**
	 * 催收员ID
	 */
	private Integer callmanId;
	/**
	 * 中介名称
	 */
	@Length(max = 30, message = "中介名称长度不超过30位")
	private String agencyName;
	/**
	 * 返点
	 */
	@DecimalMax(value = "1000000000", message = "返点长度不超过30位")
	private BigDecimal rebatePoint;
	/**
	 * 返点途径 参考LoanRebatePointWay
	 */
	private Integer rebatePointWay;
	/**
     * 返点日
     */
	private String rebatePointDate;
	/**
	 * 录入日期
	 */
	private String createTime;
	/**
	 * 到期日
	 */
	private String expireTime;
	/**
	 * 还款日
	 */
	private String paybackTime;
	/**
	 * 业务类型 参考LoanBusinessTypeEnum
	 */
	private Integer businessType;
	/**
	 * 收款金额
	 */
	@DecimalMax(value = "1000000000", message = "收款金额长度不超过10位")
	private BigDecimal incomeAmount;
	/**
	 * 收款人名字
	 */
	@Length(max = 30, message = "收款人名字长度不超过30位")
	private String incomeName;
	/**
	 * 收款人ID
	 */
	private Integer incomeId;
	/**
	 * 收款平台 参考LoanWayEnum
	 */
	private Integer incomePlatform;
	/**
	 * 下款状态 参考LoanStatusEnum
	 */
	private Integer loanStatus;
	
	/**
     * 拒绝理由
     */
	@Length(max = 200, message = "拒绝理由长度不超过200位")
	private String rejectReason;
	/**
     * 删除状态 参考LoanDelFlagEnum
     */
	private Integer delFlag;
	
	/**
	 * 查询日期类型
	 */
	private Integer dateType;
	
	private String startDate;
	
	private String endDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLenderId() {
		return lenderId;
	}
	public void setLenderId(Integer lenderId) {
		this.lenderId = lenderId;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getPaybackTime() {
		return paybackTime;
	}
	public void setPaybackTime(String paybackTime) {
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
	public Integer getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(Integer incomeId) {
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
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRebatePointDate() {
		return rebatePointDate;
	}
	public void setRebatePointDate(String rebatePointDate) {
		this.rebatePointDate = rebatePointDate;
	}
	
}

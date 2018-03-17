package com.green.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 借贷人信息
 * </p>
 *
 * @author yuanhualiang
 * @since 2018-03-17
 */
public class Lender implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借贷人ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 创建人ID
     */
	@TableField("creator_id")
	private Integer creatorId;
    /**
     * 创建人
     */
	private String creator;
    /**
     * 借贷人姓名
     */
	@TableField("lender_name")
	private String lenderName;
    /**
     * 借贷人身份证号码
     */
	@TableField("lender_identity")
	private String lenderIdentity;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 是否有效 参考LenderStatusEnum
     */
	private Integer status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

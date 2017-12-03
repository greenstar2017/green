package com.green.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 卡券审核通过记录表
 * </p>
 *
 * @author yuanhualiang
 * @since 2017-12-03
 */
@TableName("card_audit_passed_record")
public class CardAuditPassedRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 卡券ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 卡券ID
     */
	@TableField("card_id")
	private String cardId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

/**
 *
 * yuanhualiang
 */
package com.green.constants;

/**
 * @author yuanhualiang
 *
 * 业务类型
 */
public enum LoanBusinessTypeEnum {

	/**
	 * 待进行
	 */
	WAITING("WAITING", 0, "待进行"),

	/**
	 * 进行中
	 */
	DOING("DOING", 1, "进行中"),

	/**
	 * 续期
	 */
	XUQI("XUQI", 2, "续期"),

	/**
	 * 全款
	 */
	QUANKUAN("QUANKUAN", 3, "全款"),

	/**
	 * 逾期还款
	 */
	YUQIHUANKUAN("YUQIHUANKUAN", 4, "逾期还款"),

	/**
	 * 提前续期
	 */
	TIQIANHUANKUAN("TIQIANHUANKUAN", 5, "提前续期"),

	/**
	 * 提前全款
	 */
	TIQIANQUANKUAN("TIQIANQUANKUAN", 6, "提前全款"),

	/**
	 * 逾期
	 */
	YUQI("YUQI", 7, "逾期"),

	/**
	 * 催收
	 */
	CUISHOU("CUISHOU", 8, "催收");

	private String typeName;

	private int key;

	private String typeDes;

	LoanBusinessTypeEnum(String typeName, int key, String typeDes) {
		this.typeName = typeName;
		this.key = key;
		this.typeDes = typeDes;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}

	public String getTypeDes() {
		return typeDes;
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}
}

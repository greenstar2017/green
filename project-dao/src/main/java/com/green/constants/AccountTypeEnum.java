/**
 *
 * yuanhualiang
 */
package com.green.constants;

/**
 * @author yuanhualiang
 *
 * 用户类型
 */
public enum AccountTypeEnum {

	/**
	 * 管理员
	 */
	ADMIN("ADMIN", 0, "管理员"),

	/**
	 * 业务员
	 */
	YW("YW", 1, "业务员"),

	/**
	 * 财务人员
	 */
	CW("CW", 2, "财务人员"),

	/**
	 * 财务统计人员
	 */
	YWTJ("YWTJ", 3, "财务统计人员"),

	/**
	 * 催收人员
	 */
	CS("CS", 4, "催收人员");

	private String typeName;

	private int key;

	private String typeDes;

	AccountTypeEnum(String typeName, int key, String typeDes) {
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

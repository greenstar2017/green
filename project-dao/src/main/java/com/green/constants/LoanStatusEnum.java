/**
 *
 * yuanhualiang
 */
package com.green.constants;

/**
 * @author yuanhualiang
 *
 *         下款状态
 */
public enum LoanStatusEnum {

	/**
	 * 审核中
	 */
	AUDITING("AUDITING", 0, "审核中"),

	/**
	 * 通过
	 */
	PASSED("PASSED", 1, "通过"),

	/**
	 * 拒绝
	 */
	REJECT("REJECT", 2, "拒绝");

	private String typeName;

	private int key;

	private String typeDes;

	LoanStatusEnum(String typeName, int key, String typeDes) {
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

/**
 *
 * yuanhualiang
 */
package com.green.constants;

/**
 * @author yuanhualiang
 *
 * 下款途径
 */
public enum LoanWayEnum {

	/**
	 * 借贷宝
	 */
	JDB("JDB", 0, "借贷宝"),

	/**
	 * 微信
	 */
	WX("WX", 1, "微信"),

	/**
	 * 支付宝
	 */
	ALIPAY("ALIPAY", 2, "支付宝");

	private String typeName;

	private int key;

	private String typeDes;

	LoanWayEnum(String typeName, int key, String typeDes) {
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

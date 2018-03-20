package com.green.constants;

/**
 * 删除标志
 * 
 * @author yuanhualiang
 *
 */
public enum DelFlagEnum {
	/**
	 * 未删除
	 */
	UN_DEL("未删除", 0, "未删除"),

	/**
	 * 已删除
	 */
	DONE_DEL("已删除", 1, "已删除");

	private String typeName;

	private int key;

	private String typeDes;

	DelFlagEnum(String typeName, int key, String typeDes) {
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

package com.liyiandxuegang.newentity;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer cid;
	private String custName;
	private String custLevel;
	private String custSource;
	private String custPhone;
	private String custMobile;
	
	// 在客户实体类表示多个联系人， 一个客户有多个联系人
	// 在hibernate要求使用集合表示多个集合，使用set集合
	private Set<LinkMan> setLinkMans = new HashSet<LinkMan>();
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public Set<LinkMan> getSetLinkMans() {
		return setLinkMans;
	}
	public void setSetLinkMans(Set<LinkMan> setLinkMans) {
		this.setLinkMans = setLinkMans;
	}
	
}

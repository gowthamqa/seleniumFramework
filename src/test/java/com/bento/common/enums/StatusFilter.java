package com.bento.common.enums;

public enum StatusFilter {

	
 UNPROVISIONED("unProvisionedFilter"),
 MODIFIED("modifiedFilter"),
 LOCKED("lockedFilter"),
 DISABLED("disabledFilter"),
 FAILED("failedFilter");

	String m_value;

	StatusFilter() {
		m_value = toString();
	}

	StatusFilter(String value) {
		m_value = value;
	}

	
	public  String getValue() {
		return m_value;
		
	}
}

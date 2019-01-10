package com.bento.common.enums;

public enum ActionMenu {

	

	BROKEN_UNIFIER_TEST_INSTANCE("Application Instances","Broken Unifier Test Instance"),
	P6_152_VBE3("Application Instances","P6 15.2 VBE3"),
	PRIMAVERA_P6_INSTANCE("Application Instances","Primavera P6 Instance"),
	PRIMAVERA_UNIFIER_INSTANCE_151("Application Instances","Primavera Unifier Instance 15.1"),
	STAGE_P6_EPPM_84_INSTANCE("Application Instances","Stage P6 EPPM 8.4 Instance"),
	BIAUTHORS("Roles","BIAuthors"),
	PRIMAVERA_GATEWAY_ADMIN("Roles","Primavera Gateway Admin"),
	PRIMAVERA_GATEWAY_USER("Roles","Primavera Gateway User"),
	PRIMAVERA_GATEWAY_DEVELOPER("Roles","PrimaveraGatewayDeveloper");

		String m_value;
		String m_type;
		ActionMenu() {
			m_value = toString();
		}

		ActionMenu(String type , String value) {
			m_value = value;
			m_type = type;
		}

		
		public  String getValue() {
			return m_value;
		}
		public  String getType() {
			return m_type;
		}
}

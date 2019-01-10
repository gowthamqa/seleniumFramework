package com.bento.common.enums;

public enum DownloadLocation {

	 DESKTOP("Desktop"),
	 DOWNLOADS("Downloads"),
	 CUSTOM();
	
		String m_value;

		DownloadLocation() {
			m_value = toString();
		}

		DownloadLocation(String value) {
			m_value = value;
		}

		
		public  String getValue() {
			return m_value;
			
		}
}

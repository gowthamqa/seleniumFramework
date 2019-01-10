package com.bento.common.utils;

import java.io.File;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;

public class AutoIt {
	
	public static void uploadFile(String title, String filePath) {
		File file = new File(".\\src\\test\\java\\com\\bento\\common\\resources\\jacob-1.14.3\\jacob-1.14.3-x64.dll");
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
		AutoItX auto = new AutoItX();
		auto.winActivate(title);
		auto.controlSend(title, "", "[CLASS:Edit; INSTANCE:1]", filePath);
		auto.controlClick(title, "", "[CLASS:Button; INSTANCE:1]");
		
	}

}

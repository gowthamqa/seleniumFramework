package com.bento.common.utils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.bento.common.By;
import com.bento.common.dto.UserDTO;
import com.bento.common.enums.DownloadLocation;

public class AutomationUtils {

    private final static String SPACE = " ";
    private final static String ENGLISH = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";
    private final static String SPECIAL_CHARS = "+-$@!`~{}[]|\'\";:.><,?/#%^*()_=&";
    final static Logger logger = LogManager.getLogger(AutomationUtils.class);

    public static String getInputString(int length, boolean includeSpecialChars, boolean includeSpace) {
        String extraChars = (includeSpecialChars) ? SPECIAL_CHARS : "";
        extraChars += (includeSpace) ? SPACE : "";
        return RandomStringUtils.random(length, ENGLISH + extraChars).trim().replaceAll("(\\s)+", SPACE);
    }

    public static String getInputString(int length, boolean includeSpecialChars) {
        return getInputString(length, includeSpecialChars, false);
    }

    public static String getInputString(int length) {
        return getInputString(length, false);
    }

    /**
     * A helper function, Returns a random number between a range
     *
     * @param floor
     *            lowest number in range
     * @param ceiling
     *            highest number in range
     */

    public static int getRandomInt(int floor, int ceiling) {
        SecureRandom rnd = new SecureRandom();
        return rnd.nextInt(ceiling - floor) + floor;
    }

    /**
     *
     * @param seconds
     *            number of seconds to sleep as a double value eg. 0.5.
     */
    public static void sleep(final Double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param seconds
     *            number of seconds to sleep as integer.
     */
    public static void sleep(final int seconds) {
        sleep(new Double(seconds));
    }

    public static boolean isPageReady() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) ApplicationProperties.getInstance().getDriver();
        boolean jQueryAnimationDone, jQueryReady, docReadyState;

        // Handle unexpected save errors;
        handleAlertMessage(true, true);

        // Document ready State
        docReadyState = (Boolean) javascriptExecutor.executeScript("return document.readyState == 'complete'");

        // wait for jQuery animations to complete
        jQueryReady = (Boolean) javascriptExecutor.executeScript("return (typeof(jQuery) != 'undefined') && jQuery.isReady");

        // wait for jQuery animations to complete
        jQueryAnimationDone = (Boolean) javascriptExecutor.executeScript("return (typeof(jQuery) != 'undefined') && (jQuery(':animated').length === 0)");

        return (docReadyState && jQueryReady && jQueryAnimationDone && pgbuReady());
    }

    private static boolean pgbuReady() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) ApplicationProperties.getInstance().getDriver();
        boolean isReportingSpecLoaded, useCustomReporting, isCustomLoadDone;

        isReportingSpecLoaded = (Boolean) javascriptExecutor.executeScript("return window.hasOwnProperty('PGBU_TEST_EVENTS');");
        useCustomReporting = isReportingSpecLoaded
                && (Boolean) javascriptExecutor
                        .executeScript("return (typeof(window.PGBU_TEST_EVENTS.selfReportLoading) != 'undefined') && (window.PGBU_TEST_EVENTS.selfReportLoading === true)");
        isCustomLoadDone = useCustomReporting
                && (Boolean) javascriptExecutor
                        .executeScript("return (typeof(window.PGBU_TEST_EVENTS.isLoaded) != 'undefined') && (window.PGBU_TEST_EVENTS.isLoaded === true)");

        if (!isReportingSpecLoaded) {
            logger.debug("window.hasOwnProperty('PGBU_TEST_EVENTS') not set.  Ask a programmer to make sure require(['selenium:loaded']); is added in page javascript file.");
            // if reporting spec is not there after page is there,
            // then check with programmer why it was removed.

            return true;
        } else {
            return (!useCustomReporting || isCustomLoadDone);
        }
    }

    private static boolean handleAlertMessage(boolean dismiss, boolean failIfPresent) {
        try {
            Alert alert = ApplicationProperties.getInstance().getDriver().switchTo().alert();
            String message = alert.getText();

            if (dismiss) {
                alert.dismiss();
            }

            if (failIfPresent) {
                throw new RuntimeException("Unexpected Alert Message " + message + " Found on page.");
            }

            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    public static String getTodaysDateWithFormat(String expectedFormat)
	{
		   //get current date time with Date()
		   Date date = new Date();
		   DateFormat dateFormat = new SimpleDateFormat(expectedFormat);
		   //get current date time with Calendar()
		   //Calendar cal = Calendar.getInstance();
		   return dateFormat.format(date);		
		
	}
    
    
	
	public String getFilePath(String fileName ,DownloadLocation location){
		String filepath = "C:\\Users\\" + System.getProperty("user.name") + "\\"+location.getValue()+"\\"+ fileName;
		
		return filepath;
	}

	public static void updateCSV(UserDTO m_user, String filepath) {
		
		//Code to update csv file.
	}
	

	/**
	 * Temporary solution to set download path in chrome till bug in WebDriver is resolved (https://code.google.com/p/chromedriver/issues/detail?id=783)
	 * This function will set the download path to unifierSelenium\\projects\\unifier10 + Path sent in the parameter
	 * 
	 * @param path 
	 * 
	 * example:GenericHelper.setChromeDriverDownloadPath("\\TestData\\PlanningManager\\");
	 */
	public static void setChromeDriverDownloadPath(String path)
	{
		ApplicationProperties appProperties = ApplicationProperties.getInstance();
		WebDriver m_driver = null;
	try{
		
		m_driver = ApplicationProperties.getInstance().getDriver();
		m_driver.get("chrome://settings/advanced");
        JavascriptExecutor js = (JavascriptExecutor) m_driver;
        String prefId = "download.default_directory";
        File tempDir=new File(System.getProperty("user.dir")+path);
        System.out.println(tempDir);
        if (m_driver.findElements(By.xpath(String.format(".//input[@pref='%s']", prefId))).size() == 0) {
        	m_driver.get("chrome://settings-frame");
        	m_driver.findElement(By.xpath(".//a[@id='advanced-settings-expander']")).click();        }
        String tmpDirEscapedPath = tempDir.getCanonicalPath().replace("\\", "\\\\");
        js.executeScript(String.format("Preferences.setStringPref('%s', '%s', true)", prefId,
                tmpDirEscapedPath));
        m_driver.findElement(By.cssSelector("input[pref='safebrowsing.enabled']")).click();
		}
		catch(IOException e){
			
		}
	
		m_driver.get(appProperties.getAppUrl());
	
	}
}

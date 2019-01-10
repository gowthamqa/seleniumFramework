package com.bento.tests;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.bento.common.utils.ApplicationProperties;
import com.bento.common.utils.AutomationUtils;
import com.bento.common.utils.ExceptionTracker;


public abstract class CommonBaseTestCase extends RetryTest implements TestCase,ExceptionTracker{
    List<Throwable> m_exceptions = new ArrayList<>();

    protected static final Logger logger = LogManager.getLogger(CommonBaseTestCase.class);
    protected boolean m_loggedIn = false;

    public String m_sessionId;

    /** WebDriver instance for the current browser */
    protected WebDriver m_driver;

    @Override
    public void clearExceptions() {
		m_exceptions.clear();
	}
    
	@Override
	public void addException(Throwable e) {
		m_exceptions.add(e);
	}

	@Override
	public Collection<Throwable> getExceptions() {
		return m_exceptions;
	}

	
	/**
	 * Can Override to adjust what testcase runs prior to login
	 */
	@Override
	public void presetup() {
		System.out.println("Hello");
	}
	
	/**
	 * Can Override to adjust what testcase runs post login.
	 * @throws Exception 
	 */
	@Override
	public void postSetup() throws Exception {

	}

	/**
	 * Can Override to create data needed for current testcase
	 */
	@Override
	public void dataSetup() {

	}

    @Rule
    public TestRule watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            logger.info("Started Running Test {} ...", description.getMethodName());
        }

        @Override
        public void finished(Description desc) {
        	logger.info("Finishing Test case in finished() method");
			boolean fail = false;
			StringBuffer buff = new StringBuffer();
			try{
			// Close browser
			if (ApplicationProperties.getInstance().getCloseBrowserOnExit()) {
				ApplicationProperties.getInstance().getDriver().quit();
				m_loggedIn = false;
			}
			}
			catch(UnreachableBrowserException e){
				logger.info("UnreachableBrowserException while trying to quit the browser, perhaps the test might have failed and browsers were killed in failed() method to avoid hang issue");
			}
			catch(Throwable e){
				logger.info("Exception while Executing driver.quit() method"+e.getLocalizedMessage());
				e.printStackTrace();
			}
			// Print list of exceptions
			Collection<Throwable> ex = getExceptions();
			if (!ex.isEmpty()) {
				for (Throwable e : ex) {
					buff.append("\n" + e.getMessage());
					logger.error(e.getMessage(), e);
				}
				fail = true;
			}
			logger.info("===================== Finished Running Test {} ...", desc.getMethodName());

			if (fail) {
				Assert.fail("Test Case " + desc.getMethodName() + " failed due to: " + buff.toString() + "\nsee log for details.");
			}
			
			
        }

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            final String screenshotPath = ApplicationProperties.getInstance().getScreenshotPath();
            if (screenshotPath != null) {
                /*  try {
                                   final Timer timer = new Timer();
                                          final TimerTask task = new TimerTask() {
                                              @Override
                                              public void run() {
                                                  try {
                                                      Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
                                                  } catch (final IOException e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          };
                                          timer.schedule(task, 150000);*/
                final File scrFile = ((TakesScreenshot) m_driver).getScreenshotAs(OutputType.FILE);
                final String[] className = description.getClassName().split("\\.");
                final File destFile = new File(screenshotPath, className[className.length - 1] + "_" + description.getMethodName() + "_" + getDateTime() + "_Fail"
                        + ".png");
                try {
                    FileUtils.copyFile(scrFile, destFile);
                } catch (final IOException e1) {
                    logger.error("Unable to save screenshot to {}", screenshotPath, e);
                }
                /*                } catch (final UnreachableBrowserException exc) {
                                    logger.error("Unable to take screenshot");
                                }
                */}
        }

        private String getDateTime() {
            final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            final Date date = new Date();
            return dateFormat.format(date);
        }
        
    };
    
	/**
	 * @deprecated avoid using sleep as much as possible, improve the
	 *             waitForReady() method for your page
	 * @param seconds
	 *            number of seconds to sleep as a double value eg. 0.5.
	 */
	public void sleep(final Double seconds) {
		AutomationUtils.sleep(seconds);
	}

	/**
	 * @deprecated avoid using sleep as much as possible, improve the
	 *             waitForReady() method for your page
	 * @param seconds
	 *            number of seconds to sleep as integer.
	 */
	public void sleep(final int seconds) {
		AutomationUtils.sleep(seconds);
	}
	
	/**
	 * Close all instance of browser
	 * 	  
	 */	
	public static void killBrowserProcess() {
		
		try{
			
			String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
			
			logger.info("About to clean browsers - kill any existing browsers");
			
			//Kill ALL instances of iexplorer.exe in Task Manager
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
			
			}
			
		}catch(Exception e){
			logger.info("Exception occured while deleting open browser tasks "+e.getLocalizedMessage());
		}
	}
	
	

	/**
	 * Close all instance of browsers and Webdriver Process on Remote Machine (Selenium Node in Selenium Grid)
	 * 	  
	 */	
	public static void killBrowserAndWebDriverProcessOnRemoteNode(String nodeName, String nodeUserName, String nodePassword) {
		
		try{
			
			String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
			
			logger.info("About to clean browsers - kill any existing browsers on Remote Node " +nodeName);
			Runtime.getRuntime().exec("taskkill /s "+nodeName+" /u "+nodeUserName+" /p "+nodePassword+" /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /s "+nodeName+" /u "+nodeUserName+" /p "+nodePassword+" /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /s "+nodeName+" /u "+nodeUserName+" /p "+nodePassword+" /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /s "+nodeName+" /u "+nodeUserName+" /p "+nodePassword+" /F /IM safari.exe");
			logger.info("About to clean WebDriver process - kill any existing IEDriverServer.exe process on Remote Node " +nodeName);
			Runtime.getRuntime().exec("taskkill /s "+nodeName+" /u "+nodeUserName+" /p "+nodePassword+" /F /IM IEDriverServer.exe");
			
			}
			
		}catch(Exception e){
			logger.info("Exception occured while deleting open browser tasks "+e.getLocalizedMessage());
		}
		
	}
}

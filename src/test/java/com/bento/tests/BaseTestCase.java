package com.bento.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.bento.common.utils.ApplicationProperties;

/**
 * Testcases will extend this class to prevent the need to add @Before and @After to each test.
 *
 */
public abstract class BaseTestCase extends CommonBaseTestCase {
	protected static final Logger logger = LogManager.getLogger(BaseTestCase.class);
	public ApplicationProperties appProperties =   ApplicationProperties.getInstance();

    protected void starting() {
        m_driver = appProperties.createAndGetDriver();
        appProperties.setTimeouts(3000, TimeUnit.SECONDS);
        // Navigate to the right place
        //m_driver.get(appProperties.getAppUrl());
    }
    @Override
	@BeforeClass
	public void setUp() {
    	
    	starting();
		/*if (System.getProperty("os.name").toLowerCase().contains("win")) {
			try {
				int chromeDriverCounter = 0;
				String line;
				final Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
				final BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = input.readLine()) != null) {
					if (line.contains("chromedriver.exe")) {
						chromeDriverCounter++;
					}
				}
				input.close();
				if (chromeDriverCounter > 1) {
					Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
					// Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
					//Assert.fail("------More then one chrome driver exists, exactly " + chromeDriverCounter + " exist, killing all chromedriver.exe------");
				}
			} catch (final Exception err) {
				err.printStackTrace();
			}
		}*/
		//BaseState.login();
		//presetup();
	}
	
	/**
	 * After test clean up
	 */
	@Override
	@AfterClass
	public void tearDown() {
		m_driver.close();

	}
	
	


}


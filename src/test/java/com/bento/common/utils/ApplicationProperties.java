package com.bento.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.bento.common.enums.BrowserName;

public class ApplicationProperties {

    private final static int DEFAULT_COMP_LOAD_TIMEOUT = 7;
    private int m_pageFactoryCompLoadTime = DEFAULT_COMP_LOAD_TIMEOUT;

    final static String BROWSER = "browser";
    final static String WAIT_TIME = "waitTime";
    final static String CHROME_DRIVER_PATH = "chromeDriverPath";
    final static String IE_DRIVER_PATH = "ieDriverPath";
    final static String GRID_ENABLED = "gridEnabled";
    final static String GRID_HUB = "gridHub";
    final static String CLOSE_BROWSER_ON_EXIT = "closeBrowserOnExit";
    final static String SCREENSHOT_PATH = "screenshotPath";
    final static String DATA_FILE_PATH = "datafile";


    private static ApplicationProperties s_uniqueInstance;
    private static Map<Long, ApplicationProperties> s_instanceMap = Collections.synchronizedMap(new HashMap<Long, ApplicationProperties>());

    String m_app_url;
    String m_app_username;
    String m_app_password;
    String m_admin_url;
    String m_admin_username;
    String m_admin_password;
    String m_username;
    String m_password;
    String m_chromePath;
    String m_chromeDriverPath;
    String m_ieDriverPath;
    String m_firefoxDriverPath;
    String m_screenshotPath;
    String datafilePath;

	String m_adminUrl;
    String m_adminUser;
    String m_adminPass;
    String m_downloadFileTemplate;
    String m_fileUploadString ;
    
    int m_waitTime;
    boolean m_gridEnabled;
    boolean m_closeBrowserOnExit;
    String m_gridHub;
    BrowserName m_browser;
    String m_mockUiPath;
    WebDriver m_driver;
    Properties m_properties = new Properties();

    boolean m_continueOnMissingEvents;

    boolean m_webpageTestEnabled;
    String m_webpageTestUrl;
    String m_webpageTestLoc;

    public ApplicationProperties() {

        try {
            m_properties.load(getPropertiesFile());

            m_app_url = getSetting("app.url","https://localhost:448/opencart");
            m_admin_url= getSetting("admin.url","https://localhost:448/opencart/admin");
            m_admin_username = getSetting("admin.user","user");
            m_admin_password = getSetting("admin.password","user123");
            m_app_username = getSetting("app.user","");
            m_app_password = getSetting("app.password","");
            m_chromeDriverPath = getSetting(CHROME_DRIVER_PATH, null);
            m_ieDriverPath = getSetting(IE_DRIVER_PATH, null);
            m_firefoxDriverPath = getSetting("firefoxDriverPath","");
            m_waitTime = Integer.parseInt(getSetting(WAIT_TIME, String.valueOf(DEFAULT_COMP_LOAD_TIMEOUT)));
            m_gridEnabled = Boolean.parseBoolean(getSetting(GRID_ENABLED, "false"));
            m_gridHub = getSetting(GRID_HUB, null);
            m_browser = BrowserName.fromString(getSetting(BROWSER, "chrome"));
            m_closeBrowserOnExit = Boolean.parseBoolean(getSetting(CLOSE_BROWSER_ON_EXIT, "false"));
            m_screenshotPath = createScreenshotPath();
            datafilePath = getSetting("datafile","C:\\Users\\gowthamm\\eclipse-workspace\\seleniumproject\\productdata.xls");
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + "application properties" + " from classpath", e);
        }

    }

    InputStream getPropertiesFile() {
        File file = new File("./src/test/java/application.properties");
        FileInputStream propFile = null;
		try {
			propFile = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return propFile;
    }


    /**
     * First attempt to read the setting from system property. if not found, read from properties file, and both attempts fail, use default
     *
     * @param settingName
     * @param defaultValue
     *
     */
    public String getSetting(String settingName, String defaultValue) {
        String value = System.getProperty(settingName);
        if (value == null) {
            value = m_properties.getProperty(settingName, defaultValue);
        }

        return value;
    }

    String createScreenshotPath() {
        m_screenshotPath = getSetting(SCREENSHOT_PATH, null);

        if (m_screenshotPath != null) {
            File path = new File(m_screenshotPath);
            if (path.exists() && !path.isDirectory()) {
                throw new RuntimeException("Screenshot path " + m_screenshotPath + "must be folder");
            }
            path.mkdirs();
        }

        return m_screenshotPath;
    }

    public String getScreenshotPath() {
        return m_screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        m_screenshotPath = screenshotPath;
    }

    public void setAppUrl(String url) {
        m_app_url = url;
    }

    public void setUsername(String username) {
        m_username = username;
    }

    public void setPassword(String password) {
        m_password = password;
    }

    public void setadminUrl(String url) {
        m_admin_url = url;
    }

    public void setAdminUser(String username) {
        m_admin_username = username;
    }

    public void setAdminPass(String password) {
        m_admin_password = password;
    }

    public Properties getProperties() {
        return m_properties;
    }
    

    public String getDatafilePath() {
		return datafilePath;
	}

	public void setDatafilePath(String datafilePath) {
		this.datafilePath = datafilePath;
	}

    public WebDriver createAndGetDriver() {
        if (m_gridEnabled) {
            setGridDriver();
        } else {
            setLocalDriver();
        }

        // Set Timeouts
        setTimeouts();

        return m_driver;
    }

    public void setTimeouts(int waitTime, TimeUnit unit) {
        m_driver.manage().timeouts().implicitlyWait(waitTime, unit);
        m_driver.manage().timeouts().setScriptTimeout(waitTime, unit);
    }

    public void disableWaitTime() {
        setTimeouts(50, TimeUnit.MILLISECONDS);
    }

    public void setTimeouts() {
        setTimeouts(m_waitTime, TimeUnit.SECONDS);
    }

    /**
     * Get a static instance of Application Properties. We only want to read the properties file once per run.
     *
     * @return instance of this class.
     */
    public synchronized static ApplicationProperties getInstance() {
        return getInstance(false);
    }

    public synchronized static ApplicationProperties getInstance(boolean forceNew) {
        long threadId = Thread.currentThread().getId();
        if (forceNew || s_instanceMap.get(threadId) == null) {
            s_uniqueInstance = new ApplicationProperties();
            s_instanceMap.put(threadId, s_uniqueInstance);
        }
        return s_instanceMap.get(threadId);
    }

    /**
     * Set driver to be used by selenium grid. This method would be called when selenium grid is being used.
     */
    private void setGridDriver() {
        DesiredCapabilities capability;

        switch (m_browser) {
        case HTMLUNIT:
            capability = DesiredCapabilities.htmlUnit();
            capability.setJavascriptEnabled(true);
            break;
        case FIREFOX:
            capability = DesiredCapabilities.firefox();
            m_fileUploadString = "File Upload";
            break;
        case CHROME:
            capability = DesiredCapabilities.chrome();
            m_fileUploadString= "Open";
            break;
        case INTERNET_EXPLORER:
            capability = DesiredCapabilities.internetExplorer();
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            m_fileUploadString = "Open";
            break;
        case SAFARI:
            capability = DesiredCapabilities.safari();
            break;
        default:
            capability = DesiredCapabilities.chrome();
        }

        try {
            m_driver = new RemoteWebDriver(new URL(m_gridHub), capability);

            if (!m_browser.equals(BrowserName.HTMLUNIT)) {
                m_driver.manage().window().maximize();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets local driver
     */
    private void setLocalDriver() {
        DesiredCapabilities capabilities;

        switch (m_browser) {
       
        case FIREFOX:
            System.setProperty("webdriver.gecko.driver", m_firefoxDriverPath);
            m_driver = new FirefoxDriver();
            m_driver.manage().window().maximize();
            break;
        case INTERNET_EXPLORER:
            if (m_ieDriverPath != null) {
                System.setProperty("webdriver.ie.driver", m_ieDriverPath);
            }
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            m_driver = new InternetExplorerDriver();
            m_driver.manage().window().maximize();
            break;
        case SAFARI:
            m_driver = new SafariDriver();
            m_driver.manage().window().maximize();
            break;
        default:
          
            if (m_chromeDriverPath != null && m_chromeDriverPath.length() > 0) {
                System.setProperty("webdriver.chrome.driver", m_chromeDriverPath);
            }
            m_driver = new ChromeDriver();
            m_driver.manage().window().maximize();
        }
        
        m_driver.manage().timeouts().implicitlyWait(getWaitTime(), TimeUnit.SECONDS);
    }

    public String getAppUrl() {
        return m_app_url;
    }

    public String getAdminUser() {
        return m_admin_username;
    }

    public String getAdminPass() {
        return m_admin_password;
    }

    public WebDriver getDriver() {
        return m_driver;
    }

    public int getWaitTime() {
        return m_waitTime;
    }

    public void setWaitTime(int waitTime) {
        m_waitTime = waitTime;
    }

    public boolean getGridEnabled() {
        return m_gridEnabled;
    }

    public boolean getCloseBrowserOnExit() {
        return m_closeBrowserOnExit;
    }

    public String getGridhub() {
        return m_gridHub;
    }

    public int getPageFactoryCompLoadTime() {
        return m_pageFactoryCompLoadTime;
    }

    public void setPageFactoryCompLoadTime(int pageFactoryCompLoadTime) {
        m_pageFactoryCompLoadTime = pageFactoryCompLoadTime;
    }

    public void setPageFactoryCompLoadTime() {
        m_pageFactoryCompLoadTime = DEFAULT_COMP_LOAD_TIMEOUT;
    }

 

 
	public String getAdminUrl() {
		
		return m_admin_url;
	}
}
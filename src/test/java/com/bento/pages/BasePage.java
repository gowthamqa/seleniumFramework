package com.bento.pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.bento.common.By;
import com.bento.common.objects.BaseElement;
import com.bento.common.utils.ApplicationProperties;
import com.bento.common.utils.AutomationUtils;
import com.google.common.base.Predicate;

import org.junit.Assert;

public class BasePage {

    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    protected WebDriver m_driver = null;
    protected JavascriptExecutor m_jsDriver =null;

    
    public  BasePage() {
    	ApplicationProperties app  = ApplicationProperties.getInstance();
    	m_driver = app.getDriver();
    	
    }
    /**
     *
     * @param seconds
     *            number of seconds to sleep as a double value eg. 0.5.
     */
    public void sleep(final Double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param seconds
     *            number of seconds to sleep as integer.
     */
    public void sleep(final int seconds) {
        sleep(new Double(seconds));
    }

    public BasePage waitForReady() {
        return waitForReady(ApplicationProperties.getInstance().getWaitTime());
    }

    public BasePage waitForReady(int secondsToSleep) {
        FluentWait<BasePage> wait = new FluentWait<BasePage>(this);
        wait = wait.withTimeout(secondsToSleep, TimeUnit.SECONDS);
        wait = wait.pollingEvery(100, TimeUnit.MILLISECONDS);

        /*wait.withMessage("waitForReady timed out. Page not ready.  (Did the page developer remove the call to require(['selenium:loaded'])?  Is the page legitimately not loaded yet?)").until(new Predicate<BasePage>() {
            @Override
            public boolean apply(BasePage element) {
                return AutomationUtils.isPageReady();
            }
        });*/

        LOGGER.info("waitForReady completed - page is loaded.");

        return this;
    }

    public WebElement waitForElement(org.openqa.selenium.By by) {
        FluentWait<org.openqa.selenium.By> wait = new FluentWait<org.openqa.selenium.By>(by);

        final long secondsToSleep = ApplicationProperties.getInstance().getWaitTime();
        wait = wait.withTimeout(secondsToSleep, TimeUnit.SECONDS);
        wait = wait.pollingEvery(100, TimeUnit.MILLISECONDS);
/*
        wait.withMessage("waitForReady timed out. Element " + by.toString() + " not present")
        .until(new Predicate<org.openqa.selenium.By>() {
            @Override
            public boolean apply(org.openqa.selenium.By by) {
                try {
                    m_driver.findElement(by);
                    return true;
                } catch (final NoSuchElementException e) {
                    LOGGER.debug("Waiting for element {}, which is not loaded yet", by);
                    return false;
                }
            }
        });*/

        return m_driver.findElement(by);
    }

    /**
     * method waits for a particular element to appear on the page
     *
     *
     *            - search by
     *
     *            - time to wait before throwing an error
     */
    public void waitForElement(BaseElement element) {
        waitForElement(element, ApplicationProperties.getInstance().getWaitTime());
    }

    /**
     * method waits for a particular element to appear on the page
     *
     *
     *            - search by
     * @param secondsToWait
     *            - time to wait before throwing an error
     */
    public void waitForElement(BaseElement element, long secondsToWait) {
        FluentWait<BaseElement> wait = new FluentWait<BaseElement>(element);
        wait = wait.withTimeout(secondsToWait, TimeUnit.SECONDS);
        wait = wait.pollingEvery(100, TimeUnit.MILLISECONDS);

/*
        wait.withMessage("waitForReady timed out. Element " + element.toString() + " is still not present")
        .until(new Predicate<BaseElement>() {
            @Override
            public boolean apply(BaseElement element) {
                try {
                    if (element.isDisplayed()) {
                        return true;
                    }
                } catch (final NoSuchElementException e) {
                    return false;
                }
                return true;
            }
        });*/
    }

    /**
     * method waits for a particular element to disappear from the page
     *
     * @param by
     */
    public void waitForElementToDisappear(org.openqa.selenium.By by) {
        waitForElementToDisappear(by, ApplicationProperties.getInstance().getWaitTime());
    }

    /**
     * method waits for a particular element to disappear from the page
     *
     * @param by
     *            - search by
     * @param secondsToWait
     *            - time to wait before throwing an error
     */
    public void waitForElementToDisappear(org.openqa.selenium.By by, long secondsToWait) {
        final FluentWait<org.openqa.selenium.By> wait = new FluentWait<org.openqa.selenium.By>(by);

        /*wait.withTimeout(secondsToWait, TimeUnit.SECONDS).withMessage("waitForReady timed out. Element " + by.toString() + " is still present")
        .until(new Predicate<org.openqa.selenium.By>() {
            @Override
            public boolean apply(org.openqa.selenium.By by) {
                ApplicationProperties.getInstance().disableWaitTime();
                try {
                    if (!m_driver.findElement(by).isDisplayed()) {
                        return true;
                    }
                } catch (final NoSuchElementException e) {
                    return true;
                } catch (final StaleElementReferenceException ef) {
                    return true;
                } finally {
                    ApplicationProperties.getInstance().setTimeouts();
                }
                return false;
            }
        });*/
    }

    /**
     * method waits for a particular element to disappear from the page
     *
     *
     *            - search by
     *
     *            - time to wait before throwing an error
     */
    public void waitForElementToDisappear(BaseElement element) {
        waitForElementToDisappear(element, ApplicationProperties.getInstance().getWaitTime());
    }

    /**
     * method waits for a particular element to disappear from the page
     *
     *
     *            - search by
     * @param secondsToWait
     *            - time to wait before throwing an error
     */
    public void waitForElementToDisappear(BaseElement element, long secondsToWait) {
        final FluentWait<BaseElement> wait = new FluentWait<BaseElement>(element);

        /*wait.withTimeout(secondsToWait, TimeUnit.SECONDS).withMessage("waitForReady timed out. Element " + element.toString() + " is still present")
        .until(new Predicate<BaseElement>() {
            @Override
            public boolean apply(BaseElement element) {
                try {
                    if (!element.isDisplayed()) {
                        return true;
                    }
                } catch (final NoSuchElementException e) {
                    return true;
                }
                return false;
            }
        });*/
    }

    /**
     * Disables timeout and checks if an element is present on page. returns
     * true if it is, false otherwise.
     *
     * @param by
     *
     */
    public boolean isElementPresent(org.openqa.selenium.By by) {
        try {
            ApplicationProperties.getInstance().disableWaitTime();
            return m_driver.findElement(by).isDisplayed();
        } catch (final NoSuchElementException e) {
            return false;
        } finally {
            ApplicationProperties.getInstance().setTimeouts();
        }
    }

    public boolean isElementPresent(com.bento.common.By by) {
        try {
            ApplicationProperties.getInstance().disableWaitTime();
            return m_driver.findElement(by).isDisplayed();
        } catch (final NoSuchElementException e) {
            return false;
        } finally {
            ApplicationProperties.getInstance().setTimeouts();
        }
    }

    /**
     * Sets timeout and checks if an element is present on page. returns true if
     * it is, false otherwise.
     *
     * @param by
     *
     */
    public boolean isElementPresent(org.openqa.selenium.By by, int waitTime) {
        try {
            ApplicationProperties.getInstance().setTimeouts(waitTime, TimeUnit.SECONDS);
            return m_driver.findElement(by).isDisplayed();
        } catch (final NoSuchElementException e) {
            return false;
        } finally {
            ApplicationProperties.getInstance().setTimeouts();
        }
    }

    /**
     * Disables timeout and checks if an element is present on page. returns
     * true if it is, false otherwise.
     *
     *
     *
     */
    public boolean isElementPresent(BaseElement element) {
        try {
            ApplicationProperties.getInstance().disableWaitTime();
            ApplicationProperties.getInstance().setPageFactoryCompLoadTime(0);
            return element.isDisplayed();
        } catch (final NoSuchElementException e) {
            return false;
        } finally {
            ApplicationProperties.getInstance().setTimeouts();
            ApplicationProperties.getInstance().setPageFactoryCompLoadTime();
        }
    }
    
    /**
     * 
     * @param strWinObject
     * 						- Button, Link and Text as string
     * @param locator
     * 						- locators
     * @return
     * 						- element of specific object
     */
    public WebElement selectDynamicObject(String strWinObject,String locator)
    {
    	WebElement elem=null;
    	try{
    	String locators = null;
    	
    	
    	String partialString=locator;
    	
		switch(strWinObject)
    	{
    		case "link":
    			locators="//a[contains(text(),"+"'"+partialString+"')"+"]";
    			break;  			
    		
    		case "button":
    			locators="//button[contains(text(),"+"'"+partialString+"')"+"]";
    			break;
    			
    		case "text":
    			locators="//div[contains(text(),"+"'"+partialString+"')"+"]";
    			break;
    			
    		case "title":
    			locators="//li[contains(@title,'"+ partialString +"')]/a";
    			break;	
    	}
    	
		elem =m_driver.findElement(By.xpath(locators));
		if(( !elem.isDisplayed() && elem.getText()!=null))
			elem=m_driver.findElement(By.xpath("//div[@aria-hidden='false']"+locators));
    	
    } catch (NoSuchElementException e) {
    	Assert.fail(locator + "  element not found");
    }
    	return elem;
    	
    }
   	
}

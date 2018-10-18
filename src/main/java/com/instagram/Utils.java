package com.instagram;

import java.util.List;
import java.util.Random;
import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    /**
     * Scrolling window down
     *
     * @param driver
     * @throws InterruptedException
     */
    public static void scrollWindow(final WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);
    }

    /**
     * Scrolling window down
     *
     * @param driver
     * @param pixels
     * @throws InterruptedException
     */
    public static void scrollWindow(final WebDriver driver, int pixels) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + pixels + ")", "");
        Thread.sleep(1000);
    }

    /**
     * Scrolling window down
     *
     * @param driver
     * @throws InterruptedException
     */
    public static void scrollElement(final WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(1000);
    }

    /**
     * Sleep for random seconds
     */
    public static void sleep() {
        int secs = getRandom(3, 8);
        long expected = System.currentTimeMillis() / 1000 + secs;
        while (true) {
            long loopTime = System.currentTimeMillis() / 1000;
            if (expected < loopTime) {
                break;
            }
        }
    }

    /**
     * Sleep for input minutes
     * @param minutes
     */
    public static void sleep(int minutes) {
        int secs = minutes*60;
        long expected = System.currentTimeMillis() / 1000 + secs;
        while (true) {
            long loopTime = System.currentTimeMillis() / 1000;
            if (expected < loopTime) {
                break;
            }
        }
    }

    public static int getRandom(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }
    
    
    public static void appendText(JTextArea textArea, String str) {
        String currentText = textArea.getText();
        if(currentText.isEmpty()){
            textArea.setText(str);
        } else {
            textArea.setText(currentText + "\r\n" + str);
        }       
        textArea.update(textArea.getGraphics());
    }

    /**
     * Scrolling given panel up
     *
     * @param driver
     * @param locator
     * @throws InterruptedException
     */
    public static void scrollUp(final WebDriver driver, By locator) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        WebElement el = driver.findElement(locator);
        jse.executeScript("arguments[0].scrollBy(0, -300);", el);
        Thread.sleep(3000);
    }

    /**
     * Scrolling given panel down
     *
     * @param driver
     * @param el
     * @throws InterruptedException
     */
    public static void scrollDown(final WebDriver driver, WebElement el) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollBy(0, 500);", el);
        Thread.sleep(3000);
    }

    public static void scrollIntoView(final WebDriver driver, WebElement el) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", el);
        Thread.sleep(3000);
    }

    /**
     * Custom fluent wait
     *
     * @param locator
     * @param driver
     * @param timeout
     * @param polling
     * @return
     * @throws InterruptedException
     */
    public static WebElement fluentWait(final By locator, final WebDriver driver, final int timeout, final int polling)
            throws InterruptedException {
        int ii = polling;
        while (ii < timeout) {
            List<WebElement> allEle = driver.findElements(locator);
            if (allEle.size() > 0) {
                return allEle.get(0);
            }
            Thread.sleep(polling * 1000);
            ii += polling;
        }
        return null;
    }

    public static WebElement scrollToFindElement(final WebDriver driver, final By locator) throws Exception {
        int ii = 0;
        while (ii < 10) {
            List<WebElement> allEle = driver.findElements(locator);
            if (allEle.size() > 0) {
                return allEle.get(0);
            }
            scrollWindow(driver);
            ii++;
        }
        return null;
    }
}

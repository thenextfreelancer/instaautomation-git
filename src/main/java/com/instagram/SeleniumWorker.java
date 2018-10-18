/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instagram;

import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author amishra
 */
public class SeleniumWorker {

    private static SeleniumWorker worker;

    private WebDriver driver;

    private final AppConfig config;

    private final String INSTAGRAM_BASE_URL = "https://www.instagram.com";

    @FindBy(name = "username")
    WebElement userNameBox;

    @FindBy(name = "password")
    WebElement passwordBox;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElement loginButton;

    @FindBy(xpath = "//button[contains(text(),'Not Now')]")
    WebElement notificationSelectionButton;
    
    @FindBy(linkText = "Not Now")
    WebElement mobileAppPageCancelButton;

    @FindBy(xpath = "//span[contains(text(),'Search')]")
    WebElement searchTextBoxReadOnly;

    @FindBy(xpath = "//span[@placeholder='Search']")
    WebElement searchTextBox;

    @FindBy(xpath = "//button[contains(text(),'Follow')]")
    WebElement followButton;

    @FindBy(xpath = "//button[contains(text(),'Following')]")
    WebElement followingButton;

    @FindBy(xpath = "//button[contains(text(),'Requested')]")
    WebElement requestedButton;

    @FindBy(xpath = "//button[contains(text(),'Unfollow')]")
    WebElement unfollowButton;

    @FindBy(partialLinkText = "followers")
    WebElement followersLink;

    @FindBy(css = "div[role='dialog']")
    WebElement popup;

    SeleniumWorker(AppConfig config) {
        this.config = config;
    }

    public static SeleniumWorker getInstance(AppConfig config) {
        if (worker == null) {
            worker = new SeleniumWorker(config);
        }
        return worker;
    }

    public void init() {
        this.driver = new ChromeDriver();
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize(); // Maximizes
        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);// implicit
    }

    /**
     * Logging in humanly into Instagram Website
     */
    public void login() {
        init();
        driver.get(INSTAGRAM_BASE_URL + "/accounts/login/");
        Utils.sleep();
        userNameBox.sendKeys(config.getUserId());
        Utils.sleep();
        passwordBox.sendKeys(config.getPassword());
        Utils.sleep();
        loginButton.click();
        Utils.sleep();
        try{
            mobileAppPageCancelButton.click();
        } catch(Exception e){
            //ignore
        }
        Utils.sleep();
        notificationSelectionButton.click();
    }

    public void searchFollowersForInfluenceList(Set<String> influencers, int noOfUsersToCrawl, JTextArea textArea, JTextArea loggingTextArea) throws Exception {
        for (String influencer : influencers) {
            searchFollowers(influencer, noOfUsersToCrawl,textArea, loggingTextArea);
        }
    }

    /**
     * The login method must be called before calling this method
     *
     * @param influencerUrl
     * @return String[]
     * @throws Exception
     */
    private void searchFollowers(String influencerUrl, int noOfFollowers, JTextArea textArea, JTextArea loggingTextArea) throws Exception {
        try {
            //Go to influencer's profile
            Utils.sleep();
            driver.get(influencerUrl);
            Utils.sleep();

            try{
                followersLink.click();
            } catch(Exception e){
                Utils.appendText(loggingTextArea, e.getMessage());
            }
            
            Utils.sleep();
            List<WebElement> followersList = getListFromPopup();
            int count = 0;
            while (count < noOfFollowers) {
                WebElement followerListItem = followersList.get(count);
                String profileLink = followerListItem.findElement(By.xpath("div/div[1]/div[2]/div[1]/a")).getAttribute("href");
                Utils.appendText(textArea, profileLink);
                count++;

                //scrolling followers window
                if (count % 6 == 0) {
                    followersList = scroll();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            scroll();
        } catch (Exception e) {
            Utils.appendText(loggingTextArea, e.getMessage());
        }
    }

    /**
     * Make scroll of given window
     *
     * @return
     * @throws InterruptedException
     */
    private List<WebElement> scroll() throws InterruptedException {
        WebElement scrollingContainer = popup.findElement(By.xpath("div[2]"));
        Utils.scrollDown(driver, scrollingContainer);
        Utils.sleep();
        return getListFromPopup();
    }

    /**
     * Method to follow/unfollow a user list
     *
     * @param userUrl
     * @param action
     */
    public void followUnfollowUsers(String userUrl, String action, JTextArea loggingTextArea) {
        Utils.sleep();
        driver.get(userUrl);
        try {
            if ("follow".equalsIgnoreCase(action)) {
                Utils.sleep();
                followButton.click();
                Utils.sleep();
            } else {
                try {
                    followingButton.click();
                } catch (Exception e) {
                    //This means "Following" button not found which means "Requested" button should be there
                    requestedButton.click();
                }
                Utils.sleep();
                unfollowButton.click();
                Utils.sleep();
            }
        } catch (Exception e) {
            Utils.appendText(loggingTextArea, e.getMessage());
        }

    }

    /**
     * Can we used to get list of followers and followings after clicking on
     * each link.
     *
     * @return
     */
    private List<WebElement> getListFromPopup() {
        return popup.findElements(By.tagName("li"));
    }

    public void closeDriver() {
        driver.quit();
    }
}

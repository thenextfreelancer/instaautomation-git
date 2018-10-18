package com.instagram;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

public class SeleniumWorkerTest {

    /**
     * For testing purpose
     *
     * @param args
     * @throws Exception
     */
    @Test
    public void testSearchFollowers() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        AppConfig config = getTestAppConfig();
//        SeleniumWorker s = new SeleniumWorker(config);
//        s.login();
//        
//        int followersNo = config.getNoOfUsersEachInfluencer();
//        Set<String> followers = s.searchFollowersForInfluenceList(getUserProfileList(), followersNo);
//        
//        for (String follower : followers) {
//            System.out.println(follower);
//        }
    }

    /**
     * Follow-Unfollow must share same list
     */
    @Test
    public void testFollowUsers() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        AppConfig config = getTestAppConfig();
//        SeleniumWorker s = new SeleniumWorker(config);
//        s.login();
//        s.followUserList(getUserProfileList());
    }

    /**
     * Follow-Unfollow must share same list
     */
    @Test
    public void testUnFollowUsers() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        AppConfig config = getTestAppConfig();
//        SeleniumWorker s = new SeleniumWorker(config);
//        s.login();
//        s.unfollowUserList(getUserProfileList());
    }

    /**
     * For testing purpose
     *
     * @param args
     */
    private static AppConfig getTestAppConfig() {
        AppConfig conf = null;
        try {
            conf = new AppConfig();
            conf.setUserId("hagiografica@tkzumbsbottzmnr.ga");
            conf.setPassword("test1234");

            String influencers = "5";
            conf.setNoOfUsersEachInfluencer(influencers.isEmpty() ? 0 : Integer.parseInt(influencers));

            String w1 = "2";
            conf.setWaitMinutes1(w1.isEmpty() ? 0 : Integer.parseInt(w1));

            String w2 = "5";
            conf.setWaitMinutes2(w2.isEmpty() ? 0 : Integer.parseInt(w2));

            String f1 = "20";
            conf.setFollowCount1(f1.isEmpty() ? 0 : Integer.parseInt(f1));

            String f2 = "50";
            conf.setFollowCount2(f2.isEmpty() ? 0 : Integer.parseInt(f2));

            String unfollowDuration = "3";
            conf.setUnfollowAfterDays(unfollowDuration.isEmpty() ? 0 : Integer.parseInt(unfollowDuration));
        } catch (NumberFormatException e) {
            //ignore
        }
        return conf;
    }

    private Set<String> getUserProfileList() {
        Set<String> list = new HashSet<String>();
        list.add("https://www.instagram.com/awesomeboss797/");
        list.add("https://www.instagram.com/__ter_/");
        list.add("https://www.instagram.com/fernandinha_fernanda/");
        list.add("https://www.instagram.com/ali.junior.353803/");

        return list;
    }

    @Test
    public void testRandom() {
        AppConfig config = getTestAppConfig();
        System.out.println(Utils.getRandom(config.getFollowCount1(), config.getFollowCount2()));
    }
}

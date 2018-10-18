package com.instagram;

/**
 *
 * @author amishra
 */
public class AppConfig {
    
    private String userId;
    
    private String password;
    
    private int noOfUsersEachInfluencer;
    
    private int waitMinutes1;
    
    private int waitMinutes2;
    
    private int followCount1;
    
    private int followCount2;
    
    private int unfollowAfterDays;

   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public int getNoOfUsersEachInfluencer()
   {
      return noOfUsersEachInfluencer;
   }

   public void setNoOfUsersEachInfluencer(int noOfUsersEachInfluencer)
   {
      this.noOfUsersEachInfluencer = noOfUsersEachInfluencer;
   }

   public int getWaitMinutes1()
   {
      return waitMinutes1;
   }

   public void setWaitMinutes1(int waitMinutes1)
   {
      this.waitMinutes1 = waitMinutes1;
   }

   public int getWaitMinutes2()
   {
      return waitMinutes2;
   }

   public void setWaitMinutes2(int waitMinutes2)
   {
      this.waitMinutes2 = waitMinutes2;
   }

   public int getFollowCount1()
   {
      return followCount1;
   }

   public void setFollowCount1(int followCount1)
   {
      this.followCount1 = followCount1;
   }

   public int getFollowCount2()
   {
      return followCount2;
   }

   public void setFollowCount2(int followCount2)
   {
      this.followCount2 = followCount2;
   }

   public int getUnfollowAfterDays()
   {
      return unfollowAfterDays;
   }

   public void setUnfollowAfterDays(int unfollowAfterDays)
   {
      this.unfollowAfterDays = unfollowAfterDays;
   }
}

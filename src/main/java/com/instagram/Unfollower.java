/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instagram;

import javax.swing.JTextArea;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author amishra
 */
public class Unfollower implements Job {

    public static AppConfig config;
    
    public static JTextArea textArea;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        config = (AppConfig) dataMap.get("config");
        textArea = (JTextArea) dataMap.get("textArea");
        FileUtils f = new FileUtils();
        f.unfollow(config, textArea);
    }
}

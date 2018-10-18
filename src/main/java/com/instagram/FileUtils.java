/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author amishra
 */
public class FileUtils
{

   public final String BASE_LOCATION = System.getProperty("java.io.tmpdir") + "InstaAutomationDriver";

   public final String FOLLOWERS_FILE_NAME = "followers.txt";

   public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

   public void copyDriver() throws FileNotFoundException, IOException
   {
      ClassLoader classLoader = getClass().getClassLoader();
      URL resource = classLoader.getResource("chromedriver.exe");

      // No file separator because temp dir has it already
      File f = new File(BASE_LOCATION);
      if (!f.exists())
      {
         f.mkdirs();
      }
      File chromeDriver = new File(f.getAbsolutePath() + File.separator + "chromedriver.exe");
      if (!chromeDriver.exists())
      {
         chromeDriver.createNewFile();
         FileOutputStream output = null;
         InputStream input = null;
         try
         {
            output = new FileOutputStream(chromeDriver);
            input = resource.openStream();
            byte[] buffer = new byte[4096];
            int bytesRead = input.read(buffer);
            while (bytesRead != -1)
            {
               output.write(buffer, 0, bytesRead);
               bytesRead = input.read(buffer);
            }
         }
         catch (IOException e)
         {
            throw e;
         }
         finally
         {
            if (output != null)
            {
               output.close();
            }

            if (input != null)
            {
               input.close();
            }

         }
      }
      System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
   }

   public void writeFollowedInFile(String followedUserLink)
   {
      File f = new File(BASE_LOCATION);
      if (!f.exists())
      {
         f.mkdirs();
      }
      File output = new File(f.getAbsolutePath() + File.separator + FOLLOWERS_FILE_NAME);
      FileWriter fw = null;
      try
      {
         fw = new FileWriter(output, true); // the true will append the new data
         String currentDate = sdf.format(new Date());
         String log = currentDate + " " + followedUserLink + "\r\n";
         fw.write(log);
      }
      catch (IOException e)
      {

      }
      finally
      {
         if (fw != null)
         {
            try
            {
               fw.close();
            }
            catch (IOException ex)
            {
               Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      }
   }

   public void unfollow(final AppConfig config, final JTextArea loggingTextArea)
   {
      BufferedReader reader = null;
      try
      {
         int daysToUnfollow = config.getUnfollowAfterDays();
         File input = new File(BASE_LOCATION + File.separator + FOLLOWERS_FILE_NAME);
         reader = new BufferedReader(new FileReader(input));

         Calendar currentDate = Calendar.getInstance();
         currentDate.setTime(new Date());
         SeleniumWorker worker = new SeleniumWorker(config);
         worker.login();
         Calendar logDate = Calendar.getInstance();
         String readLine = null, date = null, url = null;
         while ((readLine = reader.readLine()) != null)
         {
            String[] data = readLine.split(" ");
            date = data[0];
            Date d = sdf.parse(date);

            // Setting the date to the given date
            logDate.setTime(d);

            // Number of Days to add
            logDate.add(Calendar.DAY_OF_MONTH, daysToUnfollow);

            int result = logDate.compareTo(currentDate);
            if (result < 0)
            {
               url = data[1];
               worker.followUnfollowUsers(url, "unfollow", loggingTextArea);
            }
         }
      }
      catch (IOException | ParseException e)
      {
         Utils.appendText(loggingTextArea, e.getMessage());
      }
      finally
      {
         try
         {
            reader.close();
         }
         catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
}

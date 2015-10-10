/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package Tests;

import ImageFinder.AkazeImageFinder;
import org.testng.Assert.*;
import org.apache.commons.io.FileUtils;

import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

public class SampleTest extends AppiumTestBase
{
    @BeforeClass
    public void waitAppStart() 
    {
        //wait for loading 
        System.out.println("BeforeClass Start");
        Wait(20000);
        System.out.println("BeforeClass End");
    }
    
    @Test
    public void testHomePageHeadline() throws IOException {

        AkazeImageFinder.QueryImage("Step3.png", "R2.png");
        if(true)
            return;
        System.out.println("TestStart");
        
        takeScreenshot("progress-1");
        Assert.assertTrue(TapImage("Step1.png"));
        Wait(2000);
        takeScreenshot("progress-2");
        
        Assert.assertTrue(TapImage("Step2.png"));
        takeScreenshot("progress-3");
        Wait(2000);
        
        Assert.assertTrue(TapImage("Step3.png"));
        takeScreenshot("progress-3");
        Wait(2000);
        
        System.out.println("TestDone");
    }

    public void Wait(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public File takeScreenshot(final String name) {

        System.out.println("ScreenShot: " + name);

        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
		
        try {
            FileUtils.copyFile(screenshot, new File(name + ".png"));
        } catch (IOException e) {
            System.out.println("cant take takeScreenshot : " + name);
        }

        return screenshot;
    }

    public int[] FindImage(String fileName)
    {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);        

        int[] region = AkazeImageFinder.QueryImage(fileName, screenshot.getAbsolutePath());
        return region;
    }
    
    public boolean TapImage(String fileName)
    {
        int[] region = FindImage(fileName);
        if(region == null)
               return false;
        
        int x = region[0];
        int y = region[1];

        io.appium.java_client.TouchAction touchAction = new io.appium.java_client.TouchAction(driver);
        touchAction.tap(x, y);
        driver.performTouchAction(touchAction);

        return true;
    }
}

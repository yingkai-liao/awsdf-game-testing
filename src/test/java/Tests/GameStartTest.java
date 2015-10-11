package Tests;

import org.testng.annotations.*;
import java.io.IOException;
import org.testng.Assert;

public class GameStartTest extends AppiumTestBase {

    @BeforeClass
    public void waitAppStart() {
        System.out.println("waitAppStart");
        //wait for loading 
        TestUtil.Wait(20000);
        Assert.assertTrue(TestUtil.WaitingForIamge("queryimages/Step1.png", 30));

        System.out.println("AppStart!");
    }

    @Test(groups = "StartGame")
    public void StartGameTest() throws IOException {

        System.out.println("StartGameTest");

        TestUtil.takeScreenshot("progress-1");
        Assert.assertTrue(TestUtil.TapImage("queryimages/Step1.png"));
        TestUtil.Wait(2000);
        TestUtil.takeScreenshot("progress-2");

        Assert.assertTrue(TestUtil.TapImage("queryimages/Step2.png"));
        TestUtil.takeScreenshot("progress-3");
        TestUtil.Wait(2000);

        Assert.assertTrue(TestUtil.TapImage("queryimages/Step3.png"));
        TestUtil.takeScreenshot("progress-3");
        TestUtil.Wait(2000);

        System.out.println("StartGameTest Pass");
    }
}

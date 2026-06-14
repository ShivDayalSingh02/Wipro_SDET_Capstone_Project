package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void captureScreenshot(
            WebDriver driver,
            String fileName) {

        try {

            File src =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE);

            String destinationPath =
                    System.getProperty("user.dir")
                    + "/screenshots/"
                    + fileName
                    + "_"
                    + System.currentTimeMillis()
                    + ".png";

            File destination =
                    new File(destinationPath);

            FileUtils.copyFile(
                    src,
                    destination);

            System.out.println(
                    "Screenshot Saved : "
                    + destinationPath);

        } catch(IOException e) {

            e.printStackTrace();

        } catch(Exception e) {

            System.out.println(
                    "Screenshot Capture Failed : "
                    + e.getMessage());
        }
    }
}
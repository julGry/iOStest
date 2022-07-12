import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class iOSTest {
        private AppiumDriver driver;

        private reminderApp page2;

        private DesiredCapabilities desiredCapabilities;

        @Before
        public void setUp() throws MalformedURLException {
            desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
            desiredCapabilities.setCapability("bundleId", "com.apple.reminders");
            desiredCapabilities.setCapability("autoAcceptAlerts", "true");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            URL remoteUrl = new URL("http://0.0.0.0:4723/wd/hub");

            driver = new AppiumDriver(remoteUrl, desiredCapabilities);
        }

        @Test
        public void Test1() {

            page2 = new reminderApp(driver);
            page2.addList("My iOS Capabilities");
            for (Map.Entry<String, Object> entry : desiredCapabilities.asMap().entrySet()) {
                page2.addReminder(entry.getKey() + "=" + entry.getValue().toString());
            }
        }

        @Test
        public void Test2() {
            String resultListName;
            List<String> resultListItems;

            page2 = new reminderApp(driver);

            resultListName = page2.getListName("My iOS Capabilities");
            Assert.assertEquals("My iOS Capabilities", resultListName);
            System.out.println(resultListName);

            resultListItems = page2.getListItems("My iOS Capabilities");
            int i = 0;
            for (Map.Entry<String, Object> entry : desiredCapabilities.asMap().entrySet()) {
                Assert.assertTrue(resultListItems.contains(entry.getKey() + "=" + entry.getValue().toString()));
                System.out.println(resultListItems.get(i));
                i++;
            }
        }

        @Test
        public void Test3() {
            page2 = new reminderApp(driver);
            page2.deleteList("My iOS Capabilities");
        }

        @After
        public void cleanUp() throws InterruptedException {
            Thread.sleep(5000);
            driver.quit();
        }

}

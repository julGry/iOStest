import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class reminderApp {
    private AppiumDriver driver;
    private int init_duration = 10;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Add List\"`]")
    private WebElement newList;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value == \"List Name\"`]")
    private WebElement listName;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Done\"`]")
    private WebElement addListDoneButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[`label BEGINSWITH \"My iOS Capabilities\"`]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
    private WebElement addListReminder;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"New Reminder\"`]")
    private WebElement addNewReminderButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeTextField\"")
    private WebElement reminderTextField;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Done\"")
    private WebElement buttonDone;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Lists\"")
    private WebElement buttonList;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
    private List<WebElement> listElements;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText")
    private List<WebElement> listTexts;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"More\"`]")
    private WebElement moreButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Delete List\"`]")
    private WebElement deleteListButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Delete\"`]")
    private WebElement deleteConfirmButton;

    public reminderApp(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(init_duration)), this);
    }

    public void addList(String newListName) {
        newList.click();
        listName.sendKeys(newListName);
        addListDoneButton.click();
    }

    public void addReminder(String newReminderName) {
        addListReminder.click();
        addNewReminderButton.click();
        reminderTextField.sendKeys(newReminderName);
        buttonDone.click();
        buttonList.click();
    }

    public String getListName(String listName) {
        String result;
        addListReminder.click();
        result = listTexts.get(0).getText();
        buttonList.click();
        return result;
    }

    public List<String> getListItems(String listName) {
        List<String> result = new ArrayList<>();
        addListReminder.click();
        for (WebElement element : listElements) {
            result.add(element.getText());
        }
        buttonList.click();
        return result;
    }

    public void deleteList(String listName) {
        addListReminder.click();
        moreButton.click();
        deleteListButton.click();
        deleteConfirmButton.click();
    }
}

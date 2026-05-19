package locators;

import org.openqa.selenium.By;

public class AccountLocators {

    // App Launcher and Navigation
    public static final By APP_LAUNCHER_ICON = By.cssSelector("div.slds-icon-waffle");
    public static final By SEARCH_APPS_INPUT = By.cssSelector("input.slds-input[placeholder='Search apps and items...']");
    public static final By ACCOUNTS_TAB_LINK = By.xpath("//mark[text()='Accounts']/ancestor::a");

    // Global Search
    public static final By GLOBAL_SEARCH_INPUT = By.cssSelector("input[placeholder='Search Salesforce']");
    public static final By ACCOUNT_NAME_SEARCH_RESULT_LINK(String accountName) {
        return By.xpath(String.format("//a[@title='%s']", accountName));
    }

    // Account Detail Page
    public static final By TCV_AMOUNT_DISPLAY = By.cssSelector("lightning-output-field[field-name='TCV_Amount__c'] lightning-formatted-text");
    public static final By EDIT_BUTTON = By.xpath("//button[@name='Edit']");

    // Account Edit Modal
    public static final By EDIT_MODAL_TITLE = By.cssSelector("div.slds-modal__header h2.slds-modal__title");
    public static final By TCV_AMOUNT_INPUT_EDIT_MODAL = By.cssSelector("lightning-input-field[field-name='TCV_Amount__c'] input[type='text']");
    public static final By SAVE_EDIT_BUTTON = By.cssSelector("button[name='SaveEdit']");
    public static final By CANCEL_EDIT_BUTTON = By.cssSelector("button[name='CancelEdit']");

    // Error Messages
    public static final By ERROR_MESSAGE_DISPLAY = By.cssSelector("div.forceVisualMessageList ul li");

    private AccountLocators() {}
}

package locators;

import org.openqa.selenium.By;

public class AccountLocators {
    // App Launcher and Navigation
    public static final By APP_LAUNCHER_BUTTON = By.cssSelector("div.slds-icon-waffle");
    public static final By APP_LAUNCHER_SEARCH_INPUT = By.cssSelector("input.slds-input[placeholder='Search apps and items...']");
    public static final By ACCOUNTS_TAB_LINK = By.xpath("//mark[text()='Accounts']/ancestor::a");
    public static final By GLOBAL_SEARCH_INPUT = By.cssSelector("input[placeholder='Search Salesforce']");

    // Account Record Page Elements
    public static final By ACCOUNT_RECORD_PAGE_TITLE = By.xpath("//h1[contains(@class, 'slds-page-header__title')]");
    public static final By ACCOUNT_LINK_BY_NAME(String accountName) { 
        return By.xpath("//a[@title='" + accountName + "']"); 
    }
    public static final By TCV_AMOUNT_DISPLAY_FIELD = By.cssSelector("lightning-output-field[field-name='TCV_Amount__c'] lightning-formatted-text");
    public static final By EDIT_BUTTON = By.xpath("//button[@name='Edit']");

    // Edit Modal Elements
    public static final By EDIT_MODAL_TITLE = By.cssSelector("div.slds-modal__header h2.slds-modal__title");
    public static final By TCV_AMOUNT_EDIT_INPUT = By.cssSelector("lightning-input-field[field-name='TCV_Amount__c'] input[type='text']");
    public static final By SAVE_EDIT_BUTTON = By.cssSelector("button[name='SaveEdit']");
    public static final By CANCEL_EDIT_BUTTON = By.cssSelector("button[name='CancelEdit']");

    // Generic success/error messages (if needed beyond this test)
    public static final By SUCCESS_TOAST_MESSAGE = By.cssSelector("div.forceToastMessage");
    public static final By ERROR_MESSAGE = By.cssSelector("div.forceVisualMessageList ul li");

    private AccountLocators() {}
}
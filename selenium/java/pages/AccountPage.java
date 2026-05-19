package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import locators.AccountLocators;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the Accounts application via the App Launcher.
     */
    public void navigateToAccountsApp() {
        waitForClickable(AccountLocators.APP_LAUNCHER_BUTTON).click();
        fill(AccountLocators.APP_LAUNCHER_SEARCH_INPUT, "Accounts");
        click(AccountLocators.ACCOUNTS_TAB_LINK);
        waitForElement(AccountLocators.ACCOUNT_RECORD_PAGE_TITLE);
    }

    /**
     * Searches for an account using the global search bar and navigates to its record page.
     * @param accountName The full name of the account to search for.
     */
    public void searchAndNavigateToAccount(String accountName) {
        fill(AccountLocators.GLOBAL_SEARCH_INPUT, accountName);
        // Wait for search results to appear and click the specific account link
        By accountLink = AccountLocators.ACCOUNT_LINK_BY_NAME(accountName);
        waitForClickable(accountLink).click();
        waitForElement(AccountLocators.ACCOUNT_RECORD_PAGE_TITLE);
    }

    /**
     * Retrieves the text value of the TCV Amount field displayed on the Account detail page.
     * @return The TCV Amount as a String.
     */
    public String getTCVAmountOnDetailPage() {
        waitForElement(AccountLocators.TCV_AMOUNT_DISPLAY_FIELD);
        return getText(AccountLocators.TCV_AMOUNT_DISPLAY_FIELD);
    }

    /**
     * Clicks the 'Edit' button on the Account record highlight panel.
     */
    public void clickEditRecordButton() {
        click(AccountLocators.EDIT_BUTTON);
        waitForElement(AccountLocators.EDIT_MODAL_TITLE);
    }

    /**
     * Attempts to fill a value into the TCV Amount field within the edit modal.
     * This method is used to test if a field is editable or not.
     * @param newAmount The amount to attempt to enter.
     */
    public void attemptToFillTCVAmountInEditModal(String newAmount) {
        // Even if read-only, 'fill' will attempt actions. We will check its state separately.
        fill(AccountLocators.TCV_AMOUNT_EDIT_INPUT, newAmount);
    }

    /**
     * Retrieves the current value from the TCV Amount input field in the edit modal.
     * @return The value attribute of the TCV Amount input field.
     */
    public String getTCVAmountValueInEditModal() {
        waitForElement(AccountLocators.TCV_AMOUNT_EDIT_INPUT);
        return getAttribute(AccountLocators.TCV_AMOUNT_EDIT_INPUT, "value");
    }

    /**
     * Checks if the TCV Amount field in the edit modal is read-only or disabled.
     * Salesforce Lightning often uses the 'readonly' attribute or 'disabled' attribute on the input element,
     * or the entire lightning-input-field might have a disabled class or similar.
     * This checks the 'readonly' attribute specifically.
     * @return true if the field is read-only, false otherwise.
     */
    public boolean isTCVAmountFieldReadOnlyInEditModal() {
        WebElement tcvInputField = waitForElement(AccountLocators.TCV_AMOUNT_EDIT_INPUT);
        return Boolean.parseBoolean(tcvInputField.getAttribute("readonly"));
    }

    /**
     * Clicks the 'Save' button in the edit modal.
     */
    public void clickSaveInEditModal() {
        click(AccountLocators.SAVE_EDIT_BUTTON);
        waitForSpinner(); // Wait for any processing after save
    }

    /**
     * Clicks the 'Cancel' button in the edit modal.
     */
    public void clickCancelInEditModal() {
        click(AccountLocators.CANCEL_EDIT_BUTTON);
        waitForSpinner(); // Wait for modal to close
    }
}
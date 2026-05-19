package pages;

import org.openqa.selenium.WebDriver;
import locators.AccountLocators;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the Accounts tab via the App Launcher.
     */
    public void navigateToAccountsTab() {
        waitForClickable(AccountLocators.APP_LAUNCHER_ICON).click();
        fill(AccountLocators.SEARCH_APPS_INPUT, "Accounts");
        waitForClickable(AccountLocators.ACCOUNTS_TAB_LINK).click();
        waitForSpinner();
    }

    /**
     * Searches for an account using the global search bar.
     * @param accountName The name of the account to search for.
     */
    public void searchAccount(String accountName) {
        fill(AccountLocators.GLOBAL_SEARCH_INPUT, accountName);
        // Assuming pressing enter or a search trigger happens, 
        // for simplicity, we directly click the search result.
        // In a real scenario, you might add Keys.ENTER and wait for results.
    }

    /**
     * Clicks on a specific account record link from search results or a list.
     * @param accountName The name of the account to open.
     */
    public void openAccountRecord(String accountName) {
        waitForClickable(AccountLocators.ACCOUNT_NAME_SEARCH_RESULT_LINK(accountName)).click();
        waitForSpinner();
    }

    /**
     * Retrieves the displayed TCV Amount from the Account detail page.
     * @return The TCV Amount as a String.
     */
    public String getTCVAmountOnDetailPage() {
        waitForElement(AccountLocators.TCV_AMOUNT_DISPLAY);
        return getText(AccountLocators.TCV_AMOUNT_DISPLAY);
    }

    /**
     * Clicks the 'Edit' button on the Account record page highlight panel.
     */
    public void clickEditButton() {
        waitForClickable(AccountLocators.EDIT_BUTTON).click();
        waitForElement(AccountLocators.EDIT_MODAL_TITLE); // Wait for edit modal to appear
    }

    /**
     * Enters a new TCV Amount in the edit modal.
     * @param amount The new TCV amount to enter.
     */
    public void enterTCVAmountInEditModal(String amount) {
        fill(AccountLocators.TCV_AMOUNT_INPUT_EDIT_MODAL, amount);
    }

    /**
     * Clicks the 'Save' button within the edit modal.
     */
    public void clickSaveButtonInEditModal() {
        click(AccountLocators.SAVE_EDIT_BUTTON);
        waitForSpinner(); // Wait for potential save operation or validation message
    }

    /**
     * Retrieves the error message displayed on the page.
     * @return The error message text as a String.
     */
    public String getErrorMessage() {
        waitForElement(AccountLocators.ERROR_MESSAGE_DISPLAY);
        return getText(AccountLocators.ERROR_MESSAGE_DISPLAY);
    }

    /**
     * Checks if an error message is currently displayed.
     * @return true if an error message is visible, false otherwise.
     */
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(AccountLocators.ERROR_MESSAGE_DISPLAY);
    }
}

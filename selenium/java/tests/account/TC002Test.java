package tests.account;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import tests.BaseTest;

public class TC002Test extends BaseTest {

    private static final String ACCOUNT_NAME = "Beta Solutions - TCV Test";
    private static final String INITIAL_TCV_AMOUNT = "$50,000.00";
    private static final String ATTEMPTED_NEW_TCV_AMOUNT = "75000.00";
    private static final String EXPECTED_ERROR_MESSAGE = "Security Error: Account Executives are not authorized to modify TCV Amount. Please contact CRM Ops for assistance.";

    @Test(description = "TC002 - Verify Account Executive cannot edit Total Contract Value (TCV) field due to validation rule")
    public void testAECannotEditTCVField() {
        AccountPage accountPage = new AccountPage(driver);

        // Step 1: Login is handled by BaseTest (assuming AE_User credentials are in config)
        // Assuming login redirects to a default landing page, 
        // or BaseTest navigates to home/app launcher after login.

        // Step 2: Navigate to the 'Accounts' tab
        accountPage.navigateToAccountsTab();

        // Step 3: Search for the Account 'Beta Solutions - TCV Test'
        accountPage.searchAccount(ACCOUNT_NAME);

        // Step 4: Click on the Account 'Beta Solutions - TCV Test' to open its record page
        accountPage.openAccountRecord(ACCOUNT_NAME);

        // Step 5: Verify that the 'TCV Amount' field is visible and displays '$50,000.00'
        String actualTCVAmount = accountPage.getTCVAmountOnDetailPage();
        Assert.assertEquals(actualTCVAmount, INITIAL_TCV_AMOUNT, "TCV Amount on detail page should be visible and display the initial value.");

        // Step 6: Click the 'Edit' button
        accountPage.clickEditButton();

        // Step 7 & 8: Locate the 'TCV Amount' field in the edit modal and attempt to enter a new value
        accountPage.enterTCVAmountInEditModal(ATTEMPTED_NEW_TCV_AMOUNT);

        // Step 9: Click the 'Save' button at the bottom of the edit modal
        accountPage.clickSaveButtonInEditModal();

        // Step 10 & Expected Result 2 & 3: Verify that an error message is displayed and matches the expected text
        Assert.assertTrue(accountPage.isErrorMessageDisplayed(), "Error message should be displayed.");
        String actualErrorMessage = accountPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, EXPECTED_ERROR_MESSAGE, "The displayed error message does not match the expected validation rule message.");

        // Expected Result 4 & 5: Verify the Account record is NOT saved and TCV Amount retains original value
        // To verify this, we need to refresh the page or navigate away and back.
        // Assuming the error modal blocks save, TCV_Amount__c on the detail page should not have changed.
        // If the error message disappears after refresh, we need to handle that. 
        // For now, let's assume if an error is shown, the modal likely prevented the save.
        // We can navigate back to the account detail page to re-verify.
        // Re-opening the record to ensure we get fresh data after the failed save attempt.
        accountPage.openAccountRecord(ACCOUNT_NAME); // Re-open to confirm the original value
        String tcvAmountAfterFailedSave = accountPage.getTCVAmountOnDetailPage();
        Assert.assertEquals(tcvAmountAfterFailedSave, INITIAL_TCV_AMOUNT, 
                            "TCV Amount on detail page should retain its original value after a failed save attempt.");
    }
}

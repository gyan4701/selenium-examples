package tests.account;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import tests.BaseTest;

public class TC003Test extends BaseTest {

    private static final String ACCOUNT_NAME = "Gamma Services - TCV Test";
    private static final String EXPECTED_TCV_AMOUNT = "$25,000.00";
    private static final String ATTEMPTED_NEW_TCV_AMOUNT = "$30,000.00";

    @Test(description = "TC003 - Verify Standard User (Non-Account Manager/Executive) can view TCV field but cannot edit it")
    public void testStandardUserTCVFieldPermissions() {
        AccountPage accountPage = new AccountPage(driver);

        // Step 1 & 2: Login and navigate to the 'Accounts' tab (BaseTest handles login)
        accountPage.navigateToAccountsApp();

        // Step 3 & 4: Search for the Account 'Gamma Services - TCV Test' and open its record page.
        accountPage.searchAndNavigateToAccount(ACCOUNT_NAME);

        // Step 5: Verify that the 'TCV Amount' field is visible and displays '$25,000.00'.
        String actualTCVAmount = accountPage.getTCVAmountOnDetailPage();
        Assert.assertEquals(actualTCVAmount, EXPECTED_TCV_AMOUNT,
                "TCV Amount field should be visible and display '" + EXPECTED_TCV_AMOUNT + "' for Standard User.");
        System.out.println("Verified TCV Amount on detail page: " + actualTCVAmount);

        // Step 6: Click the 'Edit' button.
        accountPage.clickEditRecordButton();

        // Step 7: Locate the 'TCV Amount' field in the edit modal.
        // Step 8 & Expected 2 & 3: Attempt to enter or modify the value and verify it's read-only/uneditable.
        Assert.assertTrue(accountPage.isTCVAmountFieldReadOnlyInEditModal(),
                "TCV Amount field should be in a read-only state within the edit modal.");
        System.out.println("Verified TCV Amount field is read-only in edit modal.");

        // Attempt to fill the field and then verify its value remains unchanged
        String originalTCVInEditModal = accountPage.getTCVAmountValueInEditModal();
        accountPage.attemptToFillTCVAmountInEditModal(ATTEMPTED_NEW_TCV_AMOUNT);
        String tcvAfterAttemptedEdit = accountPage.getTCVAmountValueInEditModal();

        Assert.assertEquals(tcvAfterAttemptedEdit,
                            originalTCVInEditModal,
                            "Attempting to type in TCV Amount field should not change its value as it's read-only.");
        System.out.println("Attempted to edit TCV Amount to '" + ATTEMPTED_NEW_TCV_AMOUNT + "', but value remained: '" + tcvAfterAttemptedEdit + "'.");

        // Optional: Click Cancel to close the edit modal without saving changes.
        accountPage.clickCancelInEditModal();
    }
}
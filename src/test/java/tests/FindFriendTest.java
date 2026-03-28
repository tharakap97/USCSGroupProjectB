package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FindPage;

public class FindFriendTest extends BaseTest {

    @Test
    public void verifyFindFriend() {

        FindPage findPage = new FindPage(driver);

        findPage.openBrowseProfessional();
        findPage.selectFindFriend();

        Assert.assertTrue(findPage.isResultsDisplayed(),
                "Find Friend results not displayed");
    }
}
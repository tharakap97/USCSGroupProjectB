package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FindPage;

import java.io.InputStream;
import java.util.List;

public class DynamicFindFriendTest extends BaseTest {

    @DataProvider(name = "testDataFromJson")
    public Object[][] getTestDataFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("testData.json");
        List<TestCase> testCases = mapper.readValue(is, new TypeReference<List<TestCase>>() {});

        Object[][] data = new Object[testCases.size()][1];
        for (int i = 0; i < testCases.size(); i++) {
            data[i][0] = testCases.get(i);
        }
        return data;
    }

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(dataProvider = "testDataFromJson")
    public void executeTestCases(TestCase testCase) {
        FindPage findPage = new FindPage(driver);

        System.out.println("Executing Test Case: " + testCase.testCaseId + " - " + testCase.testCaseName);

        // After @BeforeClass login, navigate to Find/Browse flows
        if (testCase.testCaseId.equals("TC_001")) {
            findPage.openBrowseProfessional();
            Assert.assertTrue(findPage.isBrowsePageLoaded(), "Browse page did not load for " + testCase.testCaseId);
        } else if (testCase.testCaseId.equals("TC_002")) {
            findPage.openBrowseProfessional();
            findPage.selectFindFriend();
            Assert.assertTrue(findPage.isResultsDisplayed(), "Results were not displayed for " + testCase.testCaseId);
        } else {
            System.out.println("Skipping automated execution for " + testCase.testCaseId + " (Mocked or not fully implemented)");
        }

        // Keep the page visible for 3 seconds after each test case
        pause(3000);
    }
}

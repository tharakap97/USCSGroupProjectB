package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FindPage;
import pages.LogInPage;

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

    @Test(dataProvider = "testDataFromJson")
    public void executeTestCases(TestCase testCase) {
        FindPage findPage = new FindPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        System.out.println("Executing Test Case: " + testCase.testCaseId + " - " + testCase.testCaseName);

        // Pre-requisite step for this login-dependent test case
        if (testCase.preRequisites != null && testCase.preRequisites.contains("logged in")) {
            
            logInPage.login("www.sandunarjuna@gmail.com", "Sandun@071");
        }

        if (testCase.testCaseId.equals("TC_001") || testCase.testCaseId.equals("TC_002")) {
            findPage.openBrowseProfessional();
            findPage.selectFindFriend();
            Assert.assertTrue(findPage.isResultsDisplayed(), "Results were not displayed for " + testCase.testCaseId);
        } else {
            System.out.println("Skipping automated execution for " + testCase.testCaseId + " (Mocked or not fully implemented)");
        }
    }
}

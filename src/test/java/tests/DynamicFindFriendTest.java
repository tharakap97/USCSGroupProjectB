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

    @DataProvider(name = "jsonData")
    public Object[][] getData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("testDataFind.json");

        List<TestCase> testCases = mapper.readValue(is, new TypeReference<List<TestCase>>() {
        });
        Object[][] data = new Object[testCases.size()][1];

        for (int i = 0; i < testCases.size(); i++) {
            data[i][0] = testCases.get(i);
        }
        return data;
    }

    @Test(dataProvider = "jsonData")
    public void runTest(TestCase tc) {

        FindPage findPage = new FindPage(driver);

        System.out.println("Running: " + tc.testCaseId);

        findPage.openBrowseProfessional();

        if (tc.testData != null) {

            if (tc.testData.containsKey("intent")) {
                String intent = tc.testData.get("intent").toString();
                if (intent.equalsIgnoreCase("Find a Friend")) {
                    findPage.selectFindFriend();
                } else if (intent.equalsIgnoreCase("Study Buddy")) {
                    findPage.selectStudyBuddy();
                }
            }

            if (tc.testData.containsKey("location")) {
                findPage.enterLocation(tc.testData.get("location").toString());
            }

            if (tc.testData.containsKey("rating")) {
                findPage.setRating((Integer) tc.testData.get("rating"));
            }

            if (tc.testData.containsKey("radius")) {
                findPage.setRadius(tc.testData.get("radius").toString());
            }
        }

        // Assertions
        if (tc.testCaseId.equals("TC_012")) {
            Assert.assertTrue(findPage.isNoResultsDisplayed(), "No results not shown");
        } else {
            Assert.assertTrue(findPage.isResultsDisplayed(), "Results not shown");
        }
    }
}
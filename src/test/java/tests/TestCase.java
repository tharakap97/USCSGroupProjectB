package tests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class TestCase {
    @JsonProperty("testCaseId")
    public String testCaseId;
    @JsonProperty("testCaseName")
    public String testCaseName;
    @JsonProperty("preRequisites")
    public String preRequisites;
    @JsonProperty("steps")
    public List<String> steps;
    @JsonProperty("testData")
    public Map<String, Object> testData;
    @JsonProperty("expectedResult")
    public String expectedResult;
    @JsonProperty("module")
    public String module;
    @JsonProperty("priority")
    public String priority;
}

package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import static com.jayway.restassured.RestAssured.*;

public class TestBase {


    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        RestAssured.baseURI = "https://bugify.stqa.ru/api/";
    }

    public boolean isIssueOpen(int issueId) {
        return !status(issueId).equals("Resolved");
    }

    public String status(int issueId) {
        String json = get("issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject()
                .get("state_name").getAsString();
    }

    public void skipNotFixed(int issueId) {
        if(isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}

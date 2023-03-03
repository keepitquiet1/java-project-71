package hexlet.tests;

import hexlet.code.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class AppTest {
    public static Map<String, String> json1 = new HashMap<>();
    public static Map<String, String> json2 = new HashMap<>();
    public static Map<String, String> result = new HashMap<>();
    public String[] testStrings = {"testKey_1: TestValue_1", "testKey_2: TestValue_2"};

    @BeforeAll
    public static void setUp() {

        json1.put("testKey_1", "TestValue_1");
        json1.put("testKey_2", "TestValue_2");
        json2.put("testKey_2", "TestValue_2");
        json2.put("testKey_3", "TestValue_3");
        result.put("-testKey_1", "TestValue_1");
        result.put("testKey_2", "TestValue_2");
        result.put("+testKey_3", "TestValue_3");
    }

    @Test
    void testCompareJson() {
        Assertions.assertEquals(App.compareJson(json1, json2), result);
    }

    @Test
    void getMap() {
        Assertions.assertEquals(App.getMap(testStrings), json1);
    }

}

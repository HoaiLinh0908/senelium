package com.senelium.test;

import com.senelium.Senelium;
import org.testng.annotations.Test;

public class GoogleTests extends TestBase {
    @Test
    void googleTest() {
        Senelium.open("https://www.google.com/");
        Senelium.sleep(1000);
    }
}

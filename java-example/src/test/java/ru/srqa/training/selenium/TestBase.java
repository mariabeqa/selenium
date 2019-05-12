package ru.srqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import ru.srqa.training.selenium.app.Application;
import java.io.IOException;

public class TestBase {
    public Application app;

    @Before
    public void start() throws IOException {
        app = new Application();
    }

    @After
    public void stop() {
        app.quit();
    }
}

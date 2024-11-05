package org.example;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstTest {
    //Define constants
    static final String BASE_URL = "https://practicetestautomation.com/practice-test-login/";
    static final String ERROR_MSG = "Your username is invalid!";
    static final String SUCCESS_MSG = "Logged In Successfully";

    public static void main(String[] args) {
        //Create your playwright instance
        Playwright playwright = Playwright.create();
        //Create Chromium browser
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //Create a new page (tab) in the browser
        Page page = browser.newPage();
        //Navigate to base url
        page.navigate(BASE_URL);
        //Define locators
        Locator username = page.locator("id=username");
        Locator password = page.locator("id=password");
        Locator submit = page.locator("id=submit");
        //Fill with incorrect data
        username.fill("jkc");
        password.fill("jkc");
        submit.click();
        //Check for error locator
        Locator error = page.locator("id=error");
        System.out.println(error.textContent());
        //Assert error message
        assertThat(error).containsText(ERROR_MSG);
        //Fill with correct data
        username.fill("student");
        password.fill("Password123");
        submit.click();
        //Check for success locator
        Locator success = page.locator(".post-title");
        System.out.println(success.textContent());
        assertThat(success).containsText(SUCCESS_MSG);
        //Close all
        page.close();
        browser.close();
        playwright.close();
    }
}

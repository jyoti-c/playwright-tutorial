package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.options.AriaRole;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev/java/docs/locators");
            Locator playwrightForJavaLink = page.locator("//b[text()='Playwright for Java']/parent::a");
            playwrightForJavaLink.click();
            Locator searchButton =page.getByLabel("Search");
            searchButton.click();
            Locator searchInput = page.getByPlaceholder("Search docs");
            searchInput.fill("Locators");
            Locator locatorDropDown = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Locators").setExact(true)).getByRole(AriaRole.LINK);
            locatorDropDown.click();
            page.close();
            browser.close();
        }
    }
}
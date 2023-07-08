package com.test.saucedemo.stepdefinitions;

import com.test.saucedemo.pages.CartPage;
import com.test.saucedemo.pages.CheckOutPage;
import com.test.saucedemo.pages.LoginPage;
import com.test.saucedemo.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverHelper;

public class OrderStepDef {

    WebDriver driver = DriverHelper.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    MainPage mainPage = new MainPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckOutPage checkOutPage = new CheckOutPage(driver);

    @Given("User provides username and oassword to login successfully")
    public void userProvidesUsernameAndOasswordToLoginSuccessfully() {
        loginPage.login(ConfigReader.readProperty("QA_sauceDemo_username"), ConfigReader.readProperty("QA_sauceDemo_password"));
    }

    @When("User chooses the {string},click add to cart button and validate it is added")
    public void userChoosesTheClickAddToCartButtonAndValidateItIsAdded(String productName) {
        mainPage.chooseProduct(productName);
        mainPage.addingProductToCart();

    }

    @When("User click cart item and checkout button")
    public void userClickCartItemAndCheckoutButton() {
        mainPage.clickCartIconButton(driver);
        cartPage.clickCheckOutButton();

    }

    @When("User provides {string},{string},{string} to checkout page and click continue button")
    public void userProvidesToCheckoutPageAndClickContinueButton(String firstName, String lastName, String zipCode) {
        checkOutPage.sendUserInformation(firstName, lastName, zipCode);
    }

    @Then("User validates the {string},{string},{string},{string} with {string}% tax rate")
    public void userValidatesTheWithTaxRate(String expectedProductName, String expectedItemTotal, String expectedTax, String expectedTotalPrice, String taxRate) {
        checkOutPage.validateItemOrderInformation(expectedProductName, expectedItemTotal, expectedTax, expectedTotalPrice, taxRate);
    }


    @Then("User clicks Finish button and validate {string} for purchase")
    public void userClicksFinishButtonAndValidateForPurchase(String expectedMessage) {
        checkOutPage.clickFinishButton();
        Assert.assertEquals(expectedMessage, checkOutPage.successMessage());
    }
}

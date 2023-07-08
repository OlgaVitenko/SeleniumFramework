package com.test.saucedemo.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.text.DecimalFormat;

public class CheckOutPage {
    public CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#first-name")
    private WebElement firstName;
    @FindBy(css = "#last-name")
    private WebElement lastname;
    @FindBy(css = "#postal-code")
    private WebElement zipCode;
    @FindBy(css = "#continue")
    private WebElement continueButton;
    @FindBy(css = ".inventory_item_name")
    private WebElement productName;
    @FindBy(css = ".summary_subtotal_label")
    private WebElement itemTotal;
    @FindBy(css = ".summary_tax_label")
    private WebElement tax;
    @FindBy(css = ".summary_total_label")
    private WebElement totalPrice;
    @FindBy(css = "#finish")
    private WebElement finishButton;
    @FindBy(tagName = "h2")
    private WebElement successMessage;

    public void sendUserInformation(String firstName, String lastname, String zipCode) {
        this.firstName.sendKeys(firstName);
        this.lastname.sendKeys(lastname);
        this.zipCode.sendKeys(zipCode);
        continueButton.click();
    }
    public void validateItemOrderInformation(String expectedProductName, String expectedItemTotal, String expectedTax, String expectedTotalPrice, String taxRate){

        double itemTotalConvertion=Double.parseDouble(BrowserUtils.getText(itemTotal).
                substring(BrowserUtils.getText(itemTotal).indexOf("$")+1));

        double taxConvertion=Double.parseDouble(BrowserUtils.getText(tax).
                substring(BrowserUtils.getText(tax).indexOf("$")+1));

        double totalPriceConvertion=Double.parseDouble(BrowserUtils.getText(totalPrice).
                substring(BrowserUtils.getText(totalPrice).indexOf("$")+1));

        Assert.assertEquals(expectedProductName,BrowserUtils.getText(productName));
        Assert.assertEquals(expectedItemTotal,String.valueOf(itemTotalConvertion));

        double taxCalculation=(itemTotalConvertion*(Double.parseDouble(taxRate)*0.01));
        System.out.println(taxCalculation);//2.40
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedSumPriceTax = df.format(taxCalculation);//item total
        Assert.assertEquals(expectedTax,formattedSumPriceTax);
        Assert.assertEquals(taxCalculation,taxConvertion,0.01);
        Assert.assertEquals(expectedTotalPrice,String.valueOf(totalPriceConvertion));
    }
    public void clickFinishButton(){
        finishButton.click();
    }
    public String successMessage(){
        return BrowserUtils.getText(successMessage);
    }
}

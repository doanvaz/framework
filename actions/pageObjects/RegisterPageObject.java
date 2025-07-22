package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSignUpButton() {
		waitForElementClickable(driver, RegisterPageUI.SIGN_UP_BUTTON);
		clickToElement(driver, RegisterPageUI.SIGN_UP_BUTTON);
		sleepInSecond(2);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmedPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.CONFIRMED_PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPhoneNumberTextbox() {
		return getElementText(driver, RegisterPageUI.PHONE_NUMBER_ERROR_MESSAGE);
	}

	public String getErrorMessageAtAgreeCheckbox() {
		return getElementText(driver, RegisterPageUI.AGREE_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtCountryDropdownBox() {
		return getElementText(driver, RegisterPageUI.COUNTRY_ERROR_MESSAGE);
	}

	public void selectCountryDropdownBox(String countryName) {
		selectItemInCustomDropdown(driver, RegisterPageUI.COUNTRY_DROPDOWN_PARENT_XPATH,
				RegisterPageUI.COUNTRY_DROPDOWN_CHILD_XPATH, countryName);
	}

	public void inputToEmailTextbox(String emailAddress) {
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);

	}

	public void inputToConfirmedPasswordTextbox(String confirmedPassword) {
		sendkeyToElement(driver, RegisterPageUI.CONFIRMED_PASSWORD_TEXTBOX, confirmedPassword);
	}

	public void inputToPhoneNumberTextbox(String phoneNumber) {
		sendkeyToElement(driver, RegisterPageUI.PHONE_NUMBER_TEXTBOX, phoneNumber);

	}

	public void checkOnAgreeCheckbox() {
		clickToElement(driver, RegisterPageUI.AGREE_CHECKBOX);
	}

	public String getTitleSuccessMessage() {
		return getElementText(driver, RegisterPageUI.TITLE_SUCCESS_MESSAGE);
	}

	public String getContentSuccessMessage() {
		return getElementText(driver, RegisterPageUI.CONTENT_SUCCESS_MESSAGE);
	}

}

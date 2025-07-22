package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void clickToSignInButton() {
		clickToElement(driver, LoginPageUI.SIGN_IN_BUTTON);
	}

	public void inputToEmailTextbox(String email) {
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXT_BOX, email);
	}

	public void inputToPasswordTextbox(String password) {
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, password);
	}

	// Get Message
	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, LoginPageUI.PASSWORD_ERROR_MESSAGE);
	}

}

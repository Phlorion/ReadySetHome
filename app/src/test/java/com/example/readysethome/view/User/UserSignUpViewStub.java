package com.example.readysethome.view.User;

import com.example.readysethome.view.User.UserSignUp.UserSignUpPresenter;
import com.example.readysethome.view.User.UserSignUp.UserSignUpView;

public class UserSignUpViewStub implements UserSignUpView {

    private String firstName, lastName, email, password, credit_card;
    private String error_title, error_message, success_message;
    private int account_type;
    private UserSignUpPresenter presenter;

    public void setPresenter(UserSignUpPresenter presenter) {
        this.presenter = presenter;
    }

    public UserSignUpPresenter getPresenter() {
        return presenter;
    }

    public UserSignUpViewStub() {
        firstName = lastName = email = password = credit_card = error_title = error_message = success_message = "";
        account_type = 1;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return password;
    }

    public String getCreditCard() {
        return credit_card;
    }

    public String getError_title() {
        return error_title;
    }

    public String getError_message() {
        return error_message;
    }

    public String getSuccess_message() {
        return success_message;
    }

    public int getAccountType() {
        return account_type;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }

    public void successfullyFinishActivity(String message) {
        success_message = message;
    }

    public void showErrorMessage(String title, String message) {
        error_title = title;
        error_message = message;
    }
}

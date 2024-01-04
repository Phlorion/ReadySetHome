package com.example.readysethome.view.User;

import com.example.readysethome.model.User;
import com.example.readysethome.view.User.UserLogIn.UserLogInPresenter;
import com.example.readysethome.view.User.UserLogIn.UserLogInView;

public class UserLogInViewStub implements UserLogInView {
    private String email, password;
    private String error_title, error_message, success_message;
    private UserLogInPresenter presenter;

    public void setPresenter(UserLogInPresenter presenter) {
        this.presenter = presenter;
    }

    public UserLogInPresenter getPresenter() {
        return presenter;
    }

    public UserLogInViewStub() {
        email = password = error_title = error_message = success_message = "";
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getError_title() {
        return error_title;
    }

    public String getError_message() {
        return error_message;
    }

    public void showErrorMessage(String title, String message) {
        error_title = title;
        error_message = message;
    }

    public String getSuccess_message() {
        return success_message;
    }

    public void successfullyFinishActivity(String message, User user) {
        success_message = message;
    }
}

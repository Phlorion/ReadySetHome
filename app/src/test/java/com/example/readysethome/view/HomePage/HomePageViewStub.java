package com.example.readysethome.view.HomePage;

public class HomePageViewStub implements HomePageView{

    private int loginClicks;
    private int signupClicks;
    private HomePagePresenter presenter;

    public void setPresenter(HomePagePresenter presenter) {
        this.presenter = presenter;
    }

    public HomePagePresenter getPresenter() {
        return presenter;
    }

    public void login() {
        loginClicks++;
    }

    public void signup() {
        signupClicks++;
    }

    public int getLoginClicks() {
        return loginClicks;
    }

    public int getSignupClicks() {
        return signupClicks;
    }
}

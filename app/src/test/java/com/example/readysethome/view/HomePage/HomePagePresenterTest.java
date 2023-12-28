package com.example.readysethome.view.HomePage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomePagePresenterTest {
    private HomePagePresenter presenter;
    private HomePageViewStub view;

    @Before
    public void setUp() {
        view = new HomePageViewStub();
        presenter = new HomePagePresenter(view);
        view.setPresenter(presenter);
    }

    @Test
    public void testLogIns() {
        for (int i=0; i<10; i++) {
            presenter.onUserLogIn();
        }

        Assert.assertEquals(10, view.getLoginClicks());
    }

    @Test
    public void testSignUps() {
        for (int i=0; i<10; i++) {
            presenter.onUserSignUp();
        }

        Assert.assertEquals(10, view.getSignupClicks());
    }
}

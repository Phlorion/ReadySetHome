package com.example.readysethome.view.HomePage;

public class HomePagePresenter {
    private HomePageView view;

    /**
     * Αρχικοποιεί τον Presenter.
     * @param view Ένα instance του view
     */
    public HomePagePresenter(HomePageView view)
    {
        this.view = view;
    }

    /**
     * Όταν πραγματοποιείται click στο UserLogIn activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void onUserLogIn()
    {
        view.login();
    }

    /**
     * Όταν πραγματοποιείται click στο UserSignUp activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    void onUserSignUp()
    {
        view.signup();
    }

}

package com.example.readysethome.view.User;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.view.User.UserLogIn.UserLogInPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserLogInPresenterTest {
    UserLogInViewStub view;
    UserLogInPresenter presenter;
    Initializer dataHelper;

    @Before
    public void setUp() {
        view = new UserLogInViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
    }

    @Test
    public void connect() {
        view.setPresenter(new UserLogInPresenter(view, dataHelper.getUserDAO()));
        presenter = view.getPresenter();

        view.setEmail("");
        view.setPass("");
        presenter.onUserConnect();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.", view.getError_message());

        view.setEmail("1234");
        view.setPass("0000");
        presenter.onUserConnect();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Παρακαλώ εισάγετε μία έγκυρη διεύθυνση ηλεκτρονικού ταχυδρομείου.", view.getError_message());

        view.setEmail("georgeavrabos@gmail.com");
        presenter.onUserConnect();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Δεν υπάρχει λογαριασμός με αυτή τη διέυθυνση ηλεκτρονικού ταχυδρομείου.", view.getError_message());

        view.setEmail("p3210001@aueb.gr");
        presenter.onUserConnect();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Εσφαλμένος κωδικός πρόσβασης.", view.getError_message());

        view.setPass("1234");
        presenter.onUserConnect();
        Assert.assertEquals("Καλωσήρθες Avrabos George!", view.getSuccess_message());

    }

}

package com.example.readysethome.view.User;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.view.User.UserSignUp.UserSignUpPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserSignUpPresenterTest {
    UserSignUpViewStub view;
    UserSignUpPresenter presenter;
    Initializer dataHelper;

    @Before
    public void setUp() {
        view = new UserSignUpViewStub();
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
    }

    @Test
    public void addNew() {
        presenter = new UserSignUpPresenter(view, dataHelper.getUserDAO());

        view.setFirstName("");
        view.setLastName("Bob");
        view.setEmail("george@gmail.com");
        view.setPassword("hello1234");
        view.setCredit_card("0001000200030004");
        view.setAccount_type(1);
        presenter.onSaveUser();
        Assert.assertEquals(view.getError_title(), "Σφάλμα!");
        Assert.assertEquals(view.getError_message(), "Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.");

        view.setFirstName("1");
        presenter.onSaveUser();
        Assert.assertEquals(view.getError_title(), "Σφάλμα!");
        Assert.assertEquals(view.getError_message(), "Παρακαλώ συμπληρώστε 2 εώς και 20 χαρακτήρες στο όνομα.");

        view.setFirstName("John");
        view.setEmail("123124125135");
        presenter.onSaveUser();
        Assert.assertEquals(view.getError_title(), "Σφάλμα!");
        Assert.assertEquals(view.getError_message(), "Παρακαλώ εισάγετε μία έγκυρη διεύθυνση ηλεκτρονικού ταχυδρομείου.");

        view.setEmail("p3210001@aueb.gr");
        presenter.onSaveUser();
        Assert.assertEquals(view.getError_title(), "Σφάλμα!");
        Assert.assertEquals(view.getError_message(), "Υπάρχει ήδη λογαριασμός με αυτή τη διεύθυνση ηλεκτρονικού ταχυδρομείου.");

        view.setFirstName("John");
        view.setLastName("Bob");
        view.setEmail("george@gmail.com");
        view.setPassword("1234");
        view.setCredit_card("8989787867675656");
        view.setAccount_type(2);
        presenter.onSaveUser();
        Assert.assertEquals(view.getSuccess_message(), "Επιτυχής εγγραφή του 'Bob John'!");
    }
}

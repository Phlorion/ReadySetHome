package com.example.readysethome.view.User;

import com.example.readysethome.dao.Initializer;
import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
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
        view.setPresenter(new UserSignUpPresenter(view, dataHelper.getUserDAO(), dataHelper.getTenantDAO(), dataHelper.getOwnerDAO()));
        presenter = view.getPresenter();

        view.setFirstName("");
        view.setLastName("Bob");
        view.setEmail("george@gmail.com");
        view.setPassword("hello1234");
        view.setCredit_card("0001000200030004");
        view.setAccount_type(1);
        presenter.onSaveUser();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.", view.getError_message());

        view.setFirstName("1");
        presenter.onSaveUser();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Παρακαλώ συμπληρώστε 2 εώς και 20 χαρακτήρες στο όνομα.", view.getError_message());

        view.setFirstName("John");
        view.setEmail("123124125135");
        presenter.onSaveUser();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Παρακαλώ εισάγετε μία έγκυρη διεύθυνση ηλεκτρονικού ταχυδρομείου.", view.getError_message());

        view.setEmail("p3210001@aueb.gr");
        presenter.onSaveUser();
        Assert.assertEquals("Σφάλμα!", view.getError_title());
        Assert.assertEquals("Υπάρχει ήδη λογαριασμός με αυτή τη διεύθυνση ηλεκτρονικού ταχυδρομείου.", view.getError_message());

        view.setFirstName("John");
        view.setLastName("Bob");
        view.setEmail("bob@gmail.com");
        view.setPassword("1234");
        view.setCredit_card("8989787867675656");
        view.setAccount_type(2);
        presenter.onSaveUser();
        Assert.assertEquals("Επιτυχής εγγραφή του 'Bob John'!", view.getSuccess_message());

        view.setFirstName("George");
        view.setLastName("Smith");
        view.setEmail("georgesmith@gmail.com");
        view.setPassword("0987");
        view.setCredit_card("1596159615961596");
        view.setAccount_type(1);
        presenter.onSaveUser();
        Assert.assertEquals("Επιτυχής εγγραφή του 'Smith George'!", view.getSuccess_message());
    }
}

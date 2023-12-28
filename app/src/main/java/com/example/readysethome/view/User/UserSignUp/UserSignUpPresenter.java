package com.example.readysethome.view.User.UserSignUp;

import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.User;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignUpPresenter {
    private UserSignUpView view;
    private UserDAO users;

    /**
     * Αρχικοποιεί τον Presenter έτσι ώστε
     * αργότερα να προστεθεί ο user.
     * @param view Ένα instance του view
     * @param users Ένα instance του users
     */
    public UserSignUpPresenter(UserSignUpView view, UserDAO users) {
        this.view = view;
        this.users = users;
    }

    /**
     * Επαληθεύει την διεύθυνση ηλεκτρονικού ταχυδρομείου.
     * @param email Η διεύθυνση ηλεκτρονικού ταχυδρομείου
     * @return Ένα boolean ανάλογα με το αν ήταν valid
     * η διεύθυνση ηλεκτρονικού ταχυδρομείου
     */
    private boolean validateEmail(String email)
    {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Έλεγχος αν υπάρχει ήδη λογαριασμός με αυτό το email.
     * @param email Το email
     * @return Ένα bool για το αν υπάρχει ο λογαριασμός ή όχι
     */
    private boolean emailExists(String email) {
        return users.findByEmail(email) != null;
    }

    /**
     * 'Ελεγχος αν το στοιχείο είναι κενό.
     * @param str Το στοιχείο
     * @return Ένα bool για το αν το στοιχείο είναι κενό ή όχι
     */
    private boolean isNull(String str) {
        return str.equals("");
    }

    /**
     * Επαληθεύει την πιστωτική κάρτα.
     * @param creditCard Η πιστωτική κάρτα
     * @return Ένα boolean ανάλογα με το αν ήταν valid
     * ο αριθμός της πιστωτικής κάρτας
     */
    /*
    private boolean validateCreditCard(String creditCard) {
        if (!creditCard.matches("\\d+") || creditCard.length() != 16) return false;
        return true;
    }
     */

    /**
     * Κατά την αποθήκευση του user ελέγχει
     * αν το όνομα και το επώνυμο είναι πάνω από
     * 2 χαρακτήρες και κάτω από 20. Επίσης ελέγχει
     * αν ήταν ορθή η διεύθυνση ηλεκτρονικού ταχυδρομείου
     * και υπάρχουν 16 αριθμητικά ψηφία στον αριθμό της
     * πιστωτικής κάρτας, καθώς και αν υπάρχει κάποιο κενό στοιχείο.
     * Τέλος εμφανίζει μήνυμα ότι ήταν επιτυχείς η εγγραφή
     * αν πληρώθηκαν οι από πάνω έλεγχοι.
     */
    public void onSaveUser() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String email = view.getEmail();
        String password = view.getPass();
        String creditCard = view.getCreditCard();
        int account_type_i = view.getAccountType();
        // switch account type from int to string
        String account_type = null;
        switch (account_type_i) {
            case 1:
                account_type = "Tenant";
                break;
            case 2:
                account_type = "Owner";
            default:
                break;
        }

        if (isNull(firstName) || isNull(lastName) || isNull(email) || isNull(password) || isNull(creditCard)) {
            view.showErrorMessage("Σφάλμα!", "Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.");
            return;
        }
        else if (firstName.length() < 2 || firstName.length() > 20) {
            view.showErrorMessage("Σφάλμα!", "Παρακαλώ συμπληρώστε 2 εώς και 20 χαρακτήρες στο όνομα.");
            return;
        }
        else if (!validateEmail(email)) {
            view.showErrorMessage("Σφάλμα!", "Παρακαλώ εισάγετε μία έγκυρη διεύθυνση ηλεκτρονικού ταχυδρομείου.");
            return;
        }
        /*
        else if (!validateCreditCard(creditCard))
            view.showErrorMessage("Σφάλμα!", "Παρακαλώ εισάγετε μία έγκυρη πιστωτική κάρτα.");
         */
        else if (emailExists(email)) {
            view.showErrorMessage("Σφάλμα!", "Υπάρχει ήδη λογαριασμός με αυτή τη διεύθυνση ηλεκτρονικού ταχυδρομείου.");
            return;
        }


        User new_user = new User(firstName, lastName, new EmailAddress(email), new Password(password), new CreditCard(creditCard), new Date());
        new_user.setId(account_type);
        users.save(new_user);
        view.successfullyFinishActivity("Επιτυχής εγγραφή του '"+lastName+" "+firstName+"'!");
    }

}

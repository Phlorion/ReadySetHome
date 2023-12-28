package com.example.readysethome.view.User.UserLogIn;

import com.example.readysethome.dao.UserDAO;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.User;

public class UserLogInPresenter {
    private UserLogInView view;
    private UserDAO users;

    /**
     * Αρχικοποιεί τον Presenter έτσι ώστε
     * αργότερα εισέλθει στην εφαρμογή
     * ο user.
     * @param view Ένα instance του view
     * @param users Ένα instance του users
     */
    public UserLogInPresenter(UserLogInView view, UserDAO users) {
        this.view = view;
        this.users = users;
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

    private boolean PasswordMatchEmail(String password, String email) {
        User temp = users.findByEmail(email);
        return temp.getPassword().getPassword().equals(password);
    }

    /**
     * Κατά την αναζήτηση του user ελέγχει
     * αν υπάρχει κάποιο κενό στοιχείο, αν υπάρχει χρήστης
     * με αυτό το email, και αν υπάρχει αν ο κωδικός είναι σωστός.
     * Τέλος εμφανίζει μήνυμα ότι ήταν επιτυχείς η σύνδεση
     * αν πληρώθηκαν οι από πάνω έλεγχοι.
     */
    public void onUserConnect() {
        String email = view.getEmail();
        String password = view.getPass();

        if (isNull(email) || isNull(password)) {
            view.showErrorMessage("Σφάλμα!", "Παρακαλώ εισάγετε όλα τα παραπάνω στοιχεία.");
            return;
        }
        else if (!emailExists(email)) {
            view.showErrorMessage("Σφάλμα!", "Δεν υπάρχει λογαριασμός με αυτή τη διέυθυνση ηλεκτρονικού ταχυδρομείου.");
            return;
        }
        else if (!PasswordMatchEmail(password, email)) {
            view.showErrorMessage("Σφάλμα!", "Εσφαλμένος κωδικός πρόσβασης.");
            return;
        }

        User user = users.findByEmail(email);
        view.successfullyFinishActivity("Καλωσήρθες " + user.getLastName() + " " + user.getFirstName()+ "!");
    }

}

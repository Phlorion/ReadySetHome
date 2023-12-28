package com.example.readysethome.view.User.UserSignUp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.UserDAOMemory;

public class UserSignUpActivity extends AppCompatActivity implements UserSignUpView {

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserSignUpActivity.this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    public void successfullyFinishActivity(String message)
    {
        Toast.makeText(UserSignUpActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Επιστρέφει το πρώτο όνομα του user.
     * @return Το πρώτο όνομα του user
     */
    public String getFirstName() {
        return ((EditText)findViewById(R.id.signup_fname)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το επίθετο του user.
     * @return Το επίθετο του user
     */
    public String getLastName() {
        return ((EditText)findViewById(R.id.signup_lname)).getText().toString().trim();
    }

    /**
     * Επιστρέφει το email του user.
     * @return Το email του user
     */
    public String getEmail() {
        return ((EditText)findViewById(R.id.signup_email)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον κωδικό του user.
     * @return Τον κωδικό του user
     */
    public String getPass() {
        return ((EditText)findViewById(R.id.signup_password)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον αριθμό πιστωτικής κάρτας του user.
     * @return Τον αριθμό πιστωτικής κάρτας του user
     */
    public String getCreditCard() {
        return ((EditText)findViewById(R.id.signup_creditcard)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον τύπο λογαριασμού του user.
     * @return Τον τύπο λογαριασμού του user (ενοικιαστή / ιδιοκτήτη)
     */
    public int getAccountType() {
        return ((Spinner)findViewById(R.id.signup_accTypeSpinner)).getSelectedItemPosition()+1;
    }

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     *
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        final UserSignUpPresenter presenter = new UserSignUpPresenter(this, new UserDAOMemory());

        Spinner spinner = (Spinner) findViewById(R.id.signup_accTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.account_types,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        findViewById(R.id.signup_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveUser();
            }
        });
    }
}

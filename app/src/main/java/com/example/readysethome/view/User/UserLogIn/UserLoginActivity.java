package com.example.readysethome.view.User.UserLogIn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.UserDAOMemory;
import com.example.readysethome.model.User;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainActivity;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainActivity;
import com.example.readysethome.view.User.UserSignUp.UserSignUpActivity;

public class UserLoginActivity extends AppCompatActivity implements UserLogInView {

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
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
    public void successfullyFinishActivity(String message, User user)
    {
        Toast.makeText(UserLoginActivity.this, message, Toast.LENGTH_SHORT).show();

        Intent intent = null;
        if (user.getUserType() == 0) {
            intent = new Intent(UserLoginActivity.this, TenantMainActivity.class);
        } else {
            intent = new Intent(UserLoginActivity.this, OwnerMainActivity.class);
        }
        intent.putExtra("user_id", user.getId());
        startActivity(intent);

    }

    /**
     * Επιστρέφει το email του user.
     * @return Το email του user
     */
    public String getEmail() {
        return ((EditText)findViewById(R.id.login_email)).getText().toString().trim();
    }

    /**
     * Επιστρέφει τον κωδικό του user.
     * @return Τον κωδικό του user
     */
    public String getPass() {
        return ((EditText)findViewById(R.id.login_password)).getText().toString().trim();
    }

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     *
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        final UserLogInPresenter presenter = new UserLogInPresenter(UserLoginActivity.this, new UserDAOMemory());

        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onUserConnect();
            }
        });
    }
}

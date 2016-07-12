package comp3350.recipebox.presentation;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350.recipebox.R;
import comp3350.recipebox.business.AccessUser;

public class LoginActivity extends AppCompatActivity
{
    private EditText usernameField;
    private EditText passwordField;

    private String name;
    private String password;

    private Button attemptLoginButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        usernameField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);

        usernameField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                name = usernameField.getText().toString().trim();

                refreshButtons();
            }
        });

        passwordField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                password = passwordField.getText().toString();
                refreshButtons();
            }
        });

        attemptLoginButton = (Button) findViewById(R.id.attemptLoginButton);
        attemptLoginButton.setEnabled(false);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setEnabled(false);
    }

    private void refreshButtons()
    {
        final int MIN_PASSWORD_LENGTH = 1;

        boolean validEntries = name != null
                && name.length() > 0
                && password != null
                && password.length() >= MIN_PASSWORD_LENGTH
                && password.trim().equals(password);

        attemptLoginButton.setEnabled(validEntries);
        signUpButton.setEnabled(validEntries);
    }

    public void attemptLogin(View view)
    {
        AlertDialog.Builder builder;
        AlertDialog dialog;
        final AccessUser accessUser = new AccessUser();

        if (!accessUser.isFoundUser(name))
        {
            builder = new AlertDialog.Builder(this);
            builder.setMessage("Unrecognised user " + name + ". Create new account?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    accessUser.addUser(name, password);
                    accessUser.login(name, password);
                    dialog.dismiss();
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });

            dialog = builder.create();
            dialog.show();
        }

        else if (!accessUser.login(name, password))
        {
            Messages.warning(this, "Password invalid.");
        }

        else
        {
            finish();
        }
    }

    public void signUp(View view)
    {
        AlertDialog.Builder builder;
        AlertDialog dialog;
        final AccessUser accessUser = new AccessUser();

        if (!accessUser.isFoundUser(name))
        {
            accessUser.addUser(name, password);
            accessUser.login(name, password);
            finish();
        }

        else
        {
            builder = new AlertDialog.Builder(this);
            builder.setMessage("User " + name + " already exists. Attempt log in?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    if (!accessUser.login(name, password))
                        Messages.warning(LoginActivity.this, "Password invalid.");

                    else
                        finish();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });

            dialog = builder.create();
            dialog.show();
        }
    }
}
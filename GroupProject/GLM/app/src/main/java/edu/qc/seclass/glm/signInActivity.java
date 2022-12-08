package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.qc.seclass.glm.Services.DatabaseServices;

public class signInActivity extends AppCompatActivity {
    Button btn_signin;
    DatabaseServices services = new DatabaseServices(signInActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_signin = findViewById(R.id.signinBTN);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.usernameInput);
                EditText password = findViewById(R.id.passwordInput);
                //Checks if username and password match and redirects
                services.userSignedIn(username, password);
            }
        });
    }

    public void loadRegister(View v){
        Intent intent = new Intent(this, MainActivity.class);
        signInActivity.this.startActivity(intent);
    }
}
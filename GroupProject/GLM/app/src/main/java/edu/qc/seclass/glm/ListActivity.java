package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.Services.DatabaseServices;

public class ListActivity extends AppCompatActivity  {
    //User user;
    DatabaseServices services = new DatabaseServices(ListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        LinearLayout layout = findViewById(R.id.linearLayout);
        Button createList =  findViewById(R.id.createListBTN);
        Button logoutBTN = findViewById(R.id.logout);

        User user = (User)getIntent().getSerializableExtra("User");


        TextView welcome = findViewById(R.id.welcomeTXT);
        welcome.setText("Welcome, " + user.getUsername());


        createList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, CreateListActivity.class);
                intent.putExtra("User",user);
                ListActivity.this.startActivity(intent);
            }
        });
        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, signInActivity.class);
                ListActivity.this.startActivity(intent);
            }
        });

        //Gets all the lists of the user
        services.getListsByUserId(user, layout);
    }



}
package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.Services.DatabaseServices;

public class RenameListActivity extends AppCompatActivity {
    String username;
    String userId;
    String listId;
    String name;

    User user;
    DatabaseServices services = new DatabaseServices(RenameListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_list);

        user = (User)getIntent().getSerializableExtra("User");
        username = user.username;
        listId =  user.getCurrList().listId;
        userId =user.userId;
        name =  user.getCurrList().name;

        TextView welcome = findViewById(R.id.welcomeTXT);
        TextView title = findViewById(R.id.createListTXT);
        welcome.setText("Welcome, " + username);
        title.setText("Rename \""+name+"\"");

        Button allList = findViewById(R.id.allListsBTN);
        Button createList = findViewById(R.id.createListBTN);
        Button send = findViewById(R.id.newItemBTN);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt = findViewById(R.id.nameINP);
                services.renameList(txt, user);
            }
        });



        createList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenameListActivity.this, CreateListActivity.class);
                intent.putExtra("User", user);
                RenameListActivity.this.startActivity(intent);
            }
        });

        allList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenameListActivity.this, ListActivity.class);
                intent.putExtra("User", user);
                RenameListActivity.this.startActivity(intent);
            }
        });
    }
}
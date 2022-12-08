package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import edu.qc.seclass.glm.Models.ItemList;
import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.Services.DatabaseServices;

public class AddNewItemActivity extends AppCompatActivity {
    String listId;
    String username;
    String userId;
    String listName;
    //This is input from add activity; name
    String name;
    ItemList list;
    //User user;

    LinearLayout layout;
    DatabaseServices services = new DatabaseServices(AddNewItemActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        User user = (User)getIntent().getSerializableExtra("User");

        list = user.getCurrList();

        listId = list.listId;
        username = user.username;
        userId = user.userId;
        listName = list.name;
        name =  getIntent().getStringExtra("name");
        //Toast.makeText(this, "This is crazy "+ name, Toast.LENGTH_SHORT).show();

        layout = findViewById(R.id.linearlayout);

        Button allListsBTN = findViewById(R.id.allListsBTN);
        Button addItemsBTN = findViewById(R.id.addItemsBTN);
        Button noMatches = findViewById(R.id.noMatchesFoundBTN);



        allListsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //typeSelected =false;
                Intent intent = new Intent(AddNewItemActivity.this, ListActivity.class);
                intent.putExtra("User", user);
                AddNewItemActivity.this.startActivity(intent);
            }
        });


        addItemsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //typeSelected =false;
                Intent intent = new Intent(AddNewItemActivity.this, AddItemsActivity.class);
                intent.putExtra("User", user);
                AddNewItemActivity.this.startActivity(intent);
            }
        });
        noMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //typeSelected =false;
                Intent intent = new Intent(AddNewItemActivity.this, AddDataBaseItemActivity.class);
                intent.putExtra("User", user);
                intent.putExtra("name",name);
                AddNewItemActivity.this.startActivity(intent);
            }
        });


        TextView title = findViewById(R.id.titleTXT);
        services.getItemsByName(name, user, title, list, layout);


    }


}
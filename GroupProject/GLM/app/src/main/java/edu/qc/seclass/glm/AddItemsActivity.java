package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.qc.seclass.glm.Models.User;

public class AddItemsActivity extends AppCompatActivity {

    //ItemList list;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

         user =(User) getIntent().getSerializableExtra("User");

        TextView welcome = findViewById(R.id.welcomeTXT);
        welcome.setText("Welcome, " + user.getUsername());

        Button allListBTN = findViewById(R.id.allListsBTN);

        Button searchHBTN = findViewById(R.id.newItemBTN);
        Button searchBTN = findViewById(R.id.searchHierarchicalList);

        allListBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItemsActivity.this, ListActivity.class);
                intent.putExtra("User", user);
                AddItemsActivity.this.startActivity(intent);
            }
        });

        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt = findViewById(R.id.nameINP);
                if(txt.getText().toString().trim().equals("") ){
                    Toast.makeText(AddItemsActivity.this, "Cannot leave a missing field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(AddItemsActivity.this, AddNewItemActivity.class);
                intent.putExtra("User", user);
                intent.putExtra("name", txt.getText().toString());
                AddItemsActivity.this.startActivity(intent);

            }
        });

        searchHBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddItemsActivity.this, HierarchicalAddItemActivity.class);
                intent.putExtra("User", user);
                intent.putExtra("name", "searching by type");
                AddItemsActivity.this.startActivity(intent);
            }
        });

    }
}
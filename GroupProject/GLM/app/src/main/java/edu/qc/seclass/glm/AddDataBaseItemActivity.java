package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.Services.DatabaseServices;

public class AddDataBaseItemActivity extends AppCompatActivity {
    DatabaseServices services = new DatabaseServices(AddDataBaseItemActivity.this);
    String itemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User)getIntent().getSerializableExtra("User");
        itemName = getIntent().getExtras().getString("name");
        setContentView(R.layout.activity_add_data_base_item);
        Button button = findViewById(R.id.addItemaaaBTN);
        EditText editText = findViewById(R.id.addItemDBETXT);
        Button addDataBaseToAllList= findViewById(R.id.allListsDBBTN);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String tempString = editText.getText().toString().toLowerCase().trim();
               if(tempString.equals("")||tempString.equals(null)){
                   Toast.makeText(AddDataBaseItemActivity.this,"Please enter in an Item Type", Toast.LENGTH_SHORT).show();
               }else{
                   services.addItemToList(tempString,"1",itemName,user);


                   Intent intent = new Intent(AddDataBaseItemActivity.this, ListActivity.class);
                   intent.putExtra("User",user);
                   AddDataBaseItemActivity.this.startActivity(intent);
               }


           }
       });
        addDataBaseToAllList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDataBaseItemActivity.this, ListActivity.class);
                intent.putExtra("User",user);
                AddDataBaseItemActivity.this.startActivity(intent);
            }
        });



    }
}
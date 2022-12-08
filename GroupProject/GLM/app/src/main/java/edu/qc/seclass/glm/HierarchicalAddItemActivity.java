package edu.qc.seclass.glm;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.Services.DatabaseServices;


public class HierarchicalAddItemActivity extends AppCompatActivity {
    User user;
    DatabaseServices services = new DatabaseServices(HierarchicalAddItemActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hierarchical_add_item);
        user = (User) getIntent().getSerializableExtra("User");


        //name = getIntent().getStringExtra("name");
        //Toast.makeText(HierarchicalAddItemActivity.this, "string is "+ name, Toast.LENGTH_SHORT).show();
        ListView listView = findViewById(R.id.dynamic_list_view);

        //typeList = services.getAllItemTypes();
        //System.out.println(typeList);
        Button allList = findViewById(R.id.allListsBTN);
        Button backToSearchByType = findViewById(R.id.addItemsBTN);
        TextView welcome = findViewById(R.id.welcomeTXT);
        welcome.setText("Welcome, " + user.getUsername());
        TextView ayo = findViewById(R.id.searchByTXT);
        ayo.setText("Searching Hierarchical list for item");
        //call service to set up listView
        services.hierarchicalService(listView,user);
        allList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //typeSelected =false;
                Intent intent = new Intent(HierarchicalAddItemActivity.this, ListActivity.class);
               // User user = new User(username, userId);
                intent.putExtra("User", user);
                HierarchicalAddItemActivity.this.startActivity(intent);
            }
        });
        backToSearchByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(HierarchicalAddItemActivity.this, ListActivity.class);
                services.hierarchicalService(listView,user);
            }
        });


    }


}
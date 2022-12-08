package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import edu.qc.seclass.glm.Models.User;

public class CreateListActivity extends AppCompatActivity {
    User user;
    String username;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);


        user = (User)getIntent().getSerializableExtra("User");
        username = user.username;
        userId = user.userId;

        TextView welcome = findViewById(R.id.welcomeTXT);
        welcome.setText("Welcome, " + username);

        Button allList = findViewById(R.id.allListsBTN);

        Button newList = findViewById(R.id.newItemBTN);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt = findViewById(R.id.nameINP);
                if(txt.getText().toString().trim().equals("")){
                    Toast.makeText(CreateListActivity.this, "Must enter a name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestQueue queue = Volley.newRequestQueue(CreateListActivity.this);
                String url = "https://project-370.herokuapp.com/api/lists/createList/"+ userId + "/"+txt.getText().toString();
                RequestQueue MyRequestQueue = Volley.newRequestQueue(CreateListActivity.this);
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response.toString());

                            String status = jsonObject.getString("status");

                            // This string^ is important. If the account is created, it will return "ACCOUNT_CREATED"
                            // If account is created, trigger an intent to open up the ListActivity and-
                            // pass the string "user_name" below into the intent parameters as well.
//                            String user_name = jsonObject.getString("username");

                            Toast.makeText(CreateListActivity.this, status, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CreateListActivity.this, ListActivity.class);
                            intent.putExtra("User", user);

                            CreateListActivity.this.startActivity(intent);

                        } catch (JSONException e) {
                            Toast.makeText(CreateListActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateListActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


                MyRequestQueue.add(MyStringRequest);


            }
        });

        allList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateListActivity.this, ListActivity.class);
                intent.putExtra("User",user);

                CreateListActivity.this.startActivity(intent);
            }
        });
    }
}
package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    Button btn_register;

    EditText username, password;
    //ListView theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_register = findViewById(R.id.signinBTN);



        //listeners for btn

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = findViewById(R.id.usernameInput);
                password = findViewById(R.id.passwordInput);
                if(username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Invalid or too few arguments!", Toast.LENGTH_SHORT).show();
                    return;
                }

// Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://project-370.herokuapp.com/api/users/create";
                RequestQueue MyRequestQueue = Volley.newRequestQueue(MainActivity.this);
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

                            Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("username", username.getText().toString());
                        MyData.put("password", password.getText().toString());
                        return MyData;
                    }
                };


                MyRequestQueue.add(MyStringRequest);


            }
        });
    }






    public void loadSignIn(View v){
        Intent intent = new Intent(this, signInActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
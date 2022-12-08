package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import edu.qc.seclass.glm.Models.ItemList;
import edu.qc.seclass.glm.Models.RandomColors;
import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.Services.DatabaseServices;


public class ItemsActivity extends AppCompatActivity {
    LinearLayout layout;
    ItemList list;
    String username;
    String userId;
    String listId;
    String name;

    CheckBox selectAll;

    String selectTXT;
    String checkINP;

    Button allListBTN;

    Button deleteSelected;
    Button addItems;

    DatabaseServices services = new DatabaseServices(ItemsActivity.this);

    public class itemmsData{
        String itemId;
        String itemType;
        String itemName;
        Boolean checked;
        String userId;
        public itemmsData(String itemId, String itemType, String itemName, Boolean checked, String userId) {
            this.itemId = itemId;
            this.itemType = itemType;
            this.itemName = itemName;
            this.checked= checked;
            this.userId = userId;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        layout= findViewById(R.id.linearLayout);
        allListBTN = findViewById(R.id.allListsBTN);

        selectAll = findViewById(R.id.selectBox);
        deleteSelected = findViewById(R.id.deleteSelected);

        User user = (User)getIntent().getSerializableExtra("User");
        checkINP = getIntent().getExtras().getString("checked");
        selectTXT = getIntent().getExtras().getString("txt");

        selectAll.setChecked(checkINP .equals("true")? true : false);

        selectAll.setText(selectTXT);


        list = user.getCurrList();

        username = user.username;
        name = list.getName();
        userId = user.userId;
        listId = list.listId;

        TextView welcome = findViewById(R.id.welcomeTXT);
        TextView title = findViewById(R.id.createListTXT);
        welcome.setText("Welcome, " + username);
        title.setText(name+ " list");

        addItems = findViewById(R.id.addItemsBTN);




        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsActivity.this, AddItemsActivity.class);
                intent.putExtra("User", user);
                ItemsActivity.this.startActivity(intent);
            }
        });

        allListBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsActivity.this, ListActivity.class);
                intent.putExtra("User", user);
                ItemsActivity.this.startActivity(intent);
            }
        });

        setItems(user);
    }

    public void setItems(User user){
        RequestQueue queue = Volley.newRequestQueue(ItemsActivity.this);
        String url = "https://project-370.herokuapp.com/api/lists/items/"+ listId;
        RequestQueue MyRequestQueue = Volley.newRequestQueue(ItemsActivity.this);
        JsonArrayRequest MyArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Button newBTN;
                Button delete;
                Button quant;
                TextView txt;

                if(response.length() == 0){
                    newBTN = new Button(ItemsActivity.this);
                    newBTN.setText("Ooops. You have no items yet.");
                    newBTN.setTextSize(20);
                    layout.addView(newBTN);
                    return;
                }else{
                    selectAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(listId == null){
                                return;
                            }
                            checkINP = checkINP.equals("true") ? "false" : "true";
                            services.selectAll(user, checkINP, listId);
                        }
                    });

                    deleteSelected.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(listId == null) {
                                return;
                            }
                            services.deleteSelected(user);
                        }
                    });
                }

                try {
                    for(int i=0; i< response.length(); i++){
                        LinearLayout layout2 = new LinearLayout(ItemsActivity.this);

                        JSONObject jsonObject = response.getJSONObject(i);
                        CheckBox checkbox = new CheckBox(ItemsActivity.this);
                        newBTN = new Button(ItemsActivity.this);
                        delete = new Button(ItemsActivity.this);
                        quant = new Button(ItemsActivity.this);
                        //This is for quantity
                        EditText q = new EditText(ItemsActivity.this);
                        q.setInputType(InputType.TYPE_CLASS_NUMBER);
                        txt = new TextView(ItemsActivity.this);



                        name = jsonObject.getString("name");
                        String itemId = jsonObject.getString("id");
                        String checked = jsonObject.getString("checked");
                        String itemType = jsonObject.getString("type");
                        String itemName = jsonObject.getString("name");
                        String quantity = jsonObject.getString("quantity");
                        quant.setText("q. "+quantity);
                        q.setText(quantity);
                        txt.setText("Quantity: ");


                        q.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
//                                services.changeQuantity(s.toString(), name);
                                //String tempString = q.getText().toString().trim();
                                String tempString = s.toString();
                                if(tempString.equals(null)||tempString.equals("")){

                                    Toast.makeText(ItemsActivity.this, " no input", Toast.LENGTH_SHORT).show();


                                }else{
                                    int temp = Integer.parseInt(tempString);
                                    if(temp == 0){
                                        Toast.makeText(ItemsActivity.this, "number needs to be greater than 0", Toast.LENGTH_SHORT).show();

                                    }else{
                                        //add to list
                                        services.changeQuantity(tempString, itemId,user);
                                        //services.addItemToList("dairy",tempString,name,user);
                                        Toast.makeText(ItemsActivity.this, tempString+" " + " added to list", Toast.LENGTH_SHORT).show();
                                    }


                                }

                               // Toast.makeText(ItemsActivity.this, s.toString(), Toast.LENGTH_SHORT).show();




                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                        if(checked.equals("true")){
                            checkbox.setChecked(true);
                        }
                        itemmsData data = new itemmsData(itemId, itemType, itemName, checkbox.isChecked(), userId);

                        newBTN.setText(name);

                        checkbox.setTag(data);
                        newBTN.setTextSize(18);
                        quant.setTextSize(20);
                        q.setTextSize(20);
                        newBTN.setBackgroundColor(Color.TRANSPARENT);
                        int redd =android.graphics.Color.argb(255, 121 , 31, 31);
                        newBTN.setPadding(50, 0, 60, 0);
                        delete.setText("Remove");
                        delete.setTag(itemId);
                        int grey =android.graphics.Color.argb(255, 219 , 215, 215);
//                        delete.setBackgroundColor(Color.TRANSPARENT);
//                        delete.setPadding(-150, 30, 0, 10);
                        delete.setTextSize(18);
                        delete.setScaleY(1);
//                        delete.setLayoutParams(new LinearLayout.LayoutParams(10, 10));

                        checkbox.setTextSize(30);
                        checkbox.setPadding(0, 0, -10, 0);
                        layout2.addView(newBTN);
                        layout2.addView(checkbox);
                        layout2.addView(delete);
                        layout2.addView(txt);
                        layout2.addView(q);


                        RandomColors rd = new RandomColors();
                        layout2.setBackgroundColor(rd.getColor(i));
                        layout2.setPadding(0, 20, 0, 20);


                        layout.addView(layout2);

                        checkbox.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CheckBox cb = (CheckBox) view;
                                itemmsData dt = (itemmsData) cb.getTag();
                                RequestQueue queue = Volley.newRequestQueue(ItemsActivity.this);
                                String url = "https://project-370.herokuapp.com/api/lists/items/check/"+ dt.itemId;
                                RequestQueue MyRequestQueue = Volley.newRequestQueue(ItemsActivity.this);
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

                                            Toast.makeText(ItemsActivity.this, status, Toast.LENGTH_SHORT).show();

                                        } catch (JSONException e) {
                                            Toast.makeText(ItemsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(ItemsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                                MyRequestQueue.add(MyStringRequest);
                            }
                        });

                        delete.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
//                                onDeleteClicked(layout2);
                                RequestQueue queue = Volley.newRequestQueue(ItemsActivity.this);
                                String url = "https://project-370.herokuapp.com/api/lists/items/delete/"+ view.getTag();
                                RequestQueue MyRequestQueue = Volley.newRequestQueue(ItemsActivity.this);
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

                                            Toast.makeText(ItemsActivity.this, status, Toast.LENGTH_SHORT).show();

                                            finish();
                                            startActivity(getIntent());

                                        } catch (JSONException e) {
                                            Toast.makeText(ItemsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(ItemsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                                MyRequestQueue.add(MyStringRequest);

                            }
                        });



                        //Toast.makeText(ListActivity.this, jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(ItemsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ItemsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });




        MyRequestQueue.add(MyArrayRequest);



    }

//    public void allLists(View view) {
//        Intent intent = new Intent(ItemsActivity.this, ListActivity.class);
//        intent.putExtra("User", user);
//        ItemsActivity.this.startActivity(intent);
//    }
}
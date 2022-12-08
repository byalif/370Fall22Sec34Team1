package edu.qc.seclass.glm.Services;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import edu.qc.seclass.glm.AddItemsActivity;
import edu.qc.seclass.glm.CreateListActivity;
import edu.qc.seclass.glm.HierarchicalAddItemActivity;
import edu.qc.seclass.glm.ItemsActivity;
import edu.qc.seclass.glm.ListActivity;
import edu.qc.seclass.glm.Models.Data;
import edu.qc.seclass.glm.Models.Item;
import edu.qc.seclass.glm.Models.ItemList;
import edu.qc.seclass.glm.Models.RandomColors;
import edu.qc.seclass.glm.Models.User;
import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.RenameListActivity;


public class DatabaseServices {


    public static final String HTTPS_HEROKU_SERVER = "https://project-370.herokuapp.com/api/users/login";

    public DatabaseServices(Context context) {
        this.context = context;
    }

    Context context;
   // String[] arr;


    //Signs user in
    public void userSignedIn(EditText username, EditText password) {
        if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
            Toast.makeText(context, "Invalid or too few arguments!", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = HTTPS_HEROKU_SERVER;
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    String status = jsonObject.getString("status");
                    // This string^ is important. If the account is created, it will return "ACCOUNT_CREATED"
                    // If account is created, trigger an intent to open up the ListActivity and-
                    // pass the string "user_name" below into the intent parameters as well.
                    if (status.equals("VALID")) {
                        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
                        String user_name = jsonObject.getString("username");
                        String userId = jsonObject.getString("userId");
                        Intent intent = new Intent(context, ListActivity.class);
                        User user = new User(user_name, userId);
                        intent.putExtra("User", user);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("username", username.getText().toString());
                MyData.put("password", password.getText().toString());
                return MyData;
            }
        };


        MySingleton.getInstance(context).addToRequestQueue(MyStringRequest);

    }

//    public String[] getAllItemTypes() {
//
//        String url = "https://project-370.herokuapp.com/api/lists/getAll/Types";
//
//        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.toString());
//                    JSONArray types = jsonObject.getJSONArray("types");
//
//                    //itemList = new String[types.length()];
//                    arr = new String[types.length()];
//                    System.out.println("---------------------------- " + types.length() + " -----------------------\n");
//                    for (int i = 0; i < types.length(); i++) {
//                        System.out.println("---------------------------- " + types.length() + " -----------------------\n");
//                        String type = (String) types.get(i);
//                        //itemList[i] = (String) types.get(i);
//                        arr[i] = type;
//
//
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        MySingleton.getInstance(context).addToRequestQueue(MyArrayRequest);
//        return arr;
//
//    }


    public void hierarchicalServiceHelper(ListView listView, User user,String item){

        String url = "https://project-370.herokuapp.com/api/lists/itemsByType/"+item+"/"+user.getUserId();
        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray types = jsonObject.getJSONArray("names");
                    String[] stringArr = new String[types.length()];
                    for(int i=0;  i < types.length(); i++){

                        String type = (String) types.get(i);
                        //itemList[i] = (String) types.get(i);
                        stringArr[i]=type;

                    }

                    final ArrayAdapter<String>[] arrayAdapterItem = new ArrayAdapter[]{new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, stringArr)};
                    listView.setAdapter(arrayAdapterItem[0]);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            final AlertDialog.Builder quantityAdd = new AlertDialog.Builder(context);
                            final EditText inputQuantity = new EditText(context);
                            inputQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                            quantityAdd.setView(inputQuantity);
                            quantityAdd.setPositiveButton("Add to List",new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int pos){
                                    String tempString = inputQuantity.getText().toString().trim();
                                    String name = (String) adapterView.getItemAtPosition(i);
                                    if(tempString.equals(null)||tempString.equals("")){

                                        Toast.makeText(context, " no input", Toast.LENGTH_SHORT).show();


                                    }else{
                                        int temp = Integer.parseInt(tempString);
                                        if(temp == 0){
                                            Toast.makeText(context, "number needs to be greater than 0", Toast.LENGTH_SHORT).show();

                                        }else{
                                            //add to list
                                            addItemToList(item,tempString,name,user);
                                            Toast.makeText(context, temp+" " + " added to list", Toast.LENGTH_SHORT).show();
                                        }


                                    }

                                    return;
                                }
                            });
                            quantityAdd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int neg) {

                                    return;
                                }
                            });
                            quantityAdd.setMessage("Input Quantity");
                            quantityAdd.show();

                        }

                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(MyArrayRequest);


    }

    public void hierarchicalService(ListView listView, User user){
        String url = "https://project-370.herokuapp.com/api/lists/getAll/Types/"+user.getUserId();

        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray types = jsonObject.getJSONArray("types");
                    String[] stringArr = new String[types.length()];

                    //itemList = new String[types.length()];
                    //arr= new String[types.length()];

                    for(int i=0;  i < types.length(); i++){

                        String type = (String) types.get(i);
                        //itemList[i] = (String) types.get(i);
                        stringArr[i]=type;
                    }
                    final ArrayAdapter<String>[] arrayAdapter = new ArrayAdapter[]{new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, stringArr)};
                    listView.setAdapter(arrayAdapter[0]);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String item = (String) adapterView.getItemAtPosition(i);
                            hierarchicalServiceHelper(listView,user,item);
                        }
                    });




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(MyArrayRequest);


    }


    public void changeQuantity(String quantity, String itemId,User user) {
        RequestQueue queue = Volley.newRequestQueue(context);
        ItemList list = user.getCurrList();
        String url = "https://project-370.herokuapp.com/api/lists/updateQuantity/" + itemId +"/" + quantity;
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    Toast.makeText(context, status, Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });







        MyRequestQueue.add(MyArrayRequest);}
//    public String[] getAllItemsByType(){
//
//        String url = "https://project-370.herokuapp.com/api/lists/getAll/items";
//        System.out.println("---------------------------- " + url+" -----------------------\n");
//        System.out.println("---------------------------- " + url+" -----------------------\n");
//        System.out.println("---------------------------- " + url+" -----------------------\n");
//        System.out.println("---------------------------- " + url+" -----------------------\n");
//        System.out.println("---------------------------- " + url+" -----------------------\n");
//
//
//
//
//        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.toString());
//                    JSONArray types = jsonObject.getJSONArray("types");
//
//                    //itemList = new String[types.length()];
//                    arr= new String[types.length()];
//                    System.out.println("---------------------------- " + types.length()+" -----------------------\n");
//                    for(int i=0;  i < types.length(); i++){
//
//                        String type = (String) types.get(i);
//                        //itemList[i] = (String) types.get(i);
//                        arr[i]=type;
//
//
//
//                    }
//
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        MySingleton.getInstance(context).addToRequestQueue(MyArrayRequest);
//        return arr;
//
//    }

    public void getListsByUserId(User user, LinearLayout layout){
        String url = "https://project-370.herokuapp.com/api/lists/"+ user.userId;
        JsonArrayRequest MyArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Button newBTN;
                Button delete;
                Button rename;
                if(response.length() == 0){
                    newBTN = new Button(context);
                    newBTN.setText("Ooops. You have no lists.");
                    newBTN.setTextSize(20);
                    layout.addView(newBTN);
                    return;
                }
                try {
                    for(int i=0; i< response.length(); i++){
                        LinearLayout layout2 = new LinearLayout(context);

                        JSONObject jsonObject = response.getJSONObject(i);
                        //CheckBox checkbox = new CheckBox(context);
                        newBTN = new Button(context);
                        delete = new Button(context);
                        rename = new Button(context);
                        String name = jsonObject.getString("name");
                        String listId = jsonObject.getString("id");
                        newBTN.setText(name);
                        rename.setText("Edit");
                        ItemList list = new ItemList();
                        list.setListId(listId);
                        list.setName(name);

                        //list.setUser(new User(user.username, user.userId));

                        newBTN.setTag(list);
                        rename.setTag(list);

                        rename.setTextSize(18);
                        newBTN.setTextSize(20);
                        newBTN.setBackgroundColor(Color.TRANSPARENT);
                      // int redd =android.graphics.Color.argb(255, 121 , 31, 31);
                        newBTN.setPadding(50, 0, 60, 0);
                        delete.setText("Remove");

                        delete.setTag(list);

                        delete.setTextSize(18);

                        layout2.addView(newBTN);
                        layout2.addView(rename);

                        layout2.addView(delete);


                        RandomColors rd = new RandomColors();
                        layout2.setBackgroundColor(rd.getColor(i));
                        layout2.setPadding(0, 20, 0, 20);


                        layout.addView(layout2);

                        //If you click list, you go into specific list
                        newBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ItemList list = (ItemList) view.getTag();
                                Intent intent = new Intent(context, ItemsActivity.class);

                                user.setCurrList(list);

                                intent.putExtra("txt","Select All");
                                intent.putExtra("User",user);
                                intent.putExtra("checked", "false");

                                context.startActivity(intent);
                            }
                        });

                        //The delete buttons on each row of lists
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ItemList dList = (ItemList) view.getTag();
                                deleteList(dList, user);
                            }
                        });

                        //Rename button on each row of lists
                        rename.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button btn = (Button)view;

                                ItemList list = (ItemList) btn.getTag();
                                Intent intent = new Intent(context, RenameListActivity.class);

                                user.setCurrList(list);
                                intent.putExtra("User", user);
                                context.startActivity(intent);
                            }
                        });



                        //Toast.makeText(context, jsonObject.getString("name"), Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });




        MySingleton.getInstance(context).addToRequestQueue(MyArrayRequest);


    }


    public void deleteList(ItemList itemList, User user){
        String url = "https://project-370.herokuapp.com/api/lists/delete/"+ itemList.getListId() + "/"+user.userId;
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response.toString());

                    String status = jsonObject.getString("status");

                    Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra("User", user);
                    context.startActivity(intent);

                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        MySingleton.getInstance(context).addToRequestQueue(MyStringRequest);
    }
    
    public void renameList(EditText txt, User user){
        if(txt.getText().toString().trim().equals("")){
            Toast.makeText(context, "Must enter a name!", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://project-370.herokuapp.com/api/lists/rename/"+ user.getCurrList().listId + "/"+txt.getText().toString();
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

                    Toast.makeText(context, status, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra("User", user);
                    context.startActivity(intent);

                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        MySingleton.getInstance(context).addToRequestQueue(MyStringRequest);

    }

    public void getItemTypes(LinearLayout layout, User user, String name){
          ItemList list = user.getCurrList();
//        Toast.makeText(context, "string is "+ name, Toast.LENGTH_SHORT).show();
        String url = "https://project-370.herokuapp.com/api/lists/getItemTypes/"+ list.listId;
        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray types = jsonObject.getJSONArray("types");
                    Button newBTN;
                    Button select;
                    //itemList = new String[types.length()];
                    String[] itemTypeArr = new String[types.length()];
                    //String[] arr = response.getJSONArray("itemType")
                  //  System.out.println("---------------------------- " + types.length()+" -----------------------\n");
                    for(int i=0; i<types.length(); i++){
                        LinearLayout layout2 = new LinearLayout(context);
                        String type = (String) types.get(i);
                        //itemList[i] = (String) types.get(i);
                        newBTN = new Button(context);
                        select = new Button(context);

                        newBTN.setText(type);

                        Data dt = new Data(type, "1");
                        select.setText("SELECT");

                        newBTN.setTag(type);
                        select.setTag(dt);
                        newBTN.setTextSize(20);
                        newBTN.setBackgroundColor(Color.TRANSPARENT);

                        int redd = android.graphics.Color.argb(255, 121 , 31, 31);
                        newBTN.setPadding(50, 0, 60, 0);
                        int grey = android.graphics.Color.argb(255, 219 , 215, 215);
                        select.setTextSize(18);
                        select.setScaleY(1);

                        layout2.addView(newBTN);
                        layout2.addView(select);



                        RandomColors rd = new RandomColors();
                        layout2.setBackgroundColor(rd.getColor2(i));
                        layout2.setPadding(0, 20, 0, 20);

                        select.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button data = (Button) view;
                                Data dt = (Data) data.getTag();
                                String type = dt.type;
                                String quantity = dt.quantity;
                                addItemToList(type, quantity, name, user);
                            }
                        });

                        layout.addView(layout2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        MySingleton.getInstance(context).addToRequestQueue(MyArrayRequest);


    }



    public void getItemsByName(String name, User user, TextView title, ItemList list, LinearLayout layout){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://project-370.herokuapp.com/api/lists/items/getByName/"+ name+"/"+user.getUserId();
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest MyStringRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                   // JSONObject jsonObject = response.getJSONObject(i);
                    //Dynamically add buttons to a linear layout
//                    JSONArray itms = jsonObject.getJSONArray("items");


                    if(response.length() ==1){
                        title.setText("("+response.length()+")"+ " Match found");
                    }
                    else {
                        title.setText("(" + response.length() + ")" + " Matches found");
                    }

                    Button newBTN;
                    Button select;

                    for(int i=0; i<response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String type = jsonObject.getString("type");
                        String name = jsonObject.getString("name");
                        Item item = new Item();
                        item.setItemName(name);
                        item.setItemType(type);
                        //Can u fix it
                        item.setQuantity(1);


                        LinearLayout layout2 = new LinearLayout(context);


                        newBTN = new Button(context);
                        select = new Button(context);

                        newBTN.setText(item.itemName);
                        select.setText("Add to list");

                        newBTN.setTag(item.itemName);


                        select.setTag(item);
                        newBTN.setTextSize(20);
                        newBTN.setBackgroundColor(Color.TRANSPARENT);

                        int redd = android.graphics.Color.argb(255, 121, 31, 31);
                        newBTN.setPadding(50, 0, 60, 0);
                        int grey = android.graphics.Color.argb(255, 219, 215, 215);
                        select.setTextSize(18);
                        select.setScaleY(1);

                        layout2.addView(newBTN);
                        layout2.addView(select);

                        RandomColors rd = new RandomColors();
                        layout2.setBackgroundColor(rd.getColor2(i));
                        layout2.setPadding(0, 20, 0, 20);


                        layout.addView(layout2);

                        select.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Item item = (Item)view.getTag();
                                final AlertDialog.Builder quantityAdd2 = new AlertDialog.Builder(context);
                                final EditText inputQuantity = new EditText(context);
                                inputQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                                quantityAdd2.setView(inputQuantity);

                                quantityAdd2.setPositiveButton("Add to List",new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int pos){
                                        String tempString = inputQuantity.getText().toString().trim();

                                        if(tempString.equals(null)||tempString.equals("")){
                                            Toast.makeText(context, " no input", Toast.LENGTH_SHORT).show();

                                        }else{
                                            int temp = Integer.parseInt(tempString);
                                            if(temp == 0){
                                                Toast.makeText(context, "number needs to be greater than 0", Toast.LENGTH_SHORT).show();

                                            }else{
                                                //add to list
                                                addItemToList(item.getItemName(),tempString,name,user);
                                                Toast.makeText(context, temp+" " + " added to list", Toast.LENGTH_SHORT).show();

                                            }


                                        }

                                        return;
                                    }
                                });
                                quantityAdd2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int neg) {

                                        return;
                                    }
                                });
                                quantityAdd2.setMessage("Input Quantity");
                                quantityAdd2.show();

                                //Fill in and update
                            }
                        });

                    }

                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "no items found", Toast.LENGTH_SHORT).show();
            }
        });


        MyRequestQueue.add(MyStringRequest);

    }


    public void addItemToList(String type, String quantity, String name, User user){
        RequestQueue queue = Volley.newRequestQueue(context);
        ItemList list = user.getCurrList();
        String url = "https://project-370.herokuapp.com/api/lists/addItems/"+name+"/"+ type + "/" + list.listId +"/"+ user.userId + "/"+ quantity;
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest MyArrayRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    Toast.makeText(context, status, Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(context, ItemsActivity.class);
//                    intent.putExtra("User", user);
//                    context.startActivity(intent);

                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MyRequestQueue.add(MyArrayRequest);



    }

    public void selectAll(User user, String checkINP, String listId) {
        String url = "https://project-370.herokuapp.com/api/lists/selectAll" + "/"  + listId + "/" + checkINP;
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        StringRequest MyArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(context, ItemsActivity.class);
                intent.putExtra("User",user);
                intent.putExtra("checked", checkINP);
                intent.putExtra("txt", checkINP.equals("true")? "Deselect All" : "Select All");
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MyRequestQueue.add(MyArrayRequest);




    }

    public void deleteSelected(User user) {
        String url = "https://project-370.herokuapp.com/api/lists/deleteSelected/" + user.currList.listId;
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        StringRequest MyArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(context, ItemsActivity.class);
                intent.putExtra("User",user);
                intent.putExtra("checked", "false");
                intent.putExtra("txt", "Select All");
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MyRequestQueue.add(MyArrayRequest);




    }
}

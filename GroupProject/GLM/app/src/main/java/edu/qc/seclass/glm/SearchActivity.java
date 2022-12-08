//package edu.qc.seclass.glm;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import java.util.ArrayList;
//
//import edu.qc.seclass.glm.Models.Item;
//
//public class SearchActivity extends AppCompatActivity {
//    RecyclerView recycler_view;
//    LinearLayoutManager layout_manager;
//    ArrayList<Item> item_type_list;
//    RecyclerView.Adapter ad;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
//        setViewData();
//        setRecyclerView();
//
//    }
//    public void setViewData(){
//        item_type_list = new ArrayList<>();
//        item_type_list.add(new Item("Beverages", " "));
//        item_type_list.add(new Item("Snacks", " "));
//        item_type_list.add(new Item("Produce", " "));
//        item_type_list.add(new Item("Deli", " "));
//        item_type_list.add(new Item("Meats", " "));
//    }
//    public void setRecyclerView(){
//
//        layout_manager = new LinearLayoutManager(this);
//        recycler_view = findViewById(R.id.recyclerView);
//        recycler_view.setLayoutManager(layout_manager);
//        recycler_view.setAdapter(ad);
//
//
//    }
//}
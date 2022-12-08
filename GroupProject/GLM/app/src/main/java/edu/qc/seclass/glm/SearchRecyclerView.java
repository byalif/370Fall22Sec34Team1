//package edu.qc.seclass.glm;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//import edu.qc.seclass.glm.Models.Item;
//
//public class SearchRecyclerView extends RecyclerView.Adapter<SearchRecyclerView.ItemHolder> {
//    private ArrayList<Item> item_type_list;
//
//    public SearchRecyclerView(ArrayList<Item> item){
//        this.item_type_list = item;
//    }
//
//    public class ItemHolder extends RecyclerView.ViewHolder{
//        private TextView type_view;
//
//        public ItemHolder(View view){
//            super(view);
//            this.type_view=view.findViewById(R.id.item_type_view);
//        }
//
//        public void setData(String type) {
//            type_view.setText(type);
//        }
//    }
//
//    @NonNull
//    @Override
//    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_types, parent, false);
//        return new ItemHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
//        String type = item_type_list.get(position).getItemType();
//
//        holder.setData(type);
//    }
//
//    @Override
//    public int getItemCount() {
//        return item_type_list.size();
//    }
//}

package edu.qc.seclass.glm.Models;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemList implements Serializable {
    public String name;
    public List<Item> list;
    public String listId;
    //public User user;

    public ItemList() {

    }

//    protected ItemList(Parcel in) {
//        name = in.readString();
//        list = in.createTypedArrayList(Item.CREATOR);
//        listId = in.readString();
//        //user = in.readParcelable(User.class.getClassLoader());
//    }

//    public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {
//        @Override
//        public ItemList createFromParcel(Parcel in) {
//            return new ItemList(in);
//        }
//
//        @Override
//        public ItemList[] newArray(int size) {
//            return new ItemList[size];
//        }
//    };

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public ItemList(String name, List<Item> list) {
        this.name = name;
        this.list = list;
    }
    public ItemList(String name) {
        this.name = name;
        this.list = new ArrayList<Item>();
    }

    private void autoSave(){
        // update database

    }
    public void selectAll(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).setCheckOff(true);
        }
    }
    public void deselectAll(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).setCheckOff(false);
        }
    }
    public void deleteAllSelected(){
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i).isCheckOff())deleteItem(i);
        }
        autoSave();
    }
    // for addItem and deleteItem we can add the autoSave() to the button action
    public void addItem(Item item){
        list.add(item);

    }
    public void deleteItem(int i){
        list.remove(i);
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(name);
//        parcel.writeTypedList(list);
//        parcel.writeString(listId);
//        //parcel.writeParcelable(user, i);
//    }


}

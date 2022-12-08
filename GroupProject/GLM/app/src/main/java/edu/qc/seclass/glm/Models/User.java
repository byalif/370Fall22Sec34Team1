package edu.qc.seclass.glm.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    public String username;
    public String userId;
    public ItemList currList;

    //public List<ItemList> savedLists;

    public User( /* (get form json)*/) {
        //this.savedLists = savedLists;
        // get from data base
    }

//    protected User(Parcel in) {
//        username = in.readString();
//        userId = in.readString();
//        currList = in.readParcelable(ItemList.class.getClassLoader());
//
//    }



    public User(String user_name, String userId) {
        this.userId = userId;
        this.username = user_name;
    }

//    public static final Creator<User> CREATOR = new Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };

//    public void addList(String name){
//        savedLists.add(new ItemList(name));
//    }
//    public void autoSave(){
//        //save function call db
//    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public List<ItemList> getSavedLists() {
//        return savedLists;
//    }

//    public void setSavedLists(List<ItemList> savedLists) {
//        this.savedLists = savedLists;
//    }

    public ItemList getCurrList() {
        return currList;
    }

    public void setCurrList(ItemList currList) {
        this.currList = currList;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(username);
//        parcel.writeString(userId);
//       // parcel.writeTypedList(savedLists);
//        parcel.writeValue(currList);
//    }
}



package edu.qc.seclass.glm.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Item implements Serializable {
    public String itemId;
    public String itemType;
    public String itemName;
    //ItemList list;
    public int quantity;
    public boolean checkOff;

    public Item(String itemType, String itemName, int quantity) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.quantity = quantity;
        this.checkOff = false;
    }


    public Item(){}

    public Item(String itemType, String itemName) {
        this.itemType = itemType;
        this.itemName = itemName;

    }

//    protected Item(Parcel in) {
//        itemId = in.readString();
//        itemType = in.readString();
//        itemName = in.readString();
//        quantity = in.readInt();
//        checkOff = in.readInt() == 1;
//    }

//    public static final Creator<Item> CREATOR = new Creator<Item>() {
//        @Override
//        public Item createFromParcel(Parcel in) {
//            return new Item(in);
//        }
//
//        @Override
//        public Item[] newArray(int size) {
//            return new Item[size];
//        }
//    };

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCheckOff() {
        return checkOff;
    }

    public void setCheckOff(boolean checkOff) {
        this.checkOff = checkOff;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(itemId);
//        parcel.writeString(itemType);
//        parcel.writeString(itemName);
//        parcel.writeInt(quantity);
//        parcel.writeInt(checkOff ? 1 : 0);
//    }
}

package com.example.team12bof.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item id")
    public int itemId=0;

    @ColumnInfo(name="item name")
    public String itemName;

    public Item(String itemName){
        this.itemName = itemName;
    }

    public String getItemName(){
        return this.itemName;
    }

    public int getItemId(){
        return this.itemId;
    }
}

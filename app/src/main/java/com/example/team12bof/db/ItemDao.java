package com.example.team12bof.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    List<Item> getAlllItems();

    @Query("SELECT * FROM items WHERE `item id`=:id")
    Item getItem(int id);

    @Insert
    void insertItem(Item item);

    @Delete
    void deleteItem(Item item);
}
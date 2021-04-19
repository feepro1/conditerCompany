package com.conditer.conditercompany.databasese;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface buyTableDao {

    @Query("SELECT * FROM buyTable")
    List<buyTable> getAllFromBuyTable();

    @Query("SELECT * FROM buyTable WHERE id_user = :id_user")
    buyTable getPriceByUserID(Long id_user);

    @Insert
    void addNewBuy(buyTable buyTable);

    @Update
    void update(buyTable buyTable);

    @Delete
    void delete(buyTable buyTable);
}

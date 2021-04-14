package com.conditer.conditercompany.databasese;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface priceTableDao {

    @Query("SELECT COUNT(*) FROM priceTable")
    long getCountAllPrice();

    @Query("SELECT * FROM priceTable WHERE otdelPrice = :otdel")
    List<priceTable> getAllPriceFromOtdel(short otdel);

    @Query("SELECT * FROM priceTable WHERE namePrice LIKE :namePrice")
    priceTable getPriceByID(String namePrice);

    @Insert
    void addPrice(priceTable priceTable);

    @Update
    void update(priceTable priceTable);

    @Delete
    void delete(priceTable priceTable);
}

package com.conditer.conditercompany.databasese;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//интерфейс взаимодействия с таблицей прайса
@Dao
public interface priceTableDao {
//запросы к таблице с прайсом 
    @Query("SELECT COUNT(*) FROM priceTable")
    long getCountAllPrice();

    @Query("SELECT * FROM priceTable WHERE otdelPrice = :otdel")
    List<priceTable> getAllPriceFromOtdel(short otdel);

    @Query("SELECT * FROM priceTable WHERE id_price LIKE :id_price")
    priceTable getPriceByID(Long id_price);

    @Query("SELECT * FROM priceTable")
    List<priceTable> getAllPrice();

    @Insert
    void addPrice(priceTable priceTable);

    @Update
    void update(priceTable priceTable);

    @Delete
    void delete(priceTable priceTable);
}

package com.conditer.conditercompany.databasese;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//интерфейс для работы с таблицей покупок
@Dao
public interface buyTableDao {
//запросы необходимые для работы с таблицец
    @Query("SELECT * FROM buyTable")
    List<buyTable> getAllFromBuyTable();

    @Query("SELECT * FROM buyTable WHERE id_user = :id_user")
    buyTable getPriceByUserID(Long id_user);

    @Query("SELECT id_price, rateBuy FROM buyTable WHERE id_user = :id_user ORDER BY id_buy")
    List<id_priceAndRate> getDistinctFromBuyTableByUI(Long id_user);

    @Insert
    void addNewBuy(buyTable buyTable);

    @Update
    void update(buyTable buyTable);

    @Delete
    void delete(buyTable buyTable);
}

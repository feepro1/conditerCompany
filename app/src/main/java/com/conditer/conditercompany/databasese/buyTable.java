package com.conditer.conditercompany.databasese;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class buyTable {

    @PrimaryKey(autoGenerate = true)
    public long id_buy = 0;

    public long id_user;

    public long id_price;

    public long rateBuy;

}

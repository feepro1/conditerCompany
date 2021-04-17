package com.conditer.conditercompany.databasese;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class priceTable {
    @PrimaryKey(autoGenerate = true)
    public long id_price = 0;

    public int otdelPrice;

    public String namePrice;

    public String categoryPrice;

    public String descriptionPrice;

}


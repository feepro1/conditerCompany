package com.conditer.conditercompany.databasese;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {userTable.class,priceTable.class,buyTable.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    public abstract userTableDao userTableDao();
    public abstract priceTableDao priceTableDao();
    public abstract buyTableDao buyTableDao();
}

package com.conditer.conditercompany.databasese;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {userTable.class,priceTable.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract userTableDao userTableDao();
    public abstract priceTableDao priceTableDao();

}

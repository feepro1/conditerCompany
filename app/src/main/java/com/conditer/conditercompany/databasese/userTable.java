package com.conditer.conditercompany.databasese;

//import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class userTable {

    @PrimaryKey(autoGenerate = true)
    public long id_user = 0;

    public String name;

    public String login;

    public String pass;
}
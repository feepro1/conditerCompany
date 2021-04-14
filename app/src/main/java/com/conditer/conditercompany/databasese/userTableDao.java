package com.conditer.conditercompany.databasese;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface userTableDao {

    @Query("SELECT * FROM userTable")
    List<userTable> getAllUsers();

    @Query("SELECT * FROM userTable WHERE id_user = :id_user")
    userTable getUserById(long id_user);

    @Query("SELECT COUNT(*) FROM userTable")
    long getUsersCount();

    @Query("SELECT id_user FROM userTable WHERE login = :login AND pass = :pass")
    long userIsReal(String login, String pass);

    @Insert
    void addUser(userTable userTable);

    @Update
    void update(userTable userTable);

    @Delete
    void delete(userTable userTable);
}

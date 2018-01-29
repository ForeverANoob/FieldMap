package com.example.andresbarreiros.fieldmap;

import java.util.List;

/**
 * Created by Andre S Barreiros on 1/29/2018.
 *

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertAll(User... users);

    @Update
    void updateUsers(User... users);

    @Delete
    void delete(User user);

}*/

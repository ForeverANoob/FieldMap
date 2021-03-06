package com.example.andresbarreiros.fieldmap;

/**
 * Created by Andre S Barreiros on 1/29/2018.
 *

@Entity
public class User {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.

}
*/
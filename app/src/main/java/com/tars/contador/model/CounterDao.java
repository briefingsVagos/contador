package com.tars.contador.model;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by lucasbonafe on 18/09/17.
 */

public interface CounterDao {

    @Query("SELECT * FROM counter order by ASC")
    List<Counter> getAll();

    @Query("SELECT * FROM counter WHERE name LIKE :name LIMIT 1")
    Counter findByName(String name);

    @Insert
    void insert(Counter counter);

    @Update
    void update(Counter counter);

    @Delete
    void delete(Counter counter);
}

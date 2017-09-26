package com.tars.counter.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by lucasbonafe on 18/09/17.
 */

@Dao
public interface CounterDao {

    @Query("SELECT * FROM counter")
    List<Counter> getAll();

    @Query("SELECT * FROM counter WHERE title LIKE :title LIMIT 1")
    Counter findByName(String title);

    @Insert
    void insert(Counter counter);

    @Update
    void update(Counter counter);

    @Delete
    void delete(Counter counter);
}
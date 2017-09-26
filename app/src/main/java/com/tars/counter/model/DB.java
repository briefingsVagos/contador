package com.tars.counter.model;

import android.arch.persistence.room.RoomDatabase;

/**
 * Created by lucasbonafe on 18/09/17.
 */

@android.arch.persistence.room.Database(entities = {Counter.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract CounterDao counterDao();
}

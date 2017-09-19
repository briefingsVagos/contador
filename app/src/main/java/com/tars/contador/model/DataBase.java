package com.tars.contador.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by lucasbonafe on 18/09/17.
 */

@Database(entities = {Counter.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract CounterDao counterDao();
}

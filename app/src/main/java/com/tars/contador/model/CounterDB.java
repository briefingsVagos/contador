package com.tars.contador.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by lucasbonafe on 18/09/17.
 */

public class CounterDB {

    private final String DATABASE_NAME = "counterdb";
    private static CounterDB instance;
    private CounterDB() {}

    public static CounterDB getInstance() {
        if (instance == null)
            return new CounterDB();

        return instance;
    }

    public DataBase getDataBase(Context context)
    {
        return Room.databaseBuilder(context
                ,DataBase.class, DATABASE_NAME).build();
    }
}

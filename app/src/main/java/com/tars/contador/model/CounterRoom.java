package com.tars.contador.model;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by lucasbonafe on 18/09/17.
 */

public class CounterRoom {

    private final String DATABASE_NAME = "counterdb";
    private static CounterRoom instance;
    private CounterRoom() {}

    public static CounterRoom getInstance() {
        if (instance == null)
            return new CounterRoom();

        return instance;
    }

    public DB getDatabase(Context context) {
        return Room.databaseBuilder(context
                ,DB.class, DATABASE_NAME).build();
    }
}

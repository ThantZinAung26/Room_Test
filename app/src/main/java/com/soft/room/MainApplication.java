package com.soft.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.soft.room.model.AppDatabase;
import com.soft.room.model.dao.DepartmentDAO;
import com.soft.room.model.entity.Deparment;

import java.util.concurrent.Executors;

public class MainApplication extends Application {

    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static AppDatabase getDatabase(final Context context) {

        if (database == null){
            database = Room.databaseBuilder(context,AppDatabase.class, "")
                    .allowMainThreadQueries()
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            Executors.newSingleThreadExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Deparment depHead = new Deparment("Head");
                                    Deparment depYgn = new Deparment("YGN");
                                    Deparment depMDY = new Deparment("MDY");

                                    DepartmentDAO dao = getDatabase(context).departmentDAO();
                                    dao.insert(depHead);
                                    dao.insert(depYgn);
                                    dao.insert(depMDY);
                                }
                            });

                        }
                    })
                    .build();
        }

        return database;
    }

    public static void setDatabase(AppDatabase database) {
        MainApplication.database = database;
    }
}

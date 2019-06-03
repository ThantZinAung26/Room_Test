package com.soft.room.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.soft.room.model.dao.DepartmentDAO;
import com.soft.room.model.dao.DeveloperDAO;
import com.soft.room.model.entity.Deparment;
import com.soft.room.model.entity.Developer;

@TypeConverters({Converter.class})
@Database(entities = {Developer.class, Deparment.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DepartmentDAO departmentDAO();

    public abstract DeveloperDAO developerDAO();

}

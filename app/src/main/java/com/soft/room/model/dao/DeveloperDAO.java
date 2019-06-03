package com.soft.room.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.soft.room.model.entity.Developer;

import java.util.List;
@Dao
public interface DeveloperDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Developer developer);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Developer developer);

    @Delete
    void delete(Developer developer);

    @Query("SELECT * FROM DEVELOPER WHERE id = :id LIMIT 1")
    Developer getDeveloper(int id);

    @Query("SELECT * FROM DEVELOPER")
    List<Developer> getAll();

    @Query("SELECT * FROM DEVELOPER WHERE department_id = :departmentId")
    List<Developer> getDevelopersByDepartment(int departmentId);



}

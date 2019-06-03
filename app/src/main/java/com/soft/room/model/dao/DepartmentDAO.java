package com.soft.room.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.soft.room.model.entity.Deparment;

import java.util.List;

@Dao
public interface DepartmentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Deparment deparment);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Deparment deparment);

    @Delete
    void delete(Deparment deparment);

    @Query("SELECT * FROM Deparment WHERE id = :id LIMIT 1")
    Deparment getDepartment(int id);

    @Query("SELECT * FROM Deparment")
    List<Deparment> getAllDepartment();

}

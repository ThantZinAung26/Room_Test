package com.soft.room.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Deparment.class,
                parentColumns = "id",
                childColumns = "department_id"
        )}, indices = {@Index("department_id")})
public class Developer {

    public enum Skill {
        WEB, MOBILE, BACKEND, FULLSTACK
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;
    private Skill skill;

    @Ignore
    private Deparment deparment;

    @ColumnInfo(name = "department_id")
    private int departmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Deparment getDeparment() {
        return deparment;
    }

    public void setDeparment(Deparment deparment) {
        this.deparment = deparment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id &&
                age == developer.age &&
                departmentId == developer.departmentId &&
                Objects.equals(name, developer.name) &&
                skill == developer.skill;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, skill, departmentId);
    }
}

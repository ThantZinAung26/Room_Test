package com.soft.room.model;

import androidx.room.TypeConverter;

import com.soft.room.model.entity.Developer;

public class Converter {

    @TypeConverter
    public static String formSkill(Developer.Skill skill) {
        return skill.name();
    }

    @TypeConverter
    public static Developer.Skill toSkill(String value) {
        return Developer.Skill.valueOf(value);
    }

}

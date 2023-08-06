package com.softuni.pathfinder.model.viewModel;

import com.softuni.pathfinder.model.entity.enums.LevelEnum;

public class UserViewModel {
    private String fullName;
    private String username;
    private Integer age;
    private LevelEnum level;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }
}

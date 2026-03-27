package com.fighting.domain;

import java.util.ArrayList;

public class PlayerCharacter extends Character {

    public ArrayList<String> skillList; // 技能列表

    public PlayerCharacter(String name, int HP, int attack, int defense) {
        super(name, HP, attack, defense);
        this.skillList = new ArrayList<>();
    }
}

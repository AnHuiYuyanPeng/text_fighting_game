package com.fighting.domain;

public class Character {
    String name; // 角色名称
    int HP; // 血量
    int maxHP; // 最大血量
    int attack; // 攻击力
    int defense; // 防御力

    public Character(String name, int HP, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
    }

    // 是否存活
    public boolean isAlive() {
        return HP > 0;
    }
    
    // 是否恢复血量
    public void isFullHP(int HP) {
        this.HP += HP;
        if (this.HP > this.maxHP) {
            this.HP = this.maxHP;
        }
    }

    // 是否受到伤害
    public void isDamage(int damage) {
        this.HP -= damage;
        if (this.HP < 0) {
            this.HP = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}

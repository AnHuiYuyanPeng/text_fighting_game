package com.fighting.domain;

public class EnemyCharacter extends Character {

    public String skill; // 技能名称
    public boolean defending; // 是否防御

    public EnemyCharacter(String name, int HP, int attack, int defense, String skill) {
        super(name, HP, attack, defense);
        this.skill = skill;
        this.defending = false;
    }

    @Override
    public void isDamage(int damage) {
        // 防御状态 伤害减半
        if (this.defending) {
            damage = damage / 2 > 1 ? damage / 2 : 1;
        }

        // 受到伤害
        super.isDamage(damage);
        this.defending = false;
    }
    public String getSkill() {
        return skill;
    }

}

package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class AreaSkill extends Skill {
    public AreaSkill(String skillName, int basePower, EffectImplementor effect) {
        super(skillName, basePower, effect);
    }

    @Override
    public void cast(CombatNode target) {
        int damage =resolvedDamage();
        applyDamage(target, damage);
    }
    private void applyDamage(CombatNode node, int damage){
        if (!node.isAlive()) return;
        if (node.getChildren().isEmpty()){
            node.takeDamage(damage);
            return;
        }
        for (CombatNode child : node.getChildren()){
            applyDamage(child, damage);
        }
    }
}

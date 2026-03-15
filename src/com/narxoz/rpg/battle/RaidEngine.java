package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;
import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill teamASkill, Skill teamBSkill) {
        RaidResult result = new RaidResult();
        int rounds=0;
        while(teamA.isAlive() && teamB.isAlive() && rounds<50){
            rounds++;
            result.addLine("Round " + rounds);

            if(teamA.isAlive()){
                result.addLine(teamA.getName() +" casts "+teamASkill.getSkillName());
                teamASkill.cast(teamB);
            }
            if(teamB.isAlive()){
                result.addLine(teamB.getName()+ " casts " + teamBSkill.getSkillName());
                teamBSkill.cast(teamA);
            }

            result.addLine("TeamA HP: " +teamA.getHealth());
            result.addLine("TeamB HP: " +teamB.getHealth());
            result.addLine("");
        }
        result.setRounds(rounds);

        if(teamA.isAlive() && !teamB.isAlive()){
            result.setWinner(teamA.getName());
        } 
        else if(teamB.isAlive() && !teamA.isAlive()) {
            result.setWinner(teamB.getName());
        } 
        else{
            result.setWinner("Draw");
        }
        return result;
    }
}

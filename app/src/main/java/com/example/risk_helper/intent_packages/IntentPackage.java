package com.example.risk_helper.intent_packages;

import java.io.Serializable;

public class IntentPackage implements Serializable {
    int[] attacker;
    int[] defender;

    /**
     * simple IntentPackage stores attacker-field and defender-fields (input from user)
     * @param attackerArray
     * @param defenderArray
     */
    public IntentPackage(int[] attackerArray, int[] defenderArray) {
        this.attacker = attackerArray;
        this.defender = defenderArray;
    }

    public int[]getAttacker() {
        return attacker;
    }

    public int[] getDefender() {
        return defender;
    }
}

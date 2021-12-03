package com.example.risk_helper.other;

import java.io.Serializable;

public class IntentPackage implements Serializable {
    int[] attacker;
    int[] defender;
    InformationForWar informationForWar;

    public IntentPackage(int[] attackerArray, int[] defenderArray) {
        this.attacker = attackerArray;
        this.defender = defenderArray;
    }

    public IntentPackage(InformationForWar informationForWar) {
        this.informationForWar = informationForWar;
    }

    public InformationForWar getInformationForWar() {
        return informationForWar;
    }



    public int[]getAttacker() {
        return attacker;
    }

    public int[] getDefender() {
        return defender;
    }
}

package com.example.risk_helper.intent_packages;

import java.io.Serializable;

public class CalculatedListView implements Serializable {

    private String attackerCount, defenderCount, winningProbabilityAttacker,
            winningProbabilityDefender, remainingAttacker, remainingDefender;
    InformationForWar information;

    /**
     * Constructor needed to send via Intent to other activity, has another intent package
     * stored (informationForWar)
     * @param informationForWar
     */
    public CalculatedListView(InformationForWar informationForWar) {
        this.attackerCount = informationForWar.getAttack_name();
        this.defenderCount = informationForWar.getDefend_name();
        this.winningProbabilityAttacker = informationForWar.getAtt_win_name();
        this.winningProbabilityDefender = informationForWar.getDef_win_name();
        this.remainingAttacker = informationForWar.getRemaining_att_name();
        this.remainingDefender = informationForWar.getRemaining_def_name();
        this.information = informationForWar;
    }

    public String getAttackerCount() {
        return attackerCount;
    }

    public String  getDefenderCount() {
        return defenderCount;
    }

    public String getWinningProbabilityAttacker() {
        return winningProbabilityAttacker;
    }

    public String getWinningProbabilityDefender() {
        return winningProbabilityDefender;
    }

    public String getRemainingAttacker() {
        return remainingAttacker;
    }

    public String getRemainingDefender() {
        return remainingDefender;
    }

    public InformationForWar getInformationForWar() {
        return information;
    }
}

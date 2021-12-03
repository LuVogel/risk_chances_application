package com.example.risk_helper.other;

import java.io.Serializable;

public class CalculatedListView implements Serializable {

    private String attackerCount, defenderCount, winningProbabilityAttacker,
            winningProbabilityDefender, remainingAttacker, remainingDefender;
    InformationForWar information;



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

    public void setAttackerCount(String attackerCount) {
        this.attackerCount = attackerCount;
    }

    public String  getDefenderCount() {
        return defenderCount;
    }

    public void setDefenderCount(String  defenderCount) {
        this.defenderCount = defenderCount;
    }

    public String getWinningProbabilityAttacker() {
        return winningProbabilityAttacker;
    }

    public String getWinningProbabilityDefender() {
        return winningProbabilityDefender;
    }

    public void setWinningProbabilityAttacker(String winningProbabilityAttacker) {
        this.winningProbabilityAttacker = winningProbabilityAttacker;
    }

    public String getRemainingAttacker() {
        return remainingAttacker;
    }

    public void setRemainingAttacker(String remainingAttacker) {
        this.remainingAttacker = remainingAttacker;
    }

    public String getRemainingDefender() {
        return remainingDefender;
    }

    public void setRemainingDefender(String remainingDefender) {
        this.remainingDefender = remainingDefender;
    }

    public InformationForWar getInformationForWar() {
        return information;
    }
}

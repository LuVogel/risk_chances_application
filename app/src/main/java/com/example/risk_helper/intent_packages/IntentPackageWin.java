package com.example.risk_helper.intent_packages;

import java.io.Serializable;

public class IntentPackageWin implements Serializable {

    String winner;

    /**
     * intentPackage to send to the winning screen (who is the winner)
     * @param winner
     */
    public IntentPackageWin(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}

package com.example.risk_helper.other;

import java.io.Serializable;

public class IntentPackageWin implements Serializable {

    String winner;

    public IntentPackageWin(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}

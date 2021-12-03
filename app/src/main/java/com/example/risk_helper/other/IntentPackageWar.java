package com.example.risk_helper.other;

import java.io.Serializable;

public class IntentPackageWar implements Serializable {

    InformationForWar informationForWar;

    public IntentPackageWar(InformationForWar informationForWar) {
        this.informationForWar = informationForWar;
    }

    public InformationForWar getInformationForWar() {
        return informationForWar;
    }
}

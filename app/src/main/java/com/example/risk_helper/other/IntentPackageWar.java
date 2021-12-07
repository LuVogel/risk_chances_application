package com.example.risk_helper.other;

import java.io.Serializable;

public class IntentPackageWar implements Serializable {

    InformationForWar informationForWar;
    IntentPackage intentPackage;

    public IntentPackageWar(InformationForWar informationForWar, IntentPackage intentPackage) {
        this.informationForWar = informationForWar;
        this.intentPackage = intentPackage;
    }

    public InformationForWar getInformationForWar() {
        return informationForWar;
    }

    public IntentPackage getIntentPackageFromWar() {
        return intentPackage;
    }
}

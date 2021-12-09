package com.example.risk_helper.intent_packages;

import com.example.risk_helper.intent_packages.InformationForWar;
import com.example.risk_helper.intent_packages.IntentPackage;

import java.io.Serializable;

public class IntentPackageWar implements Serializable {

    InformationForWar informationForWar;
    IntentPackage intentPackage;

    /**
     * IntentPackage, which contains custom IntentPackage(with attacker,defender) and
     * informationForWar-IntentPackage
     * @param informationForWar
     * @param intentPackage
     */
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

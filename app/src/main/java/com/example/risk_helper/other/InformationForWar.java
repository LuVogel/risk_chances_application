package com.example.risk_helper.other;

import android.icu.text.IDNA;

public class InformationForWar {


    String attack_name, defend_name, remaining_att_name, remaining_def_name, att_win_name,
            def_win_name;




    public InformationForWar(String attack_name, String defend_name, String remaining_att_name,
                             String remaining_def_name, String att_win_name, String def_win_name) {
                this.attack_name = attack_name;
                this.defend_name = defend_name;
                this.remaining_att_name = remaining_att_name;
                this.remaining_def_name = remaining_def_name;
                this.att_win_name = att_win_name;
                this.def_win_name = def_win_name;
    }

    public String getAttack_name() {
        return attack_name;
    }

    public String getDefend_name() {
        return defend_name;
    }

    public String getRemaining_att_name() {
        return remaining_att_name;
    }

    public String getRemaining_def_name() {
        return remaining_def_name;
    }

    public String getAtt_win_name() {
        return att_win_name;
    }

    public String getDef_win_name() {
        return def_win_name;
    }


}

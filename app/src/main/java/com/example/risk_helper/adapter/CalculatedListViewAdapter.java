package com.example.risk_helper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.risk_helper.R;
import com.example.risk_helper.intent_packages.CalculatedListView;

import java.util.ArrayList;

public class CalculatedListViewAdapter extends ArrayAdapter<CalculatedListView> {

    /**
     * CustomListAdapter, used to show dynamic length of listview which has stored
     * listviews as elements
     * @param context
     * @param arrayList
     */
    public CalculatedListViewAdapter(@NonNull Context context, ArrayList<CalculatedListView> arrayList) {
        super(context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_calculater_view, parent, false);
        }
        CalculatedListView currentListPosition = getItem(position);
        TextView attackerCountView = currentItemView.findViewById(R.id.attacker_count_textView);
        TextView defenderCountView = currentItemView.findViewById(R.id.defender_count_textView);
        TextView winningProbabilityViewAttacker = currentItemView.findViewById(R.id.winning_probability_textView_attacker);
        TextView winningProbabilityViewDefender = currentItemView.findViewById(R.id.winning_probability_textView_defender);
        TextView attackerLossView = currentItemView.findViewById(R.id.attacker_loss_textView);
        TextView defenderLossView = currentItemView.findViewById(R.id.defender_loss_textView);

        assert currentListPosition != null;


        attackerCountView.setText(currentListPosition.getAttackerCount());
        defenderCountView.setText(currentListPosition.getDefenderCount());
        winningProbabilityViewAttacker.setText(currentListPosition.getWinningProbabilityAttacker());
        winningProbabilityViewDefender.setText(currentListPosition.getWinningProbabilityDefender());
        attackerLossView.setText(currentListPosition.getRemainingAttacker());
        defenderLossView.setText(currentListPosition.getRemainingDefender());



        return currentItemView;
    }
}

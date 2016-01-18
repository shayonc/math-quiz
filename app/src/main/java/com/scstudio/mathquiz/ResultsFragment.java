package com.scstudio.mathquiz;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultsFragment extends Fragment {

    public interface ResultsListener {
        public void onGoMainMenu();
    }

    static final String ARG_SCORE = "com.scstudio.mathquiz.ARGSCORE";
    static final String ARG_QLENGTH = "com.scstudio.mathquiz.ARGQLENTH";
    static final String ARG_TIME = "com.scstudio.mathquiz.ARGTIME";
    ResultsListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            Activity activity = (Activity) context;
            listener = (ResultsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ResultsListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        int score = args.getInt(ARG_SCORE, 0);
        int qlength = args.getInt(ARG_QLENGTH, 25);
        String time = args.getString(ARG_TIME);

        double accr = ((double)score/(double)qlength)*100;
        int accr_int = (int)accr;

        View v = getView();
        TextView tvScore = (TextView) v.findViewById(R.id.txtScore);
        tvScore.setText(String.format("Score: %d/%d",score, qlength));
        TextView tvAccr = (TextView) v.findViewById(R.id.txtAccuracy);
        tvAccr.setText(String.format("Accuracy: %d", accr_int ) + "%");
        TextView tvTime = (TextView) v.findViewById(R.id.txtTimeTaken);
        tvTime.setText(String.format("Time: %s", time));

        Button btnMM = (Button) v.findViewById(R.id.btnGoMainMenu);
        btnMM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onGoMainMenu();
            }
        });
    }
}
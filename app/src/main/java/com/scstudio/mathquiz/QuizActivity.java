package com.scstudio.mathquiz;

//import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends FragmentActivity implements NumpadFragment.NumpadListener, ResultsFragment.ResultsListener {

    int qLength = 25;
    int qCount = 1;
    int numCorrect = 0;
    long timeElapsed = 0;
    String timeFormatted = "";
    boolean quizOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        qLength = getIntent().getIntExtra(MainMenuActivity.EXTRA_LENGTH,25);

        ListView listView = (ListView) findViewById(R.id.lstView);
        //listView.setDivider(null);

        Question q1 = getQuizQuestion(qCount);
        ArrayList<Question> listQuestions = new ArrayList<Question>();
        listQuestions.add(q1);

        QArrayAdapter adapter = new QArrayAdapter(this, listQuestions);
        listView.setAdapter(adapter);

        TextView tvProg = (TextView) findViewById(R.id.txtProgress);
        tvProg.setText(String.format("%d / %d", qCount, qLength));

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnTopExit);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoMainMenu();
            }
        });

        if (savedInstanceState == null) {
            NumpadFragment numpadFrag = new NumpadFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.numpadContainer, numpadFrag).commit();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!quizOver) {
            Chronometer quizTimer = (Chronometer) findViewById(R.id.chronometer);
            quizTimer.setBase(SystemClock.elapsedRealtime() - timeElapsed);
            quizTimer.start();
        }
    }

    @Override
    protected void onStop() {
        if (!quizOver) {
            Chronometer quizTimer = (Chronometer) findViewById(R.id.chronometer);
            timeElapsed = SystemClock.elapsedRealtime() - quizTimer.getBase();
            quizTimer.stop();
        }
        super.onStop();
    }

    public void onNumClick(int input) {
        try {
            ListView listView = (ListView) findViewById(R.id.lstView);
            QArrayAdapter adapter = (QArrayAdapter) listView.getAdapter();
            Question item = adapter.getItem(qCount - 1);
            String prevInput = item.getInput();
            item.setInput(prevInput + String.format("%d", input));
            adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(qCount - 1);
        } catch (Exception e) {}
    }

    public void onDelClick() {
        try {
            ListView listView = (ListView) findViewById(R.id.lstView);
            QArrayAdapter adapter = (QArrayAdapter) listView.getAdapter();
            Question item = adapter.getItem(qCount - 1);
            String prevInput = item.getInput();
            if (prevInput.length() != 0) {
                item.setInput(prevInput.substring(0, (prevInput.length() - 1)));
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(qCount - 1);
            }
        } catch (Exception e) {}
    }

    public void onDelHold()
    {
        try {
            ListView listView = (ListView) findViewById(R.id.lstView);
            QArrayAdapter adapter = (QArrayAdapter) listView.getAdapter();
            Question item = adapter.getItem(qCount - 1);
            String prevInput = item.getInput();
            if (prevInput.length() != 0) {
                item.setInput("");
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(qCount - 1);
            }
        } catch (Exception e) {}
    }

    public void onEntClick() {
        try {
            ListView listView = (ListView) findViewById(R.id.lstView);
            QArrayAdapter adapter = (QArrayAdapter) listView.getAdapter();
            Question item = adapter.getItem(qCount - 1);
            String strInput = item.getInput();
            if (strInput.length() != 0) {
                int intInput = Integer.parseInt(strInput);
                if (intInput == item.getAns()) {
                    item.setStatus(QStatus.Right);
                    numCorrect++;
                }
                else {
                    item.setStatus(QStatus.Wrong);
                }
                adapter.notifyDataSetChanged();

                if (qCount < qLength) {
                    qCount++;
                    TextView tvProg = (TextView) findViewById(R.id.txtProgress);
                    tvProg.setText(String.format("%d / %d", qCount,qLength));

                    Question q = getQuizQuestion(qCount);
                    adapter.add(q);
                    listView.smoothScrollToPosition(qCount-1);
                }
                else {
                    quizOver = true;
                    Chronometer quizTimer = (Chronometer) findViewById(R.id.chronometer);
                    timeElapsed = SystemClock.elapsedRealtime() - quizTimer.getBase();
                    quizTimer.stop();

                    timeFormatted = String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
                            TimeUnit.MILLISECONDS.toSeconds(timeElapsed) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))
                    );

                    ResultsFragment resultsFrag = new ResultsFragment();
                    Bundle args = new Bundle();
                    args.putInt(ResultsFragment.ARG_SCORE, numCorrect);
                    args.putInt(ResultsFragment.ARG_QLENGTH, qLength);
                    args.putString(ResultsFragment.ARG_TIME, timeFormatted);
                    resultsFrag.setArguments(args);

                    getSupportFragmentManager().beginTransaction().replace(R.id.numpadContainer, resultsFrag).commit();
                }
            }
        } catch (Exception e) {}
    }

    public void onGoMainMenu() {
        finish();
    }

    public Question getQuizQuestion(int count){
        Random rand = new Random();
        int intOper = rand.nextInt(4);
        int num1 = 0, num2 = 0, ans = 0;
        char oper = '+';

        switch (intOper) {
            case 0: //+
                oper = '+';
                num1 = rand.nextInt(30)+1;
                num2 = rand.nextInt(30)+1;
                ans = num1 + num2;
                break;
            case 1: //-
                oper = '-';
                num1 = rand.nextInt(30)+6;
                num2 = rand.nextInt(num1)+1;
                ans = num1 - num2;
                break;
            case 2: //×
                oper = '×';
                num1 = rand.nextInt(15)+1;
                num2 = rand.nextInt(15)+1;
                ans = num1 * num2;
                break;
            case 3: //÷
                oper = '÷';
                ArrayList<Integer> factors = new ArrayList<Integer>();
                do {
                    num1 = rand.nextInt(97)+4;
                    int limit = num1;
                    for (int i = 2; i < limit; i++) {
                        if (num1 % i == 0) {
                            factors.add(i);
                            limit = num1 / i;
                            factors.add(limit);
                        }
                    }
                } while (factors.size() == 0); //if num1 is prime

                int index = rand.nextInt(factors.size());
                num2 = factors.get(index);
                ans = num1 / num2;
                break;
        }
        Question q = new Question(num1,num2,oper,ans,count);
        return q;
    }
}

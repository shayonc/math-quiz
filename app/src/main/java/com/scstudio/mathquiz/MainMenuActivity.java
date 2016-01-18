package com.scstudio.mathquiz;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;

public class MainMenuActivity extends Activity implements QuizSizeDialogPrompt.DialogListener {

    public final static String EXTRA_LENGTH = "com.scstudio.mathquiz.LENGTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimal_menu);
    }

    public void onBtnStartClick(View view) {
        DialogFragment dialog = new QuizSizeDialogPrompt();
        dialog.show(getFragmentManager(),getString(R.string.tag_qlength));
    }

    public void onBtnInstrClick(View view) {
        //Coming soon: in project Phase 2
    }

    public void onBtnScoreClick(View view) {
        //Coming soon: in project Phase 2
    }

    public void onGoClick(int choice) {
        Intent intent = new Intent(this,QuizActivity.class);
        int num;
        switch (choice)
        {
            case 0:
                num = 25;
                break;
            case 1:
                num = 50;
                break;
            case 2:
                num = 100;
                break;
            default:
                num = 25;
                break;
        }
        intent.putExtra(EXTRA_LENGTH,num);
        startActivity(intent);
    }
}

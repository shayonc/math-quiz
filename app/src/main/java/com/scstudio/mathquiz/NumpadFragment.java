package com.scstudio.mathquiz;

import android.app.Activity;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NumpadFragment extends Fragment {

    public interface NumpadListener {
        public void onNumClick(int input);
        public void onDelClick();
        public void onDelHold();
        public void onEntClick();
    }

    NumpadListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            Activity activity = (Activity) context;
            listener = (NumpadListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NumpadListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_numpad, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btn0 = (Button) getView().findViewById(R.id.button0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(0);
            }
        });
        Button btn1 = (Button) getView().findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(1);
            }
        });
        Button btn2 = (Button) getView().findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(2);
            }
        });
        Button btn3 = (Button) getView().findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(3);
            }
        });
        Button btn4 = (Button) getView().findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(4);
            }
        });
        Button btn5 = (Button) getView().findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(5);
            }
        });
        Button btn6 = (Button) getView().findViewById(R.id.button6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(6);
            }
        });
        Button btn7 = (Button) getView().findViewById(R.id.button7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(7);
            }
        });
        Button btn8 = (Button) getView().findViewById(R.id.button8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(8);
            }
        });
        Button btn9 = (Button) getView().findViewById(R.id.button9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onNumClick(9);
            }
        });
        Button btnDel = (Button) getView().findViewById(R.id.buttonDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onDelClick();
            }
        });
        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null)
                    listener.onDelHold();
                return true;
            }
        });
        Button btnEnt = (Button) getView().findViewById(R.id.buttonEnt);
        btnEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onEntClick();
            }
        });
    }
}

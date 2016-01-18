package com.scstudio.mathquiz;

public class Question {
    int num1;
    int num2;
    char oper;
    int ans;
    int count; //question number
    String input;
    QStatus status;

    public Question(int num1, int num2, char oper, int ans, int count) {
        this.num1 = num1;
        this.num2 = num2;
        this.oper = oper;
        this.ans = ans;
        this.count = count;
        this.input = "";
        this.status = QStatus.Unanswered;
    }

    public int getNum1() {
        return this.num1;
    }
    public int getNum2() {
        return this.num2;
    }
    public char getOper() {
        return this.oper;
    }
    public int getAns() {
        return this.ans;
    }
    public int getCount() {
        return this.count;
    }
    public String getInput() {
        return this.input;
    }
    public QStatus getStatus() {
        return this.status;
    }
    public String getLine() {
        return String.format("%d)  %d %c %d = ",count, num1,oper,num2);
    }

    public void setInput(String s) {
        this.input = s;
    }
    public void setStatus(QStatus s) {
        this.status = s;
    }
}

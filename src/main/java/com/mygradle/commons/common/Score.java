package com.mygradle.commons.common;

public class Score {
    private int math;
    private int eng;

    public Score(int math, int eng){
        this.math = math;
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public double average(){
        return (double)(math + eng) / 2;
    }

    @Override
    public String toString(){
        return "수학 : " + math + " / 영어 : " + eng;
    }
}
class Hi{
    Score score = new Score(10, 10);
    public void hi(){
        score.setMath(-score.getMath());
    }
}
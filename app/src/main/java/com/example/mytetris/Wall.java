package com.example.mytetris;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Wall {
    private ArrayList<MyBox>boxiesWall=new ArrayList<>();
    private int[]count;
    private int number;
    private int level=1;
    private int score=0;
    private int lines=0;
    private int firstLevelScore=20;
    private int scoreInThisLevel=0;
    public Wall(int[] count,int number){
        this.count=count;
        this.number=number;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getLines() {
        return lines;
    }

    public void gamePlay(){
        if (scoreInThisLevel>=(firstLevelScore*level))
        {
            scoreInThisLevel=(firstLevelScore*level)-scoreInThisLevel;
            level++;
        }
    }

    public void add(Tetramer tetramer){
        for(int i=0;i<4;i++)
        {
            boxiesWall.add(tetramer.getBoxies()[i]);
        }
    }

    public ArrayList<MyBox> getBoxiesWall() {
        return boxiesWall;
    }

    public void linesDown(int index){
        for(MyBox box:boxiesWall)
        {
            if(box.getY()<index)
            {
                box.setY(box.getY()+1);
            }
        }
    }

    public int[] getCount() {
        return count;
    }

    public void linesDelete(int index) {
        MyBox mb=new MyBox(0,index);
        while(boxiesWall.remove(mb)) ;
        count[index]=0;
        linesDown(index);
        lines++;
    }

    public void yRound(){
        for(MyBox box:boxiesWall)
        {
            box.setY(Math.round(box.getY()));
        }
    }

    public boolean formCount() {
        int countLines=0;

        for(int i=0;i<count.length;i++)
        {
            count[i]=0;
        }
        for(int i=0;i<count.length;i++)
        {
            for(MyBox box:boxiesWall)
            {
                if((int)box.getY()==i)
                {
                    count[i]++;
                }
            }
        }
        if(count[0]!=0)
        {
            return false;
        }

        for(int i=0;i<count.length;i++) {
            if(count[i]==number)
            {
                countLines++;
                linesDelete(i);
            }
        }
        switch(countLines) {
            case 1:
                scoreInThisLevel++;
                score++;
                break;
            case 2:
                scoreInThisLevel += 3;
                score += 3;
                break;
            case 3:
                scoreInThisLevel += 5;
                score += 5;
                break;
            case 4:
                scoreInThisLevel += 8;
                score += 8;
                break;
            default:
                break;
        }
        gamePlay();
        return true;
    }

//    public void wallSort(){
//        Collections.sort(boxiesWall);
//        boxiesWall.sort(new Comparator(){
//            @Override
//            public int compare(Object o1,Object o2) {
//                MyBox m1=(MyBox)o1;
//                MyBox m2=(MyBox)o2;
//                return Integer.compare((int)m1.getY(),(int)m2.getY());
//            }
//        });
////        for(MyBox box:boxiesWall)
////        {
////
////        }
//    }

    public void draw(Canvas canvas, Paint paint, int scale, int shiftX, int shiftY){
        for(MyBox box:boxiesWall)
        {
            box.draw(canvas,paint,scale,shiftX,shiftY);
        }
    }
}

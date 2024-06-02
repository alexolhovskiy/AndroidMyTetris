package com.example.mytetris;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Tetramer {
    private int xShift;
    private int columns;
    private int rows;
    private Random rand=new Random();
    private final double DIRANG=Math.PI/2;
    private final double HALFDIRANG=Math.PI/4;
    private int[]arrX=new int[4];
    private double[]arrY=new double[4];
    private int[]R=new int[4];
    private double[]ang=new double[4];

    private MyBox[]boxies=new MyBox[4];

    private boolean over=false;

    private Wall wall;

    private int width=0;
    private int height=0;
    private double step=0.10;
    public Tetramer(int x,double y,int xShift,int columns,int rows,Wall wall){
        this.xShift=xShift;
        this.columns=columns;
        this.rows=rows;
        this.wall=wall;
        arrY[0]=y;
        arrX[0]=x;
        choice();
        takeCoord();
        for(int i=0;i<4;i++) {
            boxies[i] = new MyBox(arrX[i], arrY[i]);
        }
    }

    public void control(float x,float y){

        if(x<width/3) {
            shift(-1);
        }
        else
        {
            if(x>(width/3)*2)
            {
                shift(1);
            }
            else
            {
                rotateTetramer();
            }
        }
    }

    public MyBox[] getBoxies() {
        return boxies;
    }

    public void choice(){
        switch(rand.nextInt(5)){
            case 0://Z
                R[0]=0;
                ang[0]=0;
                R[1]=1;
                ang[1]=0;
                R[2]=1;
                ang[2]=DIRANG;
                R[3]=2;
                ang[3]=Math.PI*2-HALFDIRANG;
                break;
            case 1://T
                R[0]=0;
                ang[0]=0;
                R[1]=1;
                ang[1]=0;
                R[2]=1;
                ang[2]=Math.PI;
                R[3]=1;
                ang[3]=Math.PI+DIRANG;
                break;
            case 2://Г
                R[0]=0;
                ang[0]=0;
                R[1]=1;
                ang[1]=DIRANG;
                R[2]=1;
                ang[2]=Math.PI+DIRANG;
                R[3]=2;
                ang[3]=Math.PI*2-HALFDIRANG;
                break;
            case 3://#
                R[0]=0;
                ang[0]=0;
                R[1]=1;
                ang[1]=0;
                R[2]=2;
                ang[2]=HALFDIRANG;
                R[3]=1;
                ang[3]=DIRANG;
                break;
            case 4://|
                R[0]=0;
                ang[0]=0;
                R[1]=1;
                ang[1]=DIRANG;
                R[2]=1;
                ang[2]=Math.PI+DIRANG;
                R[3]=2;
                ang[3]=Math.PI+DIRANG;
                break;
        }
    }

    public void rotateTetramer(){//!!!!!
        for(int i=1;i<4;i++)
        {
            ang[i]+=DIRANG;
        }
        takeCoord();
    }

    public void takeCoord(){
        for(int i=1;i<4;i++) {
            arrX[i] = arrX[0] + (int) (Math.cos(ang[i]) * R[i]);
            arrY[i] = arrY[0] + (int) (Math.sin(ang[i]) * R[i]);
        }
    }

    public void reset(){
        arrY[0]=-2;
        arrX[0]=5;
        choice();
        takeCoord();
        for(int i=0;i<4;i++) {
            boxies[i] = new MyBox(arrX[i], arrY[i]);
        }
    }

    public void goDown(){
        boolean go=true;
        for (int i = 0; i < 4; i++) {
            arrY[i] += step;
        }
        for (int i = 0; i < 4 && go; i++) {
            if ((boxies[i].outVertical(rows))||(boxies[i].contactVertical(wall))) {
                for (int j = 0; j < 4; j++) {
                    arrY[j] += rows - arrY[i];
                }
                wall.add(this);
                wall.yRound();
                if(wall.formCount()) {
                    step = (double) wall.getLevel() / 10.0;
                    //wall.wallSort();
                    reset();
                    go = false;
                }
                else
                {
                    over=true;
                }
            }
        }
    }

    public boolean isOver() {
        return over;
    }

    public void shift(int shift_x){
        boolean go=true;
        for (int i = 0; i < 4 && go; i++) {
            if ((boxies[i].outHorizontal(0, (columns),(arrX[i] +shift_x))) ||
                    (boxies[i].contactHorizontal(wall,(arrX[i] +shift_x))))
            {
                go = false;
            }
        }
        if(go)
        {
            for(int i = 0; i < 4; i++) {
                arrX[i] += shift_x;
            }
        }
    }


    public void draw(Canvas canvas, Paint paint, int scale, int shiftX, int shiftY){
        width=canvas.getWidth();
        height=canvas.getHeight();
        for(int i=0;i<4;i++) {
            boxies[i].setX(arrX[i]);
            boxies[i].setY(arrY[i]);
            boxies[i].draw(canvas, paint, scale, shiftX, shiftY);
        }
    }
}

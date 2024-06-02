package com.example.mytetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Screen {
    private int xShift;
    private int columns;
    private int rows;

    private int x1,y1,x2,y2,w,h,linex,scale;

    public Screen(int xShift,int columns,int rows){
        this.xShift=xShift;
        this.columns=columns;
        this.rows=rows;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getLinex() {
        return linex;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setSize(int w, int h){
        x1=xShift;
        x2=w-xShift;
        this.w=x2-x1;
        linex=x2-this.w/3;
        scale=(linex-x1)/columns;
        this.h=scale*rows;
        y1=(h-this.h)/2;
        y2=y1+this.h;
    }
    public void draw(Canvas canvas,Paint paint){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        setSize(canvas.getWidth(),canvas.getHeight());
        canvas.drawRect(x1,y1,x2,y2,paint);
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawLine(linex,y1,linex,y2,paint);
    }
}

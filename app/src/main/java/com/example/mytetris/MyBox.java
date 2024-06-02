package com.example.mytetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyBox{
    private int x=5;
    private double y=6;

    public MyBox(int x,double y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public boolean contactHorizontal(Wall wall,int tempx){
        for(MyBox box:wall.getBoxiesWall())
        {
            if(tempx == box.getX()){
                if(((y>box.getY())&&(y<(box.getY()+1.0)))||
                        (((y+1.0)>box.getY())&&((y+1.0)<(box.getY()+1.0)))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contactVertical(Wall wall){
        for(MyBox box:wall.getBoxiesWall())
        {
            if(x == box.getX()){
                if(((y>box.getY())&&(y<(box.getY()+1.0)))||
                        (((y+1.0)>box.getY())&&((y+1.0)<(box.getY()+1.0)))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean outHorizontal(int borderMin,int borderMax,int tempx){
        if((tempx<borderMin)||((tempx+1)>borderMax))
        {
            return true;
        }
        return false;
    }

    public boolean outVertical(int borderMax){
        if((y)>(borderMax-1))
        {
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas, Paint paint, int scale,int shiftX,int shiftY){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(shiftX+x*scale,(int)(shiftY+y*scale),shiftX+x*scale+scale,(int)(shiftY+y*scale+scale),paint);
    }
//
//    @Override
//    public int compareTo(MyBox o) {
//        return Integer.compare(x,o.getX());
//    }

    public boolean equals(Object o) {
        MyBox m=(MyBox)o;
        return y==m.getY();
    }
}

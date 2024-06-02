package com.example.mytetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Draw2D extends View {
    private final int xShift=10;
    private final int columns=15;
    private final int rows=30;
    private int[]count;
    private Paint mPaint;
    private Screen screen;
    private Wall wall;
    private Tetramer tetramer;
    public Draw2D(Context context){

        super(context);
        count=new int [rows];
        for(int i=0;i<rows;i++)
        {
            count[i]=0;
        }
        mPaint = new Paint();
        screen =new Screen(xShift,columns,rows);
        wall=new Wall(count,columns);
        tetramer=new Tetramer(5,-2,xShift,columns,rows,wall);
    }

    String xyStr="";
    public boolean onTouchEvent(MotionEvent event){
        xyStr=""+event.getX()+" "+event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                tetramer.control(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawPaint(mPaint);
        mPaint.setAntiAlias(true);

        screen.draw(canvas,mPaint);
        tetramer.draw(canvas,mPaint,screen.getScale(),screen.getX1(),screen.getY1());
        tetramer.goDown();

        wall.draw(canvas,mPaint,screen.getScale(),screen.getX1(),screen.getY1());
        mPaint.setColor(Color.RED);
        //canvas.drawText(xyStr,screen.getX1(),screen.getY2()+30,mPaint);
//        for(int i=0;i<wall.getCount().length;i++)
//        {
//            canvas.drawText(""+wall.getCount()[i],screen.getLinex()+10,screen.getY1()+i*screen.getScale(),mPaint);
//        }
        mPaint.setTextSize(30);
        canvas.drawText("LEVEL "+wall.getLevel(),screen.getLinex()+20,screen.getY1()+20*screen.getScale(),mPaint);
        canvas.drawText("LINES "+wall.getLines(),screen.getLinex()+20,screen.getY1()+22*screen.getScale(),mPaint);
        canvas.drawText("SCORE "+wall.getScore(),screen.getLinex()+20,screen.getY1()+24*screen.getScale(),mPaint);

        if(tetramer.isOver())
        {
            mPaint.setTextSize(60);
            canvas.drawText("GAME OVER",screen.getX1()+2*screen.getScale(),screen.getY1()+15*screen.getScale(),mPaint);
        }

        invalidate();
    }
}

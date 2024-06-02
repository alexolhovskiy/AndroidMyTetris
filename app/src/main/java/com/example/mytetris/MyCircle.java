package com.example.mytetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyCircle {
    private Paint mPaint = new Paint();
    private int y;
    public MyCircle(int y){
        this.y=y;
    }

    public void move(){
        y++;
    }

    public void draw(Canvas canvas){
        move();
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(950,y,25,mPaint);
    }
}

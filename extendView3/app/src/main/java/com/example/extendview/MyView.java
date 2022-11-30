package com.example.extendview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public static final int round1 = 0;
    public static final int rec1 = 1;

    private int style;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        style = typedArray.getInt(R.styleable.MyView_style,round1);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wide = getSize(100,widthMeasureSpec);
        int high = getSize(100,heightMeasureSpec);
        if(style == rec1){
            setMeasuredDimension(wide,high);
        }else {
            if(wide < high){
                high = wide;}
            else {
                wide = high;}
            setMeasuredDimension(wide,high);
        }
    }

    private int getSize(int default_size,int measureSpec){
        int mysize = default_size;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mysize = default_size;
                break;
            case  MeasureSpec.EXACTLY:
                mysize = size;
                break;
            case  MeasureSpec.AT_MOST:
                mysize = size;
                break;
            default:
                break;
        }
        return mysize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int[] location = new int[2];
        getLocationOnScreen(location);

        if(style == round1){
            paint.setColor(Color.YELLOW);
            int r = getWidth()/2;
            canvas.drawCircle(r,r,r,paint);
        }else{
            paint.setColor(Color.BLUE);
            canvas.drawRect(0,0,getRight(),getBottom(),paint);
        }
    }
}

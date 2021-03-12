package com.example.table_list_widget;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class BkgAnimation extends View {

    //Code based on https://medium.com/@patrick.elmquist/continuous-animation-using-timeanimator-5b8a903603fb

    private static class Circle{
        private float x;
        private float y;
        private float radius;
        private Paint paint;
    }

    private final Circle[] circles = new Circle[7];
    private final Random random = new Random();

    private TimeAnimator timeAnimator;

    public BkgAnimation(Context context) {
        super(context);
    }

    public BkgAnimation(Context context, AttributeSet attrs){ //Needed in order to run
        super(context,attrs);
    }

    public BkgAnimation(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh){
        super.onSizeChanged(width,height,oldw,oldh);

        for (int i=0; i<circles.length; i++){
            final Circle circle = new Circle();
            initializeCircle(circle,width,height);
            circles[i] = circle;
        }
    }

    @Override
    protected void onAttachedToWindow(){
        super.onAttachedToWindow();
        timeAnimator = new TimeAnimator();
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT) // Needed for isLaidout
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                if(!isLaidOut()){ //Needed so that it ignores when screen is not set up yet
                    return;
                }
                update(deltaTime);
                invalidate();
            }
        });
        timeAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow(){
        super.onDetachedFromWindow();
        timeAnimator.cancel();
        timeAnimator.setTimeListener(null);
        timeAnimator.removeAllListeners();
        timeAnimator = null;
    }

    @Override
    protected void onDraw(Canvas canvas){
        for(final Circle circle: circles){
            canvas.drawCircle(circle.x,circle.y,circle.radius,circle.paint);
        }
    }

    //Changes the radius of the circles based on how much time has passed
    private void update(float deltaMs){
        final float deltaSeconds = deltaMs / 1000f;
        final int width = getWidth();
        final int height = getHeight();

        for(final Circle circle: circles){
            circle.radius -= 100* deltaSeconds;
            if (circle.radius < 1){
                initializeCircle(circle,width,height);
            }
        }
    }

    private void initializeCircle(Circle circle, int width, int height){
        circle.x = width * random.nextFloat();
        circle.y = height * random.nextFloat();
        circle.radius = height * random.nextFloat();
        circle.paint = new Paint();
        circle.paint.setARGB(random.nextInt(255),random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

}

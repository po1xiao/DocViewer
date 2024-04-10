
package com.cherry.lib.doc.dialog;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.lxj.xpopup.util.XPopupUtils;

public class LoadingView extends View {
    private Paint paint;
    private float radius;
    private float radiusOffset;
    private float stokeWidth;
    private ArgbEvaluator argbEvaluator;
    private int startColor;
    private int endColor;
    int lineCount;
    float avgAngle;
    int time;
    float centerX;
    float centerY;
    float startX;
    float endX;
    private Runnable increaseTask;

    public LoadingView(Context context) {
        this(context, (AttributeSet)null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.stokeWidth = 2.0F;
        this.argbEvaluator = new ArgbEvaluator();
        this.startColor = Color.parseColor("#1979FE");
        this.endColor = Color.parseColor("#4C1979FE");
        this.lineCount = 10;
        this.avgAngle = 360.0F / (float)this.lineCount;
        this.time = 0;
        this.increaseTask = new Runnable() {
            public void run() {
                ++LoadingView.this.time;
                LoadingView.this.postInvalidate(0, 0, LoadingView.this.getMeasuredWidth(), LoadingView.this.getMeasuredHeight());
                LoadingView.this.postDelayed(LoadingView.this.increaseTask, 80L);
            }
        };
        this.paint = new Paint(1);
        this.stokeWidth = (float)XPopupUtils.dp2px(context, this.stokeWidth);
        this.paint.setStrokeWidth(this.stokeWidth);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.radius = (float)this.getMeasuredWidth() / 2.0F;
        this.radiusOffset = this.radius / 2.5F;
        this.centerX = (float)this.getMeasuredWidth() / 2.0F;
        this.centerY = (float)this.getMeasuredHeight() / 2.0F;
        this.stokeWidth = (float)XPopupUtils.dp2px(this.getContext(), 2.0F);
        this.paint.setStrokeWidth(this.stokeWidth);
        this.startX = this.centerX + this.radiusOffset;
        this.endX = this.startX + this.radius / 3.0F;
    }

    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        for(int i = this.lineCount - 1; i >= 0; --i) {
            int temp = Math.abs(i + this.time) % this.lineCount;
            float fraction = (float)(temp + 1) * 1.0F / (float)this.lineCount;
            int color = (Integer)this.argbEvaluator.evaluate(fraction, this.startColor, this.endColor);
            this.paint.setColor(color);
            canvas.drawLine(this.startX, this.centerY, this.endX, this.centerY, this.paint);
            canvas.drawCircle(this.startX, this.centerY, this.stokeWidth / 2.0F, this.paint);
            canvas.drawCircle(this.endX, this.centerY, this.stokeWidth / 2.0F, this.paint);
            canvas.rotate(this.avgAngle, this.centerX, this.centerY);
        }

    }

    public void start() {
        this.removeCallbacks(this.increaseTask);
        this.postDelayed(this.increaseTask, 80L);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.start();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.increaseTask);
    }
}

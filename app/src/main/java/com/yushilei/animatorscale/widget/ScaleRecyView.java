package com.yushilei.animatorscale.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yushilei.animatorscale.RAdapter;

/**
 * @author by  yushilei.
 * @time 2016/9/8 -11:01.
 * @Desc
 */
public class ScaleRecyView extends RecyclerView {
    public ScaleRecyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float lastX;
    float lastY;
    String TAG = "ScaleRecyView";
    boolean isScale = false;

    float mScale = 1.0f;

    float scaleRatio = 0.7f;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        int childCount = getChildCount();
        Log.d(TAG, "childCount=" + childCount);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                View view = getChildAt(0);
                int pos = ((RAdapter.VH) view.getTag()).getPos();
                int count = getChildCount();
                if (pos == 0 && view.getTop() >= 0 && y - lastY > 0) {
                    //向下拉动
                    float v = y - lastY;
                    int height = getHeight();
                    mScale = 1 + v * scaleRatio / height;
                    setPivotY(0f);
                    setPivotX(getWidth() / 2);
                    ViewCompat.setScaleY(this, mScale);
                    isScale = true;
                }
                View lastView = getChildAt(count - 1);
                int pos1 = ((RAdapter.VH) lastView.getTag()).getPos();
                if (pos1 + 1 == getAdapter().getItemCount() && lastView.getBottom() <= getBottom() && y - lastY < 0) {
                    //滑动到最底部 向上拉
                    float distance = y - lastY;
                    mScale = 1 - scaleRatio * distance / getHeight();
                    setPivotX(getWidth() / 2);
                    setPivotY(getHeight());
                    ViewCompat.setScaleY(this, mScale);
                    isScale = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isScale) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(this, "scaleY", mScale, 1.0f);
                    animator.setDuration(300);
                    animator.start();
                    isScale = false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}

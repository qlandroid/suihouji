package com.example.suishouji.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ItemMoveListView extends ListView {
    private ImageView moveImageView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private int userMoveY,actionBarHeight;
    private int moveItemPostion;

    public ItemMoveListView(Context context) {
        super(context);
    }

    public ItemMoveListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemMoveListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private boolean canMove = false;
    public void setCanMove(boolean isCan){
        this.canMove = isCan;
    }

    private static final String TAG = "ItemMoveListView";
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent:  运行 ------0");
            if (ev.getAction() == MotionEvent.ACTION_DOWN){
                Log.i(TAG, "onInterceptTouchEvent:  运行 ------1");
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                //获取需要移动的itemView的角标position
                moveItemPostion = this.pointToPosition(x,y);
                View moveItemView = getChildAt(moveItemPostion - getFirstVisiblePosition());
                //当前手指点击位置
                userMoveY = y - moveItemView.getTop();
                actionBarHeight = (int) (ev.getRawY() - y);
                moveItemView.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(moveItemView.getDrawingCache());

                startDrag(bitmap,y, userMoveY,actionBarHeight);
                return false;
            }



        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent:  运行 -------2");
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP://手指离开
                int upY = (int) ev.getY();
                stopDrag();
                //需要进行交换数据
                changeDate(upY);
                return true;
            case MotionEvent.ACTION_MOVE://手指移动
                int moveY = (int) ev.getY();
                //需要重新绘图
                dragShowMove(moveY);
                return true;
        }

        return super.onTouchEvent(ev);
    }

    private void dragShowMove(int moveY) {
        if (moveImageView != null){
            mLayoutParams.y = moveY - this.userMoveY + actionBarHeight;
            mWindowManager.updateViewLayout(moveImageView,mLayoutParams);
        }
    }

    private void changeDate(int upY) {
        int comePosition = pointToPosition(0,upY);
        if (mOnMoveListener!=null){
            String data = (String) getAdapter().getItem(comePosition);
            mOnMoveListener.remove(data);
            mOnMoveListener.insertItem(comePosition,data);
        }
    }

    private void startDrag(Bitmap bitmap, int y, int moveY, int actionBarHeight) {
        stopDrag();
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.x = 0;
        mLayoutParams.y = y - moveY + actionBarHeight;
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        /****
         * 设置该layout参数的一些flags参数
         */
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        //设置该window项是半透明的格式
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        //设置没有动画
        mLayoutParams.windowAnimations = 0;

        moveImageView = new ImageView(getContext());
        moveImageView.setImageBitmap(bitmap);
        mWindowManager.addView(moveImageView,mLayoutParams);
    }

    private void stopDrag() {
        if (moveImageView != null&&mWindowManager!=null){
            mWindowManager.removeView(moveImageView);
            moveImageView = null;
        }
    }

    public interface OnMoveListener{
        void remove(String postion);
        void insertItem(int postion, String oldPosition);
    }
    private OnMoveListener mOnMoveListener ;
    public void setOnMoveListener(OnMoveListener l){
        this.mOnMoveListener = l;
    }

}

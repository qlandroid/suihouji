package com.example.suishouji.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.suishouji.R;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ShowHideNumberImageView extends ImageView {

    private int showFalseImage;
    private int showTrueImage = 0;
    private OnChangeImageListener mChangeImageListener;
    public interface OnChangeImageListener{
       void onChangeImage(boolean isShow);
    }
    public void setOnChangeImageListener(OnChangeImageListener listener){
        this.mChangeImageListener = listener;
    }

    public ShowHideNumberImageView(Context context) {
        super(context);
    }

    public ShowHideNumberImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShowHideNumberImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.ShowHideNumberImageView);
        showTrueImage =attrsArray.getResourceId(R.styleable.ShowHideNumberImageView_showClickTrueImage,R.mipmap.ic_launcher);
        setImageResource(showTrueImage);
        showFalseImage = attrsArray.getResourceId(R.styleable.ShowHideNumberImageView_showClickFalseImage,showTrueImage);
        attrsArray.recycle();
        attrsArray = null;
    }

    private boolean isShow =true;
    private static final String TAG = "ShowHideNumberImageView";
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setImageResource(isShow ? showTrueImage:showFalseImage);
                if (mChangeImageListener!= null){
                    mChangeImageListener.onChangeImage(isShow);
                }
                isShow =  !isShow;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}

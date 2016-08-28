package com.example.suishouji.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suishouji.R;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MenuButton extends FrameLayout {
    private ImageView mImageView;
    private TextView mTextView;
    private int textFalseColor;
    private int imageFalse;
    private int textTrueColor;
    private int imageTrue;

    public void setText(String content){
        mTextView.setText(content);
    }

    public MenuButton(Context context) {
        super(context);
    }

    public MenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }



    public void init(Context context ,AttributeSet attrs) {
        setClickable(true);
        TypedArray arrays = context.obtainStyledAttributes(attrs, R.styleable.MenuButton);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_button,this,true);
        mImageView =(ImageView)view.findViewById(R.id.menu_button_iv);
        mTextView = (TextView)view.findViewById(R.id.menu_button_tv);

        String textContent = arrays.getString(R.styleable.MenuButton_text);
        float textSize =  arrays.getDimension(R.styleable.MenuButton_textSize,12);

        textFalseColor = arrays.getColor(R.styleable.MenuButton_textFalseColor, Color.TRANSPARENT);
        textTrueColor = arrays.getColor(R.styleable.MenuButton_textTrueColor,Color.TRANSPARENT);

        mTextView.setText(textContent);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        mTextView.setTextColor(textFalseColor);

        imageFalse = arrays.getResourceId(R.styleable.MenuButton_imageFalse,-1);
        imageTrue = arrays.getResourceId(R.styleable.MenuButton_imageTrue,imageFalse);
        int imageSize = arrays.getDimensionPixelSize(R.styleable.MenuButton_imageSize,15);

        if (imageFalse != -1){
            mImageView.setImageResource(imageFalse);
            ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
            layoutParams.width =imageSize;
            layoutParams.height = imageSize;
            mImageView.setLayoutParams(layoutParams);
        }


    }

    @Override
    public boolean hasOnClickListeners() {
        return super.hasOnClickListeners();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int height = getHeight();
        int width = getWidth();


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://手指放下
                setTrue();
                break;
            case MotionEvent.ACTION_UP://手指离开
                setFalse();
                break;
            case MotionEvent.ACTION_MOVE://手指移动；
                if (moveContent(x, y, height, width)){
                    setTrue();
                }else {
                    setFalse();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean moveContent(int x, int y, int height, int width) {
        return y>0&& y<height && x>0&& x<width;
    }

    private void setTrue() {
        mTextView.setTextColor(textTrueColor);
        mImageView.setImageResource(imageTrue);
    }

    private void setFalse() {
        mTextView.setTextColor(textFalseColor);
        mImageView.setImageResource(imageFalse);
    }
}

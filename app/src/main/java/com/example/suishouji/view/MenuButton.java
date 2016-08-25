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
    private int textTrueColor;
    private int textFalseColor;
    private int imageTrue;
    private int imageFalse;

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
        int textColor = arrays.getColor(R.styleable.MenuButton_textColor, Color.TRANSPARENT);
        textFalseColor = arrays.getColor(R.styleable.MenuButton_textFalseColor, Color.TRANSPARENT);

        mTextView.setText(textContent);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        mTextView.setTextColor(textFalseColor);

        imageFalse = arrays.getResourceId(R.styleable.MenuButton_imageFalse,-1);
        int imageSize = arrays.getDimensionPixelSize(R.styleable.MenuButton_imageSize,15);

        if (imageFalse != -1){
            mImageView.setImageResource(imageFalse);
            ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
            layoutParams.width =imageSize;
            layoutParams.height = imageSize;
            mImageView.setLayoutParams(layoutParams);
        }


    }





}

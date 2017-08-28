package com.troy.checkeditbutton;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Author: Troy
 * Date: 2017/7/21
 * Email: 810196673@qq.com
 * Des: 检测EditText是否输入内容的Button
 */

public class CheckEditButton extends AppCompatButton {

    private Drawable clickableBg;
    private Drawable unClickableBg;
    private int clickableTextBg;
    private int unClickableTextBg;
    private static final int DEFAULT_TEXT_BG = 0xFFFFFFF; //文字默认为白色

    public CheckEditButton(Context context) {
        super(context);
        init(context,null,0);
    }

    public CheckEditButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public CheckEditButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckEditButton, defStyleAttr,0);
        clickableBg = a.getDrawable(R.styleable.CheckEditButton_clickable_bg);
        unClickableBg = a.getDrawable(R.styleable.CheckEditButton_unClickable_bg);
        clickableTextBg = a.getColor(R.styleable.CheckEditButton_clickable_text_color,DEFAULT_TEXT_BG);
        unClickableTextBg = a.getColor(R.styleable.CheckEditButton_unClickable_text_color,DEFAULT_TEXT_BG);

        //初始按钮不可点击
        setClickable(false);
        //按钮初始背景
        setBackground(unClickableBg);
        //文字初始背景
        setTextColor(unClickableTextBg);
    }

    /**
     * @param editTexts
     * EditText继承自TextView，所以这里可以同时检测TextView和EditText是否有内容
     */
    public void setCheckEditTexts(final TextView... editTexts){
        setCheckEditButtonStatus(editTexts);
        for(TextView checkView:editTexts){
            checkView.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                    setCheckEditButtonStatus(editTexts);
                }
            });
        }
    }

    /**
     * 检测当前是否所有EditText都是非空状态
     * @param editTexts
     * @return
     */
    private boolean isCheckAllNonNull(TextView... editTexts){
        boolean isNonNull = true;
        for(TextView checkView:editTexts){
            String text = checkView.getText().toString();
            if(TextUtils.isEmpty(text)){
                isNonNull = false;
                break;
            }
        }
        return isNonNull;
    }

    /**
     * 设置button初始状态
     * @param editTexts
     */
    private void setCheckEditButtonStatus(TextView... editTexts){
        if(isCheckAllNonNull(editTexts)){
            setClickable(true);
            setBackground(clickableBg);
            setTextColor(clickableTextBg);
        }else{
            setClickable(false);
            setBackground(unClickableBg);
            setTextColor(unClickableTextBg);
        }

    }

}


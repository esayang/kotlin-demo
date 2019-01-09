package com.easyang.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.easyang.base.R;

/**
 * @author SC16004984
 * @date 2019/1/8 0008.
 */
public class TitleBar extends androidx.appcompat.widget.Toolbar {


    /**
     * 图标
     */
    private Drawable leftDrawable;
    private Drawable rightDrawable;

    /**
     * 文字内容
     */
    private String leftText = "返回";
    private String rightText = "";
    private String titleText = "标题";


    /**
     * 文字大小
     */
    private float titleSize = 16;
    private float leftTextSize = 14;
    private float rightTextSize = 14;

    private int titleTextColor = Color.WHITE;
    private int leftTextColor = Color.WHITE;
    private int rightTextColor = Color.WHITE;


    /**
     * 左右间距
     */
    private float paddingLeft = dp2px(16);
    private float paddingRight = dp2px(16);

    private OnClickListener leftClickListener;
    private OnClickListener rightClickListener;


    private ConstraintLayout mContentGroup;
    private TextView mLeftView;
    private TextView mTitleView;
    private TextView mRightView;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentInsetsRelative(0, 0);
        setContentInsetsAbsolute(0, 0);
        createTitleBar(context);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        titleText = arr.getString(R.styleable.TitleBar_tb_title_text);
        titleSize = arr.getDimension(R.styleable.TitleBar_tb_title_text_size, 0);
        if (titleSize == 0) {
            titleSize = 16;
        } else {
            titleSize = px2dp(titleSize);
        }
        titleTextColor = arr.getColor(R.styleable.TitleBar_tb_title_text_color, titleTextColor);

        leftText = arr.getString(R.styleable.TitleBar_tb_left_text);
        leftTextSize = arr.getDimension(R.styleable.TitleBar_tb_left_text_size, 0);

        if (leftTextSize == 0) {
            leftTextSize = 14;
        } else {
            leftTextSize = px2dp(leftTextSize);
        }

        leftTextColor = arr.getColor(R.styleable.TitleBar_tb_left_text_color, leftTextColor);
        int leftDrawableId = arr.getResourceId(R.styleable.TitleBar_tb_left_icon, R.drawable.iv_return_white);
        if (leftDrawableId != 0) {
            setLeftDrawable(getResources().getDrawable(leftDrawableId));

        }

        rightText = arr.getString(R.styleable.TitleBar_tb_right_text);
        rightTextSize = arr.getDimension(R.styleable.TitleBar_tb_right_text_size, 0);
        if (rightTextSize == 0) {
            rightTextSize = 14;
        } else {
            rightTextSize = px2dp(rightTextSize);
        }
        rightTextColor = arr.getColor(R.styleable.TitleBar_tb_right_text_color, rightTextColor);
        int rightDrawableId = arr.getResourceId(R.styleable.TitleBar_tb_right_icon, 0);
        if (rightDrawableId != 0) {
            setRightDrawable(getResources().getDrawable(rightDrawableId));
        }

        arr.recycle();
        init();

    }

    /**
     * 生成标题栏
     *
     * @param context
     */
    private void createTitleBar(Context context) {
        mContentGroup = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.title_bar, null);
        addView(mContentGroup, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLeftView = mContentGroup.findViewById(R.id.tv_left);
        mTitleView = mContentGroup.findViewById(R.id.tv_title);
        mRightView = mContentGroup.findViewById(R.id.tv_right);
    }


    /**
     * 初始化标题栏属性
     */
    private void init() {
        initTitleView();
        initLeftView();
        initRightView();
    }


    /**
     * 初始化右视图
     */
    private void initRightView() {
        mRightView.setText(rightText);
        mRightView.setTextColor(rightTextColor);
        mRightView.setTextSize(rightTextSize);
        mRightView.setCompoundDrawables(null, null, rightDrawable, null);
        mRightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightClickListener != null) {
                    rightClickListener.onClick(view);
                }
            }
        });
    }

    /**
     * 初始化左视图
     */
    private void initLeftView() {
        mLeftView.setText(leftText);
        mLeftView.setTextColor(leftTextColor);
        mLeftView.setTextSize(leftTextSize);
        mRightView.setCompoundDrawables(leftDrawable, null, null, null);
        mLeftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftClickListener != null) {
                    leftClickListener.onClick(view);
                }
            }
        });
    }

    /**
     * 初始化标题
     */
    private void initTitleView() {
        mTitleView.setSelected(true);
        mTitleView.setText(titleText);
        mTitleView.setTextSize(titleSize);
        mTitleView.setTextColor(titleTextColor);
    }


    /**
     * 左视图点击事件
     *
     * @param leftClickListener
     */
    public void setLeftClickListener(OnClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    /**
     * 右视图点击事件
     *
     * @param rightClickListener
     */
    public void setRightClickListener(OnClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }


    public Drawable getLeftDrawable() {
        return leftDrawable;
    }

    public void setLeftDrawable(Drawable leftDrawable) {
        leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());
        this.leftDrawable = leftDrawable;
        if (mLeftView != null) {
            mLeftView.setCompoundDrawables(leftDrawable, null, null, null);
        }
    }

    public Drawable getRightDrawable() {
        return rightDrawable;
    }

    public void setRightDrawable(Drawable rightDrawable) {
        rightDrawable.setBounds(0, 0, rightDrawable.getIntrinsicWidth(), rightDrawable.getIntrinsicHeight());
        this.rightDrawable = rightDrawable;
        if (mRightView != null) {
            mRightView.setCompoundDrawables(null, null, rightDrawable, null);
        }
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        if (mLeftView != null) {
            mLeftView.setText(leftText);
        }
        this.leftText = leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        if (mRightView != null) {
            mRightView.setText(rightText);
        }
        this.rightText = rightText;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        if (mTitleView != null) {
            mTitleView.setText(titleText);
        }
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    @Override
    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        if (mTitleView != null) {
            mTitleView.setTextColor(titleTextColor);
        }
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
        if (mLeftView != null) {
            mLeftView.setTextColor(leftTextColor);
        }
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        if (mRightView != null) {
            mRightView.setTextColor(rightTextColor);
        }
    }

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        if (mTitleView != null) {
            mTitleView.setTextSize(titleSize);
        }
        this.titleSize = titleSize;
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public void setLeftTextSize(float leftTextSize) {
        if (mLeftView != null) {
            mLeftView.setTextSize(leftTextSize);
        }
        this.leftTextSize = leftTextSize;
    }

    public float getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(float rightTextSize) {
        if (mRightView != null) {
            mRightView.setTextSize(rightTextSize);
        }
        this.rightTextSize = rightTextSize;
    }

    public float getLeftPadding() {
        return paddingLeft;
    }

    public float getRightPadding() {
        return paddingRight;
    }


    public void setPadding(float paddingLeft, float paddingRight) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        if (mContentGroup != null) {
            mContentGroup.setPadding((int) paddingLeft, 0, (int) paddingRight, 0);
        }
    }


    private float dp2px(int dp) {
        return (float) (getResources().getDisplayMetrics().density * dp + 0.5);
    }

    private int px2dp(float px) {
        return (int) (px * 1.0F / getResources().getDisplayMetrics().density + 0.5);

    }

}

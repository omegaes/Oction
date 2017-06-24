package com.mega4tech.oction.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mega4tech.oction.R;

/**
 * Created by aboodba on 15/06/2017.
 */

public class RatioImageView extends ImageView {

    private float ratio = 1;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RatioImageView,
                0, 0);
        try {
            ratio = a.getFloat(R.styleable.RatioImageView_ratio, 1);
        } finally {
            a.recycle();
        }
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RatioImageView,
                0, 0);
        try {
            ratio = a.getFloat(R.styleable.RatioImageView_ratio, 1);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width * ratio);

        setMeasuredDimension(width, height);
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
        invalidate();
        requestLayout();
    }
}
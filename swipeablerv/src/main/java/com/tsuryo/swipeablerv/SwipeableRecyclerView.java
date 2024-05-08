package com.tsuryo.swipeablerv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Tsur Yohananov on 2020-05-07.
 */

public class SwipeableRecyclerView extends RecyclerView
        implements SwipeLeftRightCallback.Listener {
    private final static float CORNER_RADIUS_DEFAULT = -1.0f;
    private SwipedView mSwipedView;
    private SwipeLeftRightCallback.Listener mListener;


    public SwipeableRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createSwipedView(context, attrs);
        attachCallbackToRv();
        setWillNotDraw(false);
    }


    public void setListener(SwipeLeftRightCallback.Listener listener) {
        mListener = listener;
    }

    public void setRightBg(int bg) {
        mSwipedView.setBackgrounds(new int[]{
                mSwipedView.getLeftBg(), bg
        });
    }

    public void setLeftBg(int bg) {
        mSwipedView.setBackgrounds(new int[]{
                bg, mSwipedView.getRightBg()
        });
    }

    public void setRightText(String text) {
        mSwipedView.setTexts(new String[]{
                mSwipedView.getLeftText(),
                text
        });
    }

    public void setLeftText(String text) {
        mSwipedView.setTexts(new String[]{
                text,
                mSwipedView.getRightText()
        });
    }

    public void setRightImage(int imgRef) {
        mSwipedView.setIcons(new int[]{
                mSwipedView.getLeftIcon(),
                imgRef
        });
    }

    public void setLeftImage(int imgRef) {
        mSwipedView.setIcons(new int[]{
                imgRef,
                mSwipedView.getRightIcon()
        });
    }

    public void setTextColor(int color) {
        mSwipedView.setTextColor(color);
    }

    public void setTextSize(int size) {
        mSwipedView.setTextSize(size);
    }

    @Override
    public void onSwipedLeft(int position) {
        if (mListener != null)
            mListener.onSwipedLeft(position);
    }

    @Override
    public void onSwipedRight(int position) {
        if (mListener != null)
            mListener.onSwipedRight(position);
    }

    private void createSwipedView(@NonNull Context context, AttributeSet attrs) {
        //obtain attributes
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SwipeableRecyclerView,
                0,
                0);

        mSwipedView = new SwipedView();
        mSwipedView.setTexts(new String[]{
                typedArray.getString(R.styleable.SwipeableRecyclerView_leftText),
                typedArray.getString(R.styleable.SwipeableRecyclerView_rightText)
        });
        mSwipedView.setBackgrounds(new int[]{
                typedArray.getResourceId(
                        R.styleable.SwipeableRecyclerView_leftBgColor, R.color.white),
                typedArray.getResourceId(
                        R.styleable.SwipeableRecyclerView_rightBgColor, R.color.white)
        });
        mSwipedView.setIcons(new int[]{
                typedArray.getResourceId(
                        R.styleable.SwipeableRecyclerView_leftImage, -1),
                typedArray.getResourceId(
                        R.styleable.SwipeableRecyclerView_rightImage, -1)
        });
        mSwipedView.setTexts(new String[]{
                typedArray.getString(R.styleable.SwipeableRecyclerView_leftText),
                typedArray.getString(R.styleable.SwipeableRecyclerView_rightText)
        });
        mSwipedView.setTextColor(typedArray.getColor(
                R.styleable.SwipeableRecyclerView_textColor, Color.BLACK));
        mSwipedView.setTextSize((int) typedArray
                .getDimension(R.styleable.SwipeableRecyclerView_textSize, 15f));

        float cornerRadius = typedArray.getDimension(
                R.styleable.SwipeableRecyclerView_cornerRadius, CORNER_RADIUS_DEFAULT
        );
        if (cornerRadius == CORNER_RADIUS_DEFAULT) {
            mSwipedView.setLeftCornerRadius(typedArray.getDimension(
                    R.styleable.SwipeableRecyclerView_leftCornerRadius, CORNER_RADIUS_DEFAULT
            ));
            mSwipedView.setRightCornerRadius(typedArray.getDimension(
                    R.styleable.SwipeableRecyclerView_rightCornerRadius, CORNER_RADIUS_DEFAULT
            ));
        } else {
            mSwipedView.setCornerRadius(cornerRadius);
        }

        mSwipedView.setShouldSupportRTL(
                typedArray.getBoolean(R.styleable.SwipeableRecyclerView_supportRTL, false)
        );
        mSwipedView.setShouldForceRTL(
                typedArray.getBoolean(R.styleable.SwipeableRecyclerView_forceRTL, false)
        );

    }


    private void attachCallbackToRv() {
        SwipeLeftRightCallback mSwipeCallBack = new SwipeLeftRightCallback(
                this, mSwipedView);
        new ItemTouchHelper(mSwipeCallBack).attachToRecyclerView(this);
    }
}

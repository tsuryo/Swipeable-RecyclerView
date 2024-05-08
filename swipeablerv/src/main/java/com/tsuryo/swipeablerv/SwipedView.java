package com.tsuryo.swipeablerv;

import java.util.Arrays;

/**
 * Created by Tsur Yohananov on 2020-05-07.
 */

public class SwipedView {
    private int[] mIcons;
    private int[] mBackgrounds;
    private String[] mTexts;
    private int mTextColor;
    private int mTextSize;
    private float mCornerRadius;
    private float mLeftCornerRadius = -1f;
    private float mRightCornerRadius = -1f;
    private boolean mIsRTL;
    private boolean mShouldSupportRTL;
    private boolean mShouldForceRTL;

    SwipedView() {
        mIsRTL = RTL.isRTL();
    }

    /**
     * Represents the child to draw in {@link SwipeLeftRightCallback}
     *
     * @param icons       - must contain 2 icons - [0] - left, [1] - right
     * @param texts       - must contain 2 strings - [0] - left, [1] - right
     * @param backgrounds - must contain 2 backgrounds int - [0] - left, [1] - right
     *                    - assign null/-1 for unwanted side
     */
    SwipedView(int[] icons, int[] backgrounds, String[] texts) {
        mIcons = icons;
        mBackgrounds = backgrounds;
        mTexts = texts;
    }

    int getLeftIcon() {
        if (shouldShowRTL())
            return mIcons[1];
        return mIcons[0];
    }

    int getRightIcon() {
        if (shouldShowRTL())
            return mIcons[0];
        return mIcons[1];
    }

    int getLeftBg() {
        if (shouldShowRTL())
            return mBackgrounds[1];
        return mBackgrounds[0];
    }

    int getRightBg() {
        if (shouldShowRTL())
            return mBackgrounds[0];
        return mBackgrounds[1];
    }

    String getLeftText() {
        if (shouldShowRTL())
            return mTexts[1] == null ? "" : mTexts[1];
        return mTexts[0] == null ? "" : mTexts[0];
    }

    String getRightText() {
        if (shouldShowRTL())
            return mTexts[0] == null ? "" : mTexts[0];
        return mTexts[1] == null ? "" : mTexts[1];
    }

    float getCornerRadius() {
        return mCornerRadius;
    }

    public float getLeftCornerRadius() {
        if (shouldShowRTL())
            return mRightCornerRadius;
        return mLeftCornerRadius;
    }

    public float getRightCornerRadius() {
        if (shouldShowRTL())
            return mLeftCornerRadius;
        return mRightCornerRadius;
    }

    int getTextColor() {
        return mTextColor;
    }

    int getTextSize() {
        return mTextSize;
    }


    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
    }

    public void setLeftCornerRadius(float leftCornerRadius) {
        mLeftCornerRadius = leftCornerRadius;
    }

    public void setRightCornerRadius(float rightCornerRadius) {
        mRightCornerRadius = rightCornerRadius;
    }

    void setIcons(int[] icons) {
        mIcons = icons;
    }

    void setBackgrounds(int[] backgrounds) {
        mBackgrounds = backgrounds;
    }

    void setTexts(String[] texts) {
        mTexts = texts;
    }

    void setTextColor(int color) {
        mTextColor = color;
    }

    void setTextSize(int size) {
        mTextSize = size;
    }

    void setShouldSupportRTL(boolean shouldSupportRTL) {
        mShouldSupportRTL = shouldSupportRTL;
    }

    void setShouldForceRTL(boolean shouldForceRTL) {
        mShouldForceRTL = shouldForceRTL;
    }

    public boolean shouldShowRTL() {
        return (mIsRTL && mShouldSupportRTL) || mShouldForceRTL;
    }

    @Override
    public String toString() {
        return "SwipedView{" +
                "mIcons=" + Arrays.toString(mIcons) +
                ", mBackgrounds=" + Arrays.toString(mBackgrounds) +
                ", mTexts=" + Arrays.toString(mTexts) +
                ", mTextColor=" + mTextColor +
                ", mTextSize=" + mTextSize +
                ", mCornerRadius=" + mCornerRadius +
                ", mLeftCornerRadius=" + mLeftCornerRadius +
                ", mRightCornerRadius=" + mRightCornerRadius +
                ", mIsRTL=" + mIsRTL +
                ", mShouldSupportRTL=" + mShouldSupportRTL +
                ", mShouldForceRTL=" + mShouldForceRTL +
                '}';
    }
}

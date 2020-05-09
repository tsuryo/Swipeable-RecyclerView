package com.tsuryo.swipeablerv;

import java.util.Arrays;

public class SwipedView {
    private int[] mIcons;
    private int[] mBackgrounds;
    private String[] mTexts;
    private int mTextColor;
    private int mTextSize;

    SwipedView() {
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
        return mIcons[0];
    }

    int getRightIcon() {
        return mIcons[1];
    }

    int getLeftBg() {
        return mBackgrounds[0];
    }

    int getRightBg() {
        return mBackgrounds[1];
    }

    String getLeftText() {
        return mTexts[0] == null ?
                "" : mTexts[0];
    }

    String getRightText() {
        return mTexts[1] == null ?
                "" : mTexts[1];
    }

    void setIcons(int[] icons) {
        mIcons = icons;
    }

    void setBackrounds(int[] backrounds) {
        mBackgrounds = backrounds;
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

    int getTextColor() {
        return mTextColor;
    }

    int getTextSize() {
        return mTextSize;
    }

    boolean isFull() {
        return mBackgrounds != null && mBackgrounds.length == 2 &&
                mIcons != null && mIcons.length == 2 &&
                mTexts != null && mTexts.length == 2;
    }

    @Override
    public String toString() {
        return "SwipedView{" +
                "mIcons=" + Arrays.toString(mIcons) +
                ", mBackrounds=" + Arrays.toString(mBackgrounds) +
                ", mTexts=" + Arrays.toString(mTexts) +
                '}';
    }
}

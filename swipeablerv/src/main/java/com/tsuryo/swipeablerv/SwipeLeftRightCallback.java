package com.tsuryo.swipeablerv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Tsur Yohananov on 2020-05-07.
 */

public class SwipeLeftRightCallback extends ItemTouchHelper.SimpleCallback {
    private final Listener mListener;
    private final SwipedView mSwipedView;


    /**
     * @param listener   {@link Listener}
     * @param swipedView {@link SwipedView} represents the view to draw on swiped
     */
    SwipeLeftRightCallback(Listener listener, SwipedView swipedView) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mListener = listener;
        mSwipedView = swipedView;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {

        return false;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int movementFlags = returnMovementFlags();
        if (movementFlags != -1)
            return movementFlags;
        return super.getMovementFlags(recyclerView, viewHolder);
    }


    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                         int direction) {
        if (direction == ItemTouchHelper.RIGHT) {
            if (mSwipedView.shouldShowRTL())
                mListener.onSwipedLeft(viewHolder.getAdapterPosition());
            else
                mListener.onSwipedRight(viewHolder.getAdapterPosition());
        } else {
            if (mSwipedView.shouldShowRTL())
                mListener.onSwipedRight(viewHolder.getAdapterPosition());
            else
                mListener.onSwipedLeft(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY,
                actionState, isCurrentlyActive);
        View v = viewHolder.itemView;
        Context context = v.getContext();

        ChildToDraw toDraw = null;
        if (dX > 0) {
            toDraw = new ChildToDraw((int) dX, v, context, ChildToDraw.LEFT).invoke();
        } else if (dX < 0) {
            toDraw = new ChildToDraw((int) dX, v, context, ChildToDraw.RIGHT).invoke();
        }
        if (toDraw != null)
            draw(c, context, toDraw);
    }

    private void draw(@NonNull Canvas c, Context context, ChildToDraw toDraw) {
        GradientDrawable bg = toDraw.getBg();
        Drawable icon = toDraw.getIcon();
        Paint paint = toDraw.getPaintText();

        bg.draw(c);
        if (icon != null) {
            icon.draw(c);
            c.drawText(toDraw.getText(), icon.getBounds().centerX(),
                    icon.getBounds().centerY() + (icon.getBounds().height()) +
                            convertDpToPixel(2, context), paint);
        } else {
            c.drawText(toDraw.getText(), bg.getBounds().centerX(),
                    bg.getBounds().centerY() + mSwipedView.getTextSize() / 2, paint);
        }
    }

    private int returnMovementFlags() {
        if (mSwipedView.getLeftIcon() == -1 &&
                mSwipedView.getLeftBg() == R.color.white &&
                mSwipedView.getLeftText().isEmpty() &&
                mSwipedView.getRightIcon() == -1 &&
                mSwipedView.getRightBg() == R.color.white &&
                mSwipedView.getRightText().isEmpty())
            return makeMovementFlags(0, 0);
        if (mSwipedView.getLeftIcon() == -1 &&
                mSwipedView.getLeftBg() == R.color.white &&
                mSwipedView.getLeftText().isEmpty())
            return makeMovementFlags(0, ItemTouchHelper.LEFT);
        if (mSwipedView.getRightIcon() == -1 &&
                mSwipedView.getRightBg() == R.color.white &&
                mSwipedView.getRightText().isEmpty())
            return makeMovementFlags(0, ItemTouchHelper.RIGHT);
        return -1;
    }

    public interface Listener {
        void onSwipedLeft(int position);

        void onSwipedRight(int position);
    }


    private class ChildToDraw {
        private final static int LEFT = 0, RIGHT = 1;
        private final int mSide;
        private final int dX;
        private final View v;
        private Context context;
        private GradientDrawable bg;
        private Drawable icon;
        private Paint mPaintText;
        private String mText;

        /**
         * @param side {@value LEFT} || {@value RIGHT}
         */
        private ChildToDraw(int dX, View v, Context context, int side) {
            this.dX = dX;
            this.v = v;
            this.context = context;
            mSide = side;
        }

        private GradientDrawable getBg() {
            return bg;
        }

        private Drawable getIcon() {
            return icon;
        }

        private Paint getPaintText() {
            return mPaintText;
        }

        private String getText() {
            return mText;
        }

        private ChildToDraw invoke() {
            int iconMargin = 0;
            int iconTop = 0;
            int iconBottom = 0;
            try {
                icon = ContextCompat.getDrawable(context, mSide == LEFT ?
                        mSwipedView.getLeftIcon() : mSwipedView.getRightIcon());
                iconMargin = (v.getHeight() - icon.getIntrinsicHeight()) / 2;
                iconTop = v.getTop() + (v.getHeight() - icon.getIntrinsicHeight()) / 2;
                iconBottom = iconTop + icon.getIntrinsicHeight();
            } catch (Exception e) {
                icon = null;
                e.printStackTrace();
            }

            int iconLeft;
            int iconRight;
            bg = new GradientDrawable();

            switch (mSide) {
                case LEFT:
                    mText = mSwipedView.getLeftText();
                    if (icon != null) {
                        iconLeft = v.getLeft() + iconMargin;
                        iconRight = v.getLeft() + iconMargin + icon.getIntrinsicWidth();
                        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    }
                    bg.setColor(context.getResources().getColor(mSwipedView.getLeftBg()));
                    bg.setBounds(v.getLeft(), v.getTop(), v.getLeft() + dX, v.getBottom());
                    bg.setCornerRadii(getRadii(mSwipedView.getLeftCornerRadius()));
                    break;
                case RIGHT:
                    mText = mSwipedView.getRightText();
                    if (icon != null) {
                        iconLeft = v.getRight() - iconMargin - icon.getIntrinsicWidth();
                        iconRight = v.getRight() - iconMargin;
                        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    }
                    bg.setColor(context.getResources().getColor(mSwipedView.getRightBg()));
                    bg.setBounds(v.getRight() + dX, v.getTop(), v.getRight(), v.getBottom());
                    bg.setCornerRadii(getRadii(mSwipedView.getRightCornerRadius()));
                    break;
            }
            mPaintText = new Paint();
            mPaintText.setColor(mSwipedView.getTextColor());
            mPaintText.setTextSize(mSwipedView.getTextSize() == 15 ?
                    convertDpToPixel(mSwipedView.getTextSize(), context) :
                    mSwipedView.getTextSize());
            mPaintText.setTextAlign(Paint.Align.CENTER);
            return this;
        }

        private float[] getRadii(float radius) {
            if (radius == -1)
                radius = mSwipedView.getCornerRadius();
            switch (mSide) {
                case LEFT:
                    return new float[]{
                            convertDpToPixel(radius, context), // Top-left corner
                            convertDpToPixel(radius, context), // Top-left corner
                            0f, // Top-right corner
                            0f, // Top-right corner
                            0f, // Bottom-right corner
                            0f, // Bottom-right corner
                            convertDpToPixel(radius, context), // Bottom-left corner
                            convertDpToPixel(radius, context)  // Bottom-left corner
                    };
                case RIGHT:
                    return new float[]{
                            0f, // Top-left corner
                            0f, // Top-left corner
                            convertDpToPixel(radius, context), // Top-right corner
                            convertDpToPixel(radius, context), // Top-right corner
                            convertDpToPixel(radius, context), // Bottom-right corner
                            convertDpToPixel(radius, context), // Bottom-right corner
                            0f, // Bottom-left corner
                            0f  // Bottom-left corner
                    };
            }
            return null;
        }
    }

    private float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi /
                DisplayMetrics.DENSITY_DEFAULT);
    }
}

package com.pinger.gankit.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.pinger.gankit.R;

public class LoadingView extends View {

    // 大圆(里面包含很多小圆的半径)
    private float mRotationRadius = 50;
    // 每一个小圆的半径
    private float mCircleRadius = 10;
    // 小圆圈的颜色列表，在init方法里面初始化
    private int[] mCircleColors;
    // 大圆和小圆旋转的时间
    private long mRotationDuration = 1200;// ms
    // 第二部分动画执行的总时间
    private long mSplashDuration = 1200;
    // 整体的背景颜色
    private int mSplashBgColor = Color.WHITE;

    // 空心圆初始半径
    private float mHoleRadius = 0F;
    // 当前大圆旋转角度(弧度)
    private float mCurrentRotationAngle = 0F;
    // 当前大圆的半径
    private float mCurrentRotationRadius = mRotationRadius;

    // 绘制的画笔
    private Paint mPaint = new Paint();
    // 绘制背景的画笔
    private Paint mPaintBg = new Paint();

    // 屏幕正中心坐标
    private float mCenterX;
    private float mCenterY;
    // 屏幕对角线一半
    private float mDiagonalDist;

    // 保存当前动画状态 -- 当前在执行哪种动画
    private SplashState mState = null;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化数据
        init(context);
    }

    public LoadingView(Context context) {
        this(context, null);
    }

    private abstract class SplashState {
        public abstract void drawState(Canvas canvas);
    }


    private void init(Context context) {
        // 获取颜色数组
        mCircleColors = context.getResources().getIntArray(
                R.array.splach_circle_colors);
        // 画笔
        mPaint.setAntiAlias(true);// 消除锯齿
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Style.STROKE);// 空心圆
        mPaintBg.setColor(mSplashBgColor);// 设置背景色
    }

    /**
     * 在View显示出来的时候会调用一次
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 初始化中心点坐标
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        // 对角线的一半
        mDiagonalDist = (float) Math.sqrt(w * w + h * h) / 2; // 利用x,y 勾股定理

    }

    /**
     * 绘制视图
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设计模式 -- 模板模式
        // 绘制事件分发
        if (mState == null) {
            // 第一次执行动画
            mState = new RotateState();
        }
        mState.drawState(canvas);
    }

    /**
     * 进入主界面 -- 开启后面的动画,加载完毕
     */
    public void splashAndDisapper() {
        RotateState rotateState = (RotateState) mState;
        rotateState.cancer();
        mState = new MergingState();
        // 提醒View重新绘制 -- onDraw方法
        invalidate();
    }

    /**
     * 旋转动画
     *
     * @author Xubin
     */
    private class RotateState extends SplashState {

        private ValueAnimator mAnimator;

        public RotateState() {
            // 小圆的坐标 ---> 大圆的半径，大圆旋转了多少角度
            // 估值器，1200ms时间内 计算某个时刻当前角度是多少 0~2π
            mAnimator = ValueAnimator.ofFloat(0, (float) Math.PI * 2);
            // 设置差值器 -- 均匀计算
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setDuration(mRotationDuration);
            // 设置监听
            mAnimator.addUpdateListener(new AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 得到某个时间点的结果 -- 当前大圆旋转的角度
                    mCurrentRotationAngle = (Float) animation
                            .getAnimatedValue();
                    // 重新绘制 -- onDraw
                    invalidate();
                }
            });
            // 设置旋转次数 -- 无穷
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.start();

        }

        @Override
        public void drawState(Canvas canvas) {
            // 执行旋转动画
            // 绘制小圆的旋转动画
            // 1.清空画布
            drawBg(canvas);
            // 2.绘制小圆
            drawCircle(canvas);
        }

        /**
         * 取消动画
         */
        public void cancer() {
            mAnimator.cancel();
        }

    }

    /**
     * 聚合动画
     *
     * @author Xubin
     */
    private class MergingState extends SplashState {

        private ValueAnimator mAnimator;

        public MergingState() {
            // 绘制聚合动画
            // 估值器，1200ms时间内 计算某个时刻当前大圆的半径 r~0
            mAnimator = ValueAnimator.ofFloat(0, mRotationRadius);
            // 设置差值器 -- 加速器
            mAnimator.setInterpolator(new OvershootInterpolator(8));
            mAnimator.setDuration(mRotationDuration / 2);
            // 设置监听
            mAnimator.addUpdateListener(new AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 监听当前大圆的半径
                    mCurrentRotationRadius = (Float) animation
                            .getAnimatedValue();
                    invalidate();
                }
            });
            mAnimator.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    // 聚合动画结束，开启扩散动画
                    mState = new ExpandingState();
                    invalidate();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
            // 反过来计算
            mAnimator.reverse();
        }

        @Override
        public void drawState(Canvas canvas) {
            // 执行聚合动画
            // 绘制小圆的聚合动画
            // 1.清空画布
            drawBg(canvas);
            // 2.绘制小圆
            drawCircle(canvas);
        }
    }

    /**
     * 扩散动画
     *
     * @author Xubin
     */
    private class ExpandingState extends SplashState {

        private ValueAnimator mAnimator;

        public ExpandingState() {
            // 绘制扩散动画
            // 估值器，1200ms时间内 计算某个时刻当前空心圆的半径 0~对角线的一半
            mAnimator = ValueAnimator.ofFloat(0, mDiagonalDist);
            // 设置差值器 -- 加速器
            // mAnimator.setInterpolator(new OvershootInterpolator(8));
            mAnimator.setDuration(mRotationDuration / 2);
            // 设置监听
            mAnimator.addUpdateListener(new AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 监听当前空心圆的半径
                    mHoleRadius = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mAnimator.start();
        }

        @Override
        public void drawState(Canvas canvas) {
            // 执行扩散动画
            drawExpanding(canvas);
        }

    }

    /**
     * 绘制小圆
     *
     * @param canvas
     */
    public void drawCircle(Canvas canvas) {
        // 绘制小圆
        // 间隔角度
        float rotationAngle = (float) (2 * Math.PI / mCircleColors.length);
        for (int i = 0; i < mCircleColors.length; i++) {
            /**
             * x=r*cos(a)+mCenterX y=r*sin(a)+mCenterY a=旋转角度+间隔角度*i
             */
            double a = mCurrentRotationAngle + rotationAngle * i;
            float cx = (float) (mCurrentRotationRadius * Math.cos(a) + mCenterX);
            float cy = (float) (mCurrentRotationRadius * Math.sin(a) + mCenterY);

            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
        }
    }

    /**
     * 清空画布
     *
     * @param canvas
     */
    public void drawBg(Canvas canvas) {
        // 清空画布
        canvas.drawColor(mSplashBgColor);
    }

    /**
     * 绘制扩散动画
     *
     * @param canvas
     */
    public void drawExpanding(Canvas canvas) {
        // 绘制空心圆
        /**
         * 技巧：使用一个非常宽的画笔，不断地减小的画笔的宽度
         */
        // 设置画笔的宽度 = 对角线的一半-空心部分的半径
        float strokeWidth = mDiagonalDist - mHoleRadius;
        mPaintBg.setStrokeWidth(strokeWidth);
        float radius = mHoleRadius + strokeWidth / 2;
        canvas.drawCircle(mCenterX, mCenterY, radius, mPaintBg);
    }

}

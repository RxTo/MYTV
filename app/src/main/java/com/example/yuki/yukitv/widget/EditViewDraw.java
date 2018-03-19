package com.example.yuki.yukitv.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

import com.example.yuki.yukitv.R;
import com.example.yuki.yukitv.utils.UIutil;

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/5
 * 邮箱：125508663@qq.com
 */

public class EditViewDraw extends AppCompatEditText {
	
	private android.graphics.drawable.Drawable drawableLeft;
	private int scaleWidth; //dp值
	private int scaleHeight;
	
	public EditViewDraw(Context context) {
		super(context);
	}
	
	public EditViewDraw(Context context, android.util.AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	public EditViewDraw(Context context, android.util.AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}
	
	public void init(Context context, android.util.AttributeSet attrs) {
		android.content.res.TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditViewDraw);
		drawableLeft = typedArray.getDrawable(R.styleable.EditViewDraw_leftDrawable);
		scaleWidth = typedArray.getDimensionPixelOffset(R.styleable
			  .EditViewDraw_drawableWidth, UIutil.dp2px(context, 20));
		scaleHeight = typedArray.getDimensionPixelOffset(R.styleable
			  .EditViewDraw_drawableHeight, UIutil.dp2px(context, 20));
		typedArray.recycle();
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (drawableLeft != null) {
			drawableLeft.setBounds(0, 0, UIutil.dp2px(getContext(),scaleWidth), UIutil.dp2px(getContext(),scaleHeight));
		}
	}
	
	@Override
	protected void onDraw(android.graphics.Canvas canvas) {
		super.onDraw(canvas);
		this.setCompoundDrawables(drawableLeft, null, null, null);
	}
	
	/**
	 * 设置左侧图片并重绘
	 *
	 * @param drawableLeft
	 */
	public void setDrawableLeft(android.graphics.drawable.Drawable drawableLeft) {
		this.drawableLeft = drawableLeft;
		invalidate();
	}
	
	/**
	 * 设置左侧图片并重绘
	 *
	 * @param drawableLeftRes
	 */
	public void setDrawableLeft(int drawableLeftRes) {
		this.drawableLeft = getContext().getResources().getDrawable(drawableLeftRes);
		invalidate();
	}
}

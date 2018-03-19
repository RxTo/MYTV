package com.example.yuki.yukitv.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Author: Othershe
 * Time: 2016/8/18 10:55
 */
public class ScaleImageView extends android.support.v7.widget.AppCompatImageView {
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	private float ratio;
	
	public ScaleImageView(Context context) {
		super(context);
	}
	
	public ScaleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ratio = attrs.getAttributeFloatValue("http://schemas.android.com/apk/res-auto", "ratio", 1);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = Math.round(width / ratio);
		setMeasuredDimension(width, height);
	}
	
}

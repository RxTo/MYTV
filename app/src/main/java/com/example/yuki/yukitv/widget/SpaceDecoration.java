package com.example.yuki.yukitv.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/7
 * 邮箱：125508663@qq.com
 */

public class SpaceDecoration extends ItemDecoration {
	private int space;
	private int headerCount = -1;
	private int footerCount = 2147483647;
	private boolean mPaddingEdgeSide = true;
	private boolean mPaddingStart = true;
	private boolean mPaddingHeaderFooter = false;
	
	public SpaceDecoration(int space) {
		this.space = space;
	}
	
	public void setPaddingEdgeSide(boolean mPaddingEdgeSide) {
		this.mPaddingEdgeSide = mPaddingEdgeSide;
	}
	
	public void setPaddingStart(boolean mPaddingStart) {
		this.mPaddingStart = mPaddingStart;
	}
	
	public void setPaddingHeaderFooter(boolean mPaddingHeaderFooter) {
		this.mPaddingHeaderFooter = mPaddingHeaderFooter;
	}
	
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
		int position = parent.getChildAdapterPosition(view);
		int spanCount = 0;
		int orientation = 0;
		int spanIndex = 0;
		int headerCount = 0;
		int footerCount = 0;
		
		LayoutManager layoutManager = parent.getLayoutManager();
		if(layoutManager instanceof StaggeredGridLayoutManager) {
			orientation = ((StaggeredGridLayoutManager)layoutManager).getOrientation();
			spanCount = ((StaggeredGridLayoutManager)layoutManager).getSpanCount();
			spanIndex = ((LayoutParams)view.getLayoutParams()).getSpanIndex();
		} else if(layoutManager instanceof GridLayoutManager) {
			orientation = ((GridLayoutManager)layoutManager).getOrientation();
			spanCount = ((GridLayoutManager)layoutManager).getSpanCount();
			spanIndex = ((android.support.v7.widget.GridLayoutManager.LayoutParams)view.getLayoutParams()).getSpanIndex();
		} else if(layoutManager instanceof LinearLayoutManager) {
			orientation = ((LinearLayoutManager)layoutManager).getOrientation();
			spanCount = 1;
			spanIndex = 0;
		}
		
		if(position >= headerCount && position < parent.getAdapter().getItemCount() - footerCount) {
			float expectedHeight;
			float originHeight;
			float expectedY;
			float originY;
			if(orientation == 1) {
				expectedHeight = (float)(parent.getWidth() - this.space * (spanCount + (this.mPaddingEdgeSide?1:-1))) / (float)spanCount;
				originHeight = (float)parent.getWidth() / (float)spanCount;
				expectedY = (float)(this.mPaddingEdgeSide?this.space:0) + (expectedHeight + (float)this.space) * (float)spanIndex;
				originY = originHeight * (float)spanIndex;
				outRect.left = (int)(expectedY - originY);
				outRect.right = (int)(originHeight - (float)outRect.left - expectedHeight);
				if(position - headerCount < spanCount && this.mPaddingStart) {
					outRect.top = this.space;
				}
				
				outRect.bottom = this.space;
			} else {
				expectedHeight = (float)(parent.getHeight() - this.space * (spanCount + (this.mPaddingEdgeSide?1:-1))) / (float)spanCount;
				originHeight = (float)parent.getHeight() / (float)spanCount;
				expectedY = (float)(this.mPaddingEdgeSide?this.space:0) + (expectedHeight + (float)this.space) * (float)spanIndex;
				originY = originHeight * (float)spanIndex;
				outRect.bottom = (int)(expectedY - originY);
				outRect.top = (int)(originHeight - (float)outRect.bottom - expectedHeight);
				if(position - headerCount < spanCount && this.mPaddingStart) {
					outRect.left = this.space;
				}
				
				outRect.right = this.space;
			}
		} else {
			if(this.mPaddingHeaderFooter) {
				if(orientation == 1) {
					outRect.right = outRect.left = this.mPaddingEdgeSide?this.space:0;
					outRect.top = this.mPaddingStart?this.space:0;
				} else {
					outRect.top = outRect.bottom = this.mPaddingEdgeSide?this.space:0;
					outRect.left = this.mPaddingStart?this.space:0;
				}
			}
			
		}
	}
}


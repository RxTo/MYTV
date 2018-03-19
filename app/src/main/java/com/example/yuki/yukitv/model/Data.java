package com.example.yuki.yukitv.model;

import android.view.View;
import android.widget.TextView;

import com.example.yuki.yukitv.model.Data.Iosfocus.Ext;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/5
 * 邮箱：125508663@qq.com
 */

class Data {
	
	@SerializedName("ios-focus")
	public List<Iosfocus> iosfocus;
	@SerializedName("android-focus")
	public List<Androidfocus> androidfocus;
	@SerializedName("launch-image")
	public List<?> launchimage;
	@SerializedName("ios-launch-image")
	public List<Ioslaunchimage> ioslaunchimage;
	@SerializedName("android-launch-image")
	public List<Ioslaunchimage> androidlaunchimage;
	
	public static class Iosfocus {
		public String title;
		public String thumb;
		public String type;
		public String link;
		public String uid;
		public Ext ext;
		public Object link_object;
		
		public static class Ext {
			public String type;
		}
	}
	
	public static class Androidfocus {
		public String title;
		public String thumb;
		public String type;
		public String link;
		public String uid;
		public Ext ext;
		public Object link_object;
	}
	
	public static class Ioslaunchimage {
		public String title;
		TextView textViewm;
		

		
		public String thumb;
		public String time;
		public String start_time;
		public String end_time;
		public String link;
	}
}

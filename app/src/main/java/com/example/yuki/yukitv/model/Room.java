package com.example.yuki.yukitv.model;

import com.example.yuki.yukitv.model.Room.Live.Ws.Flv.StreamSrc;
import com.example.yuki.yukitv.utils.DecimalFormatUtil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/8
 * 邮箱：125508663@qq.com
 */

public class Room implements IModel{
	public String uid;
	public String no;
	public String title;
	public String nick;
	public String avatar;
	public int view;
	public int weight;
	public int follow;
	public String announcement;

	public List<RoomLines> room_lines;

	
	public String getView() {
		return DecimalFormatUtil.formatW(view);
	}
	
	@Override
	public boolean isNull() {
		return false;
	}
	
	@Override
	public boolean isAuthError() {
		return false;
	}
	
	@Override
	public boolean isBizError() {
		return false;
	}
	
	@Override
	public String getErrorMsg() {
		return null;
	}
	
	public static class Live {
		public Ws ws;
		
		public static class Ws {
			public String name;
			public Flv flv;
			public String def_mobile;
			public String def_pc;
			public String v;
			
			public static class Flv {
				@SerializedName("3")
				public StreamSrc vNorm;
				@SerializedName("4")
				public StreamSrc vHigh;
				@SerializedName("5")
				public StreamSrc vSuper;
				public int main_mobile;
				public int main_pc;
				
				public static class StreamSrc {
					public String name;
					public String src;
				}
			}
		}
	}
	

	
	public static class RoomLines {
		public String name;
		public FlvX flv;
		public static class FlvX {
			@SerializedName("3")
			public StreamSrc vNorm;
			@SerializedName("4")
			public StreamSrc vHigh;
			@SerializedName("5")
			public StreamSrc vSuper;
			public StreamSrc getStreamSrc(){
				if(vSuper!=null){
					return vSuper;
				}
				if(vHigh!=null){
					return vHigh;
				}
				return vNorm;
			}
		}
	}
	
	public static class RankCurr {
		public String send_uid;
		public String send_nick;
		public String avatar;
		public int rank;
		public int score;
		public String icon;
	}
}

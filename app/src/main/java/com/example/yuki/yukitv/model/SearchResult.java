package com.example.yuki.yukitv.model;


import com.example.yuki.yukitv.view.fragment.BaseFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;


public class SearchResult implements IModel {
	
	public Data data;
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
	
	public static class Data {
		public int total;
		public int pageNb;
		public List<LiveList.Live> items;
	
	}
}

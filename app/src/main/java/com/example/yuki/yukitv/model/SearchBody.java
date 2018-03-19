package com.example.yuki.yukitv.model;

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/5
 * 邮箱：125508663@qq.com
 */

public class SearchBody {
	
	/**
	 * os : 1
	 * v : 3.2.2
	 * p : {"page":0,"key":"lol","categoryId":0,"size":10}
	 * ver : 4
	 */
	
	public String os;
	public String v;
	public P p;
	public String ver;
	public SearchBody(P p) {
		this.p = p;
	}
	
	public static class P {
		/**
		 * page : 0
		 * key : lol
		 * categoryId : 0
		 * size : 10
		 */
		
		public int page;
		public String key;
		public int categoryId;
		public int size=10;
		
		public P(String key) {
			this.key = key;
		}
	}
}

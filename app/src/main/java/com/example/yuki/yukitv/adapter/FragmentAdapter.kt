package com.example.yuki.yukitv.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter


/**
 * 作者：Yuki  -  2017/03/13
 * 邮箱：125508663@qq.com
 */

class FragmentAdapter(fm : FragmentManager, var mTitles : List<String?>, var mListData : List<Fragment>?) : FragmentPagerAdapter(fm) {


    override fun getItemId(position: Int): Long {
        return mTitles[position]!!.hashCode().toLong()
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }



    override fun getPageTitle(position: Int): String? {
        return mTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return mListData!![position]
    }

    override fun getCount(): Int {
        return if (mListData == null) 0 else mListData!!.size
    }


}

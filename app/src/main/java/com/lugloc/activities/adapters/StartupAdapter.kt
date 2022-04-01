package com.lugloc.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.lugloc.R

private const val ARG_PAGES_SIZE = 3;

class StartupAdapter(private val mContext: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        var resource = R.layout.content_startup_home_step
        when (position) {
            0 -> resource = R.layout.content_startup_home_step
            1 -> resource = R.layout.content_startup_gsm_step
            2 -> resource = R.layout.content_startup_safe_zone_step
        }
        val view = layoutInflater!!.inflate(resource, container, false)
        container.addView(view, position)
        return view
    }

    override fun getCount(): Int {
        return ARG_PAGES_SIZE
    }

    override fun isViewFromObject(view: View, arg : Any )= view === arg as View
}
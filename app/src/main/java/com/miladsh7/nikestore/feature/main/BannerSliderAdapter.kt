package com.miladsh7.nikestore.feature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miladsh7.nikestore.data.Banner


class BannerSliderAdapter(fragment: Fragment,val banners:List<Banner>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = banners.size

    override fun createFragment(position: Int): Fragment = BannerFragment.newInstance(banners[position])
}
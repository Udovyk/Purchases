package udovyk.homework.purchases.ui.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList


class SectionsPageAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {

        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? = mFragmentTitleList[position]


    override fun getItem(position: Int): Fragment = mFragmentList[position]


    override fun getCount(): Int = mFragmentList.size

}
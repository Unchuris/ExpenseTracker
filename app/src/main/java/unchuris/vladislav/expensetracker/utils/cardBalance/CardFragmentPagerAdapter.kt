package unchuris.vladislav.expensetracker.utils.cardBalance

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import unchuris.vladislav.expensetracker.ui.fragments.CardFragment
import java.util.*

class CardFragmentPagerAdapter(fm: FragmentManager, private val mBaseElevation: Float) : FragmentStatePagerAdapter(fm), ICardAdapter {

    private val mFragments: MutableList<CardFragment>

    init {
        mFragments = ArrayList()

        for (i in 0..2) {
            addCardFragment(CardFragment())
        }
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView {
        return mFragments[position].getCardView()!!
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        mFragments[position] = fragment as CardFragment
        return fragment
    }

    fun addCardFragment(fragment: CardFragment) {
        mFragments.add(fragment)
    }
}

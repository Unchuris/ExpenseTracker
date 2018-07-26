package unchuris.vladislav.expensetracker.ui.wallet

import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.AdapterBinding
import unchuris.vladislav.expensetracker.utils.cardBalance.CardItem
import unchuris.vladislav.expensetracker.utils.cardBalance.ICardAdapter
import java.util.ArrayList

class WalletListAdapter : PagerAdapter(), ICardAdapter {

    private val mViews: MutableList<CardView?>
    private val mData: MutableList<CardItem>
    private var mBaseElevation: Float = 0.toFloat()

    init {
        mData = ArrayList()
        mViews = ArrayList()
    }

    fun addCardItem(item: CardItem) {
        mViews.add(null)
        mData.add(item)
        notifyDataSetChanged()
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mViews[position]
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: AdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(container.context), R.layout.adapter, container, false)

        container.addView(view.root)
        bind(mData[position], view.root)
        val cardView = view.root.findViewById<View>(R.id.cardView) as CardView

        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = mBaseElevation * ICardAdapter.MAX_ELEVATION_FACTOR
        mViews[position] = cardView
        return view.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews[position] = null
    }

    private fun bind(item: CardItem, view: View) {
        val titleTextView = view.findViewById<View>(R.id.titleTextView) as TextView
        val contentTextView = view.findViewById<View>(R.id.contentTextView) as TextView
        titleTextView.setText(item.title)
        contentTextView.setText(item.text)
    }

}

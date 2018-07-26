package unchuris.vladislav.expensetracker.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter.*
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.utils.cardBalance.ICardAdapter

class CardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        cardView.maxCardElevation = cardView.cardElevation * ICardAdapter.MAX_ELEVATION_FACTOR
        return inflater.inflate(R.layout.fragment_adapter, container, false)
    }

    fun getCardView(): CardView? {
        return cardView
    }
}
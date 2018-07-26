package unchuris.vladislav.expensetracker.utils.cardBalance

import android.support.v7.widget.CardView

interface ICardAdapter {

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView?

    fun getCount(): Int

    companion object {
        val MAX_ELEVATION_FACTOR = 8
    }
}
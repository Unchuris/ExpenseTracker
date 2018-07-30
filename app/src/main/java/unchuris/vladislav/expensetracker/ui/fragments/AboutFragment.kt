package unchuris.vladislav.expensetracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import unchuris.vladislav.expensetracker.R

class AboutFragment : DaggerFragment() {

    companion object {
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragmet_about, container, false)
}
package unchuris.vladislav.expensetracker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import unchuris.vladislav.expensetracker.R
import javax.inject.Inject

class SettingsFragment @Inject constructor(): DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_settings, container, false)

}
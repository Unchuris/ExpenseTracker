package unchuris.vladislav.expensetracker

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import unchuris.vladislav.expensetracker.ui.fragments.SettingsFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import unchuris.vladislav.expensetracker.ui.fragments.AboutFragment
import unchuris.vladislav.expensetracker.ui.fragments.WalletFragment
import unchuris.vladislav.expensetracker.ui.fragments.BalanceFragment

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        supportFragmentManager.addOnBackStackChangedListener(this)
        nav_view.setNavigationItemSelectedListener(this)
        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toggle.syncState()
        if (savedInstanceState == null) {
            initToolbar(R.string.toolbar_title_finance, 4f)
            supportFragmentManager.beginTransaction().add(R.id.container_main, WalletFragment.newInstance()).commit()
        }
    }

    private fun initToolbar(@StringRes title: Int, elevation: Float) {
        toolbar_main.setTitle(title)
        toolbar_main.elevation = elevation
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_wallet -> {
                initToolbar(R.string.toolbar_title_finance, resources.getDimension(R.dimen.default_elevation))
                supportFragmentManager.beginTransaction().replace(R.id.container_main, WalletFragment.newInstance()).commit()
                drawer_layout.closeDrawer(Gravity.START)
            }
            R.id.nav_settings -> {
                initToolbar(R.string.toolbar_title_settings, resources.getDimension(R.dimen.default_elevation))
                supportFragmentManager.beginTransaction().replace(R.id.container_main, SettingsFragment.newInstance()).addToBackStack(null).commit()
            }
            R.id.nav_transaction -> {
                initToolbar(R.string.toolbar_title_transactions, resources.getDimension(R.dimen.default_elevation))
                supportFragmentManager.beginTransaction().replace(R.id.container_main, BalanceFragment.newInstance()).commit()
                drawer_layout.closeDrawer(Gravity.START)
            }
            R.id.nav_about -> {
                initToolbar(R.string.toolbar_title_transactions, resources.getDimension(R.dimen.default_elevation))
                supportFragmentManager.beginTransaction().replace(R.id.container_main, AboutFragment.newInstance()).addToBackStack(null).commit()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackStackChanged() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            toggle.isDrawerIndicatorEnabled = false
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar_main.setNavigationOnClickListener { onBackPressed() }
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            initToolbar(R.string.toolbar_title_finance, resources.getDimension(R.dimen.default_margin))
            toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_main, R.string.app_name, R.string.app_name)
            toggle.isDrawerIndicatorEnabled = true
            drawer_layout.addDrawerListener(toggle)
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            toggle.syncState()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout != null && drawer_layout.isDrawerOpen(Gravity.START)) {
            drawer_layout.closeDrawer(Gravity.START, true)
        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}

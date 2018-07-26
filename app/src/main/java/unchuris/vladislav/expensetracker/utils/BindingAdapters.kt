package unchuris.vladislav.expensetracker.utils

import android.arch.lifecycle.Observer
import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.databinding.BindingAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import unchuris.vladislav.expensetracker.ui.wallet.WalletListAdapter
import unchuris.vladislav.expensetracker.utils.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    Log.e("tag", adapter.toString())
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: ViewPager, adapter: PagerAdapter) {
    Log.e("tag", adapter.toString())
    view.adapter = adapter
}

@BindingAdapter("pageTransformer")
fun setPageTransformer(view: ViewPager, adapter: WalletListAdapter) {
    view.setPageTransformer(false, ShadowTransformer(view, adapter))
}

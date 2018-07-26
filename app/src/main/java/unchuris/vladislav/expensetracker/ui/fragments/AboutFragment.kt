package unchuris.vladislav.expensetracker.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_wallet.*
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.FragmentWalletBinding
import unchuris.vladislav.expensetracker.utils.ShadowTransformer
import javax.inject.Inject
import unchuris.vladislav.expensetracker.utils.cardBalance.*
import unchuris.vladislav.expensetracker.ui.wallet.WalletListAdapter
import unchuris.vladislav.expensetracker.ui.wallet.WalletListModel
import unchuris.vladislav.expensetracker.utils.autoCleared

class AboutFragment @Inject constructor() : DaggerFragment() {

    private var mCardShadowTransformer: ShadowTransformer? = null

    private var mFragmentCardAdapter: CardFragmentPagerAdapter? = null
    private lateinit var mCardAdapter: WalletListAdapter

    private lateinit var walletListModel: WalletListModel
    private var binding: FragmentWalletBinding by autoCleared()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_wallet,
                container,
                false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        walletListModel = ViewModelProviders.of(this).get(WalletListModel::class.java)
//        binding.walletModel = walletListModel
//
//        mFragmentCardAdapter = CardFragmentPagerAdapter(childFragmentManager, dpToPixels(2, context))
//
////        //data
////        mCardShadowTransformer = ShadowTransformer(viewPager, mCardAdapter)
////
////        //viewPager.setPageTransformer(false, mCardShadowTransformer)
//
//        viewPager.offscreenPageLimit = 5
//        swipelayout.setOnRefreshListener { swipelayout.postDelayed({
//            swipelayout.isRefreshing = false
//        },3000)}
//
//        binding.setLifecycleOwner(this)
//    }


//    mCardAdapter = new CardPagerAdapter();
//    mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
//    mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
//    mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
//    mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
//    mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
//    dpToPixels(2, this));
//
//    mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
//    mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
//
//    mViewPager.setAdapter(mCardAdapter);
//    mViewPager.setPageTransformer(false, mCardShadowTransformer);
//    mViewPager.setOffscreenPageLimit(3);

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        walletListModel = ViewModelProviders.of(this).get(WalletListModel::class.java)
        binding.walletModel = walletListModel

//        mFragmentCardAdapter = CardFragmentPagerAdapter(childFragmentManager, dpToPixels(2, context))

        viewPager.offscreenPageLimit = 5
        swipelayout.setOnRefreshListener { swipelayout.postDelayed({
            swipelayout.isRefreshing = false
        },3000)}

        binding.setLifecycleOwner(this)
    }


    private fun dpToPixels(dp: Int, context: Context?): Float {
        return dp * context!!.resources.displayMetrics.density
    }
}

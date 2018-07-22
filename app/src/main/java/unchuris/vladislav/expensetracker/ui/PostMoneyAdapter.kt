//package unchuris.vladislav.expensetracker.ui
//
//import android.content.Context
//import android.support.annotation.LayoutRes
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.ImageView
//import android.widget.TextView
//import unchuris.vladislav.expensetracker.model.Money
//import unchuris.vladislav.expensetracker.R
//
//
//class PostMoneyAdapter(context: Context, @LayoutRes private val layoutResource: Int) : ArrayAdapter<Money>(context, layoutResource) {
//
//    private var cost = mutableListOf<Money>()
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return createViewFromResource(position, convertView, parent)
//    }
//
//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return createViewFromResource(position, convertView, parent)
//    }
//
//    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var mViewHolder = ViewHolder()
//        var conver = convertView
//        if (conver == null) {
//            val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            conver = LayoutInflater.from(context).inflate(layoutResource, parent, false)
//            mViewHolder.Img = conver.findViewById(R.id.ivFlag) as ImageView
//            mViewHolder.displayType = conver.findViewById(R.id.tvName) as TextView
//            mViewHolder.money = conver.findViewById(R.id.tvPopulation) as TextView
//            conver.tag = mViewHolder
//        } else {
//            mViewHolder = conver.tag as ViewHolder
//        }
//        mViewHolder.displayType?.text = cost[position].currency.shortName
//        mViewHolder.money?.text = cost[position].value.toString()
//
//        return conver!!
//    }
//
//    fun update(money : List<Money>?) {
//        if (money != null) {
//            cost.clear()
//            cost.addAll(money)
//            notifyDataSetChanged()
//        }
//    }
//
//}
//
//private class ViewHolder {
//    internal var Img: ImageView? = null
//    internal var displayType: TextView? = null
//    internal var money: TextView? = null
//}
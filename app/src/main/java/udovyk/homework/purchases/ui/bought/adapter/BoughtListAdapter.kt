package udovyk.homework.purchases.ui.bought.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_purchase.view.*
import udovyk.homework.purchases.R
import udovyk.homework.purchases.data.PurchaseEntity
import javax.inject.Inject

class BoughtListAdapter @Inject constructor(): RecyclerView.Adapter<BoughtListAdapter.ViewHolder>() {
    private val list = mutableListOf<PurchaseEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(list.get(position).imageUri)
            .into(holder.imPurchases)
    }

    fun add(item: PurchaseEntity) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun addAll(items: List<PurchaseEntity>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteItem(pos: Int) {
        list.removeAt(pos)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imPurchases = view.im_purchase
    }
}
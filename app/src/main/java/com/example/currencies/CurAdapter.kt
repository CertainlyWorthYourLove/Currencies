package com.example.currencies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.model.view.*

class CurAdapter(val myList:List<Currency>): RecyclerView.Adapter<CurAdapter.CurrencyViewHolder>(){

    class CurrencyViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.model, parent, false)

            return CurrencyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
            val p = myList[position]
            holder.itemView.text.text = p.text
            holder.itemView.value.text = p.value1
        }

    override fun getItemCount(): Int = myList.size


}
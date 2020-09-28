package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter(private val portals:List<Portal>, private val clickListener: (Portal) -> Unit) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val binding = ItemReminderBinding.bind(itemView);

        fun databind(portal: Portal, clickListener:(Portal) -> Unit) {
            itemView.tv_name.text = portal.titleText;
            itemView.tv_url.text = portal.urlText;
            /** Changing the databind to include a onClickListener */
            itemView.setOnClickListener { clickListener(portal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate((R.layout.item_portal), parent, false)
        );
    }


    override fun getItemCount(): Int {
        return portals.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position], clickListener);
    }
}
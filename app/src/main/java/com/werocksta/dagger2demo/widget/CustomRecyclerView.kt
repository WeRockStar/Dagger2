package com.werocksta.dagger2demo.widget

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


open class CustomRecyclerView : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return 0
    }
}

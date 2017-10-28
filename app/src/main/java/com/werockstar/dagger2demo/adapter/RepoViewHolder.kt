package com.werockstar.dagger2demo.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


import butterknife.BindView
import butterknife.ButterKnife
import com.werockstar.dagger2demo.R

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.tvName) lateinit var tvName: TextView
    @BindView(R.id.card) lateinit var cvRepo: CardView
    @BindView(R.id.tvLanguage) lateinit var tvLanguage: TextView

    init {
        ButterKnife.bind(this, view)
    }

}

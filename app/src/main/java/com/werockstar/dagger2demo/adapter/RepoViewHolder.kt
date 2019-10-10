package com.werockstar.dagger2demo.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.model.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvName by lazy { view.findViewById(R.id.tvName) as TextView }
    private val card by lazy { view.findViewById(R.id.card) as CardView }
    private val tvLanguage by lazy { view.findViewById(R.id.tvLanguage) as TextView }

    fun bind(repo: Repo, clickRepository: OnClickRepository) {
        tvName.text = repo.nameRepo
        tvLanguage.text = repo.language

        card.setOnClickListener { clickRepository.onClickRepoItem(repo) }
    }
}

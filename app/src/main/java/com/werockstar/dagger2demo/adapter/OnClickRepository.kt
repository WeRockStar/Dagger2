package com.werockstar.dagger2demo.adapter

import com.werockstar.dagger2demo.model.Repo

interface OnClickRepository {
    fun onClickRepoItem(repo: Repo)
}
package com.werockstar.dagger2demo.view.fragment


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.adapter.RepoAdapter
import com.werockstar.dagger2demo.model.RepoCollection
import com.werockstar.dagger2demo.presenter.RepoPresenter
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar
import javax.inject.Inject

class RepoFragment : Fragment(), RepoPresenter.View, RepoAdapter.OnClickRepository {

    @BindView(R.id.rvList) lateinit var rvList: RecyclerView

    @BindView(R.id.smootProgressBar) lateinit var smoothProgressBar: SmoothProgressBar

    @Inject lateinit var customTabsIntent: CustomTabsIntent

    @Inject lateinit var presenter: RepoPresenter

    private lateinit var githubAdapter: RepoAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        (activity?.application as MainApplication).component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repo, container, false)
        ButterKnife.bind(this, view)

        presenter.injectView(this)

        presenter.getRepo(user)

        configurationRecyclerView()
        return view
    }

    private val user get() = arguments?.getString(EXTRA_USER) ?: ""

    private fun configurationRecyclerView() {
        rvList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun loading() {
        smoothProgressBar.progressiveStart()
    }

    override fun loadComplete() {
        smoothProgressBar.progressiveStop()
    }

    override fun onClickRepoItem(repo: RepoCollection) {
        customTabsIntent.launchUrl(activity, Uri.parse(repo.htmlUrl))
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

    override fun displayRepo(repos: List<RepoCollection>) {
        githubAdapter = RepoAdapter(repos, this)
        githubAdapter.notifyDataSetChanged()
        rvList.adapter = githubAdapter
    }

    companion object {
        val EXTRA_USER = "EXTRA_USER"

        fun newInstance(user: String): RepoFragment {
            val fragment = RepoFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}

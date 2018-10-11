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
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.adapter.OnClickRepository
import com.werockstar.dagger2demo.adapter.RepoAdapter
import com.werockstar.dagger2demo.model.Repo
import com.werockstar.dagger2demo.presenter.RepoPresenter
import javax.inject.Inject

class RepoFragment : Fragment(), RepoPresenter.View, OnClickRepository {

    @BindView(R.id.rvList) lateinit var rvList: RecyclerView
    @BindView(R.id.smootProgressBar) lateinit var smoothProgressBar: SmoothProgressBar

    @Inject lateinit var customTabsIntent: CustomTabsIntent
    @Inject lateinit var presenter: RepoPresenter

    private lateinit var githubAdapter: RepoAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        (activity?.application as MainApplication).component.inject(this)
        presenter.injectView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repo, container, false)
        ButterKnife.bind(this, view)

        presenter.getRepo(user)

        configurationRecyclerView()
        return view
    }

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

    override fun dismissLoading() {
        smoothProgressBar.progressiveStop()
    }

    override fun onClickRepoItem(repo: Repo) {
        customTabsIntent.launchUrl(activity, Uri.parse(repo.htmlUrl))
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

    override fun displayRepo(repos: List<Repo>) {
        githubAdapter = RepoAdapter(repos, this)
        githubAdapter.notifyDataSetChanged()
        rvList.adapter = githubAdapter
    }

    private val user: String get() = arguments?.getString(EXTRA_USER) ?: ""

    companion object {
        private const val EXTRA_USER = "EXTRA_USER"

        fun newInstance(user: String): RepoFragment {
            val fragment = RepoFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}

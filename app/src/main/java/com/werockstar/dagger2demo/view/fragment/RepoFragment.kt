package com.werockstar.dagger2demo.view.fragment


import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.adapter.OnClickRepository
import com.werockstar.dagger2demo.adapter.RepoAdapter
import com.werockstar.dagger2demo.model.Repo
import com.werockstar.dagger2demo.presenter.RepoPresenter
import kotlinx.android.synthetic.main.fragment_repo.*
import javax.inject.Inject

class RepoFragment : Fragment(), RepoPresenter.View {

    @Inject
    lateinit var customTabsIntent: CustomTabsIntent
    @Inject
    lateinit var presenter: RepoPresenter

    private lateinit var githubAdapter: RepoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as MainApplication).component.inject(this)
        presenter.injectView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRepoInfo()

        configurationRecyclerView()
    }

    private fun getRepoInfo() {
        user?.let { presenter.getRepo(it) }
    }

    private fun configurationRecyclerView() {
        rvList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun loading() {
        smootProgressBar.progressiveStart()
    }

    override fun dismissLoading() {
        smootProgressBar.progressiveStop()
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

    override fun showRepo(repos: List<Repo>) {
        val didTap: (Repo) -> Unit = { repo ->
            customTabsIntent.launchUrl(activity, Uri.parse(repo.htmlUrl))
        }
        githubAdapter = RepoAdapter(repos, didTap)
        githubAdapter.notifyDataSetChanged()
        rvList.adapter = githubAdapter
    }

    private val user: String? get() = arguments?.getString(EXTRA_USER)

    companion object {
        private const val EXTRA_USER = "EXTRA_USER"

        fun newInstance(user: String): RepoFragment {
            val fragment = RepoFragment()
            Bundle().apply {
                putString(EXTRA_USER, user)
                fragment.arguments = this
            }
            return fragment
        }
    }
}

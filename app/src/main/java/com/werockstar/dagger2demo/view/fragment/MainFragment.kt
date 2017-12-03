package com.werockstar.dagger2demo.view.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.werockstar.dagger2demo.util.KeyboardUtil
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.model.GithubUserCollection
import com.werockstar.dagger2demo.presenter.GithubUserPresenter
import com.werockstar.dagger2demo.view.activity.MainActivity
import javax.inject.Inject

class MainFragment : Fragment(), GithubUserPresenter.View {

    @BindView(R.id.edtUsername) lateinit var edtUsername: EditText
    @BindView(R.id.ivProfile) lateinit var ivProfile: ImageView
    @BindView(R.id.tvUsername) lateinit var tvUsername: TextView
    @BindView(R.id.tvRepo) lateinit var tvRepo: TextView
    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar

    @Inject lateinit var keyboard: KeyboardUtil

    @Inject lateinit var presenter: GithubUserPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        (activity?.application as MainApplication).component
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        ButterKnife.bind(this, view)
        presenter.injectView(this)
        return view
    }

    @OnClick(R.id.btnLoad)
    fun onClickLoadUserInfo() {
        presenter.getUserInfo(edtUsername.text.toString())
    }

    @OnClick(R.id.rootLayout)
    fun hideKeyboard() {
        keyboard.hideKeyboard(edtUsername)
    }

    override fun loading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun getUserInfoSuccess(userInfo: GithubUserCollection) {
        tvUsername.text = userInfo.username
        tvRepo.apply {
            isClickable = true
            text = userInfo.repoUrl
            setOnClickListener { (activity as MainActivity).onClickRepoList(userInfo.username) }
        }

        displayAvatarImage(userInfo.imageUrl)
    }

    private fun displayAvatarImage(avatarUrl: String) {
        Glide.with(this).load(avatarUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivProfile)
    }

    override fun getUserInfoError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getUserInfoComplete() {
        progressBar.visibility = View.GONE
        keyboard.hideKeyboard(edtUsername)
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

}

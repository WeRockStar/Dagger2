package com.werocksta.dagger2demo.view.fragment


import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.werocksta.dagger2demo.MainApplication
import com.werocksta.dagger2demo.R
import com.werocksta.dagger2demo.model.GithubUserCollection
import com.werocksta.dagger2demo.presenter.GithubUserPresenter
import com.werocksta.dagger2demo.util.KeyboardUtil
import com.werocksta.dagger2demo.view.activity.MainActivity
import javax.inject.Inject

class MainFragment : Fragment(), GithubUserPresenter.View {

    @Inject lateinit var intent: CustomTabsIntent

    @BindView(R.id.edtUsername) lateinit var edtUsername: EditText

    @BindView(R.id.ivProfile) lateinit var ivProfile: ImageView

    @BindView(R.id.tvUsername) lateinit var tvUsername: TextView

    @BindView(R.id.tvRepo) lateinit var tvRepo: TextView

    @Inject lateinit var keyboard: KeyboardUtil

    @Inject lateinit var presenter: GithubUserPresenter

    lateinit var progressDialog: ProgressDialog

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        (activity.application as MainApplication).component
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        ButterKnife.bind(this, view)
        presenter.injectView(this)
        progressDialog = ProgressDialog(context)
        return view
    }

    @OnClick(R.id.btnLoad)
    internal fun onClickLoadUserInfo() {
        presenter.getUserInfo(edtUsername.text.toString())
    }

    @OnClick(R.id.rootLayout)
    internal fun hideKeyboard() {
        keyboard.hideKeyboard(edtUsername)
    }

    override fun loading() {
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.isIndeterminate = true
        progressDialog.show()
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
        progressDialog.cancel()
        keyboard.hideKeyboard(edtUsername)
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }

}

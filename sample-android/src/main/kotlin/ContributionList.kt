package dev.eSolovei.eXpresso.sample.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.eSolovei.eXpresso.sample.android.api.GitHubService
import dev.eSolovei.eXpresso.sample.android.api.createService
import dev.eSolovei.eXpresso.sample.android.databinding.ContributionListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ContributionList : AppCompatActivity() {
    private lateinit var contributionListBinding: ContributionListBinding

    private val disposables = CompositeDisposable()

    private lateinit var service: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        contributionListBinding = ContributionListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val viewRoot = contributionListBinding.root
        val baseUrl = intent.getStringExtra("contributors_url") ?: "https://api.github.com/"
        service = createService(baseUrl)

        setContentView(viewRoot)
        setResponseText()
    }

    private fun setResponseText() {
        service
            .contributors("michallaskowski", "kuiks")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                contributionListBinding.label.text= it.joinToString(separator = ", ") { it.login }
            }, {
                contributionListBinding.label.text = "Error"
                it.printStackTrace()
            }).addTo(disposables)
    }
}
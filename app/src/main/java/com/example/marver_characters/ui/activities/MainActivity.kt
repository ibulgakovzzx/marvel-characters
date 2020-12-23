package com.example.marver_characters.ui.activities

import android.os.Bundle
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.marver_characters.R
import com.example.marver_characters.mvp.presenter.MainPresenter
import com.example.marver_characters.mvp.view.MainView
import com.example.marver_characters.ui.navigation.BackButtonListener
import dagger.android.AndroidInjection
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        AndroidInjection.inject(this)
        return presenter;
    }

    private val navigator: Navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, R.id.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            presenter.launchFirstScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        presenter.removeNavigator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments.firstOrNull { it.isVisible }
        if (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed())
        {
            return
        }
        else
        {
            finish()
        }
    }
}
package com.example.marver_characters.ui.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.marver_characters.R
import com.example.marver_characters.application.extensions.queryTextChanges
import com.example.marver_characters.application.extensions.setToolBar
import com.example.marver_characters.data.model.Character
import com.example.marver_characters.mvp.presenter.CharactersListPresenter
import com.example.marver_characters.mvp.view.CharactersListView
import com.example.marver_characters.ui.adapters.CharactersListAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_charaters_list.*
import javax.inject.Inject

class CharactersListFragment: MvpAppCompatFragment(), CharactersListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: CharactersListPresenter

    @ProvidePresenter
    fun providePresenter(): CharactersListPresenter {
        AndroidSupportInjection.inject(this)
        return presenter;
    }


    private val charactersListAdapter: CharactersListAdapter by lazy {
        CharactersListAdapter {
            presenter.openCharactersDetail(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity!!.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_charaters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolBar("", toolbar)

        charactersList.layoutManager = GridLayoutManager(context!!, 2)
        charactersList.adapter = charactersListAdapter

        initSearchView()
        initSearchViewEditText()

        updateEmptyPlaceholderVisibility()
    }

    private fun initSearchView() {
        search.queryHint = getString(R.string.characters_list_search_hint)
        search.isIconified = false
    }

    private fun initSearchViewEditText(){
        val searchViewEditText : EditText = search.findViewById(androidx.appcompat.R.id.search_src_text)
        searchViewEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        presenter.startObserveQuery(search.queryTextChanges().skipInitialValue())
    }

    override fun setList(list: List<Character>) {
        charactersListAdapter.setItems(list)
        updateEmptyPlaceholderVisibility()
    }

    override fun showProgress() {
        if(!progressLoading.isVisible) {
            emptyListPlaceholder.isVisible = false
            charactersList.isVisible = false
            progressLoading.isVisible = true
        }
    }

    override fun hideProgress() {
        updateEmptyPlaceholderVisibility()
        if(progressLoading.isVisible) {
            progressLoading.isVisible = false
        }
    }

    override fun showNetworkError() {
        hideProgress()
        charactersListAdapter.setItems(listOf())
        Toast.makeText(context!!, R.string.characters_list_network_error, Toast.LENGTH_LONG).show()
    }

    private fun updateEmptyPlaceholderVisibility() {
        emptyListPlaceholder.isVisible = charactersListAdapter.itemCount == 0
        charactersList.isVisible = charactersListAdapter.itemCount != 0
        updateEmptyPlaceholderData()
    }

    private fun updateEmptyPlaceholderData() {
        val query = search.query
        emptyListPlaceholderIcon.isVisible = query.isEmpty()
        emptyListPlaceholderText.text =
            if (query.isEmpty())
                getString(R.string.characters_list_search_list_placeholder)
            else
                getString(R.string.characters_list_search_list_not_found_placeholder)
    }

}
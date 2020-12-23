package com.example.marver_characters.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.bumptech.glide.Glide
import com.example.marver_characters.R
import com.example.marver_characters.application.extensions.setToolBar
import com.example.marver_characters.application.extensions.showBackButton
import com.example.marver_characters.data.model.Character
import com.example.marver_characters.ui.navigation.BackButtonListener
import com.example.marver_characters.ui.navigation.MainNavigator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_charater_detail.*
import kotlinx.android.synthetic.main.view_character_list_item.view.*
import javax.inject.Inject

class CharacterDetailFragment: MvpAppCompatFragment(), BackButtonListener {

    companion object {
        private const val CHARACTER_EXTRA = "character"

        fun newInstance(character: Character): CharacterDetailFragment {
            val args = Bundle()
            args.putSerializable(CHARACTER_EXTRA, character)

            val fragment = CharacterDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var mainNavigator: MainNavigator

    lateinit var character: Character

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args?.let {
            character = args.getSerializable(CHARACTER_EXTRA) as Character
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_charater_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolBar(character.name, toolbar)
        showBackButton()

        Glide.with(thumbnail)
                .load(character.thumbnail)
                .circleCrop()
                .into(thumbnail)

        name.text = character.name
        description.text = character.description
    }

    override fun onBackPressed(): Boolean {
        mainNavigator.exit()
        return true
    }
    
}
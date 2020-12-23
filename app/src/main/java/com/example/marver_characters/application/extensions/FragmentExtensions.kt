package com.example.marver_characters.application.extensions

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.marver_characters.R

fun Fragment.setToolBar(title: String, toolbar: Toolbar) {
    toolbar.title = title
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
}

fun Fragment.showBackButton(homeUpResId: Int = R.drawable.ic_baseline_arrow) {
    (activity as AppCompatActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setDisplayShowHomeEnabled(true)
        setHomeAsUpIndicator(getCompatDrawable(homeUpResId))
    }
}

fun Fragment.getCompatDrawable(@DrawableRes drawableId: Int) =
    AppCompatResources.getDrawable(
        context!!,
        drawableId)

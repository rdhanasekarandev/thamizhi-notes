package com.dogood.thamizhinotes.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.dogood.thamizhinotes.R

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun androidx.fragment.app.FragmentManager.transact(action: androidx.fragment.app.FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.addFragmentToActivity(@IdRes frameId:Int,fragment:androidx.fragment.app.Fragment,tag:String){
    supportFragmentManager.transact{
        add(frameId,fragment,tag)
    }
}

/**
 * The `fragment` is added to the container view with tag. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentToActivity(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment, tag: String) {
    supportFragmentManager.transact {
        replace(frameId, fragment, tag)
            .addToBackStack(null)
    }
}
/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 *         setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit_pop, R.anim.fragment_enter_pop, R.anim.fragment_exit)
 */
fun AppCompatActivity.replaceFragmentInActivity(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment, tag: String) {
    supportFragmentManager.transact {
        setCustomAnimations(R.anim.slide_right, R.anim.slide_left, R.anim.slide_right_1, R.anim.slide_left_1)
            .replace(frameId, fragment, tag)
            .addToBackStack(null)
    }
}

fun AppCompatActivity.addFragmentToActivityAnim(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment, tag: String) {
    supportFragmentManager.transact {
        add(frameId, fragment, tag)
            .setCustomAnimations(R.anim.slide_right, R.anim.slide_left)

    }
}

fun View.onClick(block: () -> Unit) {
    setOnClickListener { block() }
}
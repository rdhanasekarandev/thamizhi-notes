package com.dogood.thamizhinotes.ui.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dogood.thamizhinotes.utils.Utils
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection

abstract class BaseActivity<T : ViewDataBinding,V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback, BaseNavigator {
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null
    private var snackbar: Snackbar? = null
    private var topView: View? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    override fun hideSnackBar() {
        snackbar?.let {
            if (it.isShown) {
                it.dismiss()
            }
        }
    }

    override fun showSnackBar(title: String, msg: String, action: String?) {
        hideSnackBar()
        snackbar = if (topView == null) {
            Utils.showSnackBar(this, viewDataBinding!!.root, title, msg)
        } else {
            Utils.showSnackBar(this, topView!!, title, msg)
        }
    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
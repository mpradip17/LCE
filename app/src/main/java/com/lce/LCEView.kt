package com.lce

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.lce.databinding.EmptyLayoutBinding
import com.lce.databinding.ErrorLayoutBinding
import com.lce.databinding.LceLayoutBinding
import com.lce.databinding.LoadingLayoutBinding
import java.lang.Exception

class LCEView constructor(context: Context, attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)


    private var _viewBinding: LceLayoutBinding? = null
    private val binding get() = _viewBinding!!

    private var _errorBinding: ErrorLayoutBinding? = null
    private val errorBinding get() = _errorBinding!!

    private var _emptyBinding: EmptyLayoutBinding? = null
    private val emptyBinding get() = _emptyBinding!!

    private var _loadingBinding: LoadingLayoutBinding? = null
    private val loadingBinding get() = _loadingBinding!!

    init {
        // inflate the layout
        _viewBinding = LceLayoutBinding.inflate(LayoutInflater.from(context), this)
        _errorBinding = binding.customErrorView
        _emptyBinding = binding.customEmptyView
        _loadingBinding = binding.loadingView
    }
    /***
     *Load View based on status
     ***/
    fun setStatus(status: Status) = when (status) {
        is LOADING -> showLoadingView()
        is SUCCESS -> hideAllViews()
        is FAILURE -> showErrorView()
        is EMPTY -> showEmptyView()
    }

    /***
     * Show Empty View
     ***/
    fun showEmptyView() {
        loadingBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.GONE
        emptyBinding.root.visibility = View.VISIBLE
    }

    /***
     * Show Error View
     ***/
    fun showErrorView() {
        loadingBinding.root.visibility = View.GONE
        emptyBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.VISIBLE
    }

    /***
     * Show Loading View
     ***/
    fun showLoadingView() {
        emptyBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.GONE
        loadingBinding.root.visibility = View.VISIBLE
    }

    /***
     * Hide All View
     ***/
    private fun hideAllViews() {
        loadingBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.GONE
        emptyBinding.root.visibility = View.GONE
    }

    /***
     * Retry Call Back
     ***/
    fun setOnRetryClickListener(callback: () -> Unit) {
        errorBinding.retryButton.setOnClickListener {
            callback()
        }
    }

}
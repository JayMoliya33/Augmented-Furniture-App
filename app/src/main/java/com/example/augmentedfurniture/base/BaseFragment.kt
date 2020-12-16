package com.example.augmentedfurniture.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View.OnClickListener {

    private var mContext: Context? = null
    private var mActivity: AppCompatActivity? = null
    private var mView: View? = null
    private var isViewInitiated = false

    abstract fun setContentView(): Int

    abstract fun initView(rootView: View?, savedInstanceState: Bundle?)

    abstract fun setListeners()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = getContext()
        mActivity = getContext() as AppCompatActivity?
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return if (mView != null) {
            mView
        } else inflater.inflate(setContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        if (!isViewInitiated) {
            initView(view, savedInstanceState)
            setListeners()
            isViewInitiated = true
        }
    }

    override fun onClick(view: View?) {
    }
}
package com.example.cleanarch.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-19.
 */

open class BaseFragment : Fragment(), IBaseFragment {

    override fun showError(message: Int) {
        Toast.makeText(context, getString(message), Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}
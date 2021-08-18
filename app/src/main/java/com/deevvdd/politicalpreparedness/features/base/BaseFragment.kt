package com.deevvdd.politicalpreparedness.features.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.deevvdd.politicalpreparedness.utils.NavigationCommand
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 16,August,2021
 */
abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes val resId: Int) :
    Fragment() {
    protected lateinit var binding: B

    abstract val viewModel: BaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.snackBarMessage.observe(viewLifecycleOwner) { messageEvent ->
            messageEvent.getContentIfNotHandled()?.let {
                Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.snackBarInt.observe(viewLifecycleOwner) { messageResInt ->
            messageResInt.getContentIfNotHandled()?.let {
                Snackbar.make(this.requireView(), getString(it), Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.navigateCommand.observe(viewLifecycleOwner) { command ->
            command.getContentIfNotHandled()?.let {
                Timber.d("navigation $it")
                when (it) {
                    is NavigationCommand.To -> findNavController().navigate(it.directions)
                    is NavigationCommand.Back -> findNavController().popBackStack()
                    is NavigationCommand.BackTo -> findNavController().popBackStack(
                        it.destinationId,
                        false
                    )
                }
            }
        }

        return binding.root
    }
}
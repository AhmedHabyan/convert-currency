package com.example.currencyconverter.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.domain.model.CurrencyDto
import com.example.currencyconverter.ui.utils.UiState
import com.example.currencyconverter.ui.utils.clearVisiblity
import com.example.currencyconverter.ui.utils.setVisiblity
import com.example.currencyconverter.ui.utils.showErrorDialog
import com.example.currencyconverter.ui.viewmodel.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private var adapterItem:ArrayAdapter<String>?=null

    private var _binding:FragmentConverterBinding? =null
    val binding:FragmentConverterBinding get() = _binding!!

    private val viewModel by activityViewModels<ActivityViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDropDown()
        viewModel.getAllCurrencies()

        observeData()

        onSwapClicked()


    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when(it){
                        is UiState.Error -> {
                            showErrorDialog(
                                context = requireContext(),
                                message = it.exception.message ?: "something went wrong",
                                onPositiveButtonClicked = { dialog, _ ->
                                    dialog?.dismiss()
                                    viewModel.getAllCurrencies()
                                },
                                onNegativeButtonClicked = { dialog, _ ->
                                    dialog?.dismiss()
                                },

                            )
                            binding.progressBar.clearVisiblity()
                        }
                        is UiState.Ideal -> {}
                        is UiState.Loading -> {
                            binding.progressBar.setVisiblity()
                        }
                        is UiState.Success -> {
                            binding.constraintLayoutConverterDesign.setVisiblity()
                            binding.progressBar.clearVisiblity()

                            setItemsAdapter(it.response)

                        }
                    }
                }
            }
        }
    }


    private fun setItemsAdapter(response:Any) {
        if(response is CurrencyDto) {
            response.currencies?.let { adapterItem?.addAll(it) }
        }
    }

    private fun initDropDown() {



        adapterItem = ArrayAdapter<String>(requireContext(),R.layout.list_item)

        binding.autoCompleteFrom.setAdapter(adapterItem)
        binding.autoCompleteTo.setAdapter(adapterItem)
    }

    private fun onSwapClicked(){
        binding.btnSwap.setOnClickListener{
            val fromString= binding.autoCompleteFrom.text.toString()


            binding.autoCompleteFrom.setText(binding.autoCompleteTo.text.toString(),false)
            binding.autoCompleteTo.setText(fromString,false)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }


}
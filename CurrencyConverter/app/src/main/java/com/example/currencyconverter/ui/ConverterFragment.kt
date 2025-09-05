package com.example.currencyconverter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.domain.model.CurrencyDto
import com.example.currencyconverter.domain.model.TransactionDto
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
        onConvertClicked()


    }

    private fun onConvertClicked() {
        binding.btnConvert.setOnClickListener{
            if(isValidFields()){
                viewModel.calculateAmountConversion(
                    base = binding.autoCompleteFrom.text.toString(),
                    symbol = binding.autoCompleteTo.text.toString(),
                    amount = binding.amountEditText.text.toString()
                )
            }
        }
    }

    private fun isValidFields(): Boolean {
        var isValid = true
        if(binding.amountEditText.text.isNullOrEmpty()){
            binding.textInputLayoutEditTextAmount.error = Constants.ERROR_FIELD_REQUIRED
            isValid=false
        }
        else if(binding.amountEditText.text.toString().toInt() <= 0){
            binding.textInputLayoutEditTextAmount.error = Constants.ERROR_INPUT_RANGE
            isValid=false
        }
        if(binding.textInputLayoutFrom.editText?.text.isNullOrEmpty() ){
            binding.textInputLayoutFrom.error =Constants.ERROR_FIELD_REQUIRED
            isValid=false
        }
        if(binding.textInputLayoutTo.editText?.text.isNullOrEmpty()){
            binding.textInputLayoutTo.error =Constants.ERROR_FIELD_REQUIRED
            isValid=false
        }
        if(isValid) {
            binding.textInputLayoutEditTextAmount.error = null
            binding.textInputLayoutFrom.error = null
            binding.textInputLayoutTo.error = null
        }
        return isValid
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when(it){
                        is UiState.Error -> {
                            showErrorDialog(
                                context = requireContext(),
                                message = it.exception.message ?: Constants.GENERAL_ERROR,
                                onPositiveButtonClicked = { dialog, _ ->
                                    dialog?.dismiss()
                                    viewModel.getAllCurrencies()
                                },
                                positiveButtonText = Constants.REFRESH,
                                onNegativeButtonClicked = { dialog, _ ->
                                    dialog?.dismiss()
                                },
                                negativeButtonText = Constants.CANCEL

                            )
                            binding.progressBar.clearVisiblity()
                        }
                        is UiState.Ideal -> {}
                        is UiState.Loading -> {
                            binding.converterProgressBar.clearVisiblity()
                            binding.progressBar.setVisiblity()
                        }
                        is UiState.Success<*> -> {
                            binding.constraintLayoutConverterDesign.setVisiblity()
                            binding.progressBar.clearVisiblity()

                            it.response?.let { it1 -> setItemsAdapter(it1) }

                        }
                    }
                }
            }


        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.converterUiState.collect {
                    when (it) {
                        is UiState.Error -> {
                            showErrorDialog(
                                context = requireContext(),
                                message = it.exception.message ?: Constants.GENERAL_ERROR,
                                onPositiveButtonClicked = { dialog, _ ->
                                    resetFields()
                                    dialog?.dismiss()
                                },
                                positiveButtonText = Constants.OK,
                                onNegativeButtonClicked = { dialog, _ ->
                                    dialog?.dismiss()
                                },
                                negativeButtonText = Constants.CANCEL

                            )
                            binding.converterProgressBar.clearVisiblity()
                        }

                        is UiState.Ideal -> {}
                        is UiState.Loading -> {
                            binding.converterProgressBar.setVisiblity()
                        }

                        is UiState.Success<*> -> {
                            binding.converterProgressBar.clearVisiblity()

                            binding.convertedEditText.setText((it.response as Double).toString())

                            viewModel.insertTransaction(
                                TransactionDto(
                                    amountFrom = "${binding.amountEditText.text} ${binding.autoCompleteFrom.text}",
                                    amountTo = "${binding.convertedEditText.text} ${binding.autoCompleteTo.text}"
                                )
                            )

                        }
                    }
                }
            }
        }
    }

private fun resetFields(){
    binding.convertedEditText.setText("")
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
package com.example.currencyconverter.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentConverterBinding
import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.domain.model.TransactionDto
import com.example.currencyconverter.ui.Constants
import com.example.currencyconverter.ui.utils.UiState
import com.example.currencyconverter.ui.utils.clearVisiblity
import com.example.currencyconverter.ui.utils.setVisiblity
import com.example.currencyconverter.ui.utils.showErrorDialog
import com.example.currencyconverter.ui.viewmodel.ActivityViewModel
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? =null
    val binding: FragmentHistoryBinding get() = _binding!!

    private val viewModel by activityViewModels<ActivityViewModel>()

    private var historyAdapter:HistoryAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllTransactions()

        observeData()

        initHistoryAdapter()
    }

    private fun initHistoryAdapter() {
        historyAdapter = HistoryAdapter(mutableListOf())
        binding.rvHistoryItems.apply{
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.historyUiState.collect {
                    when(it){
                        is UiState.Error -> {
                            showErrorDialog(
                                context = requireContext(),
                                message = it.exception.message ?: Constants.GENERAL_ERROR,
                                onPositiveButtonClicked = { dialog, _ ->
                                    dialog?.dismiss()
                                    viewModel.getAllTransactions()
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
                            binding.progressBar.setVisiblity()
                        }
                        is UiState.Success<*> -> {
                            binding.progressBar.clearVisiblity()

                            historyAdapter?.setData((it.response) as List<TransactionDto>)
                            binding.rvHistoryItems.setVisiblity()

                        }
                    }
                }
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }

}
@file:Suppress("RedundantNullableReturnType")

package ru.avitotech.avitotestapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ru.avitotech.avitotestapp.R
import ru.avitotech.avitotestapp.adapter.DataListAdapter
import ru.avitotech.avitotestapp.adapter.DataNumberListener
import ru.avitotech.avitotestapp.databinding.FragmentListBinding
import ru.avitotech.avitotestapp.viewmodel.ListViewModel

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

        val viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.listViewModel = viewModel

        val adapter = DataListAdapter(DataNumberListener {
            viewModel.deleteElementToList(it)
        })
        binding.recyclerList.adapter = adapter

        val layoutManager = GridLayoutManager(
            requireActivity(),
            resources.getInteger(R.integer.counter_columns)
        )
        binding.recyclerList.layoutManager = layoutManager

        viewModel.dataList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
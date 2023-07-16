package com.example.a2lesson1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a2lesson1.R
import com.example.a2lesson1.databinding.FragmentHomeBinding
import com.example.a2lesson1.model.Task
import com.example.a2lesson1.task.TaskFragment.Companion.RESULT_KEY
import com.example.a2lesson1.task.TaskFragment.Companion.RESULT_REQUEST_KEY
import com.example.a2lesson1.task.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        //slushaet resultat
        setFragmentResultListener(RESULT_REQUEST_KEY) { _, bundle ->
            val data = bundle.getSerializable(RESULT_KEY) as Task
            //Log.e("ololo", "onViewCreated:" + data)
            adapter.addTask(data)

        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.kneecheck.pasien

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kneecheck.config.ApiConfiguration
import com.example.kneecheck.config.DefaultRepo
import com.example.kneecheck.databinding.FragmentHistoryPasienBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryPasienFragment : Fragment() {

    private var _binding: FragmentHistoryPasienBinding? = null

    private val binding get() = _binding!!

    private var repo: DefaultRepo = ApiConfiguration.defaultRepo
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val mainScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var histPasienAdapter: HistoryPasienAdapter

    private lateinit var id :String
    private lateinit var name :String
    private lateinit var token :String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryPasienBinding.inflate(inflater, container, false)
        val root: View = binding.root

        id = requireActivity().intent.getStringExtra("id").toString()
        name = requireActivity().intent.getStringExtra("name").toString()
        token = requireActivity().intent.getStringExtra("token").toString()

        ioScope.launch {
            try{
                val data = repo.getHistoryPasien(token)
                mainScope.launch {
                    binding.rvHistoryPasien.layoutManager = LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false)
                    histPasienAdapter = HistoryPasienAdapter(data.data)
                    binding.rvHistoryPasien.adapter = histPasienAdapter
                }
            } catch (e:Exception){
                Log.e("Error API DASHBOARD", e.message.toString())
                Log.e("Error API DASHBOARD2", e.toString())
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
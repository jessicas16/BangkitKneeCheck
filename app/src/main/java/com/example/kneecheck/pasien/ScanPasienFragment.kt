package com.example.kneecheck.pasien

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kneecheck.config.ApiConfiguration
import com.example.kneecheck.config.DefaultRepo
import com.example.kneecheck.databinding.FragmentScanPasienBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ScanPasienFragment : Fragment() {
    private var _binding: FragmentScanPasienBinding? = null
    private val binding get() = _binding!!
    private var repo: DefaultRepo = ApiConfiguration.defaultRepo
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val mainScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var id :String
    private lateinit var name :String
    private lateinit var token :String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScanPasienBinding.inflate(inflater, container, false)
        val root: View = binding.root

        id = requireActivity().intent.getStringExtra("id").toString()
        name = requireActivity().intent.getStringExtra("name").toString()
        token = requireActivity().intent.getStringExtra("token").toString()

        binding.tvNamaPasien.text = name

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
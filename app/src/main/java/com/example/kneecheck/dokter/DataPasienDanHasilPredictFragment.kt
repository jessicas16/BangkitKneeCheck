package com.example.kneecheck.dokter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.kneecheck.R
import com.example.kneecheck.databinding.FragmentDataPasienDanHasilPredictBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DataPasienDanHasilPredictFragment : Fragment() {
    private lateinit var binding : FragmentDataPasienDanHasilPredictBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataPasienDanHasilPredictBinding.inflate(inflater, container, false)
        val root = binding.root

        val confidenceScore = arguments?.getInt("confidenceScore")
        val label = arguments?.getString("label")
        val id_xray = arguments?.getString("id_xray")
        val img_path = arguments?.getString("img_path")
        val pengobatan = arguments?.getString("pengobatan")
        val name = arguments?.getString("name")
        val gender = arguments?.getString("gender")
        val birth = arguments?.getString("birth")
        val address = arguments?.getString("address")
        val akun = arguments?.getString("akun")
        
        binding.tvTanggalScanData.text = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        binding.tvLabelKeparahanData.text = label
        binding.tvIdXrayData.text = id_xray

        if (akun == "belum"){
            binding.tvIdPasien.isVisible = false
            binding.tvIdPasienData.isVisible = false
        } else {
            binding.tvIdPasienData.text = arguments?.getString("id_pasien")
        }

        binding.tvNamaLengkapPasienData.text = name
        binding.tvBirthPasienData.text = birth
        binding.tvGenderPasienData.text = gender
        binding.tvAddressPasienData.text = address

        return root
    }

}
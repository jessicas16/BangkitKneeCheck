package com.example.kneecheck.dokter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kneecheck.LoginActivity
import com.example.kneecheck.config.ApiConfiguration
import com.example.kneecheck.config.DefaultRepo
import com.example.kneecheck.databinding.FragmentProfileBinding
import com.example.kneecheck.entity.updatePasswordDokterDTO
import com.example.kneecheck.entity.updatePasswordPasienDTO
import com.example.kneecheck.entity.updateProfileDokterDTO
import com.example.kneecheck.entity.updateProfilePasienDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        id = requireActivity().intent.getStringExtra("id").toString()
        name = requireActivity().intent.getStringExtra("name").toString()
        token = requireActivity().intent.getStringExtra("token").toString()

        binding.ibUpdateProfDokter.setOnClickListener {
            ioScope.launch {
                try{
                    val updatedData = updateProfileDokterDTO(
                        name = binding.etNamaProfDokter.text.toString(),
                        gender = binding.etGenderProfDokter.text.toString(),
                        hospital = binding.etInstansiProfDokter.text.toString(),
                        address = binding.etKotaProfDokter.text.toString()
                    )
                    val data = repo.updateProfileDokter(token, updatedData)
                    mainScope.launch {
                        binding.etNamaProfDokter.setText("")
                        binding.etGenderProfDokter.setText("")
                        binding.etInstansiProfDokter.setText("")
                        binding.etKotaProfDokter.setText("")

                        Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e:Exception){
                    mainScope.launch {
                        Toast.makeText(context, "Terjadi kesalahan input", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("Error API UpdatePass", e.message.toString())
                    Log.e("Error API UpdatePass", e.toString())
                }
            }
        }

        binding.ibUpdatePassProfDokter.setOnClickListener {
            ioScope.launch {
                try{
                    val updatedData = updatePasswordDokterDTO(
                        email = binding.etEmailProfDokter.text.toString(),
                        password = binding.etPasswordProfDokter.text.toString(),
                        verifyPassword = binding.etVerifPassProfDokter.text.toString()
                    )
                    val data = repo.updatePasswordDokter(token, updatedData)
                    mainScope.launch {
                        binding.etEmailProfDokter.setText("")
                        binding.etPasswordProfDokter.setText("")
                        binding.etVerifPassProfDokter.setText("")

                        Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e:Exception){
                    mainScope.launch {
                        Toast.makeText(context, "Terjadi kesalahan input", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("Error API UpdatePass", e.message.toString())
                    Log.e("Error API UpdatePass", e.toString())
                }
            }
        }

        binding.ibLogoutDokter.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
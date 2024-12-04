package com.example.kneecheck.pasien

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
import com.example.kneecheck.databinding.FragmentProfilePasienBinding
import com.example.kneecheck.entity.updatePasswordPasienDTO
import com.example.kneecheck.entity.updateProfilePasienDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfilePasienFragment : Fragment() {

    private var _binding: FragmentProfilePasienBinding? = null

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
        _binding = FragmentProfilePasienBinding.inflate(inflater, container, false)
        val root: View = binding.root

        id = requireActivity().intent.getStringExtra("id").toString()
        name = requireActivity().intent.getStringExtra("name").toString()
        token = requireActivity().intent.getStringExtra("token").toString()

        binding.ibUpdateProfPasien.setOnClickListener {
            ioScope.launch {
                try{
                    val updatedData = updateProfilePasienDTO(
                        name = binding.etNamaProfPas.text.toString(),
                        gender = binding.etGenderProfPasien.text.toString(),
                        birth = binding.etBirthProfPasien.text.toString(),
                        address = binding.etKotaProfPasien.text.toString()
                    )
                    val data = repo.updateProfilePasien(token, updatedData)
                    mainScope.launch {
                        binding.etNamaProfPas.setText("")
                        binding.etGenderProfPasien.setText("")
                        binding.etBirthProfPasien.setText("")
                        binding.etKotaProfPasien.setText("")

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

        binding.ibUpdatePassProfPasien.setOnClickListener {
            ioScope.launch {
                try{
                    val updatedData = updatePasswordPasienDTO(
                        email = binding.etEmailProfPasien.text.toString(),
                        password = binding.etPasswordProfPasien.text.toString(),
                        verifyPassword = binding.etVerifPassProfPasien.text.toString()
                    )
                    val data = repo.updatePasswordPasien(token, updatedData)
                    mainScope.launch {
                        binding.etEmailProfPasien.setText("")
                        binding.etPasswordProfPasien.setText("")
                        binding.etVerifPassProfPasien.setText("")

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

        binding.ibLogoutPasien.setOnClickListener {
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
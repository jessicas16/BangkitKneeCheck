package com.example.kneecheck.dokter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.content.MediaType
import androidx.fragment.app.Fragment
import com.example.kneecheck.config.ApiConfiguration
import com.example.kneecheck.config.DefaultRepo
import com.example.kneecheck.databinding.FragmentScanBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null

    private val binding get() = _binding!!
    private lateinit var getContent: ActivityResultLauncher<String>
    private var repo: DefaultRepo = ApiConfiguration.defaultRepo
    private val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val mainScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var id :String
    private lateinit var name :String
    private lateinit var token :String
    private lateinit var uriImage : Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        id = requireActivity().intent.getStringExtra("id").toString()
        name = requireActivity().intent.getStringExtra("name").toString()
        token = requireActivity().intent.getStringExtra("token").toString()

        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val resizedBitmap = resizeImage(uri, 500, 500)
                binding.imgForPreview.setImageBitmap(resizedBitmap)

                uriImage = uri
            }
        }

        binding.imgButtonGetFromGallery.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.imgButtonScanFromDokter.setOnClickListener {
            ioScope.launch {
                try {
                    val file = uriToFile(uriImage, requireContext())
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    val part = MultipartBody.Part.createFormData("img", file.name, requestFile)

                    val response = repo.predict(token, part)
                    mainScope.launch {
                        Log.e("Result PREDICT", response.toString())
                    }
                } catch (e: Exception) {
                    Log.e("Error Predict", e.toString())
                }
            }
        }

        return root
    }

    private fun resizeImage(uri: Uri, width: Int, height: Int): Bitmap? {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            return Bitmap.createScaledBitmap(originalBitmap, width, height, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun uriToFile(uri: Uri, context: Context): File {
        val contentResolver = context.contentResolver
        val file = File(context.cacheDir, "temp_${System.currentTimeMillis()}")
        try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream!!.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.kneecheck.pasien

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kneecheck.R
import com.example.kneecheck.config.ApiConfiguration
import com.example.kneecheck.config.DefaultRepo
import kotlinx.coroutines.launch

class LandingPageActivity : AppCompatActivity() {

    // Deklarasi TextView untuk update UI
    private lateinit var usiaTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var genderTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        // Hubungkan ID TextView dari XML
        usiaTextView = findViewById(R.id.usia)
        totalTextView = findViewById(R.id.total)
        genderTextView = findViewById(R.id.gender)

        // Inisialisasi API Service
        ApiConfiguration.getApiService()

        // Panggil fungsi untuk fetch data
        fetchLandingPageData()
    }

    private fun fetchLandingPageData() {
        // Inisialisasi API dan repository
        val repository = ApiConfiguration.defaultRepo

        // Gunakan Coroutine untuk memanggil data dari API
        lifecycleScope.launch {
            try {
                // Fetch data dari API
                val data = repository.fetchLandingPage("sad")

                // Update UI dengan data dari API
                usiaTextView.text = "Usia: ${data.age.average} (${data.age.total_kasus})"
                totalTextView.text = "Total: ${data.totalScanned}"
                genderTextView.text = "Gender: ${data.gender.average} (${data.gender.total_kasus})"
            } catch (e: Exception) {
                Log.e("LandingPageActivity", "Error: ${e.message}")
            }
        }
    }
}

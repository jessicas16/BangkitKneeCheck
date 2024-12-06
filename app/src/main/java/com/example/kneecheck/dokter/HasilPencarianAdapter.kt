package com.example.kneecheck.dokter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kneecheck.R
import com.example.kneecheck.entity.HistoryDokterData

class HasilPencarianAdapter(
//    val data: List<HistoryDokterData>,
//    var onAllClickListener: ((HistoryDokterData) -> Unit)? = null
): RecyclerView.Adapter<HasilPencarianAdapter.ViewHolder>() {
    class ViewHolder(val row: View): RecyclerView.ViewHolder(row) {
        val tvNama: ImageView = row.findViewById(R.id.rvNamaHasilCari)
        val tvEmail: TextView = row.findViewById(R.id.rvEmailHasilCari)
        val tvBirth: TextView = row.findViewById(R.id.rvBirthHasilCari)
        val tvKota: TextView = row.findViewById(R.id.rvKotaHasilCari)
        var views = row
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_hasil_pencarian, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hasilcari = data[position]


    }

    override fun getItemCount(): Int {
        return data.size
    }
}
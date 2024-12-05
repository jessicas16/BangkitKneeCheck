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

class HistoryDokterAdapter(
    val data: List<HistoryDokterData>,
    var onAllClickListener: ((HistoryDokterData) -> Unit)? = null
): RecyclerView.Adapter<HistoryDokterAdapter.ViewHolder>() {
    class ViewHolder(val row: View): RecyclerView.ViewHolder(row) {
        val xray: ImageView = row.findViewById(R.id.rvImageXray)
        val tvTanggal: TextView = row.findViewById(R.id.rvTvTanggal)
        val tvNama: TextView = row.findViewById(R.id.rvTvNama)
        val tvSeverity: TextView = row.findViewById(R.id.rvTvSeverity)
        var views = row
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_history, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = data[position]

        holder.tvTanggal.text = history.tgl_scan
        holder.tvNama.text = history.name
        holder.tvSeverity.text = "Tingkat keparahan : " + history.confidence_score
        when(history.confidence_score){
            4 -> holder.tvSeverity.setTextColor(Color.RED)
            2 -> holder.tvSeverity.setTextColor(Color.YELLOW)
            0 -> holder.tvSeverity.setTextColor(Color.BLUE)
        }

        Glide.with(holder.views)
            .load(history.img)
            .apply(RequestOptions().placeholder(R.drawable.logo))
            .into(holder.xray)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
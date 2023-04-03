package com.example.daggerhilt_xml.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggerhilt_xml.R
import com.example.daggerhilt_xml.model.User

class HomeAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<HomeAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val listPosition = users[position]
        with(holder) {
            uiTvUserName.text = listPosition.name
            Log.d("userName", "onBindViewHolder: ${listPosition.name}", )
            uiTvUserEmail.text = listPosition.email
            Glide.with(itemView)
                .load(listPosition.avatar)
                .into(uiIvUserPic)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<User>) {
        this.users.clear()
        list.let {
            this.users.addAll(it)
        }
        notifyDataSetChanged()
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uiTvUserName: TextView = itemView.findViewById(R.id.uiTvUserName)
        var uiTvUserEmail: TextView = itemView.findViewById(R.id.uiTvUserEmail)
        var uiIvUserPic: ImageView = itemView.findViewById(R.id.uiIvUserPic)
    }
}
package com.javalong.rxjavademo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_forward.view.*

/**
 * Created by 令狐 on 18/4/15.
 */
class ForwardAdapter(var titleList: List<String>, var forwardList: List<Class<out AppCompatActivity>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.btTitle.text = titleList[position]
        holder.itemView.btTitle.setOnClickListener({
            holder.itemView.context.startActivity(Intent(holder.itemView.context, forwardList[position]))
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_forward, parent, false)) {}
    }

    override fun getItemCount(): Int {
        return titleList.size
    }
}
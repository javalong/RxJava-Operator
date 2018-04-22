package com.javalong.rxjavademo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.javalong.rxjavademo.operator.AmbActivity
import com.javalong.rxjavademo.operator.BufferActivity
import com.javalong.rxjavademo.operator.CacheActivity
import com.javalong.rxjavademo.operator.ObservaleActivity
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.Observer
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvDemo.layoutManager = LinearLayoutManager(this)
        var titleList = arrayListOf<String>()
        var forwardList = arrayListOf<Class<out AppCompatActivity>>()
        titleList.add("Observable")
        titleList.add("Amb")
        titleList.add("Buffer")
        titleList.add("Cache")

        forwardList.add(ObservaleActivity::class.java)
        forwardList.add(AmbActivity::class.java)
        forwardList.add(BufferActivity::class.java)
        forwardList.add(CacheActivity::class.java)
        rvDemo.adapter = ForwardAdapter(titleList, forwardList)
    }
}

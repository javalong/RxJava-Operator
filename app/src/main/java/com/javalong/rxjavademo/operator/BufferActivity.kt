package com.javalong.rxjavademo.operator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.javalong.rxjavademo.R
import kotlinx.android.synthetic.main.activity_common.*
import rx.Observable
import rx.Subscriber
import rx.Subscription

/**
 * Created by 令狐 on 18/4/16.
 */
class BufferActivity : AppCompatActivity() {
    var sub: Subscription? = null
    var observable: Observable<List<String>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        btSub.setOnClickListener({
            observable?.subscribe({ msg ->
                tvContent.text = tvContent.text.toString() + "\n" + msg.toString()
            })
        })
//        Observable.create(object : Observable.OnSubscribe<String> {
//            override fun call(t: Subscriber<in String>) {
//                t.onNext("test1")
//                t.onNext("test2")
//                t.onNext("test3")
//            }
//        })
        observable = Observable.just("test1", "test2", "test3").buffer(2)
    }

    override fun onDestroy() {
        super.onDestroy()
        sub?.unsubscribe()
    }
}
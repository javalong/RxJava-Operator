package com.javalong.rxjavademo.operator

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.javalong.rxjavademo.R
import kotlinx.android.synthetic.main.activity_common.*
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.Subscription
import java.util.concurrent.TimeUnit

/**
 * Created by 令狐 on 18/4/15.
 */
class CacheActivity : AppCompatActivity() {
    var sub: Subscription? = null
    var observable: Observable<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        var handler = Handler()
        //延时后 cache的作用会更明显
//        observable = Observable.create(object : Observable.OnSubscribe<String> {
//            override fun call(t: Subscriber<in String>) {
//                t.onNext("Test1")
//                t.onNext("Test2")
//                t.onNext("Test3")
//            }
//        }).delay(4, TimeUnit.SECONDS).cache()
        observable = Observable.create(object : Observable.OnSubscribe<String> {
            override fun call(t: Subscriber<in String>) {
                t.onNext("Test1")
                t.onNext("Test2")
                t.onNext("Test3")
            }
        }).cache()

        btSub.setOnClickListener({
            observable?.subscribe({ msg ->
                handler.post(Runnable {
                    tvContent.text = tvContent.text.toString() + "\n" + msg

                })

            })
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        sub?.unsubscribe()
    }
}
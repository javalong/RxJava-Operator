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
class AmbActivity : AppCompatActivity() {
    var sub: Subscription? = null
    var observable: Observable<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

//        observable = Observable.amb(Observable.create(object : Observable.OnSubscribe<String> {
//            override fun call(t: Subscriber<in String>) {
//                t.onNext("Test1")
//            }
//        }), Observable.create(object : Observable.OnSubscribe<String> {
//            override fun call(t: Subscriber<in String>) {
//                t.onNext("Test2")
//            }
//        }))
        var handler = Handler()
        observable = Observable.amb(Observable.create(object : Observable.OnSubscribe<String> {
            override fun call(t: Subscriber<in String>) {
                t.onNext("Test1")
            }
        }).delay(1100, TimeUnit.SECONDS), Observable.create(object : Observable.OnSubscribe<String> {
            override fun call(t: Subscriber<in String>) {

                t.onNext("Test2")
            }
        }).delay(1100, TimeUnit.SECONDS))


        btSub.setOnClickListener({
            observable?.subscribe({ msg ->
                handler.post({
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
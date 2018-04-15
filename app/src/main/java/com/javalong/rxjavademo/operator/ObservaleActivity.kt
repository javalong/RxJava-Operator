package com.javalong.rxjavademo.operator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.javalong.rxjavademo.R
import kotlinx.android.synthetic.main.activity_common.*
import rx.Observable
import rx.Subscriber
import rx.Subscription

/**
 * Created by 令狐 on 18/4/15.
 */
class ObservaleActivity : AppCompatActivity() {
    var sub: Subscription? = null
    var observable: Observable<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        btSub.setOnClickListener({
            /**
             * 暂时都不考虑 onComplete onError 只做onNext的处理，由浅入深
             */
            observable?.subscribe({ msg ->
                tvContent.text = tvContent.text.toString() + "\n" + msg
            })
        })
        /**
         * 最简单的使用方式
         */
        observable = Observable.create(object : Observable.OnSubscribe<String> {
            override fun call(t: Subscriber<in String>) {
                t.onNext("Test1")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        sub?.unsubscribe()
    }
}
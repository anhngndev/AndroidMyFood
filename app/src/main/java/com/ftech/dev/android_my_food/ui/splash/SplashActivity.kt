package com.ftech.dev.android_my_food.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.ui.main.MainActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private val TAG = "SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        val root = findViewById<MotionLayout>(R.id.motionLayout)
        CoroutineScope(Dispatchers.IO).launch {//chạy dưới luồng background
            // show loading
            delay(50L)
            withContext(Dispatchers.Main) {//thực hiện công việc ở luồng chính
                //hideloading
                root.transitionToEnd()
            }
        }

        root.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                Log.d(TAG, "onTransitionStarted: ")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                Log.d(TAG, "onTransitionChange: ")
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                Log.d(TAG, "onTransitionCompleted: ")
                startActivity(intent)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                Log.d(TAG, "onTransitionTrigger: ")
            }
        })
    }
}
package com.tarmeez.notificationapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tarmeez.notificationapplication.R
import com.tarmeez.notificationapplication.notification_util.NotificationUtil
import com.tarmeez.notificationapplication.ui.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TestViewModel by viewModels()
    private var token: String? = null
    private lateinit var buttonT: Button
    private lateinit var buttonL: Button
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.send_all)
        buttonT = findViewById(R.id.send_t)
        buttonL = findViewById(R.id.send_l)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {

                return@OnCompleteListener
            }else{
                token = task.result
            }


            Log.d("onMessageReceived", "onCreate: ${token}")

        })

        button.setOnClickListener {

        }

        buttonL.setOnClickListener {

            viewModel.getToken("sarah")
        }

        buttonT.setOnClickListener {
            viewModel.getToken("sahar")

        }

    }



//        {
//            "to":"fcvV3VxbSJU:APA91bGqWAL79icQnUjB3deMhIcw3uYHcon9z3w0-uYN8la8CDV1Oi68aAJGNtF75XXjh5MILqVoGGTy1sVrjQm5ZcGVsiK2XVnuTKfiiaJVj-dfS4LfwgFnZQeJzanvxszMPICkbYEq",
//            "notification":{
//            "title": "aaa",
//            "body": "aaaaaaaa"
//        }
//        }
}
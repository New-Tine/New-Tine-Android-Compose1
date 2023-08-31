package com.example.newtineproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.RootNavGraph
import com.example.newtineproject.ui.theme.NewTineProjectTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preference: SharedPreferences =getSharedPreferences("Login", Context.MODE_PRIVATE)
        val singup_preference:SharedPreferences=getSharedPreferences("Signup",Context.MODE_PRIVATE)

        setContent {
            NewTineProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavGraph(navController = rememberNavController())
                }
            }
        }

        initFirebase()
    }

    private fun initFirebase(){
        val TAG="FirebaseService"
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d(TAG,"token:${token}")
            Toast.makeText(this,"token:${token}", Toast.LENGTH_SHORT).show()
        })
    }
}
package com.osman.firebasefcm.view

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import com.osman.firebasefcm.databinding.ActivityMainBinding
import com.osman.firebasefcm.model.NotificationData
import com.osman.firebasefcm.model.PushNotification
import com.osman.firebasefcm.service.RetrofitObject
import com.osman.firebasefcm.ui.theme.FirebaseFCMTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    val topic = "/topics/genelduyurular"
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Burada inflate et
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        //  FirebaseMessaging.getInstance().subscribeToTopic(topic)     bir konuya subscribe olma durumu

        val token = FirebaseMessaging.getInstance().token.addOnSuccessListener {

            val datamap = hashMapOf<String, String>()
            datamap.put("token", it)
            datamap.put("kullanici_adi", "isim")

            db.collection("Kullanici").add(datamap).addOnSuccessListener { }
        }


    }

    fun yolla(view: View) {

        val baslik = binding.baslikText.text.toString()
        val mesaj = binding.mesajText.text.toString()

        if (baslik.isNotBlank() && mesaj.isNotBlank()) {
            val data = NotificationData(baslik, mesaj)
            val notification = PushNotification(data, topic)
            notificationYolla(notification)
        }

    }

    private fun notificationYolla(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {

            try {

                val cevap = RetrofitObject.api.postNotification(notification)
                if (cevap.isSuccessful) {

                } else {

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseFCMTheme {
        Greeting("Android")
    }
}
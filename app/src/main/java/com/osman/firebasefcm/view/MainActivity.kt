package com.osman.firebasefcm.view

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.osman.firebasefcm.databinding.ActivityMainBinding
import com.osman.firebasefcm.model.PushNotification
import com.osman.firebasefcm.ui.theme.FirebaseFCMTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Burada inflate et
        setContentView(binding.root)


    }

    fun yolla(view: View) {


    }

    private fun notificationYolla(notification: PushNotification)=CoroutineScope(Dispatchers.IO).launch{



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
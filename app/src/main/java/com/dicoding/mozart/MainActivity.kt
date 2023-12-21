package com.dicoding.mozart


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mozart.navigation.RootNavGraph
import androidx.core.app.ActivityCompat
import com.dicoding.mozart.ui.model.MuseumItem
import com.dicoding.mozart.ui.retrofit.utils.RetrofitInstance
import com.dicoding.mozart.ui.theme.MozArtTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import android.content.pm.PackageManager


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!hasCameraPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                0
            )
        }
        setContent {
            MozArtTheme {
                navController = rememberNavController()
                var memesList by remember {
                    mutableStateOf(ArrayList<MuseumItem>())
                }
                val scope = rememberCoroutineScope()
                LaunchedEffect(key1 = true){
                    Log.d("logg", "mulai")
                    scope.launch(Dispatchers.IO) {
                        val response = try {
                            RetrofitInstance.api.getMuseumList()
                        }catch (e: IOException){
                            Log.d("toast", e.message.toString())
                            Toast.makeText(
                                this@MainActivity,
                                "app error: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@launch
                        }
                        Log.d("logg", "berhasil")
                        if (response.isSuccessful && response.body() != null){
                            withContext(Dispatchers.Main){
                                Log.d("logg", response.body().toString())
                                memesList = response.body()!!

                            }
                        }
                    }
                }
                RootNavGraph(navHostController = navController, memesList = memesList, context = applicationContext)
            }
        }
    }
    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}
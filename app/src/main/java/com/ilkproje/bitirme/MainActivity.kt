package com.ilkproje.bitirme

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ilkproje.bitirme.viewmodel.BabyViewModel
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.ButtonDefaults
import android.util.Log


lateinit var sharedPreferences: SharedPreferences


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        setContent {
            MyApp(sharedPreferences)
        }
    }
}

@Composable
fun RegistrationScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val backgroundColorTop = Color(0xFFB3E5FC)
    val backgroundColorBottom = Color(0xFFFFCDD2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(backgroundColorTop, backgroundColorBottom)
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Başlık
        Text(
            text = "BEBEK TAKİP UYGULAMASI",
            style = TextStyle(fontSize = 24.sp, color = Color.Black)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // E-posta Girişi
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-posta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Telefon Numarası Girişi
        OutlinedTextField(
            value = phone,
            onValueChange = { if (it.length <= 11 && it.all { char -> char.isDigit() }) phone = it },
            label = { Text("Telefon Numarası") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Şifre Girişi
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Şifre") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Kaydol Butonu
        Button(
            onClick = {
                sharedPreferences.edit().putBoolean("isRegistered", true).apply()
                navController.navigate("babyInfo")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kaydol ve Devam Et")
        }
    }
}

@Composable
fun BabyInfoScreen(navcontroller: NavController, babyViewModel: BabyViewModel) {
    var babyName by remember { mutableStateOf("") }
    var babySurname by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var tcNumber by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }

    val backgroundColorTop = Color(0xFFB3E5FC) // Açık mavi
    val backgroundColorBottom = Color(0xFFFFCDD2) // Açık pembe

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(backgroundColorTop, backgroundColorBottom)
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Başlık
        Text(
            text = "BEBEK BİLGİLERİ",
            style = TextStyle(fontSize = 24.sp, color = Color.Black)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Bebek Adı Girişi
        OutlinedTextField(
            value = babyName,
            onValueChange = { babyName = it },
            label = { Text("Bebek Adı") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bebek Soyadı Girişi
        OutlinedTextField(
            value = babySurname,
            onValueChange = { babySurname = it },
            label = { Text("Bebek Soyadı") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Doğum Tarihi Girişi
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Doğum Tarihi (GG.AA.YYYY)") },
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cinsiyet Girişi
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Cinsiyet (Erkek/Kız)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TC Kimlik Numarası Girişi
        OutlinedTextField(
            value = tcNumber,
            onValueChange = { if (it.length <= 11 ) tcNumber = it },
            label = { Text("TC Kimlik Numarası") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Kaydet Butonu
        Button(
            onClick = { showSnackbar = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Bebek Bilgilerini Kaydet")
        }

        // Snackbar göstermek
        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                action = {
                    Button(onClick = {
                        showSnackbar = false
                        // Cinsiyete göre ekranı aç
                        navcontroller.navigate("babyResult/$gender/$babyName/$babySurname")
                    }) {
                        Text("Devam Et")
                    }
                }
            ) {
                Text("Kaydolma işlemi tamamlandı")
            }
        }
    }
}

@Composable
fun BabyResultScreen(gender: String, babyName: String, babySurname: String) {
    val currentDate = remember { java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date()) }
    var temperature by remember { mutableStateOf<Int?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }



    val backgroundColor = when (gender.lowercase()) {
        "erkek" -> Color(0xFFB3E5FC) // Açık mavi
        "kiz" -> Color(0xFFFFCDD2)   // Açık pembe
        else -> Color.White          // Varsayılan renk
    }

    // Acil durum butonuna tıklanınca çağrılacak fonksiyon
    val context = LocalContext.current
    val emergencyPhoneNumber = "112" // Acil durum numarası (ambulans için)

    // Acil durum butonu işlevi
    fun initiateEmergencyCall() {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$emergencyPhoneNumber")
        context.startActivity(dialIntent)
    }


    LaunchedEffect(Unit) {
        val apiService = RetrofitInstance.api
        // Retrofit çağrısını enqueue metodu ile yapıyoruz
        apiService.getTemperature().enqueue(object : retrofit2.Callback<Map<String, Double>> {
            override fun onResponse(
                call: retrofit2.Call<Map<String, Double>>,
                response: retrofit2.Response<Map<String, Double>>
            ) {
                if (response.isSuccessful) { // Gelen sıcaklık verisini işliyoruz
                    val fetchedTemperature = response.body()?.get("temperatureC") as? Double
                    Log.d("Retrofit", "Başarılı yanıt: ${response.body()}")
                    temperature = fetchedTemperature?.toInt() ?: -1
                } else {
                    // API başarısız olduğunda
                    Log.e("Retrofit", "Yanıt başarısız: ${response.code()} - ${response.errorBody()?.string()}")
                    temperature = -1
                }
            }

            override fun onFailure(call: retrofit2.Call<Map<String, Double>>, t: Throwable) {
                // Hata durumunu işliyoruz
                Log.e("Retrofit", "Hata: ${t.localizedMessage}")
                temperature = -1
            }
        })
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter) // Kamera altına hizalama
        ) {
            Text(
                text = "$babyName $babySurname", // Bebeğin adı ve soyadı
                style = TextStyle(fontSize = 20.sp, color = Color.Black),
                modifier = Modifier
                    .padding(16.dp)
            )
            Text(
                text = currentDate, // Sadece tarih bilgisi
                style = TextStyle(fontSize = 14.sp, color = Color.Black),
                modifier = Modifier
                    .padding(16.dp)
            ) }
        // Acil durum butonu
        Button(
            onClick = { initiateEmergencyCall() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFC5C5C)) // Açık kırmızı
        ) {
            Text("Acil Durum", color = Color.Black)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            // Sıcaklık Verisi Gösterimi
            if (temperature != -1) {
                Text(
                    text = "Sıcaklık: $temperature C",
                    style = TextStyle(fontSize = 20.sp, color = Color.Black)
                )
            } else {
                Text(
                    text = "Veri Alınamıyor",
                    style = TextStyle(fontSize = 20.sp, color = Color.Red)
                )
            }

            Spacer(modifier = Modifier.height(8.dp)
            )
        }
    }
}




@Composable
fun MyApp(sharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    val babyViewModel: BabyViewModel = viewModel()

    // Bu 2 satır kaydolduktan sonra başlangıç ekranına dönmemeyi sağlıyor.!!
    //val isRegistered = sharedPreferences.getBoolean("isRegistered", false)
    //val startDestination = if (isRegistered) "babyResult/default" else "registration"


    NavHost(
        navController = navController,
        startDestination = "registration"
    ) {
        composable("registration") {
            RegistrationScreen(navController = navController, sharedPreferences = sharedPreferences)
        }
        composable("babyInfo") {
            BabyInfoScreen(navcontroller = navController, babyViewModel = babyViewModel)
        }
        composable("babyResult/{gender}/{babyName}/{babySurname}") { backStackEntry ->
            val gender = backStackEntry.arguments?.getString("gender") ?: ""
            val babyName = backStackEntry.arguments?.getString("babyName") ?: ""
            val babySurname = backStackEntry.arguments?.getString("babySurname") ?: ""
            BabyResultScreen(gender = gender, babyName = babyName, babySurname = babySurname)
        }

    }
}


class secondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(sharedPreferences= sharedPreferences)
        }
    }
}


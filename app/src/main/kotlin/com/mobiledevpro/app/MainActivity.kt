package com.mobiledevpro.app
package com.dacia.smartlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showSplash by remember { mutableStateOf(true) }

            // Splash Screen Timer (2 secondes)
            LaunchedEffect(key1 = true) {
                delay(2000L)
                showSplash = false
            }

            if (showSplash) {
                DaciaSplashScreen()
            } else {
                DashboardScreen()
            }
        }
    }
}

// 1. L'écran de démarrage avec le logo Dacia
@Composable
fun DaciaSplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // Remplace R.drawable.dacia_logo par ton image
        Image(
            painter = painterResource(id = android.R.drawable.ic_dialog_info), // Placeholder
            contentDescription = "Dacia Logo",
            modifier = Modifier.size(200.dp)
        )
        Text("Dacia Smart System", color = Color.White, modifier = Modifier.padding(top = 220.dp))
    }
}

// 2. L'Interface Principale du Launcher
@Composable
fun DashboardScreen() {
    Row(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        
        // A. BARRE LATÉRALE (Sidebar - 15% de l'écran)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.15f)
                .background(Color.Black)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { /* Ouvrir Téléphone */ }) { Text("📞") }
            Button(onClick = { /* Ouvrir Musique */ }) { Text("🎵") }
            Button(onClick = { /* Paramètres OBD */ }) { Text("⚙️") }
        }

        // B. ESPACE NAVIGATION HERE MAPS (65% de l'écran)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.65f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("📍 HERE Maps Intégré Ici", color = Color.Black, fontSize = 24.sp)
            // C'est ici que tu mettras le composant View de HERE SDK
        }

        // C. ESPACE INFOS OBD2 & MUSIQUE (20% de l'écran)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.20f)
                .background(Color(0xFF1E1E1E))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Widget OBD2
            Card(modifier = Modifier.fillMaxWidth().height(120.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Vitesse (OBD2)", fontSize = 16.sp, color = Color.Gray)
                    Text("120 km/h", fontSize = 32.sp, color = Color.Red)
                    Text("RPM: 2500", fontSize = 14.sp)
                }
            }

            // Widget Musique
            Card(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Musique", fontSize = 16.sp, color = Color.Gray)
                    Text("Titre en cours...", fontSize = 18.sp)
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text("⏮")
                        Text("▶️")
                        Text("⏭")
                    }
                }
            }
        }
    }
}

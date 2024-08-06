package com.uvg.example.primerproyecto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.example.primerproyecto.ui.theme.PrimerProyectoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerProyectoTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    Greeting(
                        names = listOf("Android Studio", "Compose", "Kotlin"),
                        onButtonClick = {
                            // Mostrar el Snackbar
                            scope.launch {
                                snackbarHostState.showSnackbar("Botón presionado")
                            }
                            // Mostrar mensaje en Logcat
                            Log.d("MainActivity", getString(R.string.AccionBoton))
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(names: List<String>, onButtonClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        for (name in names) {
            Text(
                text = stringResource(R.string.hello_template, name),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 4.dp) // Espaciado entre textos
            )
        }
        // Agregar el botonn
        GreetingButton(onClick = onButtonClick)
        // Contenedor de elementos
        ItemList(items = listOf("Item 1", "Item 2", "Item 3"))
    }
}

@Composable
fun GreetingButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(), // Hacer que el boton ocupe todo el ancho disponible
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = "Press me")
    }
}

@Composable
fun ItemList(items: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(items) { item ->
            Text(
                text = item,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PrimerProyectoTheme {
        Greeting(names = listOf("world", "cómo", "estamos"), onButtonClick = {})
    }
}

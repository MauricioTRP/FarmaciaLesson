package com.kotlinpl.farmacialesson.ui.components

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun LeafletMap(
    modifier: Modifier = Modifier,
) {
    val loadedUrl = remember { mutableStateOf<String?>(null) }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            WebView(ctx).apply {
                setLayerType(WebView.LAYER_TYPE_HARDWARE, null)

                // Configuraciones
                settings.apply {
                    // Js
                    javaScriptEnabled = true
                    domStorageEnabled = true

                    // Acceso a los assets
                    allowFileAccess = true
                    allowContentAccess = true

                    // CORS
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }

                // bridge entre JS <-> Kotlin
                addJavascriptInterface(
                    object {
                        @JavascriptInterface
                        fun onMapReady() {
                            Log.d("LeafletMap", "Map is ready")
                            onMapReady()
                        }


                    },
                    "Android"
                )

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        // El html ya cargó
                    }
                }

                webChromeClient = object : WebChromeClient() {
                    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                        Log.d("LeafletMap", "Console Message -> ${consoleMessage?.message()}")
                        return super.onConsoleMessage(consoleMessage)
                    }
                }

                // asset url
                val initialURL = "file:///android_asset/map.html"
                loadUrl(initialURL) // Cargamos la url

                loadedUrl.value = initialURL
            }
        }
    )

}
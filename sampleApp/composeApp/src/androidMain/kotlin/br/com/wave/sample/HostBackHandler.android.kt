package br.com.wave.sample

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun HostBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    BackHandler(enabled = enabled, onBack = onBack)
}

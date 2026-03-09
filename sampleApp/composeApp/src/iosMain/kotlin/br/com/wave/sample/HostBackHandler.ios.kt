package br.com.wave.sample

import androidx.compose.runtime.Composable

@Composable
actual fun HostBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    // iOS native back integration is host-specific.
}

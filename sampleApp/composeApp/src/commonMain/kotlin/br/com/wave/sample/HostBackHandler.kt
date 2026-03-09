package br.com.wave.sample

import androidx.compose.runtime.Composable

@Composable
expect fun HostBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
)

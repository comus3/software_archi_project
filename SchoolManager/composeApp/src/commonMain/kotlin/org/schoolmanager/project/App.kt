package org.schoolmanager.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.compose_multiplatform



@Composable
@Preview
fun App() {
    MaterialTheme {
        HomePage().Content()
    }
}




package org.schoolmanager.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.text.style.TextDecoration



class HomePage {
    @Composable
    fun Welcome(name: String) {
        BasicText(
            text= "Hello, $name!",
            style= TextStyle(
                color= Color.Black,
                fontSize= 30.sp,
                fontWeight= FontWeight.Bold,
                textDecoration= TextDecoration.Underline
            )
        )
    }
}

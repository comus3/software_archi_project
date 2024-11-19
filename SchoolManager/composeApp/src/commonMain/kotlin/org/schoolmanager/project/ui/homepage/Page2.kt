package org.schoolmanager.project.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.iconcontact



class Page2(private val BackHomePage: ()-> Unit, private val GoToSettings: ()-> Unit) {
    @Composable
    fun Content(){
        Column(modifier= Modifier.fillMaxSize().padding(16.dp)){
            Image(
                painter= painterResource(Res.drawable.iconcontact),
                contentDescription= "Back",
                modifier= Modifier
                    .size(40.dp)
                    .clickable{BackHomePage()}
                    .padding(bottom= 16.dp)
            )
            Image(
                painter= painterResource(Res.drawable.iconcontact),
                contentDescription= "Settings",
                modifier= Modifier
                    .size(40.dp)
                    .clickable {GoToSettings()}
                    .padding(bottom = 16.dp)
            )
        }
    }
}
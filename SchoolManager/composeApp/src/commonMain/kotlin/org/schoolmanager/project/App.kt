package org.schoolmanager.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material.ButtonDefaults
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.compose_multiplatform



@Composable
@Preview
fun App(){
    MaterialTheme{
        Column(Modifier.fillMaxWidth(), horizontalAlignment= Alignment.CenterHorizontally) {
            //WELCOME+USERNAME
            HomePage().Welcome("Zlatan The best")

            //2 BUTTONS: LIST OF TODAY'S CLASSES +LAST NEWS
            var selectedButton by remember {mutableStateOf("Today classes")}
            Row(
                horizontalArrangement= Arrangement.spacedBy(30.dp),
                verticalAlignment= Alignment.CenterVertically,
                modifier= Modifier.padding(top= 16.dp)
            )
            {
                Button(
                    onClick= {selectedButton= "Today classes"},
                    colors= ButtonDefaults.buttonColors(backgroundColor= if (selectedButton=="Today classes") Color.Blue else Color.Gray),
                    modifier= Modifier.padding(8.dp, top= 30.dp))
                {Text("Today classes", color= Color.White)}

                Button(
                    onClick= {selectedButton= "Last News"},
                    colors= ButtonDefaults.buttonColors(backgroundColor= if (selectedButton=="Last News") Color.Blue else Color.Gray),
                    modifier= Modifier.padding(8.dp, top= 30.dp))
                {Text("Last News", color= Color.White)                }
            }

            //VARIABLE CONTENT OF BUTTONS
            Spacer(modifier= Modifier.height(16.dp))
            when (selectedButton){
                "Today classes"-> TodayClassesContent()
                "Last News"-> LastNewsContent()
            }
        }
    }
}


//FCT VARIABLE CONTENT OF BUTTONS
@Composable
fun TodayClassesContent(){
    Column(
        modifier= Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment= Alignment.CenterHorizontally
    ) {
        Text(
            text= "Today's Classes",
            fontSize= 24.sp,
            fontWeight= FontWeight.Bold,
            textAlign= TextAlign.Center,
            color= Color.Black
        )
        Spacer(modifier= Modifier.height(8.dp))
        Text("Class 1: Mathematics")
        Text("Class 2: Science")
        Text("Class 3: History")
    }
}

@Composable
fun LastNewsContent(){
    Column(
        modifier= Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment= Alignment.CenterHorizontally
    ) {
        Text(
            text= "Latest News",
            fontSize= 24.sp,
            fontWeight= FontWeight.Bold,
            textAlign= TextAlign.Center,
            color= Color.Black
        )
        Spacer(modifier= Modifier.height(8.dp))
        Text("News 1: School event coming up!")
        Text("News 2: New library books available")
        Text("News 3: Sports day results announced")
    }
}

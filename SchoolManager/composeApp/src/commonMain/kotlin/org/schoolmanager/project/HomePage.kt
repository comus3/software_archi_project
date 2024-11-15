package org.schoolmanager.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.profilephoto



//HOMEPAGE
class HomePage(private val GoToProfile:()-> Unit){
    @Composable
    fun Content(){
        Column(Modifier.fillMaxWidth(), horizontalAlignment= Alignment.CenterHorizontally){
            //PROFILE PHOTO
            Box(modifier= Modifier.fillMaxWidth().padding(top=10.dp, bottom=6.dp, end=10.dp)){
                Image(
                    painter= painterResource(Res.drawable.profilephoto),
                    contentDescription= "ProfilePhoto",
                    modifier= Modifier
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .size(65.dp)
                        .clickable{GoToProfile()},
                    contentScale= ContentScale.Crop
                )
            }

            //WELCOME+USERNAME
            Welcome("Zlatan The best")

            //2 BUTTONS: LIST OF TODAY'S CLASSES +LAST NEWS
            var selectedButton by remember {mutableStateOf("Today classes")}
            Row(
                horizontalArrangement= Arrangement.spacedBy(30.dp),
                verticalAlignment= Alignment.CenterVertically,
                modifier= Modifier.padding(top=12.dp)
            )
            {
                Button(
                    onClick= {selectedButton= "Today classes"},
                    colors= ButtonDefaults.buttonColors(backgroundColor= if (selectedButton=="Today classes") Color(red= 62, green= 96, blue= 160) else Color.Gray),
                    modifier= Modifier.padding(8.dp, top=30.dp).width(150.dp).height(55.dp))
                {Text("Today classes", color= Color.White, fontSize= 22.sp, textAlign= TextAlign.Center)}

                Button(
                    onClick= {selectedButton= "Last News"},
                    colors= ButtonDefaults.buttonColors(backgroundColor= if (selectedButton=="Last News") Color(red= 62, green= 96, blue= 160) else Color.Gray),
                    modifier= Modifier.padding(8.dp, top=30.dp).width(150.dp).height(55.dp))
                {Text("Last News", color= Color.White, fontSize= 22.sp, textAlign= TextAlign.Center)}
            }

            //VARIABLE CONTENT OF BUTTONS
            Spacer(modifier= Modifier.height(16.dp))
            when (selectedButton){
                "Today classes"-> TodayClassesContent()
                "Last News"-> LastNewsContent()
            }
        }

    }



    //FCT WELCOME "NAME" TEXT
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

    //FCT VARIABLE CONTENT OF BUTTONS: LIST OF TODAY'S CLASSES +LAST NEWS
    @Composable
    fun TodayClassesContent(){
        Column(modifier= Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment= Alignment.CenterHorizontally){
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
        Column(modifier= Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment= Alignment.CenterHorizontally){
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
}

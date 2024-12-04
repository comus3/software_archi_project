package org.schoolmanager.project.ui.syllabus

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.viewmodel.SyllabusViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.shopping_cart

@Composable
fun HomeSyllabusScreen(BackCourse: ()-> Unit, GoToCart: ()-> Unit, syllabusviewModel: SyllabusViewModel) {
    Column(modifier= Modifier.fillMaxSize().padding(0.dp), horizontalAlignment= Alignment.CenterHorizontally, verticalArrangement= Arrangement.Top){
        Row(
            Modifier.fillMaxWidth().padding(top= 15.dp, bottom= 40.dp),
            horizontalArrangement= Arrangement.SpaceBetween,
            verticalAlignment= Alignment.CenterVertically)
        {
            Image(
                painter= painterResource(Res.drawable.back),
                contentDescription= "Back to Course",
                modifier= Modifier
                    .size(77.dp)
                    .clickable{BackCourse()}
                    .padding(bottom = 16.dp)
            )

            Text(
                text= "Syllabus",
                style= TextStyle(
                    fontSize= 36.sp,
                    fontWeight= FontWeight.Bold,
                    color= Color.Black),
                modifier= Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter= painterResource(Res.drawable.shopping_cart),
                contentDescription= "Cart",
                modifier= Modifier
                    .size(80.dp)
                    .clickable{GoToCart()}
                    .padding(bottom= 16.dp, end= 15.dp),
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }


        Text(
            text= "Select your orientation",
            style= TextStyle(
                fontSize= 22.sp,
                color= Color.Gray,
                fontWeight= FontWeight.Normal),
            modifier= Modifier.padding(bottom= 24.dp)
        )

        LazyColumn(verticalArrangement= Arrangement.spacedBy(16.dp), modifier= Modifier.fillMaxWidth()){
            items(syllabusviewModel.orientations.size){index->
                val orientation= syllabusviewModel.orientations[index]

                //BUTTON FOR EACH ORIENTATION
                Button(
                    onClick= {},
                    modifier= Modifier.fillMaxWidth().padding(horizontal= 20.dp).height(65.dp),
                    colors= ButtonDefaults.buttonColors(backgroundColor= Color(0xFF3E61A0)))
                {Text(text= orientation.name,
                    style= TextStyle(fontSize= 30.sp, fontWeight= FontWeight.Bold, color= Color.White))
                }
            }
        }
    }
}
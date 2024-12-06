package org.schoolmanager.project.ui.syllabus

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.Orientation
import org.schoolmanager.project.viewmodel.SyllabusViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.shopping_cart


@Composable
fun SyllabusScreen(BackCourse: ()-> Unit, GoToCart: ()-> Unit, orientation: Orientation, syllabusviewModel: SyllabusViewModel){
    Column(modifier= Modifier.fillMaxSize().padding(0.dp), horizontalAlignment= Alignment.CenterHorizontally, verticalArrangement= Arrangement.Top) {
        Row(
            Modifier.fillMaxWidth().padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Back to Course",
                modifier = Modifier
                    .size(77.dp)
                    .clickable {BackCourse()}
                    .padding(bottom = 16.dp)
            )

            Text(
                text = orientation.name,
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(Res.drawable.shopping_cart),
                contentDescription = "Cart",
                modifier = Modifier
                    .size(80.dp)
                    .clickable { GoToCart() }
                    .padding(bottom = 16.dp, end = 15.dp),
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }


        val filteredSyllabus = syllabusviewModel.syllabus.filter { it.idorientation == orientation.id }
        LazyColumn {
            items(filteredSyllabus.size) { index ->
                val syllabus = filteredSyllabus[index]
                Text(
                    text = syllabus.syllabus,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
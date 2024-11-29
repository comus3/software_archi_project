package org.schoolmanager.project.ui.syllabus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.schoolmanager.project.viewmodel.SyllabusViewModel

@Composable
fun HomeSyllabusScreen(syllabusviewModel: SyllabusViewModel) {
    Column(modifier= Modifier.fillMaxSize().padding(16.dp), horizontalAlignment= Alignment.CenterHorizontally, verticalArrangement= Arrangement.Top){
        Text(
            text= "Syllabus",
            style= TextStyle(
                fontSize= 36.sp,
                fontWeight= FontWeight.Bold,
                color= Color.Black),
            modifier= Modifier.padding(bottom = 16.dp)
        )

        Text(
            text= "Welcome! Select your orientation",
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
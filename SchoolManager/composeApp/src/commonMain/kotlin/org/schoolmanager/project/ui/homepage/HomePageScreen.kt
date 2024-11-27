package org.schoolmanager.project.ui.homepage

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.NewsHomePage
import org.schoolmanager.project.viewmodel.CalendarViewModel
import org.schoolmanager.project.viewmodel.NewsHomePageViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.profilephoto
import schoolmanager.composeapp.generated.resources.forward



//HOMEPAGE
@Composable
fun HomePageScreen(SelectedButton: String= "Today classes", GoToProfile:()-> Unit, newsHomePageViewModel: NewsHomePageViewModel= NewsHomePageViewModel(), calendarViewModel: CalendarViewModel = CalendarViewModel(), GoToDetailsNews: (NewsHomePage)-> Unit){
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
        var CurrentSelectedButton by remember {mutableStateOf(SelectedButton)}
        Row(
            horizontalArrangement= Arrangement.spacedBy(30.dp),
            verticalAlignment= Alignment.CenterVertically,
            modifier= Modifier.padding(top=12.dp))
        {
            Button(
                onClick= {CurrentSelectedButton= "Today classes"},
                colors= ButtonDefaults.buttonColors(backgroundColor= if (CurrentSelectedButton=="Today classes") Color(red= 62, green= 96, blue= 160) else Color.Gray),
                modifier= Modifier.padding(8.dp, top=30.dp).width(150.dp).height(55.dp))
            {Text("Today classes", color= Color.White, fontSize= 22.sp, textAlign= TextAlign.Center)}

            Button(
                onClick= {CurrentSelectedButton= "Last News"},
                colors= ButtonDefaults.buttonColors(backgroundColor= if (CurrentSelectedButton=="Last News") Color(red= 62, green= 96, blue= 160) else Color.Gray),
                modifier= Modifier.padding(8.dp, top=30.dp).width(150.dp).height(55.dp))
            {Text("Last News", color= Color.White, fontSize= 22.sp, textAlign= TextAlign.Center)}
        }

        //VARIABLE CONTENT OF BUTTONS
        Spacer(modifier= Modifier.height(16.dp))
        when (CurrentSelectedButton){
            "Today classes"-> TodayClassesContent(calendarViewModel)
            "Last News"-> LastNewsContent(newsHomePageViewModel, GoToDetailsNews)
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
fun TodayClassesContent(viewModel: CalendarViewModel){
    //DATE TODAY
    val TodayDate= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    //TODAY COURSE
    val TodayCourses= viewModel.getCoursesForDate(TodayDate)

    LazyColumn(modifier= Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment= Alignment.CenterHorizontally){
        //IF MINIMUM 1 COURSE
        if (TodayCourses.isNotEmpty()){
            items(TodayCourses){course->
                Card(
                    modifier= Modifier.fillMaxWidth().height(130.dp).padding(vertical= 12.dp),
                    elevation= 8.dp,
                    shape= RoundedCornerShape(16.dp)
                )
                {
                    Row(
                        modifier= Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement= Arrangement.SpaceBetween,
                        verticalAlignment= Alignment.CenterVertically
                    )
                    {
                        //NAME COURSE
                        Column{
                            Row(verticalAlignment= Alignment.CenterVertically){
                                course.image?.let{painterResource(it)}?.let{
                                    Image(
                                        painter= it,
                                        contentDescription= "ProfilePhoto",
                                        modifier= Modifier
                                            .clip(CircleShape)
                                            .size(40.dp),
                                        contentScale= ContentScale.Crop
                                    )
                                }
                                Spacer(modifier= Modifier.width(8.dp))
                                Text(
                                    text= course.name,
                                    fontSize= 28.sp,
                                    fontWeight= FontWeight.Bold,
                                    color= Color.Black
                                )
                            }
                            Spacer(modifier= Modifier.height(4.dp))
                            //TIME OF THE COURSE
                            Text(
                                text= course.startTime +" - " +course.endTime,
                                fontSize= 20.sp,
                                color= Color.Black
                            )
                        }
                        //ROOM
                        Column(horizontalAlignment= Alignment.End){
                            Text(
                                text= "Room",
                                fontSize= 24.sp,
                                fontWeight= FontWeight.Bold,
                                color= Color.Black
                            )
                            Spacer(modifier= Modifier.height(4.dp))
                            Text(
                                text= course.hall,
                                fontSize= 20.sp,
                                color= Color.Black
                            )
                        }
                    }
                }
            }
            //SPACE NAVIGATION
            item{Spacer(modifier= Modifier.height(70.dp))}
        }
        //IF NOT COURSE
        else{
            item{
                Text(
                    text= "No classes scheduled for today.",
                    fontSize= 20.sp,
                    color= Color.Gray,
                    modifier= Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun LastNewsContent(viewModel: NewsHomePageViewModel, GoToDetailsNews: (NewsHomePage)-> Unit){
    //DATAS FROM VIEWMODEL
    val News= viewModel.news

    LazyColumn(modifier= Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment= Alignment.CenterHorizontally)
    {
        items(News){new->
            Card(
                modifier= Modifier.fillMaxWidth().height(120.dp).padding(vertical= 12.dp)
                    .clickable {GoToDetailsNews(new)},
                elevation= 8.dp,
                shape= RoundedCornerShape(16.dp),
            )
            {
                Row(
                    modifier= Modifier.fillMaxWidth().padding(10.dp, end=35.dp),
                    horizontalArrangement= Arrangement.SpaceBetween,
                    verticalAlignment= Alignment.CenterVertically
                )
                {
                    //TIME OF THE NEWS
                    Column{
                        Text(
                            text= new.time,
                            fontSize= 22.sp,
                            fontWeight= FontWeight.Bold,
                            color= Color.Gray,
                        )
                        Spacer(modifier= Modifier.height(4.dp))
                        //TITLE OF THE NEWS
                        Text(
                            text= new.title,
                            fontSize= 25.sp,
                            fontWeight= FontWeight.Bold,
                            color= Color.Black
                        )
                    }
                    //ARROW FORWARD
                    Image(
                        painter= painterResource(Res.drawable.forward),
                        contentDescription= "GoToDetailsNews",
                        modifier= Modifier.size(40.dp),
                    )
                }
            }
        }
        //SPACE NAVIGATION
        item{Spacer(modifier= Modifier.height(70.dp))}
    }
}
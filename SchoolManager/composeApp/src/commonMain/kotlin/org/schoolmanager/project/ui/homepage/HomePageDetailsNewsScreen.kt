package org.schoolmanager.project.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.NewsHomePage
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back

@Composable
fun HomePageDetailsNews(news: NewsHomePage, BackHomePage:(String)-> Unit){
    Column(
        modifier= Modifier.fillMaxSize().padding(16.dp).background(MaterialTheme.colors.background),)
    {
        //BACK BUTTON TO HOMEPAGE
        Row(
            Modifier.fillMaxWidth().padding(bottom= 16.dp),
            verticalAlignment= Alignment.CenterVertically)
        {
            Image(
                painter= painterResource(Res.drawable.back),
                contentDescription= "Go to Home Page",
                modifier= Modifier.size(50.dp).clickable {BackHomePage("Last News")}
            )
        }

        //TIME OF THE NEWS
        Text(
            text= news.time,
            fontSize= 22.sp,
            fontWeight= FontWeight.Bold,

            modifier= Modifier.padding(start= 10.dp,bottom = 8.dp)
        )

        //TITLE OF THE NEWS
        Text(
            text= news.title,
            fontSize= 30.sp,
            fontWeight= FontWeight.Bold,

            modifier= Modifier
                .padding(start= 10.dp, bottom= 8.dp)
        )
        //DESCRIPTION OF THE NEWS
        Text(
            text= news.description,
            fontSize= 20.sp,

            modifier= Modifier.padding(start= 10.dp, bottom= 8.dp)
        )
    }
}
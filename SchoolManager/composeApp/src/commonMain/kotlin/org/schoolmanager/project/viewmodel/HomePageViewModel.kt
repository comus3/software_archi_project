package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import org.schoolmanager.project.data.model.Contact
import org.schoolmanager.project.data.model.DataHomePageCourse
import org.schoolmanager.project.data.model.DataHomePageNews

//DATAS COURSE CALENDAR
class HomePageViewModel: ViewModel() {
    val courses= mutableStateListOf(
       DataHomePageCourse("Management", "08:30 - 11:40", "2F10"),
       DataHomePageCourse("Electronic", "12:30 - 15:30", "2F50"),
       DataHomePageCourse("Network", "16:00 - 18:30", "1E04")
    )

    val news= mutableStateListOf(
        DataHomePageNews("12/11/2024", "Exam schedule", "Dear Students,\n"+
                "We are thrilled to announce an upcoming school event that promises to be both exciting and enriching for everyone! Mark your calendars and stay tuned for more details coming your way soon. We can’t wait to see you there!\n" +
                "\n"+
                "Best regards,\nDENIS Anaïs"),
        DataHomePageNews("09/11/2024", "Revue 2024", "Dear Students,\n" +
                "We are thrilled to announce an upcoming school event that promises to be both exciting and enriching for everyone! Mark your calendars and stay tuned for more details coming your way soon. We can’t wait to see you there!\n" +
                "\n"+
                "Best regards,\nSAHIB Oussama"),
        DataHomePageNews("28/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe")
    )
}
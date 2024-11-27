package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.NewsHomePage


class NewsHomePageViewModel: ViewModel(){
    val news= mutableStateListOf(
        NewsHomePage("12/11/2024", "Exam schedule", "Dear Students,\n"+
                "We are thrilled to announce an upcoming school event that promises to be both exciting and enriching for everyone! Mark your calendars and stay tuned for more details coming your way soon. We can’t wait to see you there!\n" +
                "\n"+
                "Best regards,\nDENIS Anaïs"),
        NewsHomePage("09/11/2024", "Revue 2024", "Dear Students,\n" +
                "We are thrilled to announce an upcoming school event that promises to be both exciting and enriching for everyone! Mark your calendars and stay tuned for more details coming your way soon. We can’t wait to see you there!\n" +
                "\n"+
                "Best regards,\nSAHIB Oussama"),
        NewsHomePage("28/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe"),
        NewsHomePage("25/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe"),
        NewsHomePage("20/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe"),
        NewsHomePage("15/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe"),
        NewsHomePage("13/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe"),
        NewsHomePage("10/10/2024", "Closing of the Library", "Dear [Recipient's Name],\n" +
                "Please be advised that the library will be closed for two weeks from [start date] to [end date]. We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for your understanding!\n" +
                "\n" +
                "Best regards,\nLAHAYE Philipe")
    )
}
package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.NewsHomePage


class NewsHomePageViewModel: ViewModel(){
    val news= mutableStateListOf(
        NewsHomePage(1, "10/10/2024", "School Annual Talent Show",
        "Dear Students and Staff,\n"+
            "We are excited to announce that our Annual Talent Show will take place on 22/10/2024. We encourage all students"+
            "to showcase their talents, whether it's singing, dancing, acting, or any other creative expression."+
            "There will be prizes for the best performances!\n\n"+
            "Best regards,\nEvent Team"),

        NewsHomePage(2,"13/10/2024", "Teachers' Professional Development Day",
        "Dear Students,\n"+
            "Please be informed that there will be no classes on 15/10/2024 as our teachers will be attending"+
            "a professional development workshop. This is a great opportunity for our teaching staff to enhance"+
            "their skills and knowledge to improve the learning experience.\n\n"+
            "Best regards,\nAdministration Team"),

        NewsHomePage(3,"15/10/2024", "New School Club Introductions",
        "Dear Students,\n"+
            "We are excited to introduce new school clubs for the year 2024, including Photography Club,"+
            "Debate Club, and Coding Club. If you're interested in joining any of these clubs, please visit"+
            "the student services desk to sign up.\n\n"+
            "Best regards,\nStudent Affairs"),

        NewsHomePage(4,"20/10/2024", "Parent-Teacher Conferences",
        "Dear Parents and Guardians,\n"+
                "We would like to invite you to the upcoming Parent-Teacher Conferences scheduled for 22/10/2024."+
                "This is a great opportunity for you to meet with your child's teachers and discuss their progress."+
                "Please sign up at the reception.\n\n"+
                "Best regards,\nSchool Administration"),

        NewsHomePage(5,"28/10/2024", "Winter Break Announcement",
        "Dear Students and Staff,\n" +
                "Please be informed that the school will be closed for Winter Break from 02/11/2024 to 08/11/2024."+
                "We hope everyone has a restful and joyful break!\n\n"+
                "Best regards,\nSchool Calendar Team"),

        NewsHomePage(6,"01/11/2024", "Closing of the Library",
        "Dear Students,\n"+
            "Please be advised that the library will be closed for two weeks from 02/11/2024 to 08/11/2024."+
            "We apologize for any inconvenience and encourage you to plan your visits accordingly. Thank you for"+
            "your understanding!\n\n"+
            "Best regards,\nLAHAYE Philipe"),

        NewsHomePage(7,"09/11/2024", "Revue 2024",
        "Dear Students,\n" +
            "We are thrilled to announce an upcoming school event that promises to be both exciting and enriching"+
            "for everyone! Mark your calendars and stay tuned for more details coming your way soon. We can’t wait to"+
            "see you there!\n\n"+
            "Best regards,\nSAHIB Oussama"),

        NewsHomePage(8,"12/11/2024", "Exam schedule",
        "Dear Students,\n"+
            "We are thrilled to announce an upcoming school event that promises to be both exciting and enriching"+
            "for everyone! Mark your calendars and stay tuned for more details coming your way soon."+
            "We can’t wait to see you there!\n\n"+
            "Best regards,\nDENIS Anaïs"),

        NewsHomePage(9,"14/11/2024", "School Community Charity Drive",
        "Dear Students,\n"+
            "This year, we are launching a school-wide charity drive to support local organizations."+
            "Donations of clothes, food, and toys will be accepted from 16/11/2024. Let’s come together to give back to"+
            "the community!\n\n"+
            "Best regards,\nCommunity Service Club"),

        NewsHomePage(10,"17/11/2024", "International Day Celebration",
        "Dear Students,\n"+
            "Join us as we celebrate our diverse cultures on International Day, 20//11/2024. Students will have " +
            "the opportunity to share their cultural traditions, food, and performances. It’s a day to learn and " +
            "appreciate our global community!\n\n" +
            "Best regards,\nEvent Committee"),

        NewsHomePage(11,"18/11/2024", "Sports Day Announcement",
        "Dear Students,\n"+
        "We are excited to announce that the annual Sports Day will take place on 25/11/2024."+
        "Get ready to participate in various sports events and cheer on your classmates! There will be prizes"+
        "for winners in each category.\n\n"+
        "Best regards,\nSports Committee"),

        NewsHomePage(12,"20/11/2024", "School Talent Show Auditions",
        "Dear Students,\n"+
        "Auditions for the School Talent Show are scheduled for 27/11/2024. If you wish to perform in the talent show,"+
        "please sign up at the student services desk before 25/11/2024. We look forward to seeing your talent!\n\n"+
        "Best regards,\nEvent Team"),

        NewsHomePage(13,"22/11/2024", "Book Fair Announcement",
        "Dear Students and Parents,\n"+
        "We are pleased to announce the upcoming Book Fair that will be held from 30/11/2024 to 03/12/2024"+
        "in the school library. This is a great opportunity to purchase books for the holiday season and"+
        "support our school library.\n\n"+
        "Best regards,\nLibrary Committee")
    )
}
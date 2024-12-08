package org.schoolmanager.project.ui.syllabus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
fun SyllabusScreen(
    BackCourse: () -> Unit,
    GoToCart: () -> Unit,
    orientation: Orientation,
    syllabusviewModel: SyllabusViewModel
) {
    val syllabusList by syllabusviewModel.syllabus.collectAsState()
    val filteredSyllabus = syllabusList.filter { it.idorientation == orientation.id }

    val selectedItems = remember { mutableStateListOf<Boolean>().apply { addAll(List(filteredSyllabus.size) { false }) } }
    val quantities = remember { mutableStateListOf<Int>().apply { addAll(filteredSyllabus.map { it.quantity }) } }

    val allSelected = selectedItems.all { it }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Haut de la page
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Back to Course",
                modifier = Modifier.size(60.dp).clickable { BackCourse() }
            )
            Text(
                text = orientation.name,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Image(
                painter = painterResource(Res.drawable.shopping_cart),
                contentDescription = "Cart",
                modifier = Modifier.size(60.dp).clickable { GoToCart() }
            )
        }

        // Section "Tout sélectionner" et bouton panier
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = allSelected,
                    onCheckedChange = { isChecked ->
                        for (i in selectedItems.indices) {
                            selectedItems[i] = isChecked
                        }
                    }
                )
                Text(
                    text = "Tout sélectionner",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(
                onClick = {

                },
                modifier = Modifier.size(width = 140.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3E61A0))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.shopping_cart),
                        contentDescription = "Panier",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        text = "Panier",
                        style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Liste des syllabus avec bordure
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(filteredSyllabus.size) { index ->
                    val syllabus = filteredSyllabus[index]

                    Row(
                        Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedItems[index],
                            onCheckedChange = { isChecked -> selectedItems[index] = isChecked },
                            modifier = Modifier.padding(end = 16.dp)
                        )

                        Column(Modifier.weight(1f)) {
                            Text(
                                text = syllabus.syllabus,
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "${syllabus.price}€",
                                style = TextStyle(fontSize = 14.sp, color = Color.Black)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Button(
                                onClick = { if (quantities[index] > 1) quantities[index] -= 1 },
                                modifier = Modifier.size(30.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                            ) { Text("-", style = TextStyle(fontSize = 18.sp, color = Color.Black)) }
                            Text(
                                text = quantities[index].toString(),
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = TextStyle(fontSize = 16.sp)
                            )
                            Button(
                                onClick = { quantities[index] += 1 },
                                modifier = Modifier.size(30.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                            ) { Text("+", style = TextStyle(fontSize = 18.sp, color = Color.Black)) }
                        }

                        Button(
                            onClick = {
                                val syllabusItem = filteredSyllabus[index].copy(quantity = quantities[index])
                                syllabusviewModel.addToCart(syllabusItem)
                            },
                            modifier = Modifier.size(width = 50.dp, height = 40.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3E61A0))
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.shopping_cart),
                                contentDescription = "Add to Cart",
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                    }
                }
            }
        }
    }
}



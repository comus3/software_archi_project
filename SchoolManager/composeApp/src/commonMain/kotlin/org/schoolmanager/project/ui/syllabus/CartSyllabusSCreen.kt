package org.schoolmanager.project.ui.syllabus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*

import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.viewmodel.SyllabusViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.trash


@Composable
fun CartSyllabusScreen(
    BackHomeSyllabus: () -> Unit,
    syllabusviewModel: SyllabusViewModel
) {
    val cartItems by syllabusviewModel.cartItems.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header
        Row(
            Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Back to Course",
                modifier = Modifier.size(60.dp).clickable { BackHomeSyllabus() }
            )
            Text(
                text = "My Cart",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
        }

        // List of Cart Items
        if (cartItems.isEmpty()) {
            Text("Votre panier est vide", style = TextStyle(fontSize = 18.sp, color = Color.Gray))
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(cartItems.size) { index ->
                    val item = cartItems[index]
                    CartItem(
                        title = item.syllabus,
                        description = "Bloc ${item.idorientation}",
                        price = item.price,
                        quantity = item.quantity,
                        onQuantityChange = { newQuantity ->
                            syllabusviewModel.addToCart(item.copy(quantity = newQuantity))
                        },
                        onRemove = {
                            syllabusviewModel.removeFromCart(item.id)
                        }
                    )
                }
            }

            // Total Price
            val totalPrice = cartItems.sumOf { it.price * it.quantity }
            val formattedTotalPrice = (totalPrice * 100).toInt() / 100.0
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TOTAL : $formattedTotalPrice€",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Button(onClick = { syllabusviewModel.clearCart() }) {
                    Text("Vider le panier")
                }
            }
        }
    }
}















//
//@Composable
//fun CartSyllabusScreen(BackHomeSyllabus: () -> Unit, syllabusviewModel: SyllabusViewModel) {
//    val cartItems = syllabusviewModel.cartItems
//
//
//    Column(
//        modifier= Modifier.fillMaxSize().padding(5.dp), horizontalAlignment= Alignment.CenterHorizontally, verticalArrangement= Arrangement.Top
//    ){
//    Row(
//        Modifier.fillMaxWidth().padding(top= 12.dp, start= 3.dp),
//        horizontalArrangement= Arrangement.SpaceBetween,
//        verticalAlignment= Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(Res.drawable.back),
//            contentDescription = "Back to Course",
//            modifier = Modifier
//                .size(60.dp)
//                .clickable { BackHomeSyllabus() }
//        )
//        Spacer(modifier = Modifier.weight(0.55f))
//        Text(
//            text = "My Cart",
//            style = TextStyle(
//                fontSize = 36.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            ),
////                    modifier = Modifier.padding(bottom = 16.dp)
//        )
//        Spacer(modifier = Modifier.weight(1f))
//    }
//
//
//    LazyColumn(
//        modifier= Modifier.fillMaxSize().padding(5.dp, top= 13.dp), horizontalAlignment= Alignment.CenterHorizontally, verticalArrangement= Arrangement.Top
//    ) {
//        // Zone avec bordure contenant uniquement les articles
////        item {
////            Column(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(16.dp, bottom=0.dp, end= 16.dp, top= 10.dp) // Padding externe ajouté ici pour espacer la zone de bordure
////                    .border(
////                        BorderStroke(2.dp, Color.Black),
////                        shape = RoundedCornerShape(12.dp)
////                    )
////                    .padding(13.dp) // Padding interne (inchangé)
////            )  {
//                items(cartItems.size) { index ->
//                    val item = cartItems[index]
//                    CartItem(
//                        title = item.syllabus,
//                        description = "Bloc ${item.idorientation}",
//                        price = item.price,
//                        quantity = item.quantity,
//                        onQuantityChange = { newQuantity ->
//                            if (newQuantity > 0) {
//                                cartItems[index] = item.copy(quantity = newQuantity)
//                            } else {
//                                syllabusviewModel.removeFromCart(item)
//                            }
//                        },
//                        onRemove = { syllabusviewModel.removeFromCart(item) }
//                    )
//                }
////            }
////        }
//
//        // Calcul du prix total et affichage avec le bouton "Order"
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//            val totalPrice = cartItems.sumOf { it.price * it.quantity }
//            val formattedTotalPrice = (totalPrice * 100).toInt() / 100.0
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
////                    .padding(vertical = 0.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // Texte pour le total à gauche
//                Text(
//                    text = "TOTAL : $formattedTotalPrice€",
//                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
//                    modifier = Modifier.align(Alignment.CenterVertically).padding(start= 25.dp)
//                )
//
//                // Bouton "Order" à droite
//                Button(
//                    onClick = {},
//                    modifier = Modifier.size(width = 160.dp, height = 50.dp).padding(end= 20.dp),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 30, green = 100, blue = 50))
//                ) {
//                    Text(
//                        text = "ORDER",
//                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
//                    )
//                }
//            }
//        }
//        //SPACE NAVIGATION
//        item{Spacer(modifier= Modifier.height(100.dp))}
//    }}
//}
//
//
@Composable
fun CartItem(
    title: String,
    description: String,
    price: Double, // Prix unitaire
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit // Fonction appelée lors de la suppression
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        // Titre de l'article
        Text(
            text = "$description - $title",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Prix sous le titre
        Text(
            text = "${price}€", // Prix fixe
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Ligne avec les boutons de quantité et suppression
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End // Aligne à la fin de la ligne
        ) {
            // Boutons de quantité
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onQuantityChange(quantity - 1) },
                    enabled = quantity > 0,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    modifier = Modifier.size(38.dp) // Augmentez la taille du bouton
                ) {
                    Text("-", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)) // Plus grand texte
                }
                Text(
                    text = quantity.toString(),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Button(
                    onClick = { onQuantityChange(quantity + 1) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    modifier = Modifier.size(38.dp)
                ) {
                    Text("+", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Bouton poubelle à côté des boutons de quantité
            Image(
                painter = painterResource(Res.drawable.trash), // Remplacez par l'image de poubelle
                contentDescription = "Remove Item",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { showDialog = true }
            )
        }
    }

    // Affichage de la boîte de dialogue de confirmation
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Confirmation",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this article?",
                    style = TextStyle(fontSize = 18.sp) // Agrandir le texte
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly // Centre les boutons
                ) {

                    Button(
                        onClick = {
                            onRemove() // Supprime l'élément
                            showDialog = false // Ferme la boîte de dialogue
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        modifier = Modifier
                            .size(width = 120.dp, height = 50.dp) // Largeur et hauteur du bouton
                    ) {
                        Text("Yes", color= Color.Black,style = TextStyle(fontSize = 18.sp)) // Texte plus grand
                    }
                    Button(
                        onClick = {
                            showDialog = false // Annule la suppression
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(red = 62, green = 96, blue = 160)),
                        modifier = Modifier
                            .size(width = 120.dp, height = 50.dp) // Largeur et hauteur du bouton
                    ) {
                        Text("No",  color= Color.White,style = TextStyle(fontSize = 18.sp)) // Texte plus grand
                    }

                }
            }
        )
    }
}

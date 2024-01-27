package pl.edu.uwr.pum.lista6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    navController: NavHostController,
    user: User?
) {

    if (user != null) {
        CustomScaffold(
            title = "${user.username}",
            nav = {
                IconButton(onClick = { navController.navigate("usersList") }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Localized description",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(50.dp)
                    )
                }
            },
            content = {

                ElevatedCard(
                    modifier = Modifier.padding(20.dp),
                    onClick = {  },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    )

                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                    ){


                        Box (
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){


                            val painter = rememberImagePainter(data = user.avatar)
                            Image(
                                painter = painter,
                                contentDescription = "Avatar for ${user.username}",
                                modifier = Modifier
                                    .size(300.dp)
                                    .clip(shape = CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                            )

                            if (painter.state is ImagePainter.State.Loading) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    modifier = Modifier
                                        .size(120.dp)
                                        .background(MaterialTheme.colorScheme.primary)
                                )
                            }
                        }

                        Text(
                            text = user.firstName + " " + user.lastName,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(10.dp)
                        )

                        Text(
                            text = user.dateOfBirth,
                            fontSize = 26.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(5.dp)
                        )

                        Text(
                            text = "gender: " + user.gender,
                            fontSize = 26.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(5.dp)
                        )

                        Text(
                            text = "Country: " + user.address.country +
                                    "\nState: " + user.address.state +
                                    "\nCity: " + user.address.city +
                                    "\nStreet Name: " + user.address.streetName,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(10.dp)
                        )

                    }
                }

            })
    }
}



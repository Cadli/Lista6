package pl.edu.uwr.pum.lista6

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersListScreen(
    navController: NavHostController,
    response: Resource<List<User>>
    ) {

    CustomScaffold(
        title = "Lista użytkowników",
        nav = {},
        content = {
            when (response) {
                is Resource.Success -> { response.data?.let {
                    ShowList(users = it, navController = navController)
                } }

                is Resource.Error -> {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(150.dp)
                    )
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }
    )
}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
private fun ShowList(
    users: List<User>,
    navController: NavHostController,
) {
    LazyColumn {
        items(users) { user ->

            ElevatedCard(
                modifier = Modifier.padding(20.dp),
                onClick = { navController.navigate("userDetails/${user.id}") },
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
                        text = user.username,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = user.email,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }
        }

    }
}
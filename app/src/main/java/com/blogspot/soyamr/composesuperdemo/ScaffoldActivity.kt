package com.blogspot.soyamr.composesuperdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.blogspot.soyamr.composesuperdemo.ui.theme.ComposeSuperDemoTheme
import kotlinx.coroutines.launch

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                LayoutsCodelab()
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Dangerous, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.BrokenImage, contentDescription = null)
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.HideImage, contentDescription = null)
                }
            }
        }

    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ImageList()
    }
}

@Composable
fun ImageList() {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (toTheTop, toTheBottom, list) = createRefs()

        Button(
            onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            },
            modifier = Modifier.constrainAs(toTheTop) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
                end.linkTo(toTheBottom.start)
                width = Dimension.preferredWrapContent
            }) {
            Text("Scroll to the topppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            },
            modifier = Modifier.constrainAs(toTheBottom) {
                start.linkTo(toTheTop.end, 16.dp)
                top.linkTo(parent.top, 16.dp)
                end.linkTo(parent.end, 16.dp)
                width = Dimension.preferredWrapContent
            }) {
            Text("Scroll to the end")
        }
        val barrier = createBottomBarrier(toTheBottom, toTheTop)
        LazyColumn(
            state = scrollState,
            modifier = Modifier.constrainAs(list) {
                top.linkTo(barrier)
                centerHorizontallyTo(parent)
            }
        ) {
            items(100) {
                ImageListItem(it)
            }
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(IntrinsicSize.Min)) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Divider(color = Color.Red, modifier = Modifier.fillMaxHeight().width(1.dp).padding(bottom = 5.dp))
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    ComposeSuperDemoTheme() {
        LayoutsCodelab()
    }
}

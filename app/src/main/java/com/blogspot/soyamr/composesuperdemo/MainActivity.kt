package com.blogspot.soyamr.composesuperdemo

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blogspot.soyamr.composesuperdemo.ui.theme.ComposeSuperDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Conversation(
                    messages = sampleData
                )
            }
        }
        startActivity(Intent(this, ScaffoldActivity::class.java))
    }

}

val sampleData = listOf(
    Message("he", "texttexttexttexttexttexttexttext"),
    Message("she", "text text text text text text text "),
    Message("it", "texxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxt"),
    Message("we", "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"),
    Message(
        "are",
        "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext"
    ),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
    Message("you", "text"),
)

data class Message(val author: String, val message: String)

// What if you want to create a container that has all the common configurations of your app?
@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeSuperDemoTheme {
        content()
    }
}


@Composable
fun Conversation(messages: List<Message>) {
    var count by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                MessageCard(msg = message, count) { newCount ->
                    count = newCount
                }//state hoisting: State hoisting is the way to make internal state controllable by the function that called it.
                Divider(color = Color.White)
            }
        }
        Divider(color = Color.Black)
        Counter(count)
    }
}

@Composable
fun MessageCard(msg: Message, count: Int, updateCount: (Int) -> Unit) {
    // We keep track if the message is expanded or not in this variable
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceColo: Color by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )

    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = {})
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.amira),
            contentDescription = "contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColo,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                // We toggle the isExpanded variable when we click on this text
                Text(
                    text = msg.message,
                    modifier = Modifier
                        .clickable {
                            isExpanded = !isExpanded
                            updateCount(count + 1)
                        }
                        .padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun Counter(count: Int) {
    Text(text = "$count times", color = if (count % 2 == 0) Color.Green else Color.White)
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewConversation() {
    ComposeSuperDemoTheme(darkTheme = false) {
        MyApp {
            Conversation(
                messages = sampleData
            )
        }
    }
}
/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */
@file:OptIn(ExperimentalHorologistApi::class, ExperimentalWearFoundationApi::class)

package com.example.gritters_cis357_drinking_wearos_final_project.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.foundation.rotary.RotaryScrollableDefaults.behavior
import androidx.wear.compose.foundation.rotary.rotaryScrollable
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TitleCard
import androidx.wear.compose.material.dialog.Dialog
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.gritters_cis357_drinking_wearos_final_project.R
import com.example.gritters_cis357_drinking_wearos_final_project.presentation.theme.Gritterscis357drinkingwearosfinalprojectTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.material.Text
import androidx.wear.compose.material3.AppScaffold
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.compose.material.AlertContent
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.Chip
import com.google.android.horologist.compose.material.ListHeaderDefaults.firstItemPadding
import com.google.android.horologist.compose.material.ResponsiveListHeader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp("Android")
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    val navController = rememberSwipeDismissableNavController()

    Gritterscis357drinkingwearosfinalprojectTheme {
        AppScaffold {
            SwipeDismissableNavHost(navController = navController, startDestination = "menu") {
                composable("menu") {
                    TimeText()
                    GreetingScreen(
                        "Android",
                        onShowList = { navController.navigate("list") }
                    )
                }
                composable("list") {
                    ListScreen()
                }
            }
        }
    }
}


@Composable
fun GreetingScreen(greetingName: String, onShowList: () -> Unit) {
    val scrollState = rememberScrollState()

    /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
     * version of LazyColumn for wear devices with some added features. For more information,
     * see d.android.com/wear/compose.
     */
    ScreenScaffold(scrollState = scrollState) {
        val padding = ScalingLazyColumnDefaults.padding(
            first = ItemType.Text,
            last = ItemType.Chip
        )()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .rotaryScrollable(
                    behavior(scrollableState = scrollState),
                    focusRequester = rememberActiveFocusRequester()
                )
                .padding(padding),
            verticalArrangement = Arrangement.Center
        ) {
            Greeting(greetingName = greetingName)
            Chip(label = "Show List", onClick = onShowList)
        }
    }
}

@Composable
fun ListScreen() {
    var showDialog by remember { mutableStateOf(false) }

    /*
     * Specifying the types of items that appear at the start and end of the list ensures that the
     * appropriate padding is used.
     */
    val columnState = rememberResponsiveColumnState(
        contentPadding = ScalingLazyColumnDefaults.padding(
            first = ItemType.Text,
            last = ItemType.SingleButton
        )
    )

    ScreenScaffold(scrollState = columnState) {
        /*
         * The Horologist [ScalingLazyColumn] takes care of the horizontal and vertical
         * padding for the list, so there is no need to specify it, as in the [GreetingScreen]
         * composable.
         */
        ScalingLazyColumn(
            columnState = columnState
        ) {
            item {
                ResponsiveListHeader(contentPadding = firstItemPadding()) {
                    Text(text = "Header")
                }
            }
            item {
                TitleCard(title = { Text("Example Title") }, onClick = { }) {
                    Text("Example Content\nMore Lines\nAnd More")
                }
            }
            item {
                Chip(label = "Example Chip", onClick = { })
            }
            item {
                Button(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Example Button",
                    onClick = { showDialog = true }
                )
            }
        }
    }

    SampleDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onCancel = {},
        onOk = {}
    )
}

@Composable
fun Greeting(greetingName: String) {
    ResponsiveListHeader(contentPadding = firstItemPadding()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = stringResource(R.string.hello_world, greetingName)
        )
    }
}

@Composable
fun SampleDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onOk: () -> Unit
) {
    val state = rememberResponsiveColumnState()

    Dialog(
        showDialog = showDialog,
        onDismissRequest = onDismiss,
        scrollState = state.state
    ) {
        SampleDialogContent(onCancel, onDismiss, onOk)
    }
}

@Composable
fun SampleDialogContent(
    onCancel: () -> Unit,
    onDismiss: () -> Unit,
    onOk: () -> Unit
) {
    AlertContent(
        icon = {},
        title = "Title",
        onCancel = {
            onCancel()
            onDismiss()
        },
        onOk = {
            onOk()
            onDismiss()
        }
    ) {
        item {
            Text(text = "An unknown error occurred during the request.")
        }
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun GreetingScreenPreview() {
    GreetingScreen("Preview Android", onShowList = {})
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun ListScreenPreview() {
    ListScreen()
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}
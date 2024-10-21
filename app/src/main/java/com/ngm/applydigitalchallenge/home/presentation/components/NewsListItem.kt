package com.ngm.applydigitalchallenge.home.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ngm.applydigitalchallenge.home.domain.model.News
import com.ngm.applydigitalchallenge.home.presentation.navigation.NavigationEvent
import java.time.Duration
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsListItem(
    newsItem: News,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    ListItem(
        modifier = Modifier.clickable {
            newsItem.storyUrl?.let { url ->
                onNavigationEvent(
                    NavigationEvent.NewsClick(
                        url
                    )
                )
            }
        },
        headlineContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(newsItem.title)
            }
        },
        supportingContent = {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = newsItem.author + newsItem.createdAt.toTimeElapsed()
            )
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
private fun String?.toTimeElapsed(): String {
    if (this == null) return ""
    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

    val now = ZonedDateTime.now()
    val givenTime: ZonedDateTime = ZonedDateTime.parse(this, formatter)
    val duration = Duration.between(givenTime, now)

    val minutes = abs(duration.toMinutes())
    val hours = abs(duration.toHours())
    val days = abs(duration.toDays())
    val prefix = " * "
    return prefix + when {
        minutes < 60 -> "${minutes}m"
        hours < 24 -> "${(minutes / 60.0).toString().take(3)}h"
        else -> "${days}d"
    }
}

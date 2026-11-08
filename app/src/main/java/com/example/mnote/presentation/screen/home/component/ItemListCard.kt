package com.example.mnote.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mnote.data.local.NoteEntity
import java.text.SimpleDateFormat
import java.util.Date

private val AccentColor = Color(0xFF6C63FF)
private val SecondaryText = Color(0xFF858B98)

@Composable
fun ItemListCard(
    note: NoteEntity,
    onClick: () -> Unit) {

    val locale = LocalLocale.current.platformLocale

    val date = SimpleDateFormat(
        "dd MMM yyyy",
        locale
    ).format(Date(note.createdAt))

    val time = SimpleDateFormat(
        "hh:mm a",
        locale
    ).format(Date(note.createdAt))

    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Note Title
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF20232A),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Note Description
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryText,
                lineHeight = 21.sp,
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFF0F0F4))
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Date and Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = null,
                        tint = AccentColor,
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = date,
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccessTime,
                        contentDescription = null,
                        tint = AccentColor,
                        modifier = Modifier.size(15.dp)
                    )

                    Text(
                        text = time,
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                }
            }
        }
    }
}

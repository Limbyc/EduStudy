package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valance.petproject.R
import com.valance.petproject.data.model.ItemData
import com.valance.petproject.data.model.LessonsData

@Composable
fun CardSubject(
    modifier: Modifier = Modifier,
    item: ItemData,
) {
    Box(
        modifier = modifier
            .width(149.dp)
            .height(119.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = item.cardColor)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = modifier
                        .padding(
                            top = 16.dp,
                            start = 16.dp
                        ),
                    painter = painterResource(id = item.imageId),
                    contentDescription = null,
                )

                Spacer(modifier = modifier.weight(1f))

                Image(
                    modifier = modifier
                        .padding(
                        top = 16.dp,
                        end = 16.dp
                    ),
                    painter = painterResource(id = R.drawable.ellipsis),
                    contentDescription = null,
                )
            }
            Spacer(modifier = modifier.weight(1f))

            Text(
                modifier = modifier
                    .padding(
                    start = 16.dp,
                    bottom = 16.dp
                ),
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun CardSubjectPreview() {
    CardSubject(item = ItemData(R.drawable.earth, "Hello",  Color.Gray ))
}


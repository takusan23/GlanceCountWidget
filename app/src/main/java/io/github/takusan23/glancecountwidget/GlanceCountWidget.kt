package io.github.takusan23.glancecountwidget

import android.content.Context
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.material3.ColorProviders
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import io.github.takusan23.glancecountwidget.ui.theme.DarkColorScheme
import io.github.takusan23.glancecountwidget.ui.theme.LightColorScheme

class GlanceCountWidget : GlanceAppWidget() {

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) = provideContent {

        // カウンター
        // TODO 再起動とかで値をロストするので、永続化が必要
        var counter by remember { mutableStateOf(0) }

        // テーマの設定
        GlanceTheme(colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) GlanceTheme.colors else colors) {

            // 横並び
            Row(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .background(GlanceTheme.colors.secondaryContainer)
                    .cornerRadius(16.dp),
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
                verticalAlignment = Alignment.Vertical.CenterVertically
            ) {

                Button(
                    modifier = GlanceModifier.size(50.dp),
                    text = "-1",
                    onClick = { counter-- }
                )

                Text(
                    modifier = GlanceModifier.defaultWeight(),
                    text = counter.toString(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Button(
                    modifier = GlanceModifier.size(50.dp),
                    text = "+1",
                    onClick = { counter++ }
                )

            }
        }
    }

    companion object {

        /** ウィジェットの色 */
        val colors = ColorProviders(
            light = LightColorScheme,
            dark = DarkColorScheme
        )

    }
}
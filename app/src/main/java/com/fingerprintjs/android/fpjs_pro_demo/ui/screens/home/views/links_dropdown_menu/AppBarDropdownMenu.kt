package com.fingerprintjs.android.fpjs_pro_demo.ui.screens.home.views.links_dropdown_menu

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fingerprintjs.android.fpjs_pro_demo.utils.StateMocks
import com.fingerprintjs.android.fpjs_pro_demo.ui.theme.AppTheme

data class AppBarDropdownMenuItem(
    @DrawableRes
    val icon: Int,
    val description: String,
    val onClick: () -> Unit,
)

@Composable
fun AppBarDropdownMenu(
    expanded: Boolean,
    items: List<AppBarDropdownMenuItem>,
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
    ) {
        items.forEachIndexed { index, item ->
            DropdownMenuItem(
                text = {
                    Text(
                        modifier = Modifier.padding(end = 12.dp),
                        text = item.description,
                        style = AppTheme.materialTheme.typography.bodyLarge,
                    )
                },
                onClick = item.onClick,
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(item.icon),
                        contentDescription = "Icon for ${item.description}",
                        colorFilter = ColorFilter.tint(color = AppTheme.materialTheme.colorScheme.onSurfaceVariant)
                    )
                },
            )
            if (index != items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    var expanded by remember { mutableStateOf(true) }
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.materialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = { expanded = !expanded }) {
                Text("Toggle dropdown")
            }
            Box {
                AppBarDropdownMenu(
                    expanded = expanded,
                    items = StateMocks.appBarDropdownMenuItems,
                    onDismiss = { expanded = false },
                )
            }
        }
    }
}

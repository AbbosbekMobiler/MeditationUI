package abbosbek.mobiler.meditationui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title : String,
    @DrawableRes val iconId : Int,
    val color : Color
)

package com.justinale3362.chikanomaze.data

import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.acceleration
import chikanomaze.composeapp.generated.resources.chicken2_front
import chikanomaze.composeapp.generated.resources.chicken3_front
import chikanomaze.composeapp.generated.resources.chicken4_front
import org.jetbrains.compose.resources.DrawableResource

data class ProgressItem(
    val starsToUnlock: Int,
    val description: String,
    val image: DrawableResource
)

val progressItems = listOf(
    ProgressItem(3, "New Skin", Res.drawable.chicken2_front),
    ProgressItem(7, "+1 Acceleration", Res.drawable.acceleration),
    ProgressItem(12, "New Skin", Res.drawable.chicken3_front),
    ProgressItem(15, "+1 Acceleration", Res.drawable.acceleration),
    ProgressItem(18, "New Skin", Res.drawable.chicken4_front),
)

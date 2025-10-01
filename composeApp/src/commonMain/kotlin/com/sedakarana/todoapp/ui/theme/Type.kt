package com.sedakarana.todoapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.bold
import todoapp.composeapp.generated.resources.medium
import todoapp.composeapp.generated.resources.regular

@Composable
fun getFontRegular() = FontFamily(Font(Res.font.regular))

@Composable
fun getFontMedium() = FontFamily(Font(Res.font.medium))

@Composable
fun getFontBold() = FontFamily(Font(Res.font.bold))
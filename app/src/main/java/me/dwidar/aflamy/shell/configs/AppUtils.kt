package me.dwidar.aflamy.shell.configs

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

const val spacerHeight = 0.03f

val textFieldRoundedCorner = 24.dp

val cardRoundedCorner = 10.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun screenWidth() : Int = LocalConfiguration.current.screenWidthDp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun screenHeight() : Int = LocalConfiguration.current.screenHeightDp

@Composable
fun getWidthUnit() : Int = screenWidth() / 100

@Composable
fun getHeightUnit() : Int = screenHeight() / 100

@Composable
fun getGeneralHorizontalPadding() : Dp = (screenWidth() * 0.04).dp

@Composable
fun getGeneralVerticalPadding() : Dp = (getHeightUnit() * 2).dp

@Composable
fun getDefaultIconSize() : Dp = (getWidthUnit() * getHeightUnit() * 0.8).dp
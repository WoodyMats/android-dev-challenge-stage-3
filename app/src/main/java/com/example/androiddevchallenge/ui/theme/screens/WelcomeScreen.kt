/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.DEST_LOGIN_SCREEN
import com.example.androiddevchallenge.DEST_SIGNUP_SCREEN
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun WelcomeScreen(navController: NavController?) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        WelcomeScreenContent(
            clickAction = {
                when (it) {
                    0 -> navController?.navigate(DEST_SIGNUP_SCREEN)
                    else -> navController?.navigate(DEST_LOGIN_SCREEN)
                }
            }
        )
    }
}

@Composable
fun WelcomeScreenContent(clickAction: (Int) -> Unit?) {
    val isLightTheme = MaterialTheme.colors.isLight
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = if (isLightTheme) R.drawable.ic_light_welcome else R.drawable.ic_dark_welcome),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = if (isLightTheme) R.drawable.ic_light_logo else R.drawable.ic_dark_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.padding(top = 32.dp))
                Button(
                    onClick = { clickAction(0) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth(),
                    shape = shapes.medium
                ) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        style = typography.button,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Button(
                    onClick = { clickAction(1) },
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSecondary
                    ),
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth(),
                    shape = shapes.medium
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        style = typography.button,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        val navController = rememberNavController()
        WelcomeScreen(navController = navController)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        val navController = rememberNavController()
        WelcomeScreen(navController = navController)
    }
}

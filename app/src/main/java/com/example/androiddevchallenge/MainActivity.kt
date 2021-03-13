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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.screens.HomeScreen
import com.example.androiddevchallenge.ui.theme.screens.LoginScreen
import com.example.androiddevchallenge.ui.theme.screens.WelcomeScreen

const val DEST_WELCOME_SCREEN = "welcome_screen"
const val DEST_LOGIN_SCREEN = "login_screen"
const val DEST_SIGNUP_SCREEN = "signup_screen"
const val DEST_HOME_SCREEN = "home_screen"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DEST_WELCOME_SCREEN) {
        composable(DEST_WELCOME_SCREEN) {
            Crossfade(targetState = DEST_WELCOME_SCREEN) {
                WelcomeScreen(navController = navController)
            }
        }
        composable(DEST_LOGIN_SCREEN) {
            Crossfade(targetState = DEST_LOGIN_SCREEN) {
                LoginScreen(navController = navController)
            }
        }
        composable(DEST_HOME_SCREEN) {
            Crossfade(targetState = DEST_HOME_SCREEN) {
                HomeScreen()
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

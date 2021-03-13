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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.DEST_HOME_SCREEN
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun LoginScreen(navController: NavController?) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LoginScreenContent(
            clickAction = {
                navController?.navigate(DEST_HOME_SCREEN)
            }
        )
    }
}

@Composable
fun LoginScreenContent(clickAction: () -> Unit?) {
    val isLightTheme = MaterialTheme.colors.isLight
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = if (isLightTheme) R.drawable.ic_light_login else R.drawable.ic_dark_login),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .paddingFromBaseline(top = 200.dp),
                    color = MaterialTheme.colors.onBackground
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            stringResource(R.string.email_address),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .paddingFromBaseline(top = 32.dp)
                        .height(56.dp)
                        .background(color = MaterialTheme.colors.onPrimary, shape = shapes.small),
                    textStyle = typography.body1,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            stringResource(R.string.password),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(color = MaterialTheme.colors.onPrimary, shape = shapes.small)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { clickAction() },
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    shape = shapes.medium
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        style = typography.button,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                Text(
                    text = with(AnnotatedString.Builder(stringResource(R.string.have_not_account) + " ")) {
                        pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                        append(stringResource(R.string.sign_up_lower))
                        addStyle(
                            SpanStyle(color = MaterialTheme.colors.onBackground),
                            "Hello World".length,
                            this.length
                        )
                        toAnnotatedString()
                    },
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.paddingFromBaseline(top = 32.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

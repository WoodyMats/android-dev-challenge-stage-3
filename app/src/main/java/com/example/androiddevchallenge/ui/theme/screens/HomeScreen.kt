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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        BottomNavBar()
    }
}

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.padding(top = 40.dp))
        SearchBar()
        FavoriteCollection()
        AlignRecycler(
            list = fillAlignYourBodyData(),
            title = stringResource(R.string.align_your_body)
        )
        AlignRecycler(
            list = fillAlignYourMindData(),
            title = stringResource(R.string.align_your_mind)
        )
    }
}

@Composable
fun FavoriteCollection() {
    val list = fillListOfFavorites()
    Text(
        text = stringResource(R.string.favorite_collections),
        style = typography.h2,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp)
            .padding(bottom = 8.dp)
    )
    Spacer(Modifier.padding(top = 8.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(list.chunked(2)) { itemColumn ->
            Column {
                itemColumn.forEachIndexed { index, item ->
                    if (index > 0) {
                        Spacer(Modifier.padding(top = 8.dp))
                    }
                    FavoriteCard(item)
                }
            }
        }
    }
}

@Composable
fun AlignRecycler(list: List<DummyItem>, title: String) {
    Text(
        text = title,
        style = typography.h2,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp)
            .padding(bottom = 8.dp)
    )
    Spacer(Modifier.padding(top = 8.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(list) { item ->
            RecyclerItem(item)
        }
    }
}

@Composable
fun RecyclerItem(item: DummyItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = item.title,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = item.title,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun FavoriteCard(item: DummyItem) {
    Surface(
        modifier = Modifier
            .width(192.dp)
            .height(56.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = item.imageId), item.title)
            Text(
                text = item.title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.h3
            )
        }
    }
}

fun fillListOfFavorites(): List<DummyItem> {
    return arrayOf(
        DummyItem("Short mantras", R.drawable.short_mantras),
        DummyItem("Nature meditations", R.drawable.nature_meditations),
        DummyItem("Stress and anxiety", R.drawable.stress_and_anxiety),
        DummyItem("Self-massage", R.drawable.self_massage),
        DummyItem("Overwhelmed", R.drawable.overwhelmed),
        DummyItem("Nightly wind down", R.drawable.nightly_wind_down)
    ).toList()
}

fun fillAlignYourBodyData(): List<DummyItem> {
    return arrayOf(
        DummyItem("Inversions", R.drawable.inversions),
        DummyItem("Quick yoga", R.drawable.quick_yoga),
        DummyItem("Stretching", R.drawable.stretching),
        DummyItem("Tabata", R.drawable.tabata),
        DummyItem("HIIT", R.drawable.hiit),
        DummyItem("Pre-natal yoga", R.drawable.pre_natal_yoga)
    ).toList()
}

fun fillAlignYourMindData(): List<DummyItem> {
    return arrayOf(
        DummyItem("Mediate", R.drawable.meditate),
        DummyItem("With kids", R.drawable.with_kids),
        DummyItem("Aromatherapy", R.drawable.aromatherapy),
        DummyItem("On the go", R.drawable.on_the_go),
        DummyItem("With pets", R.drawable.with_pets),
        DummyItem("High stress", R.drawable.high_stress)
    ).toList()
}

data class DummyItem(val title: String, val imageId: Int)

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
    TextField(
        value = searchText,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.onPrimary, shape = shapes.small),
        textStyle = typography.body1,
        onValueChange = { searchText = it },
        placeholder = {
            Text(
                stringResource(R.string.search), style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
        },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.search),
                contentDescription = stringResource(R.string.search),
                modifier = Modifier.size(18.dp)
            )
        }
    )
}

@Composable
fun BottomNavBar() {
    var selectedTab by remember { mutableStateOf(MenuItems.HOME.title) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .zIndex(10f)
                    .offset(y = (-2).dp),
                backgroundColor = MaterialTheme.colors.onSurface
            ) {
                Icon(
                    painterResource(id = R.drawable.play_arrow),
                    contentDescription = "play",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            Surface(
                elevation = 8.dp,
                color = MaterialTheme.colors.background
            ) {
                Column {
                    BottomNavigation(
                        elevation = 0.dp,
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        MenuItems.values().forEach { item ->
                            val selected = selectedTab == item.title
                            BottomNavigationItem(
                                selected = selected,
                                onClick = { selectedTab = item.title },
                                label = {
                                    Text(
                                        item.title,
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                },
                                icon = {
                                    Icon(
                                        painterResource(id = item.image),
                                        item.title,
                                        Modifier.size(18.dp),
                                        tint = MaterialTheme.colors.onSurface
                                    )
                                }
                            )
                        }
                    }
                    Spacer(Modifier.padding(8.dp))
                }
            }
        },
        content = {
            HomeScreenContent()
        }
    )
}

enum class MenuItems(val title: String, val image: Int) {
    HOME("HOME", R.drawable.spa),
    PROFILE("PROFILE", R.drawable.account_circle)
}

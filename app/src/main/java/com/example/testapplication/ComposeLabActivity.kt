package com.example.testapplication

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapplication.ui.theme.TestApplicationTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

class ComposeLabActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            TestApplicationTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                MySootheApp(windowSizeClass)
            }
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {

    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

}

val alignYourBodyData = listOf(
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions)
)

val favoriteCollectionsData = listOf(
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions),
    AlignYourBodyData(R.drawable.pikapika, R.string.ab1_inversions)
)

data class AlignYourBodyData (
    val drawable: Int,
    val text: Int
)

@Composable
fun AlignYourBodyRow(modifier: Modifier) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text, modifier = Modifier)

        }
    }
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData.size) { item ->
            FavoriteCollectionCard(
            modifier = Modifier.height(80.dp),
            text = R.string.ab1_inversions,
            drawable = R.drawable.pikapika
        )
//            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(stringResource(title),
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp))
        content()
    }

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.title1) {
            AlignYourBodyRow(modifier = Modifier)
        }
        HomeSection(title = R.string.title2) {
            FavoriteCollectionsGrid(modifier = modifier)
        }
        Spacer(Modifier.height(48.dp))
        HomeSection(title = R.string.title3) {
            AlignYourBodyRow(modifier = Modifier)
        }
        HomeSection(title = R.string.title4) {
            FavoriteCollectionsGrid(modifier = modifier)
        }
        Spacer(Modifier.height(48.dp))
        HomeSection(title = R.string.title2) {
            AlignYourBodyRow(modifier = Modifier)
        }
        HomeSection(title = R.string.title3) {
            FavoriteCollectionsGrid(modifier = modifier)
        }
        Spacer(Modifier.height(16.dp))

    }

}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Spa, contentDescription = null) },
            label = {
                Text(stringResource(R.string.title1))
            },
            selected = true,
            onClick = {}
        )

        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
            label = {
                Text(stringResource(R.string.title2))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.title1))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.title2))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun MySootheAppPortrait() {
    TestApplicationTheme() {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun MySootheAppLandscape() {
    TestApplicationTheme {
        Row {
            SootheNavigationRail()
            HomeScreen()
        }
    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }

        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    TestApplicationTheme {
//        AlignYourBodyElement(
//            text = R.string.ab1_inversions,
//            drawable = R.drawable.pikapika,
//            modifier = Modifier.padding(8.dp)
//        )

//        FavoriteCollectionCard(
//            modifier = Modifier.padding(6.dp),
//            text = R.string.ab1_inversions,
//            drawable = R.drawable.pikapika
//        )

//        HomeSection(R.string.align_your_body) {
//            AlignYourBodyRow(modifier = Modifier)
//        }

        HomeScreen()
    }
}
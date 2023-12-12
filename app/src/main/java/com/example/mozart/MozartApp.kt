//package com.example.mozart
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import com.example.mozart.ui.components.HomeSection
//import com.example.mozart.ui.components.Banner
//import com.example.mozart.ui.screen.MenuRow
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun MozartApp(modifier: Modifier = Modifier) {
//    Column {
//        Scaffold{
//
//            Column(
//                modifier = modifier
//                    .verticalScroll(rememberScrollState())
//            ){
//                Banner()
//                HomeSection(title = stringResource(R.string.section_favorite_place), Modifier) {
//                    MenuRow(dummyPlace)
//                }
//                HomeSection(title = stringResource(R.string.section_utara), Modifier) {
//                    MenuRow(dummyPlace)
//                }
//                HomeSection(title = stringResource(R.string.section_barat), Modifier) {
//                    MenuRow(dummyPlace)
//                }
//                HomeSection(title = stringResource(R.string.section_selatan), Modifier) {
//                    MenuRow(dummyPlace)
//                }
//                HomeSection(title = stringResource(R.string.section_timur), Modifier) {
//                    MenuRow(dummyPlace)
//                }
//            }
//        }
//
//    }
//}
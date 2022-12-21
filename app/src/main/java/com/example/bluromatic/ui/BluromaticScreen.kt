/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluromatic.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluromatic.R
import com.example.bluromatic.data.BlurAmount
import com.example.bluromatic.ui.theme.BluromaticTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BluromaticScreen(blurViewModel: BlurViewModel = viewModel(factory = BlurViewModel.Factory)) {
    val uiState by blurViewModel.blurUiState.collectAsStateWithLifecycle()
    BluromaticTheme {
        BluromaticScreenContent(
            blurUiState = uiState,
            blurAmountOptions = blurViewModel.blurAmount,
            applyBlur = blurViewModel::applyBlur,
            cancelWork = {}
        )
    }
}

@Composable
fun BluromaticScreenContent(
    blurUiState: BlurUiState,
    blurAmountOptions: List<BlurAmount>,
    applyBlur: (Int) -> Unit,
    cancelWork: () -> Unit) {
    var selectedValue by rememberSaveable { mutableStateOf(1) }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(R.drawable.android_cupcake),
            contentDescription = stringResource(R.string.description_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Fit,
        )
        BlurAmountContent(
            selectedValue = selectedValue,
            blurAmounts = blurAmountOptions,
            onSelectedValueChange = { selectedValue = it }
        )
        BlurActions(
            blurUiState = blurUiState,
            onGoClick = { applyBlur(selectedValue) },
            onSeeFileClick = {},
            onCancelClick = { cancelWork() }
        )
    }
}

@Composable
private fun BlurActions(
    blurUiState: BlurUiState,
    onGoClick: () -> Unit,
    onSeeFileClick: (String) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onGoClick) { Text(stringResource(R.string.go)) }
    }
}

@Composable
private fun BlurAmountContent(
    selectedValue: Int,
    blurAmounts: List<BlurAmount>,
    modifier: Modifier = Modifier,
    onSelectedValueChange: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup(),
    ) {
        Text(
            text = stringResource(R.string.blur_title),
            style = MaterialTheme.typography.h5
        )
        blurAmounts.forEach { amount ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        role = Role.RadioButton,
                        selected = (selectedValue == amount.blurAmount),
                        onClick = { onSelectedValueChange(amount.blurAmount) }
                    )
                    .size(48.dp)
            ) {
                RadioButton(
                    selected = (selectedValue == amount.blurAmount),
                    onClick = null,
                    modifier = Modifier.size(48.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colors.primary
                    )
                )
                Text(stringResource(amount.blurAmountRes))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BluromaticScreenContentPreview() {
    BluromaticTheme {
        BluromaticScreenContent(
            blurUiState = BlurUiState.Default,
            blurAmountOptions = listOf(BlurAmount(R.string.blur_lv_1, 1)),
            {},
            {}
        )
    }
}

private fun showBlurredImage(context: Context, currentUri: String) {
    val uri = if (currentUri.isNotEmpty()) {
        Uri.parse(currentUri)
    } else {
        null
    }
    val actionView = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(actionView)
}

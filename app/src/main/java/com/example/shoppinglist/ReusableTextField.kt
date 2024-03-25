package com.example.shoppinglist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableTextField() {
    val textState = remember {mutableStateOf("")}
    val iconState = remember {mutableStateOf(Icons.Default.ArrowBack)}
    val colorState = remember {mutableStateOf(Color.White)}
    val validText = remember {mutableStateOf("")}
    val focusRequester = remember {FocusRequester()}

    Column(modifier = Modifier.padding(12.dp)) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                                focusState ->
                                if (!focusState.isFocused){
                                    iconState.value=Icons.Default.Check
                                    colorState.value = Color.White
                                    validText.value=""
                                }
                },
            value = textState.value,
            onValueChange = {
                textState.value=it

                if (ListFinder.codeInList(it)){
                    iconState.value=Icons.Default.Check
                    colorState.value = Color.Green
                    validText.value="referral code is correct"
                }
                else{
                    iconState.value=Icons.Default.Warning
                    colorState.value = Color.Red
                    validText.value=if (textState.value.length < 8) "Minimum 8 Digits required" else "referral code is not correct"
                }

                            },
            //label = "Referral Code",
            singleLine = true,
            trailingIcon = { Icon(imageVector = iconState.value, contentDescription = "", tint = colorState.value)},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Gray,
            )

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = validText.value, color = colorState.value, fontWeight = FontWeight.Bold)
    }


}
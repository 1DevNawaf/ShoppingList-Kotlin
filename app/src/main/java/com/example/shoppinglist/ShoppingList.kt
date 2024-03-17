package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun ShoppingListApp() {

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember {mutableStateOf(false) }
    var itemName by remember {mutableStateOf("")}
    var itemQuantity by remember { mutableIntStateOf(0) }


    Column (
        Modifier.fillMaxSize(),
        Arrangement.Center
    ){
        Button(
            onClick = {showDialog=true},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),

        ) {
            Text(text = "Add Item")
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            items(sItems){

            }
        }

    }

    //this will popups the dialog to add items
    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog=false },
            confirmButton = { /*TODO*/ },
            title = { Text(text = "Add Shopping Item")},
            text = {
                Column {
                    OutlinedTextField(
                        label = { Text(text = "Item Name")},
                        value = itemName,
                        onValueChange = {itemName=it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        )
                    OutlinedTextField(
                        label = { Text(text = "Item Quantity")},
                        value = itemName,
                        onValueChange = {itemQuantity=it.toInt()},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                }
            }
        )
    }


}


data class ShoppingItem(
    val id: Int,
    var name : String,
    var quantity : Int,
    var isEditing : Boolean = false
)
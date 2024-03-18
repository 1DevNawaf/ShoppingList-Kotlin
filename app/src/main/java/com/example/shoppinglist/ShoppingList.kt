package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun ShoppingListApp() {

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember {mutableStateOf(false) }
    var itemName by remember {mutableStateOf("")}
    var itemQuantity by remember { mutableIntStateOf(1) }
    println("hello")
    Column (

        Modifier.fillMaxSize(),
        Arrangement.Center
    ){
        Button(
            onClick = {showDialog=true},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),

        ) {
            Text(text = "Add Item")
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            items(sItems){
                ShoppingListItem(it,{},{})
            }
        }

    }

    //this will popups the dialog to add items
    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog=false },
            confirmButton = {
                          Row (
                              modifier = Modifier.fillMaxWidth(),
                              horizontalArrangement = Arrangement.SpaceBetween,
                              ){
                              Button(
                                  onClick = {
                                      if (itemName.isNotBlank() && itemQuantity >= 1){
                                          val newItem = ShoppingItem(
                                              sItems.size+1,
                                              itemName,
                                              itemQuantity
                                          )
                                          sItems = sItems+newItem
                                          //resetting the values
                                          itemName=""
                                          itemQuantity=1
                                          showDialog = false
                                          println("Item has been added \n$itemName with quantity of $itemQuantity")
                                      }
                                  }
                              ) {
                                  Text(text = "Add")
                              }
                              Button(
                                  onClick = {showDialog = false}
                              ) {
                                  Text(text = "Cancel")
                              }
                          }
            },
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
                        value = itemQuantity.toString(),
                        onValueChange = {itemQuantity=it.toInt()},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick:() -> Unit,
    onDeleteClick:() -> Unit,
) {
   Row (
       modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp)
           .border(
               border = BorderStroke(2.dp, Color.Blue),
               shape = RoundedCornerShape(20)
           ),
       horizontalArrangement = Arrangement.SpaceBetween

   ){
       Row {
           Text(text = item.name, modifier = Modifier.padding(8.dp))
           Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
       }

       Row (modifier = Modifier.padding(8.dp)){
           IconButton(onClick = { /*TODO*/ }) {
               Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
           }
           IconButton(onClick = { /*TODO*/ }) {
               Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
           }
       }
   }
}

@Composable
fun EditShoppingItem(item: ShoppingItem,onEditComplete:(String,Int)->Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,

    ){
        Column (){
            BasicTextField(
                value = editedName,
                onValueChange = {editedName=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = editedQuantity,
                onValueChange = {editedQuantity=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }


        Button(onClick = {
            isEditing=false
            onEditComplete(editedName,editedQuantity.toIntOrNull() ?: 1)
        }) {
            Text(text = "Save")
        }

    }

}
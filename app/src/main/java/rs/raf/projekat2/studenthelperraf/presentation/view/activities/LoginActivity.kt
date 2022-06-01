package rs.raf.projekat2.studenthelperraf.presentation.view.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.raf.projekat2.studenthelperraf.data.models.User
import rs.raf.projekat2.studenthelperraf.presentation.theme.StudentHelperRafTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentHelperRafTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Show()
                }
            }
        }
    }
}

@Composable
fun Show() {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }

            Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif))

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Username") },
                value = username.value,
                onValueChange = { username.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Password") },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 40.dp, 40.dp, 0.dp)) {
                val context = LocalContext.current
                val activity = (context as? Activity)
                Button(
                    onClick = {
                       val valid = validateInput(username, password)
                        if(valid){
                            //zapamtimo u sharedPref username i password
                            val pref: SharedPreferences =
                                context.getSharedPreferences(
                                    context.packageName,
                                    Context.MODE_PRIVATE
                                )
                            val editor = pref.edit()
                            editor.putString("username", username.component1().text)
                            editor.putString("password", password.component1().text)
                            editor.apply()
                            context.startActivity(Intent(context, MainActivity::class.java))
                            activity?.finish()
                        }else{
                            Toast.makeText(context, "Login failed. Wrong credentials.", Toast.LENGTH_LONG).show()
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }

    }
}

fun validateInput(username: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
    println(username.component1().text + ", " + password.component1().text)
    for(user in getAllUsers()){
        if(username.component1().text.equals(user.username) && password.component1().text.equals(user.password)){
            return true;
        }
    }
    return false;
}

private fun getAllUsers(): List<User> {
    val u1 = User("miki", "miki")
    val u2 = User("peki", "peki")
    val u3 = User("feki", "feki")
    val users: MutableList<User> = ArrayList<User>()
    users.add(u1)
    users.add(u2)
    users.add(u3)
    return users
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StudentHelperRafTheme {
        Show()
    }
}
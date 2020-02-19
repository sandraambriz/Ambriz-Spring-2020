package edu.boisestate.project1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

          //*********** adding to person list ******//
//        PersonRepository.addPerson(Person("Bob", "Doe", 55, Date()))
//        PersonRepository.addPerson(Person("Jane", "Doe", 23, Date()))
//        PersonRepository.addPerson(Person("Random", "Person", 100, Date()))
//        for(currentPerson in PersonRepository.personList()){
//            Log.d("SA", currentPerson.firstName)
//        }


        //Getting button, and EditText
        val button:Button = findViewById(R.id.display_button)
        val firstNameEditText:EditText = findViewById(R.id.firstname_edittext)
        val lastNameEditText:EditText = findViewById(R.id.lastname_edittext)
        val ageEditText:EditText = findViewById(R.id.age_edittext)

        //Getting action from button, creating person and intent, and bundling data
        button.setOnClickListener{
            val person:Person = Person(firstNameEditText.text.toString(), lastNameEditText.text.toString(), ageEditText.text.toString().toInt(), Date())
            val intent:Intent = Intent(this, PersonDisplayActivity::class.java)
            intent.putExtra("person", person)
            intent.putExtra("somekey", 10)
            startActivityForResult(intent, 12)
        }

    }

    //Function that takes in a requestCode, resultCode, and Intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            //Checks if result ok is being used, if there exists data, and create a person
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val returnedPerson: Person = data.getSerializableExtra("return_person") as Person
                    Log.d("SA", returnedPerson.toString())
                }
            }
            else if(resultCode == Activity.RESULT_CANCELED){
                Log.d("SA", "User cancelled")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

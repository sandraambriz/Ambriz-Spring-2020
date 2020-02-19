package edu.boisestate.project1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_person_display.*
import org.w3c.dom.Text

class PersonDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_display)
        setSupportActionBar(toolbar)

        //Getting serializable data to be shared between activities
        val person:Person = intent.getSerializableExtra("person") as Person
        val someValue:Int = intent.getIntExtra("somekey", 999)

        //Finding TextView
        val firstNameTextView:TextView = findViewById(R.id.firstname_textview)
        val lastNameTextView:TextView = findViewById(R.id.lastname_textview)
        val ageTextView:TextView = findViewById(R.id.age_textview)

        //Making Person with values from TextView
        firstNameTextView.text = person.firstName
        lastNameTextView.text = person.lastName
        ageTextView.text = person.age.toString()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //Making Save and Cancel buttons
        val saveButton: Button = findViewById(R.id.save_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)

        //Activating save button,
        saveButton.setOnClickListener{

            //Creating newPerson, adding to PersonRepository array object, logging for debugging
            val newPerson:Person = Person("First name has been changed",
                "Last name has been changed", 10)
            PersonRepository.addPerson(newPerson)
            Log.d("SA", "$person has been added")
            for(currPerson in PersonRepository.personList()){
                Log.d("SA", "$currPerson")
            }

            //Making a return Intent, bundling data, alerting the user with Toast
            val returnIntent: Intent = Intent()
            returnIntent.putExtra("return_person", newPerson)
            returnIntent.putExtra("Some_new_value", 55)
            Toast.makeText(this, "Your information has been saved.", Toast.LENGTH_SHORT).show()

            //Implementing onActivity result
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        //Activating cancel button and alerting user with Toast
        cancelButton.setOnClickListener{
            Toast.makeText(this, "Your information submission has been cancelled.", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}

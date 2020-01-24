package edu.boisestate.homework1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //Get text from EditText
        val getEditTextOne = findViewById<EditText>(R.id.editTextOne)
        val getEditTextTwo = findViewById<EditText>(R.id.editTextTwo)

        //Use the buttons
        val concatButton = findViewById<Button>(R.id.buttonOne)
        val addButton = findViewById<Button>(R.id.buttonTwo)

        //Display to TextView
        val textViewOneResult = findViewById<TextView>(R.id.textViewOne)
        val textViewTwoResult = findViewById<TextView>(R.id.textViewTwo)

        //Concatenate EditTextOne and EditTextTwo -> Display using button 1
        concatButton.setOnClickListener{
            val concatResult = getEditTextOne.text.toString() + getEditTextTwo.text.toString()
            textViewOneResult.text = concatResult
        }

        //Adds Integers from getEditTextOne + getEditTextTwo
        addButton.setOnClickListener{
            val addResult = getEditTextOne.text.toString().toInt() + getEditTextTwo.text.toString().toInt()
            textViewTwoResult.text = addResult.toString()

        }

    }


    //Control + o allows us to jump into a different class file

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

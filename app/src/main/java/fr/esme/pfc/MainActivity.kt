package fr.esme.pfc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var actionPaper : Button
    lateinit var actionScissor : Button
    lateinit var actionRock : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //intialisation
        actionPaper = findViewById(R.id.actionPaperButton)
        actionScissor = findViewById(R.id.actionScissorButton)
        actionRock = findViewById(R.id.actionRockButton)

        //onClick
        actionPaper.setOnClickListener {
            Log.d("PCF","Paper")
            var textView : TextView = findViewById(R.id.textView)
            textView.text = "Paper"
        }

        actionScissor.setOnClickListener {
            Log.d("PCF","Scissor")
            var textView : TextView = findViewById(R.id.textView)
            textView.text = "Scissor"
        }

        actionRock.setOnClickListener {
            Log.d("PCF","Rock")
            var textView : TextView = findViewById(R.id.textView)
            textView.text = "Rock"
        }

    }

}


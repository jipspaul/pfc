package fr.esme.pfc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher_menu.*
import kotlinx.android.synthetic.main.activity_main.buttonStartGame

class LauncherMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_menu)
        supportActionBar?.hide()

        Log.d("LauncherMenu", "oncreate")

        buttonStartGame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonFindPlayer.setOnClickListener {
            val intent = Intent(this, FindPlayers::class.java)
            startActivity(intent)
        }

        buttonStartTournament.setOnClickListener {
            val intent = Intent(this, TournamentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("LauncherMenu", "onresume")

    }

    override fun onStart() {
        super.onStart()
        Log.d("LauncherMenu", "onstart")

    }


    override fun onPause() {
        super.onPause()
        Log.d("LauncherMenu", "onpause")


    }

    override fun onStop() {
        super.onStop()
        Log.d("LauncherMenu", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LauncherMenu", "onDestroy")

    }
}
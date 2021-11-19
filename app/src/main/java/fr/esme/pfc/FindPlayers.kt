package fr.esme.pfc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esme.pfc.adapters.CustomAdapter
import fr.esme.pfc.entities.Player
import kotlinx.android.synthetic.main.activity_find_players.*


class FindPlayers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_players)
    }


    override fun onResume() {
        super.onResume()

        var players = arrayOf(
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10),
            Player("tte", "joueur 1", 10)
        )


        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        playerList.setLayoutManager(llm)
        playerList.adapter = CustomAdapter(players)
    }
}


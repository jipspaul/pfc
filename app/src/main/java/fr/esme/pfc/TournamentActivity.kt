package fr.esme.pfc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esme.pfc.adapters.TournamentAdapter
import fr.esme.pfc.entities.Player
import fr.esme.pfc.usecase.TournamentUseCase
import kotlinx.android.synthetic.main.activity_find_players.*
import kotlinx.android.synthetic.main.activity_find_players.playerList
import kotlinx.android.synthetic.main.activity_tournament.*
import java.util.*

class TournamentActivity : AppCompatActivity() {

    var players = arrayOf(
        Player(
            UUID.randomUUID().toString(),
            "Luffy",
            "https://www.alfabetajuega.com/wp-content/uploads/2020/03/one-piece-luffy-sombrero.jpg",
            100
        ),
        Player(
            UUID.randomUUID().toString(),
            "Zorro",
            "https://www.cinemascomics.com/wp-content/uploads/2020/09/teoria-one-piece-zoro-ronoa.jpg",
            80
        ),
        Player(UUID.randomUUID().toString(), "joueur 1", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 1", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 1", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 1", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 1", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 1", "", 10),

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tournament)


        //Charger recycler view
        runOnUiThread {

            val llm = LinearLayoutManager(this)
            llm.orientation = LinearLayoutManager.VERTICAL
            tournamentList.setLayoutManager(llm)

            tournamentList.adapter = TournamentAdapter(
                TournamentUseCase().generateTournamentList(players.toList()).toTypedArray()
            ) {}

        }


    }
}
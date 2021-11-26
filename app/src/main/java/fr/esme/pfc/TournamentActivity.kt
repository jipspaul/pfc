package fr.esme.pfc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esme.pfc.adapters.TournamentAdapter
import fr.esme.pfc.entities.GameTournament
import fr.esme.pfc.entities.Player
import fr.esme.pfc.usecase.TournamentUseCase
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
        Player(UUID.randomUUID().toString(), "joueur 2", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 3", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 4", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 5", "", 10),
        Player(UUID.randomUUID().toString(), "joueur 6", "", 10),

        )

    val games: ArrayList<GameTournament> =
        TournamentUseCase().generateTournamentList(players.toList())

    var currentGame : Int = 0

    val getContent = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {


        when (it.data?.extras?.get("WINNER") as GameResult) {
            GameResult.USER1WIN -> games[currentGame].gameResult = GameResult.USER1WIN
            GameResult.USER2WIN ->  games[currentGame].gameResult = GameResult.USER2WIN
            GameResult.STILL_PLAYING ->  games[currentGame].gameResult = GameResult.USER1WIN //TODO remove
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tournament)

    }

    override fun onResume() {
        super.onResume()

        //Charger recycler view
        runOnUiThread {

            val llm = LinearLayoutManager(this)
            llm.orientation = LinearLayoutManager.VERTICAL
            tournamentList.layoutManager = llm

            tournamentList.adapter = TournamentAdapter(
                games.toTypedArray()
            ) {

                //TODO send info
                currentGame = it

                runOnUiThread {

                    val intent = Intent(this, MainActivity::class.java)
                    getContent.launch(intent)
                }

            }

        }

    }
}
package fr.esme.pfc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.esme.pfc.entities.Player

class TournamentActivity : AppCompatActivity() {

    var players = arrayOf(
        Player(
            "tte",
            "Luffy",
            "https://www.alfabetajuega.com/wp-content/uploads/2020/03/one-piece-luffy-sombrero.jpg",
            100
        ),
        Player(
            "tte",
            "Zorro",
            "https://www.cinemascomics.com/wp-content/uploads/2020/09/teoria-one-piece-zoro-ronoa.jpg",
            80
        ),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10),
        Player("tte", "joueur 1", "", 10)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tournament)


        //TODO generate tournament tree

    }
}
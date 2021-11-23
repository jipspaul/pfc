package fr.esme.pfc.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.esme.pfc.entities.Player
import org.jetbrains.anko.runOnUiThread

class PlayerRepository {

    private val _playerLiveData = MutableLiveData<List<Player>>().apply {
        value = listOf()
    }
    val playerLiveData: LiveData<List<Player>> = _playerLiveData


    var playersList: ArrayList<Player> = arrayListOf()

    fun getPlayers(context: Context): LiveData<List<Player>> {
        loadDataFromMockServer(context)
        return playerLiveData
    }

    fun loadDataFromMockServer(context: Context) {

        //TODO start thread every 2sec add player
        Thread {
            for (player in players) {
                Thread.sleep(2000)
                playersList.add(player)

                context.runOnUiThread {
                    _playerLiveData.value = playersList.toList()
                }

            }
        }.start()
    }


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
        Player("tte", "joueur 1", "", 10)
    )

}
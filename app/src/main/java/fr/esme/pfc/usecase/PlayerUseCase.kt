package fr.esme.pfc.usecase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.esme.pfc.Actions
import fr.esme.pfc.Game
import fr.esme.pfc.entities.Player
import org.jetbrains.anko.runOnUiThread

class PlayerUseCase(private val context: Context) {

    /**
     * Ask player to start a game
     */
    fun askPlayerToStartGame(player: Player): LiveData<Boolean> {
        val playerAnswer = MutableLiveData<Boolean>()

        //TODO ask server
        Thread {
            Thread.sleep(5000)

            context.runOnUiThread {
                playerAnswer.value = true
            }
        }.start()

        return playerAnswer
    }

    /**
     * ecouter les action de jeu de l'utilisateur
     */
    fun getPlayerAction(): LiveData<Actions> {
        val playerActions = MutableLiveData<Actions>()

        return playerActions
    }

    /**
     * Envoyer a l utilisateur le status du game en cours
     */
    fun sendGameData(data:Game){

    }

    fun quitGame(){

    }

    fun sendMessage(){

    }

}
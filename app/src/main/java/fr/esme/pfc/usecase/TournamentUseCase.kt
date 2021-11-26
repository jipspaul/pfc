package fr.esme.pfc.usecase

import fr.esme.pfc.entities.GameTournament
import fr.esme.pfc.entities.Player

class TournamentUseCase {

    var tournamentGames: ArrayList<GameTournament> = arrayListOf()


    //Generate bracket
    fun generateTournamentList(players: List<Player>): ArrayList<GameTournament> {

        //generate first lign
        for (i in 0 until players.size step 2) {
            tournamentGames.add(GameTournament(players[i], players[i + 1]))
        }

        return tournamentGames
    }


}
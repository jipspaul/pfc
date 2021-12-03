package fr.esme.pfc.usecase

import fr.esme.pfc.GameResult
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

    fun isGameFinish(games: List<GameTournament>): Boolean {

        for (game in games){
            if (game.gameResult == GameResult.STILL_PLAYING){
                return false
            }
        }

        return true
    }

    fun getWinners(games: List<GameTournament>): ArrayList<Player> {

        var winners : ArrayList<Player> = arrayListOf()

        for(game in games){

            when(game.gameResult){
                GameResult.USER1WIN -> winners.add(game.userOne)
                GameResult.USER2WIN -> winners.add(game.userTwo)
                GameResult.STILL_PLAYING -> {}
            }
        }

        return winners
    }


}
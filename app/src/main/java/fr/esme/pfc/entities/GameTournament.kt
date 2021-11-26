package fr.esme.pfc.entities

import fr.esme.pfc.GameResult

class GameTournament(
    var userOne: Player,
    var userTwo: Player
){
    var gameResult: GameResult = GameResult.STILL_PLAYING
}


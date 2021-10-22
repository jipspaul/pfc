package fr.esme.pfc

import java.util.*

class GamePlayEngine {

    //Create game data
    var game: Game = Game()

    //
    var playerOneActions: Actions = Actions.NONE
    var playerTwoActions: Actions = Actions.NONE


    fun selectAction(playerOneCurrentAction: Actions) {
        playerOneActions = playerOneCurrentAction

        //TODO use server
        val random = Random()
        val randomValue = random.nextInt(3)

        playerTwoActions = when (randomValue) {
            0 -> Actions.ROCK
            1 -> Actions.PAPER
            2 -> Actions.SCISSOR
            else -> Actions.NONE
        }


    }

    fun whoWinTheGame(): GameResult {
        if (game.scoreOne == 3) {
            return GameResult.USER1WIN
        } else if (game.scoreTwo == 3) {
            return GameResult.USER2WIN
        } else {
            return GameResult.STILL_PLAYING
        }
    }

    fun startGame() {
        game.scoreOne = 0
        game.scoreTwo = 0
        game.currentRound = 0
    }

    fun startRound() {

    }

    fun whoWinRound(
        userOneAction: Actions,
        userTwoAction: Actions
    ): RoundResult {

        var result: RoundResult = RoundResult.DRAW


        //DRAW
        if (userOneAction == userTwoAction) {
            result = RoundResult.DRAW
        }

        //USER NONE
        if (userTwoAction == Actions.NONE) {
            result = RoundResult.USER1;
        } else if (userOneAction == Actions.NONE) {
            result = RoundResult.USER2;
        }

        if (userOneAction == Actions.SCISSOR) {
            //userTwo rock
            if (userTwoAction == Actions.ROCK) {
                result = RoundResult.USER2;
            }
            //userTwo paper
            if (userTwoAction == Actions.PAPER) {
                result = RoundResult.USER1;
            }
            //userTwo none
            if (userTwoAction == Actions.NONE) {
                result = RoundResult.USER1;
            }
        }

        if (userOneAction == Actions.ROCK) {
            //userTwo paper
            if (userTwoAction == Actions.PAPER) {
                result = RoundResult.USER2;
            }
            //userTwo scissor
            if (userTwoAction == Actions.SCISSOR) {
                result = RoundResult.USER1;
            }
            //userTwo none
            if (userTwoAction == Actions.NONE) {
                result = RoundResult.USER1;
            }
        }

        if (userOneAction == Actions.PAPER) {
            //userTwo rock
            if (userTwoAction == Actions.ROCK) {
                result = RoundResult.USER1;
            }
            //userTwo scissor
            if (userTwoAction == Actions.SCISSOR) {
                result = RoundResult.USER2;
            }
        }

        if (result == RoundResult.USER1) {
            game.scoreOne = game.scoreOne + 1
        }
        else if (result == RoundResult.USER2) {
            game.scoreTwo = game.scoreTwo + 1
        }

        //Check who win
        return result
    }


}
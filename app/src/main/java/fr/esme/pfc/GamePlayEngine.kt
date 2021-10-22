package fr.esme.pfc

class GamePlayEngine {

    //Create game data
    var game: Game = Game()

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

        //DRAW
        if (userOneAction == userTwoAction) {
            return RoundResult.DRAW;
        }

        //USER NONE
        if (userTwoAction == Actions.NONE) {
            return RoundResult.USER1;
        } else if (userOneAction == Actions.NONE) {
            return RoundResult.USER2;
        }

        if (userOneAction == Actions.SCISSOR) {
            //userTwo rock
            if (userTwoAction == Actions.ROCK) {
                return RoundResult.USER2;
            }
            //userTwo paper
            if (userTwoAction == Actions.PAPER) {
                return RoundResult.USER1;
            }
            //userTwo none
            if (userTwoAction == Actions.NONE) {
                return RoundResult.USER1;
            }
        }

        if (userOneAction == Actions.ROCK) {
            //userTwo paper
            if (userTwoAction == Actions.PAPER) {
                return RoundResult.USER2;
            }
            //userTwo scissor
            if (userTwoAction == Actions.SCISSOR) {
                return RoundResult.USER1;
            }
            //userTwo none
            if (userTwoAction == Actions.NONE) {
                return RoundResult.USER1;
            }
        }

        if (userOneAction == Actions.PAPER) {
            //userTwo rock
            if (userTwoAction == Actions.ROCK) {
                return RoundResult.USER1;
            }
            //userTwo scissor
            if (userTwoAction == Actions.SCISSOR) {
                return RoundResult.USER2;
            }
        }

        return RoundResult.DRAW
    }


}
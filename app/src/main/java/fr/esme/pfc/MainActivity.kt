package fr.esme.pfc

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    //Button
    lateinit var actionPaper: Button
    lateinit var actionScissor: Button
    lateinit var actionRock: Button
    lateinit var buttonStartGame: Button

    //Score
    lateinit var firstPointImageView: ImageView
    lateinit var secondPointImageView: ImageView
    lateinit var thirdPointImageView: ImageView
    lateinit var scorePlayerTwoTextView: TextView

    //TextView
    lateinit var textView: TextView

    val gamePlayEngine: GamePlayEngine = GamePlayEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        //intialisation
        actionPaper = findViewById(R.id.actionPaperButton)
        actionScissor = findViewById(R.id.actionScissorButton)
        actionRock = findViewById(R.id.actionRockButton)
        buttonStartGame = findViewById(R.id.buttonStartGame)

        //textview
        textView = findViewById(R.id.textView)

        firstPointImageView = findViewById(R.id.firstPoint)
        secondPointImageView = findViewById(R.id.secondPoint)
        thirdPointImageView = findViewById(R.id.thirdPoint)

        scorePlayerTwoTextView = findViewById(R.id.scorePlayerTwo)

        //Observer
        gamePlayEngine.liveData.observe(this,
            Observer {
                when (it) {
                    GameStateMachine.INITIAL -> showInitialState()
                    GameStateMachine.PLAYING -> showPlayingState()
                    GameStateMachine.FINISH -> showFinishState()
                }
            })


        //onClick
        actionPaper.setOnClickListener {
            play(Actions.PAPER)
        }

        actionScissor.setOnClickListener {
            play(Actions.SCISSOR)

        }

        actionRock.setOnClickListener {
            play(Actions.ROCK)

        }

        buttonStartGame.setOnClickListener {
            gamePlayEngine.startGame()
        }

    }

    var OFFSET_INTERVAL: Long = 2000
    var NUMBER_OF_ROUND = 3
    var OFFSET_GAME: Long = OFFSET_INTERVAL * 5 * NUMBER_OF_ROUND

    enum class GAME_MANAGEMENT { PLAY, ONE, TWO, THREE, FINISH }

    var gameManagement: GAME_MANAGEMENT = GAME_MANAGEMENT.PLAY


    private fun startTimer() {
        val timer = object : CountDownTimer(OFFSET_GAME, OFFSET_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = millisUntilFinished.toString()


                //Show button only during timer
                when (gameManagement) {
                    GAME_MANAGEMENT.PLAY -> {
                        textView.text = "JOUEZ"
                        gameManagement = GAME_MANAGEMENT.ONE
                        buttonGameVisibility(false)
                    }
                    GAME_MANAGEMENT.ONE -> {
                        textView.text = "3"
                        gameManagement = GAME_MANAGEMENT.TWO
                        buttonGameVisibility(true)

                    }
                    GAME_MANAGEMENT.TWO -> {
                        textView.text = "2"
                        gameManagement = GAME_MANAGEMENT.THREE
                        buttonGameVisibility(true)

                    }
                    GAME_MANAGEMENT.THREE -> {
                        textView.text = "1"
                        gameManagement = GAME_MANAGEMENT.FINISH
                        buttonGameVisibility(true)

                    }
                    GAME_MANAGEMENT.FINISH -> {
                        textView.text = "FINI"
                        gameManagement = GAME_MANAGEMENT.PLAY
                        buttonGameVisibility(false)

                    }
                }

            }

            override fun onFinish() {
                textView.text = "FINISH"
            }
        }

        timer.start()
    }

    private fun showWinner() {

        val textView: TextView = findViewById(R.id.textView)

        when (gamePlayEngine.whoWinTheGame()) {
            GameResult.USER1WIN -> textView.text = "Joueur1 a Gagné"
            GameResult.USER2WIN -> textView.text = "Joueur2 a Gagné"
            GameResult.STILL_PLAYING -> return
        }

        if (gamePlayEngine.whoWinTheGame() != GameResult.STILL_PLAYING) {
            //Show button play
            buttonStartGame.visibility = View.VISIBLE

            //Hide buttons game
            actionPaper.visibility = View.GONE
            actionScissor.visibility = View.GONE
            actionRock.visibility = View.GONE
        }

    }

    private fun buttonGameVisibility(isVisible: Boolean) {
        if (isVisible) {
            actionPaper.visibility = View.VISIBLE
            actionScissor.visibility = View.VISIBLE
            actionRock.visibility = View.VISIBLE
        } else {
            actionPaper.visibility = View.GONE
            actionScissor.visibility = View.GONE
            actionRock.visibility = View.GONE
        }
    }

    private fun showScore(score: Int) {


        scorePlayerTwoTextView.text = "Score J2: " + gamePlayEngine.game.scoreTwo

        when (score) {
            0 -> {
                firstPointImageView.visibility = View.GONE
                secondPointImageView.visibility = View.GONE
                thirdPointImageView.visibility = View.GONE
            }

            1 -> {
                firstPointImageView.visibility = View.VISIBLE
                secondPointImageView.visibility = View.GONE
                thirdPointImageView.visibility = View.GONE
            }

            2 -> {
                firstPointImageView.visibility = View.VISIBLE
                secondPointImageView.visibility = View.VISIBLE
                thirdPointImageView.visibility = View.GONE
            }

            3 -> {
                firstPointImageView.visibility = View.VISIBLE
                secondPointImageView.visibility = View.VISIBLE
                thirdPointImageView.visibility = View.VISIBLE
            }
        }

    }

    private fun play(action: Actions) {

        gamePlayEngine.selectAction(action)

        var result = gamePlayEngine.whoWinRound(
            gamePlayEngine.playerOneActions,
            gamePlayEngine.playerTwoActions
        )

        showResult(result)
        showScore(gamePlayEngine.game.scoreOne)
        showWinner()
    }

    private fun showResult(result: RoundResult) {
        val textView: TextView = findViewById(R.id.textView)
        when (result) {
            RoundResult.USER1 -> textView.text = "WIN"

            RoundResult.USER2 -> textView.text = "LOSE"

            RoundResult.DRAW -> textView.text = "DRAW"

        }
    }

    fun showPlayingButton(isVisible: Boolean) {
        if (isVisible) {
            buttonStartGame.visibility = View.VISIBLE
        } else {
            buttonStartGame.visibility = View.INVISIBLE
        }
    }

    fun showScoresButton(isVisible: Boolean) {
        if (isVisible) {
            firstPointImageView.visibility = View.VISIBLE
            secondPointImageView.visibility = View.VISIBLE
            thirdPointImageView.visibility = View.VISIBLE
            scorePlayerTwoTextView.visibility = View.VISIBLE
        } else {
            firstPointImageView.visibility = View.INVISIBLE
            secondPointImageView.visibility = View.INVISIBLE
            thirdPointImageView.visibility = View.INVISIBLE
            scorePlayerTwoTextView.visibility = View.INVISIBLE
        }
    }

    fun showInfoScreen(isVisible: Boolean) {
        if (isVisible) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.INVISIBLE
        }
    }

    fun showActionButton(isVisible: Boolean) {
        if (isVisible) {
            actionPaper.visibility = View.VISIBLE
            actionScissor.visibility = View.VISIBLE
            actionRock.visibility = View.VISIBLE
        } else {
            actionPaper.visibility = View.INVISIBLE
            actionScissor.visibility = View.INVISIBLE
            actionRock.visibility = View.INVISIBLE
        }
    }

    /// only playing button shown
    fun showInitialState() {
        showPlayingButton(true)
        showInfoScreen(false)
        showActionButton(false)
        showScoresButton(false)
    }

    fun showPlayingState() {
        showPlayingButton(false)
        showInfoScreen(true)
        showActionButton(true)
        showScoresButton(true)
    }

    fun showFinishState() {
        showPlayingButton(true)
        showInfoScreen(true)
        showActionButton(false)
        showScoresButton(false)
    }

}


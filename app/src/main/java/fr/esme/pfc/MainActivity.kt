package fr.esme.pfc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    //Button
    lateinit var actionPaper: ImageButton
    lateinit var actionScissor: ImageButton
    lateinit var actionRock: ImageButton
    lateinit var buttonStartGame: Button

    //Score
    lateinit var firstPointImageView: ImageView
    lateinit var secondPointImageView: ImageView
    lateinit var thirdPointImageView: ImageView

    lateinit var firstPointPlayerTwoImageView: ImageView
    lateinit var secondPointPlayerTwoImageView: ImageView
    lateinit var thirdPointPlayerTwoImageView: ImageView


    //TextView
    lateinit var textView: TextView

    //initial screen
    lateinit var appTitle: TextView
    lateinit var logo: ImageView

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

        appTitle = findViewById(R.id.appTitle)
        logo = findViewById(R.id.logo)


        //textview
        textView = findViewById(R.id.textView)

        firstPointImageView = findViewById(R.id.firstPoint)
        secondPointImageView = findViewById(R.id.secondPoint)
        thirdPointImageView = findViewById(R.id.thirdPoint)

        firstPointPlayerTwoImageView = findViewById(R.id.firstPointSecond)
        secondPointPlayerTwoImageView = findViewById(R.id.secondPointSecond)
        thirdPointPlayerTwoImageView = findViewById(R.id.thirdPointSecond)


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

            showScore(0)
            textView.text = "";
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
        when (gamePlayEngine.game.scoreOne) {
            0 -> {
                firstPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
                secondPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
                thirdPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
            }

            1 -> {
                firstPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                secondPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
                thirdPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
            }

            2 -> {
                firstPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                secondPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                thirdPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
            }

            3 -> {
                firstPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                secondPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                thirdPointImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
            }
        }

        when (gamePlayEngine.game.scoreTwo) {
            0 -> {
                firstPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
                secondPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
                thirdPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
            }

            1 -> {
                firstPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                secondPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
                thirdPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
            }

            2 -> {
                firstPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                secondPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                thirdPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_grey)))
            }

            3 -> {
                firstPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                secondPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
                thirdPointPlayerTwoImageView.setImageDrawable(getResources().getDrawable((R.drawable.star_yellow)))
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

    fun showScoresStars(isVisible: Boolean) {
        if (isVisible) {

            findViewById<TextView>(R.id.playerOne).visibility = View.VISIBLE
            findViewById<TextView>(R.id.playerTwo).visibility = View.VISIBLE

            firstPointImageView.visibility = View.VISIBLE
            secondPointImageView.visibility = View.VISIBLE
            thirdPointImageView.visibility = View.VISIBLE

            firstPointPlayerTwoImageView.visibility = View.VISIBLE
            secondPointPlayerTwoImageView.visibility = View.VISIBLE
            thirdPointPlayerTwoImageView.visibility = View.VISIBLE

        } else {

            findViewById<TextView>(R.id.playerOne).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.playerTwo).visibility = View.INVISIBLE


            firstPointImageView.visibility = View.INVISIBLE
            secondPointImageView.visibility = View.INVISIBLE
            thirdPointImageView.visibility = View.INVISIBLE

            firstPointPlayerTwoImageView.visibility = View.INVISIBLE
            secondPointPlayerTwoImageView.visibility = View.INVISIBLE
            thirdPointPlayerTwoImageView.visibility = View.INVISIBLE
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

    fun showInitialElement(isVisible: Boolean) {
        if (isVisible) {
            logo.visibility = View.VISIBLE
            appTitle.visibility = View.VISIBLE
        } else {
            logo.visibility = View.INVISIBLE
            appTitle.visibility = View.INVISIBLE
        }
    }

    /// only playing button shown
    fun showInitialState() {
        showPlayingButton(true)
        showInitialElement(true)
        showInfoScreen(false)
        showActionButton(false)
        showScoresStars(false)
    }

    fun showPlayingState() {
        showPlayingButton(false)
        showInitialElement(false)
        showInfoScreen(true)
        showActionButton(true)
        showScoresStars(true)
    }

    fun showFinishState() {
        showInitialElement(false)
        showPlayingButton(true)
        showInfoScreen(true)
        showActionButton(false)
        showScoresStars(false)
    }

}


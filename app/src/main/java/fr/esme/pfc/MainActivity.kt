package fr.esme.pfc

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    //Button
    lateinit var actionPaper: Button
    lateinit var actionScissor: Button
    lateinit var actionRock: Button

    //Score
    lateinit var firstPointImageView: ImageView
    lateinit var secondPointImageView: ImageView
    lateinit var thirdPointImageView: ImageView

    val gamePlayEngine: GamePlayEngine = GamePlayEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //intialisation
        actionPaper = findViewById(R.id.actionPaperButton)
        actionScissor = findViewById(R.id.actionScissorButton)
        actionRock = findViewById(R.id.actionRockButton)

        firstPointImageView = findViewById(R.id.firstPoint)
        secondPointImageView = findViewById(R.id.secondPoint)
        thirdPointImageView = findViewById(R.id.thirdPoint)

        //onClick
        actionPaper.setOnClickListener {
            Log.d("PCF", "Paper")
            play(Actions.PAPER)
        }

        actionScissor.setOnClickListener {
            Log.d("PCF", "Scissor")
            play(Actions.SCISSOR)

        }

        actionRock.setOnClickListener {
            Log.d("PCF", "Rock")
            play(Actions.ROCK)

        }

    }

    private fun showScore(score: Int) {

        when(score){
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
    }

    private fun showResult(result: RoundResult) {
        val textView: TextView = findViewById(R.id.textView)
        when (result) {
            RoundResult.USER1 -> textView.text = "WIN"

            RoundResult.USER2 -> textView.text = "LOSE"

            RoundResult.DRAW -> textView.text = "DRAW"

        }
    }

}


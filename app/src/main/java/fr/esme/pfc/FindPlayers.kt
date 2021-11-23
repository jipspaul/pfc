package fr.esme.pfc

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fr.esme.pfc.adapters.CustomAdapter
import fr.esme.pfc.entities.Player
import fr.esme.pfc.repositories.PlayerRepository
import fr.esme.pfc.usecase.PlayerUseCase
import kotlinx.android.synthetic.main.activity_find_players.*


class FindPlayers : AppCompatActivity() {

    lateinit var playerUseCase: PlayerUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_players)
    }


    override fun onResume() {
        super.onResume()

        playerUseCase = PlayerUseCase(this)


        PlayerRepository().getPlayers(this).observe(this,
            androidx.lifecycle.Observer {

                runOnUiThread {

                    val llm = LinearLayoutManager(this)
                    llm.orientation = LinearLayoutManager.VERTICAL
                    playerList.setLayoutManager(llm)

                    playerList.adapter = CustomAdapter(it.toTypedArray()) {

                        showLoader(it)

                        //ASK player to play
                        playerUseCase.askPlayerToStartGame(it).observe(this,
                            androidx.lifecycle.Observer { isOK ->
                                hideLoader()
                                if (isOK) {
                                    startGame(it)
                                }
                            }
                        )
                    }

                }

            })

    }

    fun showLoader(player: Player) {

        cardview.visibility = View.VISIBLE

        Glide.with(applicationContext)
            .load(player.imageUrl)
            .circleCrop()
            .into(avatarProgress)


    }

    fun hideLoader() {

        cardview.visibility = View.INVISIBLE

    }

    fun startGame(player: Player) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}


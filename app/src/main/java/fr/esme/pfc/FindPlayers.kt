package fr.esme.pfc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esme.pfc.adapters.CustomAdapter
import fr.esme.pfc.repositories.PlayerRepository
import kotlinx.android.synthetic.main.activity_find_players.*


class FindPlayers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_players)
    }


    override fun onResume() {
        super.onResume()

        PlayerRepository().getPlayers(this).observe(this,
            androidx.lifecycle.Observer {

                runOnUiThread {

                    val llm = LinearLayoutManager(this)
                    llm.orientation = LinearLayoutManager.VERTICAL
                    playerList.setLayoutManager(llm)

                    playerList.adapter = CustomAdapter(it.toTypedArray()) {
                        Log.d("TEST", "on click from adapter")
                    }

                }

            })


    }
}


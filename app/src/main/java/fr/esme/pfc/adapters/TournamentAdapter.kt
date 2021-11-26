package fr.esme.pfc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.esme.pfc.R
import fr.esme.pfc.entities.GameTournament
import fr.esme.pfc.entities.Player

class TournamentAdapter(
    private val dataSet: Array<GameTournament>,
    private val onCLick: (player: Player) -> Unit
) :
    RecyclerView.Adapter<TournamentAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarPlayerOne: ImageView
        val playerOneNametextView: TextView

        val avatarPlayerTwo: ImageView
        val playerTwoNametextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            avatarPlayerOne = view.findViewById(R.id.avatarPlayerOne)
            playerOneNametextView = view.findViewById(R.id.playerOneTextView)
            avatarPlayerTwo = view.findViewById(R.id.avatarPlayerTwo)
            playerTwoNametextView = view.findViewById(R.id.playerTwoTextView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.tournament_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Glide.with(viewHolder.avatarPlayerOne.context)
            .load(dataSet[position].userOne.imageUrl)
            .circleCrop()
            .into(viewHolder.avatarPlayerOne)
        viewHolder.playerOneNametextView.text = dataSet[position].userOne.name

        Glide.with(viewHolder.avatarPlayerTwo.context)
            .load(dataSet[position].userTwo.imageUrl)
            .circleCrop()
            .into(viewHolder.avatarPlayerTwo)
        viewHolder.playerTwoNametextView.text = dataSet[position].userOne.name

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


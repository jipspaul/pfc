package fr.esme.pfc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.esme.pfc.R
import fr.esme.pfc.entities.Player

class CustomAdapter(private val dataSet: Array<Player>, private val onCLick: (player : Player) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val avatar: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
            avatar = view.findViewById(R.id.avatar)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.player_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].name

        Glide.with(viewHolder.avatar.context)
            .load(dataSet[position].imageUrl)
            .circleCrop()
            .into(viewHolder.avatar)

        viewHolder.avatar.setOnClickListener {
            onCLick(dataSet[position])
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


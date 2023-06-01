package be.seppevandenberk.degrootrally.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.seppevandenberk.degrootrally.R
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class RallyAdapter(val items: List<RallyItem>) :
    RecyclerView.Adapter<RallyAdapter.RallyItemViewHolder>() {
    private var deleteButtonsVisible = true
    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
        fun onEditClick(position: Int)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    fun setEditDeleteButtonsVisible(visible: Boolean) {
        deleteButtonsVisible = visible
        notifyDataSetChanged()
    }
    inner class RallyItemViewHolder(currentItemView: View) :
        RecyclerView.ViewHolder(currentItemView){
            val deleteImageView: ImageView = currentItemView.findViewById(R.id.im_trash_vw)
            val editImageView: ImageView = currentItemView.findViewById(R.id.im_editPen_vw)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RallyItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rally_item_view, parent, false)
        return RallyItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RallyItemViewHolder, position: Int) {
        val currentRally = items[position]
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentRally.date)

        holder.itemView.apply {
            findViewById<TextView>(R.id.TitelRallyEnDatum).text =
                currentRally.title.plus(" - ").plus(formattedDate)
            findViewById<TextView>(R.id.Resultaat).text = currentRally.result
            findViewById<TextView>(R.id.PilootEnCoPiloot).text =
                currentRally.piloot.plus(" - ").plus(currentRally.copiloot)
        }

        holder.deleteImageView.setOnClickListener {
            listener?.onDeleteClick(position)
        }
        holder.editImageView.setOnClickListener {
            listener?.onEditClick(position)
        }

        if (deleteButtonsVisible) {
            holder.deleteImageView.visibility = View.VISIBLE
            holder.editImageView.visibility = View.VISIBLE
        } else {
            holder.deleteImageView.visibility = View.INVISIBLE
            holder.editImageView.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int = items.size

}
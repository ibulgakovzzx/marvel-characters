package com.example.marver_characters.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marver_characters.R
import com.example.marver_characters.data.model.Character
import kotlinx.android.synthetic.main.view_character_list_item.view.*

class CharactersListAdapter
constructor(private val onClick: (character: Character) -> Unit): RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    private val list: MutableList<Character> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_character_list_item, parent, false))
        {
            onClick(list[it])
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =
        list.size

    override fun getItemId(position: Int): Long =
        list[position].id.toLong()

    fun setItems(list: List<Character>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    open class ViewHolder(view: View, onClick: (position: Int) -> Unit)
        : RecyclerView.ViewHolder(view)
    {
        init {
            itemView.setOnClickListener {
                if(adapterPosition == RecyclerView.NO_POSITION)
                    return@setOnClickListener

                onClick(adapterPosition)
            }
        }

        fun bind(character: Character) {
            Glide.with(itemView.thumbnail)
                .load(character.thumbnail)
                .circleCrop()
                .into(itemView.thumbnail)

            itemView.name.text = character.name
        }
    }
}
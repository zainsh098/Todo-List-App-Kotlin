package com.example.todolistapp.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.AllTaskFragment
import com.example.todolistapp.R
import com.example.todolistapp.RoomDatabase.Notes

class MyAdapter(val context: Context, private val listener: NotesClickInterface) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    private val allNotesList = ArrayList<Notes>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById<TextView>(R.id.textViewTitle)
        val priorityTextView: TextView = itemView.findViewById<TextView>(R.id.textViewPriority)
        val checkBox: CheckBox = itemView.findViewById<CheckBox>(R.id.checkman)

//        val priorityButtonHigh=itemView.findViewById<Button>(R.id.buttonPriorityHigh)
//        val priorityButtonMedium=itemView.findViewById<Button>(R.id.buttonPriorityMedium)
//        val priorityButtonLow=itemView.findViewById<Button>(R.id.buttonPriorityLow)
//        val priorityButtonVeryLow=itemView.findViewById<Button>(R.id.buttonPriorityVeryLow)
//    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val itemView1 = MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_card_item, parent, false)
        )

        itemView1.checkBox.setOnClickListener {

            listener.onItemClicked(allNotesList[itemView1.adapterPosition])

        }



        return itemView1
    }


    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val currentNote = allNotesList[position]
        holder.titleTextView.text = currentNote.titleTask
        holder.priorityTextView.text = currentNote.priorityTask
        holder.checkBox.isChecked = currentNote.statusTask


    }


    fun updateList(newList: List<Notes>) {

        this.allNotesList.clear()
        allNotesList.addAll(newList)

        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return allNotesList.size

    }


}

interface NotesClickInterface {


    fun onItemClicked(notes: Notes)


}
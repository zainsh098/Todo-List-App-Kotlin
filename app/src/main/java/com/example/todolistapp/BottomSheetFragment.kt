package com.example.todolistapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.R.drawable.round_button
import com.example.todolistapp.RoomDatabase.Notes
import com.example.todolistapp.viewmodel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var noteText: EditText
    private lateinit var viewModel: NoteViewModel

    private lateinit var highButton: AppCompatButton
    private lateinit var mediumButton: AppCompatButton
    private lateinit var lowButton: AppCompatButton
    private lateinit var vLowButton: AppCompatButton

    private var selectedPriority: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteText = view.findViewById(R.id.EditTextTask)
        highButton = view.findViewById(R.id.buttonPriorityHigh)
        mediumButton = view.findViewById(R.id.buttonPriorityMedium)
        lowButton = view.findViewById(R.id.buttonPriorityLow)
        vLowButton = view.findViewById(R.id.buttonPriorityVeryLow)
        val button = view.findViewById<Button>(R.id.buttonBottomSheetCreateTask)

        viewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        highButton.setOnClickListener {
            handlePriorityButton(highButton, "High")
        }

        mediumButton.setOnClickListener {
            handlePriorityButton(mediumButton, "Medium")
        }

        lowButton.setOnClickListener {
            handlePriorityButton(lowButton, "Low")
        }

        vLowButton.setOnClickListener {
            handlePriorityButton(vLowButton, "Very Low")
        }

        button.setOnClickListener {
            submitData()

            Toast.makeText(requireContext(), "Task Added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handlePriorityButton(button: AppCompatButton, priority: String) {

            highButton.isSelected = false

        highButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.round_button))
        highButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))

            mediumButton.isSelected = false
            mediumButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.round_button))
            mediumButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))

            lowButton.isSelected = false
        lowButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.round_button))
        lowButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))


        vLowButton.isSelected = false
        vLowButton.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.round_button))
        vLowButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))



        button.isSelected = true
        button.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.selected_roundbutton))
        button.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            selectedPriority = priority

    }

    private fun submitData() {
        val textN = noteText.text.toString()

        if (textN.isEmpty()) {
           Toast.makeText(requireContext(), "Please enter a task", Toast.LENGTH_SHORT).show()
        }
        else{
            viewModel.addNote(Notes(textN, selectedPriority,false))
            noteText.text.clear()
            Toast.makeText(requireContext(), "Task Added Successfully", Toast.LENGTH_SHORT).show()

            dismiss()

        }
    }
}

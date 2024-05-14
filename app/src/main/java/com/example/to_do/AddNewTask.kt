package com.example.to_do

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewTask :  BottomSheetDialogFragment() {

    companion object {
        const val TAG = "AddNewTaskDialog"

        fun newInstance(): AddNewTask {
            return AddNewTask()
        }
    }

    private lateinit var editText: EditText
    private lateinit var btn_save: Button
    private lateinit var datePicker: DatePicker


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_newtask, container, false)
        editText = view.findViewById(R.id.edittext)
        btn_save = view.findViewById(R.id.btn_save)
        datePicker = view.findViewById(R.id.datePicker)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isUpdate = false

        val bundle = arguments
        if (bundle != null) {
            isUpdate = true
            val task = bundle.getString("task")
            editText.setText(task)
            if (!task.isNullOrEmpty()) {
                btn_save.isEnabled = false
                btn_save.setBackgroundColor(Color.GRAY)
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    btn_save.isEnabled = false
                    btn_save.setBackgroundColor(Color.GRAY)
                } else {
                    btn_save.isEnabled = true
                    btn_save.setBackgroundColor(resources.getColor(R.color.yellow))
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val finalIsUpdate = isUpdate
        btn_save.setOnClickListener {
            val text = editText.text.toString()

            val selectedDate = "${datePicker.year}-${datePicker.month}-${datePicker.dayOfMonth}"


        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = requireActivity()
        if (activity is OnDialogCloseListener) {
            (activity as OnDialogCloseListener).onDialogClose(dialog)
        }
    }
}

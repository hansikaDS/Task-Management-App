package com.example.to_do

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.to_do.Utils.DataBaseHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

class AddNewTask : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "AddNewTask"
    }

    // widgets

    private lateinit var editText: EditText
    private lateinit var btn_save: Button

    private lateinit var myDb: DataBaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { //parameters can be nullable
        val v = inflater.inflate(R.layout.add_newtask, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText = view.findViewById(R.id.edittext)
        btn_save = view.findViewById(R.id.btn_save)

        myDb = DataBaseHelper(requireActivity())

        val isUpdate = false
    }



}
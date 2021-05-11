package com.company.stupidsimplebudget.ui.home;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.company.stupidsimplebudget.R;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String name;
    private float amount;

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    ExpenseDialogListener listener;

    public interface ExpenseDialogListener {
        public void onExpenseDialogSave(ExpenseDialogFragment dialog);
        public void onExpenseDialogCancel(ExpenseDialogFragment dialog);
    }

    public ExpenseDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpenseDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseDialogFragment newInstance(String param1, String param2) {
        ExpenseDialogFragment fragment = new ExpenseDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            amount = getArguments().getFloat(ARG_PARAM2);
        }

        listener = (ExpenseDialogListener)getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_dialog, container, false);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onExpenseDialogCancel(ExpenseDialogFragment.this);
            }
        });

        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt1 = view.findViewById(R.id.txtExpenseName);
                EditText txt2 = view.findViewById(R.id.txtExpenseAmount);
                name = txt1.getText().toString();
                try {
                    amount = Float.parseFloat(txt2.getText().toString());
                }
                catch(NumberFormatException pe){
                    amount = 0;
                }
                if(name == null || name.length() < 1){
                    Toast.makeText(getActivity(), "Name is required!", Toast.LENGTH_SHORT).show();
                }
                else if(amount >= 1000000){
                    Toast.makeText(getActivity(), "Too expensive!", Toast.LENGTH_SHORT).show();
                }
                else{
                    listener.onExpenseDialogSave(ExpenseDialogFragment.this);
                }
            }
        });

        return view;
    }
}
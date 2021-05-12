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
 * Use the {@link IncomeEditDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeEditDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int incomeId;
    private String name;
    private float amount;

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    IncomeEditDialogListener listener;

    public void setListener(IncomeEditDialogListener listener) {
        this.listener = listener;
    }

    public interface IncomeEditDialogListener {
        public void onIncomeEditDialogSave(IncomeEditDialogFragment dialog);
        public void onIncomeEditDialogDelete(IncomeEditDialogFragment dialog);
        public void onIncomeEditDialogCancel(IncomeEditDialogFragment dialog);
    }

    public IncomeEditDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IncomeEditDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeEditDialogFragment newInstance(String param1, String param2) {
        IncomeEditDialogFragment fragment = new IncomeEditDialogFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income_edit_dialog, container, false);
        EditText txt1 = view.findViewById(R.id.txtIncomeAmount);
        txt1.setText(String.valueOf(this.amount));
        EditText txt2 = view.findViewById(R.id.txtIncomeName);
        txt2.setText(this.name);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIncomeEditDialogCancel(IncomeEditDialogFragment.this);
            }
        });

        Button btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIncomeEditDialogDelete(IncomeEditDialogFragment.this);
            }
        });

        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt1 = view.findViewById(R.id.txtIncomeName);
                EditText txt2 = view.findViewById(R.id.txtIncomeAmount);
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
                    Toast.makeText(getActivity(), "Yea right!", Toast.LENGTH_SHORT).show();
                }
                else{
                    listener.onIncomeEditDialogSave(IncomeEditDialogFragment.this);
                }
            }
        });

        return view;
    }
}
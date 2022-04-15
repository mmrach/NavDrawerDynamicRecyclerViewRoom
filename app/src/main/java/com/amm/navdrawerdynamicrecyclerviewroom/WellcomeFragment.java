package com.amm.navdrawerdynamicrecyclerviewroom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amm.navdrawerdynamicrecyclerviewroom.main.SharedViewModel;

import navdrawerdynamicrecyclerviewroom.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WellcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WellcomeFragment extends Fragment {

    // Referencia a la devault ViewMoedelFactory de la App, a usar cuando el ViewModel no recibe parámetros y se usa su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;
    // Declaramos una referencia para el ViewModel de SharedViewModel.
    private SharedViewModel sharedViewModel;

    Pair<String,Integer> selectedColor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WellcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WellcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WellcomeFragment newInstance(String param1, String param2) {
        WellcomeFragment fragment = new WellcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sin Factory, cogiendo la ViewModelFactory del objeto Application
        theAppFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        sharedViewModel = new ViewModelProvider( requireActivity(), (ViewModelProvider.Factory) theAppFactory).get(SharedViewModel.class);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wellcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel.getSelectedColor().observe(this, selectedColor->{
            getView().findViewById(R.id.miWellcome).setBackgroundColor(selectedColor.second);
        });
    }
}
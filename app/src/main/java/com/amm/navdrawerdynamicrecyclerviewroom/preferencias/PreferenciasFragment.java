package com.amm.navdrawerdynamicrecyclerviewroom.preferencias;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amm.navdrawerdynamicrecyclerviewroom.main.SharedViewModel;

import java.util.ArrayList;

import navdrawerdynamicrecyclerviewroom.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreferenciasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreferenciasFragment extends Fragment  {

    private RecyclerView rvColors;
    private ArrayList<Pair<String,Integer>> bgColors;
    private Pair<String,Integer> selectedColor;
    private Button btnIngredientes;

    // Declaramos una referencia para el ViewModel de Preferencias.
    private SharedViewModel sharedViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PreferenciasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreferenciasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreferenciasFragment newInstance(String param1, String param2) {
        PreferenciasFragment fragment = new PreferenciasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferencias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Instanciamos el button ingredientes
        btnIngredientes = (Button) getView().findViewById(R.id.btnIngredientes);
        btnIngredientes.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_preferencias_dest_to_ingredientesFragment));

        //Instanciamos el recycler view
        rvColors = (RecyclerView) getView().findViewById(R.id.rvColors);

        //Creamos un ColorRVAdapter y le pasamos el contexto (la Activity) con requireContext
        ColorRVAdapter adapter = new ColorRVAdapter(requireContext());

        //Establecemos el adapter
        rvColors.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvColors.setAdapter(adapter);

//        //Utilizando una función lambda   selectedColor -> {...}
//        sharedViewModel.getSelectedColor().observe(this, selectedColor -> {
//            TextView tvSelectedColor = view.findViewById(R.id.tvSelectedColor);
//
//            tvSelectedColor.setBackgroundColor(selectedColor.second);
//            tvSelectedColor.setText(selectedColor.first.toString());
//
//            if (selectedColor.second == Color.BLUE || selectedColor.second == Color.BLACK){
//                tvSelectedColor.setTextColor(Color.WHITE);
//            }
//            else tvSelectedColor.setTextColor(Color.BLACK);
//        });

        //Utilizando un nuevo objeto Observer y sobrecargando el método onChanged
        sharedViewModel.getSelectedColor().observe(getViewLifecycleOwner(), new Observer<Pair<String, Integer>>() {
            @Override
            public void onChanged(Pair<String, Integer> selectedColor) {
                TextView tvSelectedColor = view.findViewById(R.id.tvSelectedColor);

                tvSelectedColor.setBackgroundColor(selectedColor.second);
                tvSelectedColor.setText(selectedColor.first.toString());

                if (selectedColor.second == Color.BLUE || selectedColor.second == Color.BLACK){
                    tvSelectedColor.setTextColor(Color.WHITE);
                }
                else tvSelectedColor.setTextColor(Color.BLACK);
            }
        });


    }

}
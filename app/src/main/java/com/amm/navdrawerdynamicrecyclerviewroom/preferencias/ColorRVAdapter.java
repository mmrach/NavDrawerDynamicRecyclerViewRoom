package com.amm.navdrawerdynamicrecyclerviewroom.preferencias;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.amm.navdrawerdynamicrecyclerviewroom.main.SharedViewModel;

import java.util.ArrayList;

import navdrawerdynamicrecyclerviewroom.R;


public class ColorRVAdapter extends RecyclerView.Adapter<ColorRVAdapter.ColorViewHolder> {

    private ArrayList<Pair<String,Integer>> bgColors;

    // Referencia a la default ViewModelFactory de la App, a usar cuando el ViewModel no recibe parámetros y se usa su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;
    // Declaramos una referencia para el ViewModel de SharedViewModel.
    private SharedViewModel sharedViewModel;

    //Constructor el Adapter.
    //Necesitamos el Context para poder acceder a la Application e instanciar la default ViewModel Factory
    public ColorRVAdapter(Context context) {
        super();
        // Sin Factory, cogiendo la Factory del objeto Application
        theAppFactory = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) context.getApplicationContext());
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) context, (ViewModelProvider.Factory) theAppFactory).get(SharedViewModel.class);
        //Creamos los colores
        SetupColors();
    }

    private void SetupColors(){
        bgColors = new ArrayList<Pair<String,Integer>>();
        bgColors.add(new Pair<>("RED", Color.RED));
        bgColors.add(new Pair<>("GREEN",Color.GREEN));
        bgColors.add(new Pair<>("BLUE",Color.BLUE));
        bgColors.add(new Pair<>("YELLOW",Color.YELLOW));
        bgColors.add(new Pair<>("BLACK",Color.BLACK));
        bgColors.add(new Pair<>("MAGENTA",Color.MAGENTA));
        bgColors.add(new Pair<>("WHITE",Color.WHITE));
    }

    //Callback que se ejecuta cuando se crea el ViewHolder
    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_row_layout, parent, false);
        return new ColorViewHolder(view);
    }

    //Callback que se ejecuta cuando se vincula el ViewHolder al Adapter
    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        final Pair<String, Integer> color = bgColors.get(position);

        if (color.first.toString().equals("BLACK") || color.first.toString().equals("BLUE")){
            holder.tvColor.setTextColor(Color.WHITE);
        }
        holder.tvColor.setText(color.first.toString());
        holder.tvColor.setBackgroundColor((Integer) color.second);
    }

    @Override
    public int getItemCount() {
        return bgColors.size();
    }

    // --------- IMPLEMENTACION DE NUESTRO VIEW HOLDER ESPECÍFICO ----------------------------
    // stores and recycles views as they are scrolled off screen
    public class ColorViewHolder extends RecyclerView.ViewHolder{
        TextView tvColor;

        ColorViewHolder(View itemView) {
            super(itemView);
            tvColor = itemView.findViewById(R.id.tvColor);

            //El onItemClick lo gestionamos aquí.
            //Modificamos el sharedViewModel, no propagamos el evento onItemClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer position = getLayoutPosition();
                    Pair<String, Integer> color = bgColors.get(position);
                    sharedViewModel.setSelectedColor(color);
                }
            });
        }
    }
    // ---------------------------------------------------------------------------------------
}

package com.amm.navdrawerdynamicrecyclerviewroom.preferencias;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.amm.navdrawerdynamicrecyclerviewroom.Ingrediente;
import com.amm.navdrawerdynamicrecyclerviewroom.IngredientesViewModel;
import com.amm.navdrawerdynamicrecyclerviewroom.main.MainActivity;

import navdrawerdynamicrecyclerviewroom.R;

public class IngredientesAdapter extends ListAdapter<Ingrediente, IngredientesAdapter.IngredienteViewHolder> {
    // Referencia a la default Factory de la App, a usar cuando el ViewModel no recibe parámetros y usando su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;

    //Declaramos una referencia al ingredientesViewModel
    private IngredientesViewModel ingredientesViewModel;

    protected IngredientesAdapter(@NonNull DiffUtil.ItemCallback<Ingrediente> diffCallback, ViewModelStoreOwner owner) {
        super(diffCallback);
        //Instanciamos el ingredientesViewModel
        //  El segundo parametro que recibimos debe ser el objeto en donde se creo por primera vez el ViewModel
        //     En este ejemplo es el  IngredientesFragment porque en el se instancia por primara vez
        //  Un (ViewModelStoreOwner) es un objeto con ciclo de vida (una Activity o un Fragment).
        ingredientesViewModel = new ViewModelProvider(owner).get(IngredientesViewModel.class);
    }

    @NonNull
    @Override
    public IngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.ingrediente_row_layout, parent, false);
        return new IngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredienteViewHolder holder, int position) {
        Ingrediente ingrediente = getCurrentList().get(position);
        if (ingrediente != null){
            holder.bind(ingrediente.toString());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Seleccionado: " + ingrediente.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class IngredienteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIngredienteItem;
        private ImageButton ibDelete;

         public IngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredienteItem = itemView.findViewById(R.id.tvIngredienteItem);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingredientesViewModel.deleteIngrediente(getLayoutPosition());
                }
            });
        }

        public void bind(String string) {
             tvIngredienteItem.setText(string);
        }
    }

}


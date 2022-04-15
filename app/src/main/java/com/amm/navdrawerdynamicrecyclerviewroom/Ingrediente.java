package com.amm.navdrawerdynamicrecyclerviewroom;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredientes")
public class Ingrediente implements Comparable<Ingrediente> {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ingrediente")
    private String strIngrediente;

    public Ingrediente(@NonNull String strIngrediente) {
        this.strIngrediente = strIngrediente;
    }

    @Override
    public String toString() {
        return getStrIngrediente();
    }

    public String getStrIngrediente(){
        return strIngrediente;
    }

    @Override
    public int compareTo(Ingrediente ingrediente) {
        return strIngrediente.compareTo(ingrediente.toString());
    }

    public void setName(String strIngrediente) {
        this.strIngrediente = strIngrediente;
    }

    public static DiffUtil.ItemCallback<Ingrediente> ingredienteDiffCallback = new DiffUtil.ItemCallback<Ingrediente>() {
        @Override
        public boolean areItemsTheSame(@NonNull Ingrediente oldItem, @NonNull Ingrediente newItem) {
            return oldItem.toString().equals(newItem.toString());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ingrediente oldItem, @NonNull Ingrediente newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    };

}

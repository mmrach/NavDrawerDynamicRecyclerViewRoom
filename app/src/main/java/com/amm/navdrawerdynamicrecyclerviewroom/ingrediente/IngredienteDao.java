package com.amm.navdrawerdynamicrecyclerviewroom.ingrediente;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.amm.navdrawerdynamicrecyclerviewroom.ingrediente.Ingrediente;

import java.util.List;

@Dao
public interface IngredienteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Ingrediente ingrediente);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIngredientes(Ingrediente... ingredientes);

    @Query("DELETE FROM ingredientes")
    void deleteAll();

    //Con la Anotation @Delet podemos pasar un objeto al método que pongamos.
    //Room buscará ese objeto por clave primaria en la base de datos para eliminarlo
    @Delete
    void delete(Ingrediente ingrediente);

    //Aquí para borrar utilizamos nostoros una query con parametro
    //La función recibirá ahora un string, no un objeto, y será el que ponemos como parametro en la query
    @Query("DELETE FROM ingredientes WHERE ingrediente = :ingrediente")
    void deleteIngrediente(String ingrediente);

    @Query("SELECT * FROM ingredientes ORDER BY ingrediente ASC")
    LiveData<List<Ingrediente>> getIngredientes();
}

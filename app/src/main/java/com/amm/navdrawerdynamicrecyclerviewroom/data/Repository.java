package com.amm.navdrawerdynamicrecyclerviewroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.amm.navdrawerdynamicrecyclerviewroom.ingrediente.Ingrediente;
import com.amm.navdrawerdynamicrecyclerviewroom.ingrediente.IngredienteDao;

import java.util.List;

public class Repository {
    private IngredienteDao ingredienteDao;
    private LiveData<List<Ingrediente>> mIngredientes;

    public Repository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        ingredienteDao= db.ingredienteDao();
        mIngredientes = ingredienteDao.getIngredientes();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Ingrediente>> getAllIngredientes() {
        return mIngredientes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Ingrediente ingrediente) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            ingredienteDao.insert(ingrediente);
        });
    }

    public void insertIngredientes(String[] ingredientes) {
//        //Funciona con más de un thread en el executor porque el trabajo lo recibe uno solo
//        Ingrediente ingredientesArray[] = new Ingrediente[ingredientes.length];
//        for (int i = 0; i < ingredientes.length; i++) {
//            ingredientesArray[i]=new Ingrediente(ingredientes[i]);
//        }
//        IngredientesRoomDatabase.databaseWriteExecutor.execute(() -> {
//            ingredienteDao.insertIngredientes(ingredientesArray);
//        });
        //Con un único thread en el executor
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            for (int i = 0; i < ingredientes.length; i++) {
                ingredienteDao.insert(new Ingrediente(ingredientes[i]));
            }
        });
    }

    public void delete(Ingrediente ingrediente){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            ingredienteDao.deleteIngrediente(ingrediente.toString());
            //ingredienteDao.delete(ingrediente);
        });
    }

    public void deleteAll(){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            ingredienteDao.deleteAll();
        });
    }
}

package com.amm.navdrawerdynamicrecyclerviewroom;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

public class IngredientesViewModel extends AndroidViewModel {

    private IngredientesRepository ingredientesRepository;

    private LiveData<List<Ingrediente>> _theList;

//    //Constructor sin parametros para que podamos usar la default Application Factory.
//    public IngredientesViewModel() {
//    }

    public IngredientesViewModel(Application application) {
        super(application);
        ingredientesRepository = new IngredientesRepository(application);
        _theList = ingredientesRepository.getAllIngredientes();
    }

    public void initList(String[] arrayIngredientes) {
        ingredientesRepository.deleteAll();
        ingredientesRepository.insertIngredientes(arrayIngredientes);
//        for (int i = 0; i < arrayIngredientes.length; i++) {
//            ingredientesRepository.insert(new Ingrediente(arrayIngredientes[i]));
//        }
    }

    public LiveData<List<Ingrediente>> getIngredientes() {
        if (_theList == null) {
            _theList = new MutableLiveData<>();
        }
        return _theList;
    }

    public void deleteIngrediente(int position) {
        if (_theList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_theList.getValue());
            ingredientesRepository.delete(ingredienteList.get(position));
            //ingredienteList.remove(position);
            //_theList.setValue(ingredienteList);
        }
    }

    public void addIngrediente(Ingrediente ingrediente) {
        if (_theList.getValue() != null) {
            ingredientesRepository.insert(ingrediente);
//            List<Ingrediente> ingredienteList = new ArrayList<>(_theList.getValue());
//            ingredienteList.add(ingrediente);
//            ingredienteList.sort(Ingrediente::compareTo);
//            _theList.setValue(ingredienteList);
        }
    }

    public void updateIngrediente(Ingrediente newIngrediente, int position) {
        if (_theList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_theList.getValue());
            ingredienteList.remove(position);
            ingredienteList.add(position, newIngrediente);
            //_theList.setValue(ingredienteList);
        }
    }

    public boolean findIngredienteByName(String ingredienteName) {
        boolean retVal=false;
        if (_theList.getValue() != null) {
            for (Ingrediente ingrediente : _theList.getValue()) {
                if (ingrediente.toString().equals(ingredienteName)){
                    retVal = true;
                    break;
                }
            }
        }
        else {
            retVal=false;
        }
        return retVal;
    }
    //-------------------------------

}

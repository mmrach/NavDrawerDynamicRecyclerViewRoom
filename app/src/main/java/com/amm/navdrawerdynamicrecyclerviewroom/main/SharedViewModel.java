package com.amm.navdrawerdynamicrecyclerviewroom.main;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<Pair<String ,Integer>> _color;
    private MutableLiveData<String> _ingrediente;

    //Constructor sin parametros para que podamos usar la default Application Factory.
    public SharedViewModel() {
    }

    //-----_color set-get
    public LiveData<Pair<String, Integer>> getSelectedColor() {
        if (_color == null){
            _color = new MutableLiveData<Pair<String,Integer>>();
        }
        return _color;
    }

    public void setSelectedColor(Pair<String, Integer> selectedColor) {
        this._color.setValue(selectedColor);
    }
    //-----------------------------

}

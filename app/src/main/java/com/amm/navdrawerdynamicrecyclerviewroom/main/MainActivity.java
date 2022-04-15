package com.amm.navdrawerdynamicrecyclerviewroom.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.amm.navdrawerdynamicrecyclerviewroom.preferencias.IngredientesViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

import navdrawerdynamicrecyclerviewroom.R;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;

    private SharedViewModel sharedViewModel;
    private IngredientesViewModel ingredientesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        NavController navController = host.getNavController();

        setupActionBar(navController, appBarConfiguration);
        setupNavigationMenu(navController);
        setupSharedViewModel();
        setupIngredientesViewModel();
    }

    private void setupSharedViewModel(){
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
    }

    private void setupIngredientesViewModel(){
        ingredientesViewModel = new ViewModelProvider(this).get(IngredientesViewModel.class);
    }

    private void setupNavigationMenu(NavController navController) {
        //TODO STEP 10 - Use NavigationUI to set up a Navigation View
        // In split screen mode, you can drag this view out from the left
        // This does NOT modify the actionbar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null){
            NavigationUI.setupWithNavController(navigationView,navController);
        }
        //END STEP 10
    }

    private void setupActionBar(NavController navController, AppBarConfiguration appBarConfig) {
        drawerLayout = findViewById(R.id.drawer_layout);

        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.wellcome_dest);
        topLevelDestinations.add(R.id.guisos_dest);
        topLevelDestinations.add(R.id.fritos_dest);
        topLevelDestinations.add(R.id.pastas_dest);
        topLevelDestinations.add(R.id.sopas_dest);
        topLevelDestinations.add(R.id.postres_dest);

        if (null != drawerLayout) {
            appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations)
                    .setOpenableLayout(drawerLayout)
                    .build();
        }
        else {
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        }

        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment)))
            return true;
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), appBarConfiguration);
    }

}
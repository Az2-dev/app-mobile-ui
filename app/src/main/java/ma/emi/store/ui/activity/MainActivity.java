package ma.emi.store.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;

import ma.emi.store.R;
import ma.emi.store.helper.EnvironementPropertiesHelper;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        try {
            EnvironementPropertiesHelper.initEnvironementProperties(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.initLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public Toolbar getTopAppBar() {
        return this.topAppBar;
    }

    private void initLayout() {
        setContentView(R.layout.activity_main);
        this.topAppBar = findViewById(R.id.topAppBar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.categoryFragment,
                R.id.fournisseurFragment,
                R.id.productFragment
        ).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        //fab.setOnClickListener(view -> navController.navigate(R.id.nav_facial_auth));
    }

}
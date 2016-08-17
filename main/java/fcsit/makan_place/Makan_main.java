package fcsit.makan_place;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by elliotching on 28-Jun-16.
 */
public class Makan_main extends AppCompatActivity {

    private static String isOpen_StringKey = "isOpen";
    public Context mContext = this;
    public AppCompatActivity mAppCompatActivity = this;
    List<ITEM> dataList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Activity mActivity = this;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private CustomDrawerAdapter adapter;
//    NavigationView mNavView;
    private boolean mShowVisible = true;
    Menu mMenu;
    MenuItem mMenuItemAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeDark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makan_place_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.m_main_toolbar);
        setSupportActionBar(toolbar);
                // Initializing
        dataList = new ArrayList<>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        dataList.add(
                new ITEM( get_R_string(R.string.m_item1))
        );

        dataList.add(
                new ITEM( get_R_string(R.string.m_item2_map) )
        );

        adapter = new CustomDrawerAdapter(mContext, R.layout.custom_drawer_item,
                dataList);



//
//        mNavView = (NavigationView) findViewById(R.id.nav_view);
////        mNavView.addView();
//        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                return false;
//            }
//        });
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);

        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerListView.setAdapter(adapter);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        mDrawerListView.addHeaderView(inflater.inflate(R.id.));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
//                 onPrepareOptionsMenu(mMenu);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
//                 onPrepareOptionsMenu(mMenu);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0, dataList.size());
        }


    }


    public class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener{
        public boolean onNavigationItemSelected(MenuItem item){

            return true;
        }
    }
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
            menu.findItem(R.id.add_team).setVisible(mShowVisible);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    private String get_R_string(int R) {
        return mContext.getResources().getString(R);
    }


    public void SelectItem(int position, int listSize) {

        Fragment fragment = null;
        Bundle mArgs = new Bundle();

        if (position >= listSize) {
            position = listSize - 1;
        }


        fragment = getNewFragmentAt(position);
//        mArgs.putBoolean(isOpen_StringKey, true);
//        fragment.setArguments(mArgs);


        FragmentManager frgManager = mActivity.getFragmentManager();
        System.out.println("frgManager = "+frgManager.toString());
        System.out.println("fragment = "+fragment.toString());
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();

        invalidateOptionsMenu();
        mDrawerLayout.closeDrawer(mDrawerListView);


    }

    private Fragment getNewFragmentAt(int position){
        if(position == 0){
            setTitle(R.string.app_name);
            return new FragmentOne();
        }
        else if(position == 1){
            setTitle(R.string.m_title_activity_maps);
            return new MapsActivity();
        }
        else{
            return new FragmentOne();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    public void setTitle(int ResStringID) {
        mTitle = mContext.getResources().getString(ResStringID);
        mAppCompatActivity.getSupportActionBar().setTitle(mTitle);
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        mAppCompatActivity.getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        mMenu = menu;
//        if (FragmentOne.isOpened) mMenu.findItem(R.id.add_team).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        mMenuItemAdd = menu.findItem(R.id.add_team);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if(item == mMenuItemAdd){
            Intent intent = new Intent(mContext , Add_Food.class);
            mContext.startActivity(intent);
        }
//            return true;

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            DoTask reTask = new DoTask();
                            // PerformBackgroundTask this class is the class that extends AsynchTask
                            reTask.execute();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 100); //execute in every 100 ms
    }

    private void search_function(Menu mMenu) {
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(mMenu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position, dataList.size());

        }
    }

    private class PressHold implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            callAsynchronousTask();
            return false;
        }
    }

    private class Released implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }

    }

    private class DoTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            System.out.println("PRESSING");
            return null;
        }
    }
}


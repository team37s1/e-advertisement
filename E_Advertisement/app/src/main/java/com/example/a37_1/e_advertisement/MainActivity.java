package com.example.a37_1.e_advertisement;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends SignInActivity
implements FirstFragment.OnFragmentInteractionListener,
            SecondFragment.OnFragmentInteractionListener,
            ThirdFragment.OnFragmentInteractionListener,
            FourthFragment.OnFragmentInteractionListener{
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private FloatingActionButton fab;
    // Убедитесь, что используется версия
    // android.support.v7.app.ActionBarDrawerToggle.

    // android.support.v4.app.ActionBarDrawerToggle устарел.

    private ActionBarDrawerToggle drawerToggle;
    TableRow news1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment();
//
        // Установить Toolbar для замены ActionBar'а.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Найти наш view drawer'а
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Настроить view drawer'а
        setupDrawerContent(nvDrawer);

        // Поиск navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        // Раздуть header view во время выполнения
        View headerLayout = navigationView.getHeaderView(0);
        // Теперь, при необходимости, мы можем найти элементы внутри
        // header'а
        ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Найти наш view drawer'а

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Привязать события DrawerLayout'а к ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        //nvDrawer.setItemIconTintList(null);
       fab =  findViewById(R.id.floatingActionButton);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminPage.class));
            }
        });

    }


    private void openFragment() {
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = FirstFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Создать новый фрагмент и задать фрагмент для отображения
        // на основе нажатия на элемент навигации
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, AdminPage.class);
                startActivity(intent);
            case R.id.main:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.gaz:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.voda:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.elektruka:
                fragmentClass = FourthFragment.class;
                break;
            case R.id.perekrutia:
                fragmentClass = FifthFragment.class;
                break;
            case R.id.wtorm:
                fragmentClass = SixthFragment.class;
                break;
//            case R.id.settings:
//                Intent intent = new Intent(MainActivity.this, AdminPage.class);
//                startActivity(intent);
            case R.id.logOut:
                Intent logout = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(logout);
//                FirebaseAuth fAuth = FirebaseAuth.getInstance();
//                fAuth.signOut();
                FirebaseAuth.getInstance().signOut();
                

            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Вставить фрагмент, заменяя любой существующий
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Выделение существующего элемента выполнено с помощью
        // NavigationView
        menuItem.setChecked(true);
        // Установить заголовок для action bar'а
        setTitle(menuItem.getTitle());
        // Закрыть navigation drawer
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // Примечание: Убедитесь, что вы передаёте допустимую ссылку
        // на toolbar
        // ActionBarDrawToggle() не предусматривает в ней
        // необходимости и не будет отображать иконку гамбургера без
        // неё
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    // Есть две сигнатуры и только `onPostCreate(Bundle state)`
    // показывает иконку гамбургера.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Синхронизировать состояние переключения после того, как
        // возникнет onRestoreInstanceState
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Передать любые изменения конфигурации переключателям
        // drawer'а
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    View.OnClickListener viewContent = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(v.getContext(),DescriptionNewActivity.class);
            startActivityForResult(intent,0);

        }
    };

}
package com.example.franklinsierra.lab3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.franklinsierra.lab3.R;
import com.example.franklinsierra.lab3.adapters.FruitAdapter;
import com.example.franklinsierra.lab3.modules.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //atributos
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<Fruit> fruits;

    //atributo opcional (para la app)
    private int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lleno la lista con las frutas
        this.fruits = allFruits();
        //ubico los atriburtos
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        //creo el adaptador
        adapter = new FruitAdapter(fruits, this, R.layout.recycler_view, new FruitAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Fruit fruit, int position) {
                fruit.addQuantity(1);
                adapter.notifyItemChanged(position);
            }
        });

        //mejoras para el recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //enlazo el recyclerView con adaptador y con layoutManager
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    private List<Fruit> allFruits() {
        return new ArrayList<Fruit>() {{
            add(new Fruit("Apple", "its good for you heart", R.drawable.apple_bg, R.mipmap.apple_ic, 0));
            add(new Fruit("Banana", "its have fiber content", R.drawable.banana_bg, R.mipmap.banana_ic, 0));
            add(new Fruit("Cherry", "its have antioxidants", R.drawable.cherry_bg, R.mipmap.cherry_ic, 0));
            add(new Fruit("Orange", "its keep the blood presure", R.drawable.orange_bg, R.mipmap.orange_ic, 0));
            add(new Fruit("Pear", "its good for choresterol", R.drawable.pear_bg, R.mipmap.pear_ic, 0));
            add(new Fruit("Raspberry", "its have anticancer propieties", R.drawable.raspberry_bg, R.mipmap.raspberry_ic, 0));
            add(new Fruit("Strawberry", "its threats symptoms arthritis", R.drawable.strawberry_bg, R.mipmap.strawberry_ic, 0));
        }};
    }

    //creo el boton de add en el accion bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_add,menu);
        return true;    }

    //acciones cuando lo clickean

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                //tomo la cantidad de frutas de la lista
                int posicion=fruits.size();
                //agrego una nueva fruta en la ultima posicion
                this.fruits.add(posicion,new Fruit("Added "+(++counter)+" by user",
                        "I dont know about this",R.drawable.plum_bg,R.mipmap.plum_ic,0));
                //notifico al adaptador
                adapter.notifyItemChanged(posicion);
                //bajo hasta la posicion donde se agrego
                layoutManager.scrollToPosition(posicion);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

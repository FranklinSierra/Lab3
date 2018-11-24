package com.example.franklinsierra.lab3.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franklinsierra.lab3.R;
import com.example.franklinsierra.lab3.modules.Fruit;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    //atributos
    private List<Fruit> fruits;
    private Activity activity;
    private int layout;
    //interfaz para acceder desde el main
    private OnItemClickListener listener;

    //creo el constructor

    public FruitAdapter(List<Fruit> fruits, Activity activity, int layout, OnItemClickListener listener) {
        this.fruits = fruits;
        this.activity = activity;
        this.layout = layout;
        this.listener = listener;
    }


    //metodo para inflar la vista y crear un holder

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }


    //pasamos al holder una fruta que esta en la lista

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        holder.bin(fruits.get(position), listener);
    }

    //contamos la cantidad de frutas de la lista

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    //Aca se hace la opcion de eliminar los items

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem

            .OnMenuItemClickListener {

        //Llamo los elementos de la GUI

        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewQuantity;
        private ImageView imageViewIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            //ubico los elementos de GUI por id

            textViewName = (TextView) itemView.findViewById(R.id.textName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textDescription);
            textViewQuantity = (TextView) itemView.findViewById(R.id.textQuantity);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageIcon);

            //se anexa el context menu para eliminar items
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bin(final Fruit fruit, final OnItemClickListener listener) {

            //"lleno" los elementos de la GUI con la fruta de la linea 40

            textViewName.setText(fruit.getName());
            textViewDescription.setText(fruit.getDescription());
            textViewQuantity.setText("Cantidad: " + fruit.getQuantity() + "");

            //logica que alerta en rojo el contador "quantity" a 10
            if (fruit.getQuantity() == Fruit.maxQuantity) {

                textViewQuantity.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
                textViewQuantity.setTypeface(null, Typeface.BOLD);
            } else {
                textViewQuantity.setTextColor(ContextCompat.getColor(activity, R.color.cardview_dark_background));
                textViewQuantity.setTypeface(null, Typeface.NORMAL);
            }

            //cargo la imagen con Piccasso
            Picasso.get().load(fruit.getIcon()).fit().into(imageViewIcon);

            //hago que solo funcione el click en la imagen de la fruta
            this.imageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(fruit, getAdapterPosition());
                }
            });

        }

        //inflo la vista del pop up

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            //Se recoge a la fruta que se selecciono

            Fruit currentFruit = fruits.get(this.getAdapterPosition());
            //se establece el titulo e icono del pop up
            contextMenu.setHeaderTitle(currentFruit.getName());
            contextMenu.setHeaderIcon(currentFruit.getImgBack());

            //se infla el menu
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu, contextMenu);

            //añadimos estas caracteristicas a todos los elementos del recyclerView
            for (int i = 0; i < contextMenu.size(); i++) {
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        //Se trabaja en la diferenciacion entre eliminar o restablecer conteo

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.deleteItem:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.restartQuantity:
                    fruits.get(getAdapterPosition()).restartQuantity();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }

    }

    //interfaz para acceder desde el main
    public interface OnItemClickListener {
        void OnItemClick(Fruit fruit, int position);
    }
}

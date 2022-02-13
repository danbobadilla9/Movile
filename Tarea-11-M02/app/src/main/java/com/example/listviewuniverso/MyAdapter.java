package com.example.listviewuniverso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> names;
    private List<Integer> imagenes;
    public MyAdapter(Context context,int layout, List<String> names,List<Integer> imagenes){
        this.context = context;
        this.layout = layout;
        this.names = names;
        this.imagenes = imagenes;
    }
    //LE DICE AL ADAPTER CUANTAS VECES VAMOS A ITERAR SOBRE UNA COLECCION
    @Override
    public int getCount() {
        return this.names.size();
    }//NUMEROS DE ITEM QUE SERAN DIBUJADOS

    //OBTENER EL ITEM
    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }
    //OBTENER EL ID DEL ELEMENTO
    @Override
    public long getItemId(int id) {
        return id;
    }
    //TOMA CADA ITEM Y SE RENDERIZA
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.list_item,null);
            viewHolder =new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String currentName = this.names.get(position);
        viewHolder.nameTextView.setText(currentName);
        int id = imagenes.get(position);
        viewHolder.imageView.setImageResource(id);
        return convertView;
    }

    static class ViewHolder{
        private TextView nameTextView;
        private ImageView imageView;

    }

}

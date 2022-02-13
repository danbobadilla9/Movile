package com.example.examen_02.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.examen_02.R;
import com.example.examen_02.models.Datos;

import java.util.List;

public class DatosAdapter extends BaseAdapter {
    private Context context;
    private List<Datos> list;
    private int layout;

    public DatosAdapter(Context context, List<Datos> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Datos getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new ViewHolder();
            vh.name = (TextView) convertView.findViewById(R.id.datos_Name);
            vh.valor = (TextView) convertView.findViewById(R.id.datos_Valor);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        Datos datos = list.get(position);
        vh.name.setText(datos.getNombre());
        vh.valor.setText(datos.getValor());
        return convertView;
    }

    public class ViewHolder{
        TextView name;
        TextView valor;
    }
}

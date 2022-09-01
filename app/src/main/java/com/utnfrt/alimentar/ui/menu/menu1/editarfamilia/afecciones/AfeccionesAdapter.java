package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.afecciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.Afeccione;
import java.util.List;

public class AfeccionesAdapter extends RecyclerView.Adapter<AfeccionesAdapter.ViewHolderAfecciones> implements View.OnClickListener {

    List<Afeccione> listAfecciones;
    Context ctx;
    private View.OnClickListener listener;

    public AfeccionesAdapter(List<Afeccione> listAfecciones, Context ctx) {
        this.listAfecciones = listAfecciones;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolderAfecciones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list_afeccion,null,false);

        view.setOnClickListener(this);

        return new ViewHolderAfecciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAfecciones viewHolderAfecciones, int i) {
        viewHolderAfecciones.tvName.setText(listAfecciones.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return listAfecciones.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderAfecciones extends RecyclerView.ViewHolder{

        TextView tvName;

        public ViewHolderAfecciones(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_afeccion);
        }
    }

}


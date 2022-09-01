package com.utnfrt.alimentar.ui.menu.menu2.crearvegetal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.Hortaliza;
import java.util.List;

public class SelectVegetalAdapter extends RecyclerView.Adapter<SelectVegetalAdapter.ViewHolderAfecciones> implements View.OnClickListener {

    List<Hortaliza> listAfecciones;
    Context ctx;
    private View.OnClickListener listener;

    public SelectVegetalAdapter(List<Hortaliza> listAfecciones, Context ctx) {
        this.listAfecciones = listAfecciones;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SelectVegetalAdapter.ViewHolderAfecciones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list_vegetales,null,false);

        view.setOnClickListener(this);

        return new SelectVegetalAdapter.ViewHolderAfecciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectVegetalAdapter.ViewHolderAfecciones viewHolderAfecciones, int i) {
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

            tvName = itemView.findViewById(R.id.tv_name_vegetales);
        }
    }

}
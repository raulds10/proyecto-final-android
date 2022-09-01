package com.utnfrt.alimentar.ui.menu.menu3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.Menu;
import java.util.List;

public class Menu3Adapter extends RecyclerView.Adapter<Menu3Adapter.ViewHolderAfecciones> implements View.OnClickListener {

    List<Menu> listMenu;
    Context ctx;
    private View.OnClickListener listener;

    public Menu3Adapter(List<Menu> listMenu, Context ctx) {
        this.listMenu = listMenu;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Menu3Adapter.ViewHolderAfecciones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list_menu,null,false);

        view.setOnClickListener(this);

        return new Menu3Adapter.ViewHolderAfecciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Menu3Adapter.ViewHolderAfecciones viewHolderAfecciones, int i) {
        viewHolderAfecciones.tvName.setText(listMenu.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
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

            tvName = itemView.findViewById(R.id.tv_name_menu);
        }
    }

}

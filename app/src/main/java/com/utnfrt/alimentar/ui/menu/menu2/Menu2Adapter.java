package com.utnfrt.alimentar.ui.menu.menu2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.utnfrt.alimentar.R;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import java.util.List;

public class Menu2Adapter extends RecyclerView.Adapter<Menu2Adapter.ViewHolderMenu1> implements View.OnClickListener {

    List<HortalizaUsuarioResponse> listFamiliares;
    Context ctx;
    private View.OnClickListener listener;

    public Menu2Adapter(List<HortalizaUsuarioResponse> listFamiliares, Context ctx) {
        this.listFamiliares = listFamiliares;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolderMenu1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list_vegetales,null,false);

        view.setOnClickListener(this);

        return new ViewHolderMenu1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMenu1 viewHolderMenu1, int i) {

        viewHolderMenu1.tvName.setText(listFamiliares.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return listFamiliares.size();
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

    public class ViewHolderMenu1 extends RecyclerView.ViewHolder{

        TextView tvName;

        public ViewHolderMenu1(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_vegetales);
        }
    }

}

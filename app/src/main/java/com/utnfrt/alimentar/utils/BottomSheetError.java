package com.utnfrt.alimentar.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.utnfrt.alimentar.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomSheetError extends BottomSheetDialogFragment {

    @BindView(R.id.tv_title_bottom_sheet_error)
    TextView tvTitle;

    @BindView(R.id.tv_subtitle_bottom_sheet_error)
    TextView tvSubtitle;

    @BindView(R.id.btn_bottom_sheet_error)
    Button btnAceptar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_error,container,false);

        ButterKnife.bind(this,view);

        if (getArguments() !=null){
            tvSubtitle.setText(getResources().getString(getArguments().getInt("idMessage")));
            tvTitle.setText(getResources().getString(getArguments().getInt("idTitle")));
        }
        btnAceptar.setOnClickListener(v->dismiss());
        return view;
    }
}

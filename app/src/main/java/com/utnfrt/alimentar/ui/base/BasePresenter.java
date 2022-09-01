package com.utnfrt.alimentar.ui.base;

import com.utnfrt.alimentar.R;
import com.google.gson.JsonSyntaxException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLPeerUnverifiedException;
import retrofit2.HttpException;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    @Override
    public void setView(V view) {
        this.view = view;
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public void rxJavaUnsuscribe() {}

    @Override
    public void handleApiError(Throwable error) {
        view.finishLoading();
        if (error instanceof HttpException) {
            switch (((HttpException) error).code()) {
                case 400:
                    view.showError(R.string.error_title_bottom_sheet, R.string.error_no_refresh_token);
                    break;
                case 401:
                    view.logout();
                    break;
                case 403:
                    view.showError(R.string.error_title_bottom_sheet, R.string.error_no_autorizado);
                    break;
                case 404:
                    view.showError(R.string.error_title_bottom_sheet, R.string.error_dato_no_encontrado);
                    break;
                case 422:
                    view.showError(R.string.error_title_bottom_sheet, R.string.error_datos_mal_ingresados);
                    break;
                default:
                    view.showError(R.string.error_title_bottom_sheet, R.string.error_servidor);
                    break;
            }
        }
        else if (error instanceof SocketTimeoutException) view.showError(R.string.error_title_bottom_sheet, R.string.error_socket_timeout);
        else if (error instanceof JsonSyntaxException) view.showError(R.string.error_title_bottom_sheet, R.string.error_sintaxis_json);
        else if (error instanceof SSLPeerUnverifiedException) view.showError(R.string.error_title_bottom_sheet, R.string.error_ssl_url_no_verificada);
        else if (error instanceof ConnectException) view.showError(R.string.error_title_bottom_sheet, R.string.error_connected);
        else view.showError(R.string.error_title_bottom_sheet, R.string.no_hay_conexion);
    }
}

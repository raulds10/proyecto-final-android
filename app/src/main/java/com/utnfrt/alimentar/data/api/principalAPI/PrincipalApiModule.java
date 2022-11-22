package com.utnfrt.alimentar.data.api.principalAPI;

import com.utnfrt.alimentar.data.api.authenticator.AccessTokenAuthenticator;
import com.utnfrt.alimentar.data.api.authenticator.ApiHandler;
import com.utnfrt.alimentar.data.api.authenticator.OAuthModule;
import com.utnfrt.alimentar.data.api.authenticator.RepositoryOAuth;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OAuthModule.class)
public class PrincipalApiModule {

    //CAMBIAR DIRECCION IP POR LA QUE CORRESPONDA
    public final String BASE_URL = "http://10.0.2.2:3001/api/crud/";

    @Provides
    public OkHttpClient provideClient(RepositoryOAuth repo){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().authenticator(new AccessTokenAuthenticator(repo)).addInterceptor(interceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public PrincipalApi providePrincipalApi(@Named("provideOAuth")RepositoryOAuth repo, ApiHandler handler){
        PrincipalApi p = provideRetrofit(BASE_URL, provideClient(repo)).create(PrincipalApi.class);
        handler.setPrincipalApi(p);
        return p;
    }

}

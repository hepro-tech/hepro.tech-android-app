package Controller;

import retrofit2.Retrofit;

/**
 * Created by Jessica on 2018-02-18.
 */

public class RetrofitService {
    public static HeprotechApi buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.hepro.tech:5000/")
                .build();

        HeprotechApi service = retrofit.create(HeprotechApi.class);

        return service;
    }
}

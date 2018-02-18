package Controller;

import Model.HeprotechDevice;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jessica on 2018-02-18.
 */

public interface HeprotechApi {
    @GET("status")
    Call<HeprotechDevice> getHeprotechDevice();

    @POST("arm")
    Call<HeprotechDevice> armHeprotechDevice();

    @POST("disarm")
    Call<HeprotechDevice> disarmHeprotechDevice();

}

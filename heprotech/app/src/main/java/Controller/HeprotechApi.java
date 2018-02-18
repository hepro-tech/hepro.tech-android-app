package Controller;

import java.util.List;

import Model.HeprotechDevice;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jessica on 2018-02-18.
 */

public interface HeprotechApi {
    @GET("status")
    Call<HeprotechDevice> getHeprotechDevice();
}

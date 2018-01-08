package net.in.sat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.in.sat.model.APIResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetroRequestBuilder {

    private static Retrofit retrofit = null;

    protected IDataFetcher getDataFetcher() {

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            String BASE_URL = "https://stark-spire-93433.herokuapp.com/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(RetroRequestBuilder.IDataFetcher.class);

    }

    public static interface IDataFetcher {

        @GET("json")
        Call<APIResponse> fetchData();
    }
}
package com.funnystep.teamworktest.network;

import android.util.Log;

import com.funnystep.teamworktest.model.ProjectDetailsResponse;
import com.funnystep.teamworktest.model.ProjectsListResponse;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private static final String TAG = DataManager.class.getSimpleName();

    private static final String sToken = "april294unreal";
    private static DataManager sInstance;
    private final ProjectsService mProjectsService;

    private DataManager() {
        final String authToken = Credentials.basic(sToken, "");

        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder().header("Authorization", authToken);
                Request request = builder.build();
                Log.d(TAG, "request: " + request.url());
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProjectsService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProjectsService = retrofit.create(ProjectsService.class);
    }

    public static DataManager get() {
        if (sInstance == null)
            sInstance = new DataManager();

        return sInstance;
    }

    public void fetchProjects(final FetchProjectsCallback callback) {
        mProjectsService.getProjects().enqueue(new Callback<ProjectsListResponse>() {
            @Override
            public void onResponse(Call<ProjectsListResponse> call, retrofit2.Response<ProjectsListResponse> response) {
                if (response.body() != null)
                    callback.onProjectsFetched(response.body());
                else
                    callback.onProjectsFetchError(response.message());
            }

            @Override
            public void onFailure(Call<ProjectsListResponse> call, Throwable t) {
                callback.onProjectsFetchError(t.getLocalizedMessage());
            }
        });
    }

    public void fetchProjectDetails(int id, final FetchProjectDetailsCallback callback) {
        mProjectsService.getProject(id).enqueue(new Callback<ProjectDetailsResponse>() {
            @Override
            public void onResponse(Call<ProjectDetailsResponse> call, retrofit2.Response<ProjectDetailsResponse> response) {
                if (response.body() != null)
                    callback.onProjectDetailsFetched(response.body());
                else
                    callback.onProjectDetailsFetchError(response.message());
            }

            @Override
            public void onFailure(Call<ProjectDetailsResponse> call, Throwable t) {
                callback.onProjectDetailsFetchError(t.getLocalizedMessage());
            }
        });
    }

    public interface FetchProjectsCallback {
        void onProjectsFetched(ProjectsListResponse response);
        void onProjectsFetchError(String message);
    }

    public interface FetchProjectDetailsCallback {
        void onProjectDetailsFetched(ProjectDetailsResponse response);
        void onProjectDetailsFetchError(String message);
    }

}

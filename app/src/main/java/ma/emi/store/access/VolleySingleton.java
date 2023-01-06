package ma.emi.store.access;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ma.emi.store.aspect.TaskListner;
import ma.emi.store.client.CategoryWebServiceClient;
import ma.emi.store.helper.EnvironementPropertiesHelper;


public class VolleySingleton {

    private final static int LRU_CACHE_MAX_SIZE = 20;

    private static VolleySingleton mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;
    private final ImageLoader mImageLoader;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageCacheImpl());
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void exchangeJsonObjectRequest(
            int method,
            String url,
            JSONObject requestJSON,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {

        Log.e("HAzzaoui URL", url);
        JsonObjectRequest request = new JsonObjectRequest(
                method,
                url,
                requestJSON,
                response -> this.onResponseExchange(responseTaskListner, response),
                error -> this.onErrorExchange(errorTaskListner, error)
        );
        VolleySingleton.getInstance(this.mCtx).getRequestQueue().add(request);
    }

    public void exchangeJsonArrayRequest(
            int method,
            String url,
            JSONArray requestJSON,
            TaskListner<JSONArray> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {

        Log.e("HAzzaoui URL", url);
        JsonArrayRequest request = new JsonArrayRequest(
                method,
                url,
                requestJSON,
                response -> this.onResponseExchange(responseTaskListner, response),
                error -> this.onErrorExchange(errorTaskListner, error)
        );
        VolleySingleton.getInstance(this.mCtx).getRequestQueue().add(request);
    }

    private <RE> void onResponseExchange(TaskListner<RE> responseTaskListner, RE response) {
        Log.d("HAzzaoui RESPONSE", response.toString());
        try {
            responseTaskListner.runTask(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onErrorExchange(TaskListner<VolleyError> errorTaskListner, VolleyError error) {
        Log.e("HAzzaoui", "ERROR");
        if (error instanceof com.android.volley.ServerError) {
            com.android.volley.ServerError serverError = (com.android.volley.ServerError) error;
            Log.e("HAzzaoui", String.format("ERROR HTTP CODE : %s", serverError.networkResponse.statusCode));
            try {
                String errorBodyJson = VolleySingleton.OBJECT_MAPPER.writeValueAsString(
                        serverError.networkResponse.data
                );
                Log.e("HAzzaoui", String.format("ERROR BODY : %s", errorBodyJson));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        try {
            errorTaskListner.runTask(error);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private class ImageCacheImpl implements ImageLoader.ImageCache {

        private final LruCache<String, Bitmap> cache = new LruCache<>(LRU_CACHE_MAX_SIZE);

        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }

    }

}

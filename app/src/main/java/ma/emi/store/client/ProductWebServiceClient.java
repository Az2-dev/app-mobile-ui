package ma.emi.store.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ma.emi.store.access.VolleySingleton;
import ma.emi.store.aspect.TaskListner;
import ma.emi.store.helper.EnvironementPropertiesHelper;
import ma.emi.store.model.dto.ProductDto;

public class ProductWebServiceClient {

    private static final String PRODUCT_ENDPOINT_URI = "product";
    private static final String ADD_ENDPOINT_URI = "add";
    private static final String MODIFY_ENDPOINT_URI = "modify";
    private static final String DELETE_ENDPOINT_URI = "delete";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Context context;


    public ProductWebServiceClient(Context context) {
        this.context = context;
    }


    public void getAllProducts(
            TaskListner<JSONArray> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s", baseUrl, PRODUCT_ENDPOINT_URI);
        Log.e("HAzzaoui URL", urlWebService);
        VolleySingleton.getInstance(context).exchangeJsonArrayRequest(
                Request.Method.GET,
                urlWebService,
                null,
                responseTaskListner,
                errorTaskListner
        );
    }

    public void getProductById(
            Long productId,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s", baseUrl, PRODUCT_ENDPOINT_URI, productId);
        Log.e("HAzzaoui URL", urlWebService);
        VolleySingleton.getInstance(context).exchangeJsonObjectRequest(
                Request.Method.GET,
                urlWebService,
                null,
                responseTaskListner,
                errorTaskListner
        );
    }

    public void addProduct(
            ProductDto productRequest,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s", baseUrl, PRODUCT_ENDPOINT_URI, ADD_ENDPOINT_URI);
        Log.e("HAzzaoui URL", urlWebService);
        String requestJsonString = ProductWebServiceClient.OBJECT_MAPPER.writeValueAsString(productRequest);
        Log.d("YMahtat ARJS", requestJsonString);
        JSONObject requestJSON = new JSONObject(requestJsonString);
        VolleySingleton.getInstance(context).exchangeJsonObjectRequest(
                Request.Method.POST,
                urlWebService,
                requestJSON,
                responseTaskListner,
                errorTaskListner
        );
    }

    public void modifyProduct(
            Long productId,
            ProductDto productRequest,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s/%s", baseUrl, PRODUCT_ENDPOINT_URI, MODIFY_ENDPOINT_URI, productId);
        Log.e("HAzzaoui URL", urlWebService);
        String requestJsonString = ProductWebServiceClient.OBJECT_MAPPER.writeValueAsString(productRequest);
        Log.d("YMahtat ARJS", requestJsonString);
        JSONObject requestJSON = new JSONObject(requestJsonString);
        VolleySingleton.getInstance(context).exchangeJsonObjectRequest(
                Request.Method.PUT,
                urlWebService,
                requestJSON,
                responseTaskListner,
                errorTaskListner
        );
    }

    public void deleteProductById(
            Long productId,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s/%s", baseUrl, PRODUCT_ENDPOINT_URI, DELETE_ENDPOINT_URI, productId);
        Log.e("HAzzaoui URL", urlWebService);
        VolleySingleton.getInstance(context).exchangeJsonObjectRequest(
                Request.Method.DELETE,
                urlWebService,
                null,
                responseTaskListner,
                errorTaskListner
        );
    }

}

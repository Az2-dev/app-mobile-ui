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
import ma.emi.store.model.dto.FournisseurDto;

public class FournisseurWebServiceClient {

    private static final String FOURNISSEUR_ENDPOINT_URI = "fournisseur";
    private static final String ADD_ENDPOINT_URI = "add";
    private static final String MODIFY_ENDPOINT_URI = "modify";
    private static final String DELETE_ENDPOINT_URI = "delete";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Context context;


    public FournisseurWebServiceClient(Context context) {
        this.context = context;
    }


    public void getAllFournisseurs(
            TaskListner<JSONArray> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s", baseUrl, FOURNISSEUR_ENDPOINT_URI);
        Log.e("HAzzaoui URL", urlWebService);
        VolleySingleton.getInstance(context).exchangeJsonArrayRequest(
                Request.Method.GET,
                urlWebService,
                null,
                responseTaskListner,
                errorTaskListner
        );
    }

    public void getFournisseurById(
            Long fournisseurId,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s", baseUrl, FOURNISSEUR_ENDPOINT_URI, fournisseurId);
        Log.e("HAzzaoui URL", urlWebService);
        VolleySingleton.getInstance(context).exchangeJsonObjectRequest(
                Request.Method.GET,
                urlWebService,
                null,
                responseTaskListner,
                errorTaskListner
        );
    }

    public void addFournisseur(
            FournisseurDto fournisseurRequest,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s", baseUrl, FOURNISSEUR_ENDPOINT_URI, ADD_ENDPOINT_URI);
        Log.e("HAzzaoui URL", urlWebService);
        String requestJsonString = FournisseurWebServiceClient.OBJECT_MAPPER.writeValueAsString(fournisseurRequest);
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

    public void modifyFournisseur(
            Long fournisseurId,
            FournisseurDto fournisseurRequest,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s/%s", baseUrl, FOURNISSEUR_ENDPOINT_URI, MODIFY_ENDPOINT_URI, fournisseurId);
        Log.e("HAzzaoui URL", urlWebService);
        String requestJsonString = FournisseurWebServiceClient.OBJECT_MAPPER.writeValueAsString(fournisseurRequest);
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

    public void deleteFournisseurById(
            Long fournisseurId,
            TaskListner<JSONObject> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        final String baseUrl = EnvironementPropertiesHelper.getApiBaseUrl();
        final String urlWebService = String.format("%s/%s/%s/%s", baseUrl, FOURNISSEUR_ENDPOINT_URI, DELETE_ENDPOINT_URI, fournisseurId);
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

package ma.emi.store.service;

import android.content.Context;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;

import java.util.List;

import ma.emi.store.aspect.TaskListner;
import ma.emi.store.client.FournisseurWebServiceClient;
import ma.emi.store.mapper.FournisseurMapper;
import ma.emi.store.model.dto.FournisseurDto;

public class FournisseurService {

    private final Context context;
    private final FournisseurWebServiceClient fournisseurWebServiceClient;


    public FournisseurService(Context context) {
        this.context = context;
        this.fournisseurWebServiceClient = new FournisseurWebServiceClient(this.context);
    }


    public void getAllFournisseurs(
            TaskListner<List<FournisseurDto>> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.fournisseurWebServiceClient.getAllFournisseurs(
                jsonArray -> {
                    final List<FournisseurDto> fournisseurs = FournisseurMapper.mapJsonArrayToDtos(jsonArray);
                    responseTaskListner.runTask(fournisseurs);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void getFournisseurById(
            Long fournisseurId,
            TaskListner<FournisseurDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.fournisseurWebServiceClient.getFournisseurById(
                fournisseurId,
                jsonObject -> {
                    final FournisseurDto fournisseur = FournisseurMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(fournisseur);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void addFournisseur(
            FournisseurDto fournisseurRequest,
            TaskListner<FournisseurDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        this.fournisseurWebServiceClient.addFournisseur(
                fournisseurRequest,
                jsonObject -> {
                    final FournisseurDto fournisseur = FournisseurMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(fournisseur);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void modifyFournisseur(
            Long fournisseurId,
            FournisseurDto fournisseurRequest,
            TaskListner<FournisseurDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        this.fournisseurWebServiceClient.modifyFournisseur(
                fournisseurId,
                fournisseurRequest,
                jsonObject -> {
                    final FournisseurDto fournisseur = FournisseurMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(fournisseur);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void deleteFournisseurById(
            Long fournisseurId,
            TaskListner<Object> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.fournisseurWebServiceClient.deleteFournisseurById(
                fournisseurId,
                jsonObject -> {
                    responseTaskListner.runTask(jsonObject);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

}

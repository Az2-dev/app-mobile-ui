package ma.emi.store.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ma.emi.store.access.VolleySingleton;
import ma.emi.store.aspect.TaskListner;
import ma.emi.store.client.CategoryWebServiceClient;
import ma.emi.store.helper.EnvironementPropertiesHelper;
import ma.emi.store.mapper.CategoryMapper;
import ma.emi.store.model.dto.CategoryDto;

public class CategoryService {

    private final Context context;
    private final CategoryWebServiceClient categoryWebServiceClient;


    public CategoryService(Context context) {
        this.context = context;
        this.categoryWebServiceClient = new CategoryWebServiceClient(this.context);
    }


    public void getAllCatgories(
            TaskListner<List<CategoryDto>> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.categoryWebServiceClient.getAllCatgories(
                jsonArray -> {
                    final List<CategoryDto> categories = CategoryMapper.mapJsonArrayToDtos(jsonArray);
                    responseTaskListner.runTask(categories);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void getCategoryById(
            Long categoryId,
            TaskListner<CategoryDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.categoryWebServiceClient.getCategoryById(
                categoryId,
                jsonObject -> {
                    final CategoryDto category = CategoryMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(category);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void addCategory(
            CategoryDto categoryRequest,
            TaskListner<CategoryDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        this.categoryWebServiceClient.addCategory(
                categoryRequest,
                jsonObject -> {
                    final CategoryDto category = CategoryMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(category);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void modifyCategory(
            Long categoryId,
            CategoryDto categoryRequest,
            TaskListner<CategoryDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        this.categoryWebServiceClient.modifyCategory(
                categoryId,
                categoryRequest,
                jsonObject -> {
                    final CategoryDto category = CategoryMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(category);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void deleteCategoryById(
            Long categoryId,
            TaskListner<Object> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.categoryWebServiceClient.deleteCategoryById(
                categoryId,
                jsonObject -> {
                    responseTaskListner.runTask(jsonObject);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

}

package ma.emi.store.service;

import android.content.Context;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;

import java.util.List;

import ma.emi.store.aspect.TaskListner;
import ma.emi.store.client.ProductWebServiceClient;
import ma.emi.store.mapper.ProductMapper;
import ma.emi.store.model.dto.ProductDto;

public class ProductService {

    private final Context context;
    private final ProductWebServiceClient productWebServiceClient;


    public ProductService(Context context) {
        this.context = context;
        this.productWebServiceClient = new ProductWebServiceClient(this.context);
    }


    public void getAllProducts(
            TaskListner<List<ProductDto>> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.productWebServiceClient.getAllProducts(
                jsonArray -> {
                    final List<ProductDto> products = ProductMapper.mapJsonArrayToDtos(jsonArray);
                    responseTaskListner.runTask(products);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void getProductById(
            Long productId,
            TaskListner<ProductDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.productWebServiceClient.getProductById(
                productId,
                jsonObject -> {
                    final ProductDto product = ProductMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(product);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void addProduct(
            ProductDto productRequest,
            TaskListner<ProductDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        this.productWebServiceClient.addProduct(
                productRequest,
                jsonObject -> {
                    final ProductDto product = ProductMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(product);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void modifyProduct(
            Long productId,
            ProductDto productRequest,
            TaskListner<ProductDto> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) throws JSONException, JsonProcessingException {
        this.productWebServiceClient.modifyProduct(
                productId,
                productRequest,
                jsonObject -> {
                    final ProductDto product = ProductMapper.mapJsonObjectToDto(jsonObject);
                    responseTaskListner.runTask(product);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

    public void deleteProductById(
            Long productId,
            TaskListner<Object> responseTaskListner,
            TaskListner<VolleyError> errorTaskListner
    ) {
        this.productWebServiceClient.deleteProductById(
                productId,
                jsonObject -> {
                    responseTaskListner.runTask(jsonObject);
                },
                volleyError -> {
                    errorTaskListner.runTask(volleyError);
                }
        );
    }

}

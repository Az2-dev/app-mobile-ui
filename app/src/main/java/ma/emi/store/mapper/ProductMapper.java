package ma.emi.store.mapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ma.emi.store.model.dto.ProductDto;

public class ProductMapper {

    public static ProductDto mapJsonObjectToDto(JSONObject jsonObject) throws JSONException {
        if (Objects.nonNull(jsonObject)) {
            final ProductDto product = new ProductDto();
            product.setId(jsonObject.getLong("id"));
            product.setTitle(jsonObject.getString("title"));
            product.setDescription(jsonObject.getString("description"));
            product.setPrice(jsonObject.getDouble("price"));
            product.setImage(jsonObject.getString("image"));
            product.setCategory(CategoryMapper.mapJsonObjectToDto(jsonObject.getJSONObject("category")));
            product.setFournisseur(FournisseurMapper.mapJsonObjectToDto(jsonObject.getJSONObject("fournisseur")));
            return product;
        } else {
            return null;
        }
    }

    public static List<ProductDto> mapJsonArrayToDtos(JSONArray jsonArray) throws JSONException {
        final List<ProductDto> products = new ArrayList<>();
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                products.add(ProductMapper.mapJsonObjectToDto(jsonArray.getJSONObject(i)));
            }
        }
        return products;
    }

}

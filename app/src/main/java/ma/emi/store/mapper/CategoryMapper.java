package ma.emi.store.mapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ma.emi.store.model.dto.CategoryDto;

public class CategoryMapper {

    public static CategoryDto mapJsonObjectToDto(JSONObject jsonObject) throws JSONException {
        if (Objects.nonNull(jsonObject)) {
            final CategoryDto category = new CategoryDto();
            category.setId(jsonObject.getLong("id"));
            category.setName(jsonObject.getString("name"));
            category.setDescription(jsonObject.getString("description"));
            category.setImage(jsonObject.getString("image"));
            return category;
        } else {
            return null;
        }
    }

    public static List<CategoryDto> mapJsonArrayToDtos(JSONArray jsonArray) throws JSONException {
        final List<CategoryDto> categories = new ArrayList<>();
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                categories.add(CategoryMapper.mapJsonObjectToDto(jsonArray.getJSONObject(i)));
            }
        }
        return categories;
    }

}

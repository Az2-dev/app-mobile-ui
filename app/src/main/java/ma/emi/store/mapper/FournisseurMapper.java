package ma.emi.store.mapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ma.emi.store.model.dto.FournisseurDto;

public class FournisseurMapper {

    public static FournisseurDto mapJsonObjectToDto(JSONObject jsonObject) throws JSONException {
        if (Objects.nonNull(jsonObject)) {
            final FournisseurDto fournisseur = new FournisseurDto();
            fournisseur.setId(jsonObject.getLong("id"));
            fournisseur.setName(jsonObject.getString("name"));
            fournisseur.setMail(jsonObject.getString("mail"));
            fournisseur.setNumero(jsonObject.getString("numero"));
            return fournisseur;
        } else {
            return null;
        }
    }

    public static List<FournisseurDto> mapJsonArrayToDtos(JSONArray jsonArray) throws JSONException {
        final List<FournisseurDto> fournisseurs = new ArrayList<>();
        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                fournisseurs.add(FournisseurMapper.mapJsonObjectToDto(jsonArray.getJSONObject(i)));
            }
        }
        return fournisseurs;
    }

}

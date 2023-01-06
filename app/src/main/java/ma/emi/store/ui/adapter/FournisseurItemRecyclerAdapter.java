package ma.emi.store.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.emi.store.R;
import ma.emi.store.model.dto.FournisseurDto;

public class FournisseurItemRecyclerAdapter extends RecyclerView.Adapter<FournisseurItemRecyclerAdapter.FournisseurItemViewHolder> {

    private static final String FOURNISSEUR_LABEL_PATTERN = "FOURNISSEUR ID : %s";
    private static final String FOURNISSEUR_NAME_PATTERN = "NAME : %s";
    private static final String FOURNISSEUR_EMAIL_PATTERN = "EMAIL : %s";
    private static final String FOURNISSEUR_PHONE_PATTERN = "PHONE : %s";

    private final List<FournisseurDto> fournisseursItems;

    public FournisseurItemRecyclerAdapter(List<FournisseurDto> fournisseursItems) {
        this.fournisseursItems = fournisseursItems;
    }

    @NonNull
    @Override
    public FournisseurItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fournisseur_item_adapter, parent, false);
        return new FournisseurItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FournisseurItemViewHolder holder, int position) {
        FournisseurDto fournisseur = FournisseurItemRecyclerAdapter.this.fournisseursItems.get(position);
        holder.getFournisseurLabelTextView().setText(String.format(FOURNISSEUR_LABEL_PATTERN, fournisseur.getId()));
        holder.getFournisseurNameTextView().setText(String.format(FOURNISSEUR_NAME_PATTERN, fournisseur.getName()));
        holder.getFournisseurEmailTextView().setText(String.format(FOURNISSEUR_EMAIL_PATTERN, fournisseur.getMail()));
        holder.getFournisseurPhoneTextView().setText(String.format(FOURNISSEUR_PHONE_PATTERN, fournisseur.getNumero()));
    }

    @Override
    public int getItemCount() {
        final int fournisseursCount = FournisseurItemRecyclerAdapter.this.fournisseursItems.size();
        Log.d("HAzzaoui PROVID-COUNT", String.valueOf(fournisseursCount));
        return fournisseursCount;
    }


    public static class FournisseurItemViewHolder extends RecyclerView.ViewHolder {

        private TextView fournisseurLabelTextView;
        private TextView fournisseurNameTextView;
        private TextView fournisseurEmailTextView;
        private TextView fournisseurPhoneTextView;
        private Button fournisseurViewButton;

        public FournisseurItemViewHolder(View itemView) {
            super(itemView);
            this.fournisseurLabelTextView = itemView.findViewById(R.id.fournisseur_label);
            this.fournisseurNameTextView = itemView.findViewById(R.id.fournisseur_name);
            this.fournisseurEmailTextView = itemView.findViewById(R.id.fournisseur_email);
            this.fournisseurPhoneTextView = itemView.findViewById(R.id.fournisseur_phone);
            this.fournisseurViewButton = itemView.findViewById(R.id.fournisseur_item_view_button);
        }

        public TextView getFournisseurLabelTextView() {
            return fournisseurLabelTextView;
        }

        public TextView getFournisseurNameTextView() {
            return fournisseurNameTextView;
        }

        public TextView getFournisseurEmailTextView() {
            return fournisseurEmailTextView;
        }

        public TextView getFournisseurPhoneTextView() {
            return fournisseurPhoneTextView;
        }

        public Button getFournisseurViewButton() {
            return fournisseurViewButton;
        }

    }

}

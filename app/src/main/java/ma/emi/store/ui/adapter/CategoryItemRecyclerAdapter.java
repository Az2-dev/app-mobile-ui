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
import ma.emi.store.model.dto.CategoryDto;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

    private static final String CATEGORY_LABEL_PATTERN = "CATEGORY ID : %s";
    private static final String CATEGORY_NAME_PATTERN = "NAME : %s";
    private static final String CATEGORY_DESCRIPTION_PATTERN = "%s";

    private final List<CategoryDto> categoriesItems;

    public CategoryItemRecyclerAdapter(List<CategoryDto> categoriesItems) {
        this.categoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_adapter, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        CategoryDto category = CategoryItemRecyclerAdapter.this.categoriesItems.get(position);
        holder.getCategoryLabelTextView().setText(String.format(CATEGORY_LABEL_PATTERN, category.getId()));
        holder.getCategoryNameTextView().setText(String.format(CATEGORY_NAME_PATTERN, category.getName()));
        holder.getCategoryDescriptionTextView().setText(String.format(CATEGORY_DESCRIPTION_PATTERN, category.getDescription()));
    }

    @Override
    public int getItemCount() {
        final int categoriesCount = CategoryItemRecyclerAdapter.this.categoriesItems.size();
        Log.d("HAzzaoui CAT-COUNT", String.valueOf(categoriesCount));
        return categoriesCount;
    }


    public static class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryLabelTextView;
        private TextView categoryNameTextView;
        private TextView categoryDescriptionTextView;
        private Button categoryViewButton;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);
            this.categoryLabelTextView = itemView.findViewById(R.id.category_label);
            this.categoryNameTextView = itemView.findViewById(R.id.category_name);
            this.categoryDescriptionTextView = itemView.findViewById(R.id.category_description);
            this.categoryViewButton = itemView.findViewById(R.id.category_item_view_button);
        }

        public TextView getCategoryLabelTextView() {
            return categoryLabelTextView;
        }

        public TextView getCategoryNameTextView() {
            return categoryNameTextView;
        }

        public TextView getCategoryDescriptionTextView() {
            return categoryDescriptionTextView;
        }

        public Button getCategoryViewButton() {
            return categoryViewButton;
        }

    }

}

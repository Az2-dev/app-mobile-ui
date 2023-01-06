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
import ma.emi.store.model.dto.ProductDto;

public class ProductItemRecyclerAdapter extends RecyclerView.Adapter<ProductItemRecyclerAdapter.ProductItemViewHolder> {

    private static final String PRODUCT_LABEL_PATTERN = "PRODUCT ID : %s";
    private static final String PRODUCT_TITLE_PATTERN = "TITLE : %s";
    private static final String PRODUCT_DESCRIPTION_PATTERN = "%s";
    private static final String PRODUCT_PRICE_PATTERN = "PRICE : %s";
    private static final String PRODUCT_CATEGORY_PATTERN = "CATEGORY : %s";
    private static final String PRODUCT_FOURNISSEUR_PATTERN = "FOURNISSEUR : %s";

    private final List<ProductDto> productsItems;

    public ProductItemRecyclerAdapter(List<ProductDto> productsItems) {
        this.productsItems = productsItems;
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_adapter, parent, false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {
        ProductDto product = ProductItemRecyclerAdapter.this.productsItems.get(position);
        holder.getProductLabelTextView().setText(String.format(PRODUCT_LABEL_PATTERN, product.getId()));
        holder.getProductTitleTextView().setText(String.format(PRODUCT_TITLE_PATTERN, product.getTitle()));
        holder.getProductDescriptionTextView().setText(String.format(PRODUCT_DESCRIPTION_PATTERN, product.getDescription()));
        holder.getProductPriceTextView().setText(String.format(PRODUCT_PRICE_PATTERN, product.getPrice()));
        holder.getProductCategoryTextView().setText(String.format(PRODUCT_CATEGORY_PATTERN, product.getCategory().getName()));
        holder.getProductFourniseeurTextView().setText(String.format(PRODUCT_FOURNISSEUR_PATTERN, product.getFournisseur().getName()));
    }

    @Override
    public int getItemCount() {
        final int productsCount = ProductItemRecyclerAdapter.this.productsItems.size();
        Log.d("HAzzaoui PROD-COUNT", String.valueOf(productsCount));
        return productsCount;
    }


    public static class ProductItemViewHolder extends RecyclerView.ViewHolder {

        private TextView productLabelTextView;
        private TextView productTitleTextView;
        private TextView productDescriptionTextView;
        private TextView productPriceTextView;
        private TextView productCategoryTextView;
        private TextView productFourniseeurTextView;
        private Button productViewButton;

        public ProductItemViewHolder(View itemView) {
            super(itemView);
            this.productLabelTextView = itemView.findViewById(R.id.product_label);
            this.productTitleTextView = itemView.findViewById(R.id.product_title);
            this.productDescriptionTextView = itemView.findViewById(R.id.product_description);
            this.productPriceTextView = itemView.findViewById(R.id.product_price);
            this.productCategoryTextView = itemView.findViewById(R.id.product_category);
            this.productFourniseeurTextView = itemView.findViewById(R.id.product_fournisseur);
            this.productViewButton = itemView.findViewById(R.id.product_item_view_button);
        }

        public TextView getProductLabelTextView() {
            return productLabelTextView;
        }

        public TextView getProductTitleTextView() {
            return productTitleTextView;
        }

        public TextView getProductDescriptionTextView() {
            return productDescriptionTextView;
        }

        public TextView getProductPriceTextView() {
            return productPriceTextView;
        }

        public TextView getProductCategoryTextView() {
            return productCategoryTextView;
        }

        public TextView getProductFourniseeurTextView() {
            return productFourniseeurTextView;
        }

        public Button getProductViewButton() {
            return productViewButton;
        }

    }

}

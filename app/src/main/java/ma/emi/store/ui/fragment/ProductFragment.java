package ma.emi.store.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ma.emi.store.R;
import ma.emi.store.databinding.FragmentProductBinding;
import ma.emi.store.service.ProductService;
import ma.emi.store.ui.activity.MainActivity;
import ma.emi.store.ui.adapter.ProductItemRecyclerAdapter;


public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) this.getActivity()).getTopAppBar().setTitle(this.getString(R.string.provider_fragment_label));
        // Add the following lines to create RecyclerView
        this.binding.productRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.binding.productRecyclerView.setLayoutManager(linearLayoutManager);
        // call api and set products
        ProductService productService = new ProductService(this.getContext());
        productService.getAllProducts(
                products -> {
                    this.binding.productRecyclerView.setAdapter(new ProductItemRecyclerAdapter(products));
                },
                volleyError -> {
                    //
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
}
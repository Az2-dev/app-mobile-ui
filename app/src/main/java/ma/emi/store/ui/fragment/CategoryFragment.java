package ma.emi.store.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ma.emi.store.R;
import ma.emi.store.databinding.FragmentCategoryBinding;
import ma.emi.store.service.CategoryService;
import ma.emi.store.ui.activity.MainActivity;
import ma.emi.store.ui.adapter.CategoryItemRecyclerAdapter;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) this.getActivity()).getTopAppBar().setTitle(this.getString(R.string.category_fragment_label));
        // Add the following lines to create RecyclerView
        this.binding.categoryRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.binding.categoryRecyclerView.setLayoutManager(linearLayoutManager);
        // call api and set categories
        CategoryService categoryService = new CategoryService(this.getContext());
        categoryService.getAllCatgories(
                categories -> {
                    this.binding.categoryRecyclerView.setAdapter(new CategoryItemRecyclerAdapter(categories));
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
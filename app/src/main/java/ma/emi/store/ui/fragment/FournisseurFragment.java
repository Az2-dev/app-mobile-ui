package ma.emi.store.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import ma.emi.store.R;
import ma.emi.store.databinding.FragmentFournisseurBinding;
import ma.emi.store.service.FournisseurService;
import ma.emi.store.ui.activity.MainActivity;
import ma.emi.store.ui.adapter.FournisseurItemRecyclerAdapter;

public class FournisseurFragment extends Fragment {

    private FragmentFournisseurBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFournisseurBinding.inflate(inflater, container, false);
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
        this.binding.fournisseurRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.binding.fournisseurRecyclerView.setLayoutManager(linearLayoutManager);
        // call api and set fournisseurs
        FournisseurService fournisseurService = new FournisseurService(this.getContext());
        fournisseurService.getAllFournisseurs(
                fournisseurs -> {
                    this.binding.fournisseurRecyclerView.setAdapter(new FournisseurItemRecyclerAdapter(fournisseurs));
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
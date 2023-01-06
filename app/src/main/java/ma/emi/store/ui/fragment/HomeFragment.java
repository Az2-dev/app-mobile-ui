package ma.emi.store.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ma.emi.store.R;
import ma.emi.store.databinding.FragmentHomeBinding;
import ma.emi.store.ui.activity.MainActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) this.getActivity()).getTopAppBar().setTitle(this.getString(R.string.home_fragment_label));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
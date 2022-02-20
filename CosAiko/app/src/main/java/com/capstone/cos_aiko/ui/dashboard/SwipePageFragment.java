package com.capstone.cos_aiko.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.capstone.cos_aiko.databinding.FragmentDashboardBinding;

<<<<<<< HEAD:CosAiko/app/src/main/java/com/capstone/cos_aiko/ui/dashboard/SwipePageFragment.java
public class SwipePageFragment extends Fragment {
    ArrayList<String> s;
    ArrayAdapter arrayAdapter;
    int n=0;
=======
public class DashboardFragment extends Fragment {

>>>>>>> origin/main:CosAiko/app/src/main/java/com/capstone/cos_aiko/ui/dashboard/DashboardFragment.java
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SwipePageViewModel swipePageViewModel =
                new ViewModelProvider(this).get(SwipePageViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
<<<<<<< HEAD:CosAiko/app/src/main/java/com/capstone/cos_aiko/ui/dashboard/SwipePageFragment.java
        swipePageViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        s = new ArrayList<String>();
        s.add("ONE");
        s.add("TWO");
        s.add("Three");
        s.add("four");
        s.add("five");
        s.add("six");
        s.add("seven");

        SwipeFlingAdapterView swipeFlingAdapterView = (SwipeFlingAdapterView) root.findViewById(R.id.frame);

        arrayAdapter = new ArrayAdapter<String>(root.getContext(), R.layout.item, R.id.helloText, s);
        swipeFlingAdapterView.setAdapter(arrayAdapter);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                s.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });
=======
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
>>>>>>> origin/main:CosAiko/app/src/main/java/com/capstone/cos_aiko/ui/dashboard/DashboardFragment.java
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
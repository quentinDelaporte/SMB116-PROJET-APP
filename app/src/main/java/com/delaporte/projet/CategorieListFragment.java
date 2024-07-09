package com.delaporte.projet;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class CategorieListFragment extends Fragment implements CategorieAdapter.OnItemClickListener {

    RecyclerView recyclerView;

    public CategorieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorie_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String categoriesJson = CategorieApiService.getAll();
        Log.d("categories", categoriesJson);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Categorie>>() {}.getType();
        List<Categorie> categories = gson.fromJson(categoriesJson, listType);

        CategorieAdapter adapter = new CategorieAdapter(categories, this); // 'this' refers to the OnItemClickListener
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(int categoryId) {
        Log.d("CategorieListFragment", "Clicked category ID: " + categoryId);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        PlayFragment playFragment = PlayFragment.newInstance(categoryId);
        recyclerView.setVisibility(View.INVISIBLE);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, playFragment)
                //.detach(this)
                //.attach(playFragment)
                .addToBackStack(null)
                .commit();
    }

    public static CategorieListFragment newInstance() {
        CategorieListFragment fragment = new CategorieListFragment();
        return fragment;
    }
}

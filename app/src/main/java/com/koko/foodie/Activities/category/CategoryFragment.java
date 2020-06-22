package com.koko.foodie.Activities.category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koko.foodie.Activities.detail.DetailActivity;
import com.koko.foodie.Adapter.RecyclerViewMealByCategory;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.R;
import com.koko.foodie.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.koko.foodie.Activities.home.HomeActivity.EXTRA_DETAIL;


public class CategoryFragment extends Fragment implements CategoryView  {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
//    @BindView(R.id.imageCategory)
//    ImageView imageCategory;
//    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;
//    @BindView(R.id.textCategory)
//    TextView textCategory;

    AlertDialog.Builder descDialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
//            textCategory.setText(getArguments().getString("DESC"));
//            Picasso.get().load(getArguments().getString("IMAGE")).into(imageCategory);
//            Picasso.get().load(getArguments().getString("IMAGE")).into(imageCategoryBg);

            descDialog = new AlertDialog.Builder(getActivity()).setTitle(getArguments().getString("NAME")).setMessage(getArguments().getString("DESC"));

            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("NAME"));

        }

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        //TODO 16. set RecyclerViewMealByCategory adapter;
        RecyclerViewMealByCategory adapter = new RecyclerViewMealByCategory(getActivity(),meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        adapter.setOnItemClickListener(new RecyclerViewMealByCategory.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

//    @OnClick(R.id.cardCategory)
//    public void onClick(){
//        descDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                descDialog.show();
//            }
//        });
//    }

}
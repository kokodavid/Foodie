package com.koko.foodie.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.koko.foodie.Models.FodieA;
import com.koko.foodie.Models.Meals;
import com.koko.foodie.Models.SpoonMeals;
import com.koko.foodie.Models.TestModel;
import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TestAdapter extends PagerAdapter {

    private List<TestModelB.ExtendedIngredient> fodie;
    private List<TestModelB.AnalyzedInstruction> instructions;
    private String image;
    private Context context;

    public TestAdapter(List<TestModelB.ExtendedIngredient> fodie,List<TestModelB.AnalyzedInstruction> instructions, Context context,String image) {
        this.fodie = fodie;
        this.instructions = instructions;
        this.image = image;
        this.context = context;
    }


    @Override
    public int getCount() {
        return  fodie.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_food_detail,
                container,
                false
        );

        ImageView DetailImage = view.findViewById(R.id.detailImage);
        TextView DetailName = view.findViewById(R.id.detailName);
        TextView DetailUnit = view.findViewById(R.id.unit);
        TextView DetailAmount = view.findViewById(R.id.amount);

        String Image = fodie.get(position).getImage();
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_250x250/" + Image).into(DetailImage);


        String name = fodie.get(position).getName();
        DetailName.setText(name);

        String unit = fodie.get(position).getUnit();
        DetailUnit.setText(unit);

        Float amount = fodie.get(position).getAmount();
        DetailAmount.setText(Float.toString(amount));

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);

    }
}

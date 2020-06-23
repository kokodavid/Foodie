package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.R;

import java.util.List;

public class StepsAdapter extends PagerAdapter {
    private List<TestModelB.Step> instructions;
    private Context context;

    public StepsAdapter(List<TestModelB.Step> instructions, Context context) {
        this.instructions = instructions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return instructions.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.steps_layout_item,
                container,
                false
        );

        TextView stepNumber = view.findViewById(R.id.stepNumber);
        TextView stepInstructions = view.findViewById(R.id.stepInstructions);


        Integer number = instructions.get(position).getNumber();
        stepNumber.setText(Integer.toString(number));

        String unit = instructions.get(position).getStep();
        stepInstructions.setText(unit);

        container.addView(view,0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}

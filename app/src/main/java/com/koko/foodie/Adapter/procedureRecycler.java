package com.koko.foodie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koko.foodie.Models.TestModelB;
import com.koko.foodie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class procedureRecycler  extends RecyclerView.Adapter<procedureRecycler.RecyclerViewHolder>{
    private List<TestModelB.Step> instructions;
    private Context context;

    public procedureRecycler(List<TestModelB.Step> instructions, Context context) {
        this.instructions = instructions;
        this.context = context;
    }
    @NonNull
    @Override
    public procedureRecycler.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.steps_item,
                parent, false);
        return new procedureRecycler.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull procedureRecycler.RecyclerViewHolder holder, int position) {

        String unit = instructions.get(position).getStep();
        holder.stepInstructions.setText(unit);

        Integer number = instructions.get(position).getNumber();
       holder.stepNumber.setText("Step" + " " + Integer.toString(number) + ".");



    }

    @Override
    public int getItemCount() {
        return instructions.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.stepNumber)
        TextView stepNumber;
        @BindView(R.id.stepInstructions)
        TextView stepInstructions;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }




    }


}

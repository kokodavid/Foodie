package com.koko.foodie.Adapter;

        import android.content.Context;
        import android.content.Intent;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.koko.foodie.Activities.home.HomeActivity;
        import com.koko.foodie.Models.Food;
        import com.koko.foodie.Models.Meals;
        import com.koko.foodie.Models.SpoonMeals;
        import com.koko.foodie.R;
        import com.squareup.picasso.Picasso;

        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;

        import static android.content.ContentValues.TAG;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.RecyclerViewHolder> {

    private List<Food> food;
    private Context context;
    private static FoodAdapter.ClickListener clickListener;


    public FoodAdapter(Context context, List<Food> food) {
        this.food = food;
        this.context = context;

    }

    @NonNull
    @Override
    public FoodAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal_spoon,
                parent, false);
        return new FoodAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.RecyclerViewHolder holder, int position) {
        String foodImage = food.get(position).getImage();
        Picasso.get().load("https://spoonacular.com/recipeImages/" + foodImage)
                .centerCrop()
                .resize(600,200)
                .placeholder(R.drawable.ic_circle)
                .into(holder.mealThumb);


        String FoodName = food.get(position).getTitle();
        holder.mealName.setText(FoodName);

        Integer FoodServings = food.get(position).getServings();
        holder.servings.setText(Integer.toString(FoodServings));

        Integer id = food.get(position).getId();
        holder.id.setText(Integer.toString(id));

        Integer FoodReadyIn = food.get(position).getReadyInMinutes();
        holder.readyIn.setText(Integer.toString(FoodReadyIn));
    }

    @Override
    public int getItemCount() {
        return food.size();
    }




    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;
        @BindView(R.id.id)
        TextView id;
        @BindView(R.id.servings)
        TextView servings;
        @BindView(R.id.readyIn)
        TextView readyIn;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());


        }
    }


    public void setOnItemClickListener(FoodAdapter.ClickListener clickListener) {
        FoodAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}

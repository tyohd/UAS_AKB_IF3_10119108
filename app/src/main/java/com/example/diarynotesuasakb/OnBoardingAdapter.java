package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardongViewHolder>{

    private List<OnBoardingItem> onboardingItems;
    public OnBoardingAdapter(List<OnBoardingItem> onboardingItems){
        this.onboardingItems = onboardingItems;
    }


    @NonNull
    @Override
    public OnboardongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardongViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardongViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardongViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageOnboarding;

        public OnboardongViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);
        }

        void setOnboardingData(OnBoardingItem onboardingitem) {
            textTitle.setText(onboardingitem.getTitle());
            textDescription.setText(onboardingitem.getDescription());
            imageOnboarding.setImageResource(onboardingitem.getImage());



        }
    }
}
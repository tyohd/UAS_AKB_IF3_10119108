package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private  OnBoardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingViewIndicator);
        buttonOnboardingAction = findViewById(R.id.ButtonOnBoardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.layoutOnboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });
    }


    private void setupOnboardingItems(){

        List<OnBoardingItem> onboardingItems = new ArrayList<>();

        OnBoardingItem itemMyFood = new OnBoardingItem();
        itemMyFood.setTitle("Your Dairy Activities");
        itemMyFood.setDescription("Catat hal-hal yang menurutmu penting");
        itemMyFood.setImage(R.drawable.img1);

        OnBoardingItem itemLocation= new OnBoardingItem();
        itemLocation.setTitle("Mudah Digunakan");
        itemLocation.setDescription("Mencatat lebih mudah dengan aplikasi Your Dairy Activities");
        itemLocation.setImage(R.drawable.img2);

        OnBoardingItem itemEatTogether = new OnBoardingItem();
        itemEatTogether.setTitle("Simpan");
        itemEatTogether.setDescription("Jika kamu pelupa. Maka, aplikasi ini cocok untuk kamu. Tekan start untuk memulai");
        itemEatTogether.setImage(R.drawable.img3);

        onboardingItems.add(itemMyFood);
        onboardingItems.add(itemLocation);
        onboardingItems.add(itemEatTogether);

        onboardingAdapter = new OnBoardingAdapter(onboardingItems);

    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layouParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layouParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layouParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int childcount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childcount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }else{

                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );

            }


        }
        if(index == onboardingAdapter.getItemCount() - 1){
            buttonOnboardingAction.setText("Start");
        }else{
            buttonOnboardingAction.setText("Next");
        }
    }
}

package com.example.picword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    private ImageView imageViewDice;
    private Random rng = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        imageViewDice = findViewById(R.id.image_view_dice);
        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
                Intent intent = new Intent(getApplicationContext(), SetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void rollDice() {
        int randomNumber = rng.nextInt(6) + 1;

        switch (randomNumber) {
            case 1 : imageViewDice.setImageResource(R.drawable.dice1);
                     Toast.makeText(DiceActivity.this, "Select SET 1", Toast.LENGTH_SHORT).show();
                     break;
            case 2 : imageViewDice.setImageResource(R.drawable.dice2);
                     Toast.makeText(DiceActivity.this, "Select SET 2", Toast.LENGTH_SHORT).show();
                     break;
            case 3 : imageViewDice.setImageResource(R.drawable.dice3);
                     Toast.makeText(DiceActivity.this, "Select SET 3", Toast.LENGTH_SHORT).show();
                     break;
            case 4 : imageViewDice.setImageResource(R.drawable.dice4);
                     Toast.makeText(DiceActivity.this, "Select SET 4", Toast.LENGTH_SHORT).show();
                     break;
            case 5 : imageViewDice.setImageResource(R.drawable.dice5);
                     Toast.makeText(DiceActivity.this, "Select SET 5", Toast.LENGTH_SHORT).show();
                     break;
            case 6 : imageViewDice.setImageResource(R.drawable.dice6);
                     Toast.makeText(DiceActivity.this, "Select SET 6", Toast.LENGTH_SHORT).show();
                     break;
        }
    }
}
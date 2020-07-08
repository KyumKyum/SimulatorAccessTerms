package com.example.simulator_accessterms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondTerm extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioButton buttonYesOne, buttonNoOne, buttonYesTwo, buttonNoTwo;
    private RadioGroup group1, group2;
    private Button nextButton, cancelButton;

    private Boolean agree1, agree2;
    private Boolean buttonAvailable;

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms2);

        buttonYesOne = findViewById(R.id.button_yes_term1);
        buttonYesTwo = findViewById(R.id.button_yes_term2);
        buttonNoOne = findViewById(R.id.button_no_term1);
        buttonNoTwo = findViewById(R.id.button_no_term2);
        nextButton = findViewById(R.id.button_next);
        cancelButton = findViewById(R.id.button_cancel);
        group1 = findViewById(R.id.group_one);
        group2 = findViewById(R.id.group_two);

        nextButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        group1.setOnCheckedChangeListener(this);
        group2.setOnCheckedChangeListener(this);

        agree1 = false;
        agree2 = false;
        buttonAvailable = false;

        updateButton();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_next:
                if(!buttonAvailable){
                    if (mToast != null) mToast.cancel();
                    mToast = Toast.makeText(this, "모든 약관에 동의해야합니다!", Toast.LENGTH_SHORT);
                    mToast.show();
                } else{
                    Intent intent = new Intent(this,SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);

                    Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button_cancel:
                Intent intent = new Intent(this, FirstTerm.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.button_yes_term1:
                agree1 = true;
                break;

            case R.id.button_no_term1:
                agree1 = false;
                break;

            case R.id.button_yes_term2:
                agree2 = true;
                break;

            case R.id.button_no_term2:
                agree2 = false;
                break;
        }
        updateButton();
    }

    public void updateButton() {
        if (agree1 && agree2) {
            buttonAvailable = true;
        } else {
            buttonAvailable = false;
        }
    }
}

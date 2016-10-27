package com.thisatmind.appingpot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.thisatmind.appingpot.R;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    private ListView list;
    private Button chooseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        ArrayList<String> items = new ArrayList<>();
        items.add("여행");
        items.add("다이어트");
        items.add("스포츠");
        items.add("게임");
        items.add("IT");
        items.add("뷰티");
        items.add("맛집");
        items.add("인테리어");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);

        this.list = (ListView)findViewById(R.id.interestList);
        list.setAdapter(adapter);

        this.chooseBtn = (Button) findViewById(R.id.chooseBtn);
        this.chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplication(), MainActivity.class));
                InterestActivity.this.finish();
            }
        });
    }

}

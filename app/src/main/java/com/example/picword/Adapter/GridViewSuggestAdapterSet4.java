package com.example.picword.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.picword.Common.Common;
import com.example.picword.Set1Activity;
import com.example.picword.Set2Activity;
import com.example.picword.Set3Activity;
import com.example.picword.Set4Activity;

import java.util.List;

public class GridViewSuggestAdapterSet4 extends BaseAdapter {
    private List<String> suggestSource;
    private Context context;
    private Set4Activity set4Activity;

    public GridViewSuggestAdapterSet4(List<String> suggestScore, Context context, Set4Activity set4Activity) {
        this.suggestSource = suggestScore;
        this.context = context;
        this.set4Activity = set4Activity;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int i) {
        return suggestSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button button;
        if(view == null) {
            if(suggestSource.get(i).equals("null")) {
                button = new Button(context);
                button.setLayoutParams(new android.widget.GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(Color.DKGRAY);
            } else {
                button = new Button(context);
                button.setLayoutParams(new android.widget.GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(Color.YELLOW);
                button.setTextColor(Color.DKGRAY);
                button.setText(suggestSource.get(i));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //if correct answer contains character
                        char compare = suggestSource.get(i).charAt(0);

                        Common.user_submit_answer[set4Activity.fill] = compare;

                        if(set4Activity.fill < Common.user_submit_answer.length-1) {
                            set4Activity.fill++;
                        }

                        //update ui
                        GridView answerAdapter = new GridView(Common.user_submit_answer, context);
                        set4Activity.gridViewAnswer.setAdapter(answerAdapter);
                        answerAdapter.notifyDataSetChanged();

                        //remove from suggest source
                        set4Activity.suggestSource.set(i, "null");
                        set4Activity.suggestAdapter = new GridViewSuggestAdapterSet4(set4Activity.suggestSource, context, set4Activity);
                        set4Activity.gridViewSuggest.setAdapter(set4Activity.suggestAdapter);
                        set4Activity.suggestAdapter.notifyDataSetChanged();
                    }
                });
            }
        } else {
            button = (Button) view;
        }

        return button;
    }
}

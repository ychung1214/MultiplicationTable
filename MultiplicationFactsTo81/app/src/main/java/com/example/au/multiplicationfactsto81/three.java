package com.example.au.multiplicationfactsto81;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ClipData;
import android.content.res.TypedArray;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
/**
 * Created by AU on 2017/10/20.
 */

public class three extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    LinearLayout area1, area2;
    TextView prompt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);


        area1 = (LinearLayout) findViewById(R.id.area1);
        area2 = (LinearLayout) findViewById(R.id.area2);

        prompt = (TextView) findViewById(R.id.prompt);
        // make TextView scrollable
        prompt.setMovementMethod(new ScrollingMovementMethod());
        //clear prompt area if LongClick
        prompt.setOnLongClickListener(new OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                prompt.setText("");
                return true;
            }});

        TypedArray arrayResources = getResources().obtainTypedArray(
                R.array.three);

        for (int i = 0; i < arrayResources.length(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(arrayResources.getDrawable(i));
            imageView.setOnLongClickListener(myOnLongClickListener);
            area1.addView(imageView);
        }

        arrayResources.recycle();

        area1.setOnDragListener(myOnDragListener);
        area2.setOnDragListener(myOnDragListener);


    }

    public void onClick(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    OnLongClickListener myOnLongClickListener = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            //v.setVisibility(View.INVISIBLE);
            return true;
        }

    };

    OnDragListener myOnDragListener = new OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            String area;
            if(v == area1){
                area = "area1";
            }else if(v == area2){
                area = "area2";
            }else{
                area = "unknown";
            }

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View)event.getLocalState();
                    LinearLayout oldParent = (LinearLayout)view.getParent();
                    oldParent.removeView(view);
                    LinearLayout newParent = (LinearLayout)v;
                    newParent.addView(view);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;

        }

    };

}
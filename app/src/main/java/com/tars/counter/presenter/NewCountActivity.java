package com.tars.counter.presenter;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.tars.counter.R;
import com.tars.counter.async.AsyncCounterSave;
import com.tars.counter.async.AsyncTaskListener;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.view.NewCountViewImpl;

import static com.tars.counter.view.MainViewImpl.REVEAL_X;
import static com.tars.counter.view.MainViewImpl.REVEAL_Y;

public class NewCountActivity extends AppCompatActivity implements MVP.PresenterNewCount {

    MVP.NewCountView newCountView;

    FrameLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        newCountView = new NewCountViewImpl(this, null);
        setContentView(newCountView.getRootView());

        rootLayout = (FrameLayout) findViewById(R.id.root_layout);

        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    private void circularRevealActivity() {

        int cx = getIntent().getIntExtra(REVEAL_X, 0);
        int cy = getIntent().getIntExtra(REVEAL_Y, 0);

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
        }
        circularReveal.setDuration(650);

        // make the view visible and start the animation
        rootLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    @Override
    public void saveACounter(Counter newCounter) {
        new AsyncCounterSave(new TaskCounterSave(), newCounter, getApplicationContext()).execute();
    }

    public class TaskCounterSave implements AsyncTaskListener<Boolean>
    {
        @Override
        public void onTaskStart() {
            // TODO show a progress here?
        }

        @Override
        public void onTaskComplete(Boolean result) {
            newCountView.saveACounter(result);
        }
    }
}
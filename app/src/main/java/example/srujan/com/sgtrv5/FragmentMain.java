package example.srujan.com.sgtrv5;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by Srujan on 09-05-2016.
 */
public class FragmentMain extends Fragment {

    final static String TAG = "FragmentMain";
    TextView mainTextView;
    DiscreteSeekBar fontSizeSeekBar;
    View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initViews();
        initSeekBar();

        return rootView;
    }

    private void initViews() {
        mainTextView = (TextView) rootView.findViewById(R.id.mainTextView);
        fontSizeSeekBar = (DiscreteSeekBar) rootView.findViewById(R.id.fontSizeSeekBar);
    }

    private void initSeekBar() {
        final Handler handler = new Handler();
        final Runnable hideFontSizeSeekBar = new Runnable() {
            @Override
            public void run() {
                fontSizeSeekBar.setVisibility(View.GONE);
            }
        };

        //mainTextView on click seekBarVisibility change
        mainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fontSizeSeekBar.getVisibility() == View.VISIBLE) {
                    fontSizeSeekBar.setVisibility(View.GONE);
                }
                else {
                    fontSizeSeekBar.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.Wave)
                            .duration(500)
                            .playOn(fontSizeSeekBar);
                }
            }
        });

        //fontSizeSeekBar change listener
        fontSizeSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mainTextView.setTextSize(value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                try {
                    handler.removeCallbacks(hideFontSizeSeekBar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                try {
                    handler.postDelayed(hideFontSizeSeekBar, 400);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            handler.postDelayed(hideFontSizeSeekBar, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.activity_itemdetail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}

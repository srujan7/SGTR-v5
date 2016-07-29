package example.srujan.com.sgtrv5;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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
import com.github.clans.fab.FloatingActionButton;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by Srujan on 09-05-2016.
 */
public class FragmentMain extends Fragment {

    final static String TAG = "FragmentMain";
    TextView mainTextView;
    DiscreteSeekBar fontSizeSeekBar;
    View rootView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initViews();
        initObjects();
        setMainTextView();
        setSeekbarActions();
        setFAB();

        return rootView;
    }

    private void initViews() {
        mainTextView = (TextView) rootView.findViewById(R.id.mainTextView);
        fontSizeSeekBar = (DiscreteSeekBar) rootView.findViewById(R.id.fontSizeSeekBar);
    }

    @SuppressLint("CommitPrefEdits")
    private void initObjects(){
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();
    }

    private void setMainTextView(){
        //mainTextView on click seekBarVisibility change
        ClipboardManager manager;
        String clipboardContent;
        int fontSize;

        //Setting up fonts
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "shruti.ttf");
        mainTextView.setTypeface(tf);

        //mainTextView onclick listener
        mainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fontSizeSeekBar.getVisibility() == View.VISIBLE) {
                    fontSizeSeekBar.setVisibility(View.GONE);
                }
                else {
                    fontSizeSeekBar.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.Tada)
                            .duration(800)
                            .playOn(fontSizeSeekBar);
                }
            }
        });

        //Set text from clipboard to mainTextView
        manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardContent = manager.getPrimaryClip().getItemAt(0).getText().toString();
        mainTextView.setText(clipboardContent);

        //Set mainTextView font size
        fontSize = preferences.getInt("mainTextViewFontSize",16);
        mainTextView.setTextSize(fontSize);
    }

    private void setFAB() {
    }

    private void setSeekbarActions() {
        final Handler handler = new Handler();
        int fontSize;
        final Runnable hideFontSizeSeekBar = new Runnable() {
            @Override
            public void run() {
                fontSizeSeekBar.setVisibility(View.GONE);
            }
        };

        //Set seekbar initial progress
        fontSize = preferences.getInt("mainTextViewFontSize", (int) mainTextView.getTextSize());
        fontSizeSeekBar.setProgress(fontSize);

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
                editor.putInt("mainTextViewFontSize",seekBar.getProgress());
                editor.commit();
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

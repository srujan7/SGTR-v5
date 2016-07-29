package example.srujan.com.sgtrv5;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Srujan on 09-05-2016.
 */
public class FragmentEdit extends Fragment{

    final static String TAG = "FargmentEdit";
    EditText mainEditText;
    View rootView;

    @Nullable//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit,container,false);

        initViews();
        setEditText();

        return rootView;

    }

    private void initViews() {
        mainEditText = (EditText) rootView.findViewById(R.id.mainEditText);

    }

    private void setEditText() {
        //set font type
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"shruti.ttf");
        mainEditText.setTypeface(tf);

        //set Gujarati mapper
        mainEditText.addTextChangedListener(new GujaratiMapper(mainEditText, getContext()));
    }

}

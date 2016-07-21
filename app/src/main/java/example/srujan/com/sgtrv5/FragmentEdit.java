package example.srujan.com.sgtrv5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Srujan on 09-05-2016.
 */
public class FragmentEdit extends Fragment{

    final static String TAG = "FargmentEdit";
    View rootView;

    @Nullable//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit,container,false);
        return view;
    }

}

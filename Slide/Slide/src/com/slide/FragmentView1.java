package com.slide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentView1 extends Fragment {	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	    
	    
        View myView = inflater.inflate(R.layout.about, container, false);
        
      
        return myView;
    }
}

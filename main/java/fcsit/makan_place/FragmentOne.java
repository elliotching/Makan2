package fcsit.makan_place;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Timer;

public class FragmentOne extends Fragment {

//    public static boolean isOpened = false;
//    public static String isOpen_StringKey = "isOpen";
    Context mContext;
    Fragment mFragment;
    AppCompatActivity mActivity;
    Timer timer = new Timer();
    String mToBeDisplayMsg = "";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button mLoginButton;

    public FragmentOne() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = this.getActivity();
        mActivity = (AppCompatActivity) mContext;
        mFragment = this;

        View view = inflater.inflate(R.layout.fragment_one_log_in, container,
                false);

//        isOpened = mFragment.getArguments().getBoolean(isOpen_StringKey);
        mLoginButton = (Button) view.findViewById(R.id.m_login_button);
        mLoginButton.setTransformationMethod(null);
//        this.getArguments().getBoolean()
//        mActivity.getSupportActionBar().addOnMenuVisibilityListener();

        mActivity.supportInvalidateOptionsMenu();


        return view;
    }

    public void stopAsyncTask() {
        timer.cancel();
    }

    private class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
        }
    }

    private class DoTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }


}
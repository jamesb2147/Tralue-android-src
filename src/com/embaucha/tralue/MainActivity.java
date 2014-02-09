package com.embaucha.tralue;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	Fragment fragment, test_fragment;
	static DemoCollectionPagerAdapter pagerAdapter;
	ViewPager viewPager;
	static int sort;
	static OpenHelper open_helper;
	SQLiteDatabase rodb, rwdb;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
		System.out.println("test");
		viewPager = (ViewPager) findViewById(R.id.pager);
		//viewPager.setOffscreenPageLimit(0);
		viewPager.setAdapter(pagerAdapter);
		//frag = this.getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
		setUpDB();
	}
	
	//menu creation
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}

    //menu listener (does not have to be manually assigned
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_us: {
                Intent intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                return true;
            }
            case R.id.simple_mode: {
            	Intent intent = new Intent(this, NewMain.class);
            	startActivity(intent);
            	return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
	
	public static void updateDataset() {
		pagerAdapter.notifyDataSetChanged();
	}
	
	public void setUpDB() {
		open_helper = new OpenHelper(this);
		rwdb = open_helper.getWritableDatabase();
		rodb = open_helper.getReadableDatabase();
	}
	
	public void onDestroy() {
		rodb.close();
		rwdb.close();
		
		super.onDestroy();
	}
	
	//outdated code from a different onClick implementation
	/*
	public void launchPointValues(View v) {
		(frag).launchPointValues(v);
	}
	
	public void launchSelector(View v) {
		((TestFragment)frag).launchSelector(v);
	}
	*/
	
	public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
		
	    public DemoCollectionPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }

	    @Override
	    public Fragment getItem(int i) {
	    	System.out.println("Getting fragment number " + i);
	    	
	    	switch (i) {
	    	case 0: {
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
	    		//System.out.println("Calling new fragment.");
	    		fragment = new PointValues();
	    		break;
	    	}
	    	case 1: {
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment_selector);
	    		//fragment = new Selector();
	    		fragment = new Selector();
	    		break;
	    	}
	    	case 2: {
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
	    		fragment = new CardsActivity();
	    		break;
	    	}
	    	default: {
	    		System.out.println("Default fragment state.");
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
	    		fragment = new Selector();
	    		break;
	    	}
	    	}
	    	//demo code - copied from android docs
	    	/*
	        Fragment fragment = new DemoObjectFragment();
	        Bundle args = new Bundle();
	        // Our object is just an integer :-P
	        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
	        fragment.setArguments(args);
	        */
	        return fragment;
	    }

	    @Override
	    public int getCount() {
	        return 3;
	    }
	    
	    
	    @Override
	    public int getItemPosition(Object o) {
	    	if (o instanceof CardsActivity) {
	    		System.out.println("Card activity is being resorted...");
	    		((CardsActivity) o).resort();
	    		System.out.println("Card activity resorted.");
	    	} else if (o instanceof PointValues) {
	    		System.out.println("Point values are being saved...");
	    		((PointValues) o).saveIt();
	    		System.out.println("Point values are saved.");
	    	}
	    	
	    	return super.getItemPosition(o);
	    }
	    

	    @Override
	    public CharSequence getPageTitle(int position) {
	        switch (position) {
	        case 0: {
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
	    		return "Point values and other settings";
	    	}
	    	case 1: {
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment_selector);
	    		//fragment = new Selector();
	    		return Selector.title;
	    	}
	    	case 2: {
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
	    		return "Cards";
	    	}
	    	default: {
	    		System.out.println("Default fragment state.");
	    		//fragment = getSupportFragmentManager().findFragmentById(R.id.tralue_fragment);
	    		return "Test fragment";
	    	}
	        }
	    }
	}
}
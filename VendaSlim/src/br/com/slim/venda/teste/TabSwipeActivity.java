package br.com.slim.venda.teste;

import java.util.ArrayList;
import java.util.List;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.TextView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import br.com.slim.venda.R;

public class TabSwipeActivity  extends Activity {/*

	private ViewPager mViewPager;
	private TabsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 * Create the ViewPager and our custom adapter
		 
		mViewPager = new ViewPager(this);
		adapter = new TabsAdapter(this, mViewPager);
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(adapter);		
		
		 * We need to provide an ID for the ViewPager, otherwise we will get an
		 * exception like:
		 * 
		 * java.lang.IllegalArgumentException: No view found for id 0xffffffff
		 * for fragment TestFragment{40de5b90 #0 id=0xffffffff
		 * android:switcher:-1:0} at
		 * android.support.v4.app.FragmentManagerImpl.moveToState
		 * (FragmentManager.java:864)
		 * 
		 * The ID 0x7F04FFF0 is large enough to probably never be used for
		 * anything else
		 
		mViewPager.setId(0x7F04FFF0);

		super.onCreate(savedInstanceState);

		
		 * Set the ViewPager as the content view
		 


		setContentView(mViewPager);
	}

	*//**
	 * Add a tab with a backing Fragment to the action bar
	 * 
	 * @param titleRes
	 *            A string resource pointing to the title for the tab
	 * @param fragmentClass
	 *            The class of the Fragment to instantiate for this tab
	 * @param args
	 *            An optional Bundle to pass along to the Fragment (may be null)
	 *//*
	protected void addTab(int titleRes,
			Class<? extends Fragment> fragmentClass, Bundle args,int icon) {
		adapter.addTab(getString(titleRes), fragmentClass, args, icon);
	}

	*//**
	 * Add a tab with a backing Fragment to the action bar
	 * 
	 * @param titleRes
	 *            A string to be used as the title for the tab
	 * @param fragmentClass
	 *            The class of the Fragment to instantiate for this tab
	 * @param args
	 *            An optional Bundle to pass along to the Fragment (may be null)
	 *//*
	protected void addTab(CharSequence title,
			Class<? extends Fragment> fragmentClass, Bundle args,  int icon) {
		adapter.addTab(title, fragmentClass, args, icon);
	}

	private void setContent() {
		//setContentView(R.layout.activity_task_new);
	}


	private static class TabsAdapter extends FragmentPagerAdapter implements
	TabListener, ViewPager.OnPageChangeListener {

		private final Activity mActivity;
		private final ActionBar mActionBar;
		private final ViewPager mPager;

		*//**
		 * @param fm
		 * @param fragments
		 *//*
		public TabsAdapter(Activity activity, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			this.mActivity = activity;
			this.mActionBar = activity.getSupportActionBar();
			this.mPager = pager;

			mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}

		private static class TabInfo {
			public final Class<? extends Fragment> fragmentClass;
			public final Bundle args;

			public TabInfo(Class<? extends Fragment> fragmentClass,
					Bundle args) {
				this.fragmentClass = fragmentClass;
				this.args = args;
			}
		}

		private List<TabInfo> mTabs = new ArrayList<TabInfo>();

		public void addTab(CharSequence title,
				Class<? extends Fragment> fragmentClass, Bundle args, int icon) {
			final TabInfo tabInfo = new TabInfo(fragmentClass, args);

			TextView tv2 = new TextView(mActivity.getApplicationContext());
			tv2.setText(title);
			tv2.setTextColor(Color.WHITE);
			tv2.setTextSize(12);
			tv2.setCompoundDrawablesWithIntrinsicBounds(0, icon	, 0, 0);
			
			Tab tab = mActionBar.newTab();
			//tab.setText(title);
			tab.setTabListener(this);
			tab.setTag(tabInfo);
			//tab.setIcon(icon);
			tab.setCustomView(tv2);
			mTabs.add(tabInfo);			

			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		@SuppressWarnings("deprecation")
		@Override
		public Fragment getItem(int position) {
			final TabInfo tabInfo = mTabs.get(position);
			return (Fragment) Fragment.instantiate(mActivity,
					tabInfo.fragmentClass.getName(), tabInfo.args);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int position) {
			
			 * Select tab when user swiped
			 
			mActionBar.setSelectedNavigationItem(position);


		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			
			 * Slide to selected fragment when user selected tab
			 
			TabInfo tabInfo = (TabInfo) tab.getTag();
			for (int i = 0; i < mTabs.size(); i++) {
				if (mTabs.get(i) == tabInfo) {
					mPager.setCurrentItem(i);					
				}
			}

		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			System.out.println("***************"+((TextView)tab.getCustomView()).getText().toString());
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			tab.getCustomView().refreshDrawableState();
			System.out.println("+++++++++++++++++++"+((TextView)tab.getCustomView()).getText().toString());
		}
	}
*/}
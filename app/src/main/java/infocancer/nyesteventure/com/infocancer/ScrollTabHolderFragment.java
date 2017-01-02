package infocancer.nyesteventure.com.infocancer;

import android.support.v4.app.Fragment;

public abstract class ScrollTabHolderFragment extends Fragment implements ScrollTabHolder {

	protected ScrollTabHolder mScrollTabHolder,mScrollview;

	public void setScrollTabHolder(ScrollTabHolder scrollTabHolder) {
		mScrollTabHolder = scrollTabHolder;
	}

	public void setScollTB(ScrollTabHolder scollTB) {
		mScrollview = scollTB;
	}
}
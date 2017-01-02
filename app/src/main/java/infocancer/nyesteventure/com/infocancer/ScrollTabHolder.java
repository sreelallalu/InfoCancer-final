package infocancer.nyesteventure.com.infocancer;

import android.widget.ScrollView;

public interface ScrollTabHolder {

	void adjustScroll(int scrollHeight);

	void OnScroller(ScrollView who, int l, int t, int oldl, int oldt, int position);
	void OnScroller1(ScrollView who, int l, int t, int oldl, int oldt, int position);

}

package com.jekton.mobilelearn.course;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jekton.mobilelearn.R;
import com.jekton.mobilelearn.course.widget.AllCourseListAdapter;

import java.util.List;

/**
 * @author Jekton
 */
abstract class CourseListFragment extends Fragment
        implements AdapterView.OnItemClickListener, MainActivity.CourseListOps {

    private MainActivity mActivity;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AllCourseListAdapter mCourseListAdapter;
    private List<Course> mCourses;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    protected MainActivity getMainActivity() {
        return mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_all_courses, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        mCourseListAdapter = new AllCourseListAdapter(getActivity());
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(mCourseListAdapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity.showDialog();
        initCourseList();
    }


    @Override
    public void onCoursesChange(List<Course> courses) {
        mCourses = courses;
        mCourseListAdapter.updateCourseList(courses);
    }


    protected abstract void initCourseList();

    protected List<Course> getCourses() {
        return mCourses;
    }
}
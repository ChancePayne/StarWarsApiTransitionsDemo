package com.lambdaschool.starwarsapitransitionsdemo;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
// S04M03-11 remove all references to dummy content
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the mItem ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private SwApiObject mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            // S04M03-19 pull the object from the arguments
            mItem = (SwApiObject) getArguments().getSerializable(ARG_ITEM_ID);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            /*appBarLayout.setBackground(getContext().getDrawable(
                    DrawableResolver.getDrawableId(
                            mItem.getCategory(),
                            mItem.getId())));*/
            if (appBarLayout != null) {
                if(mItem.getCategory().equals(DrawableResolver.CHARACTER)) {
                    appBarLayout.setTitle(mItem.getName());
                } else if (mItem.getCategory().equals(DrawableResolver.STARSHIP)) {
                    appBarLayout.setTitle(((Starship) mItem).getCostInCredits());
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            // S04M03-20 set content to be our drawable
            ((ImageView) rootView.findViewById(R.id.item_image)).setImageDrawable(
                    rootView.getContext().getDrawable(
                            DrawableResolver.getDrawableId(
                                    mItem.getCategory(),
                                    mItem.getId())));

            ((TextView) rootView.findViewById(R.id.item_text)).setText(mItem.toString());
        }

        return rootView;
    }
}

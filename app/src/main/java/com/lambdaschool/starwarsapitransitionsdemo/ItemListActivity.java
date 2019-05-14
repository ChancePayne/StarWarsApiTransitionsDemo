package com.lambdaschool.starwarsapitransitionsdemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
// S04M03-6 replace all references to dummycontent with out model object
public class ItemListActivity extends AppCompatActivity {

    private static final int TYPE_PEOPLE    = 1;
    private static final int TYPE_PLANETS   = 2;
    private static final int TYPE_STARSHIPS = 3;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private ArrayList<SwApiObject>        swApiObjects;
    private SimpleItemRecyclerViewAdapter viewAdapter;

    private DrawerLayout drawerLayout;

    private int currentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        // this will assign our toolbar xml to be the system toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

//        S09M02-3 get handle to drawer layout and bind to toolbar toggle
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        swApiObjects = new ArrayList<>();

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

//        S09M02-6 create a menu item selection listener and bind it to our navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                Toast.makeText(getBaseContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                swApiObjects.clear();
                viewAdapter.notifyDataSetChanged();
                switch (menuItem.getItemId()) {
                    case R.id.nav_category_people:
                        getData(TYPE_PEOPLE);
                        break;
                    case R.id.nav_category_planets:
                        getData(TYPE_PLANETS);
                        break;
                    case R.id.nav_category_starships:
                        getData(TYPE_STARSHIPS);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    // S04M03-8 pull out fields from recyclerview construction and call our method
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        viewAdapter = new SimpleItemRecyclerViewAdapter(this, swApiObjects, mTwoPane);
        recyclerView.setAdapter(viewAdapter);
//        getData();
    }

    class PeopleGetter implements Runnable {

        int start, offset;

        public PeopleGetter(int start, int offset) {
            this.start = start;
            this.offset = offset;
        }

        @Override
        public void run() {
            Person person;
            int    counter   = start;
            int    failCount = 0;
            do {
                person = SwApiDao.getPerson(counter);
                counter += offset;

                if (person != null && currentType == TYPE_PEOPLE) {
                    swApiObjects.add(person);
//                        dbDao.createPerson(person);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewAdapter.notifyItemChanged(swApiObjects.size() - 1);
                        }
                    });
                    failCount = 0;
                } else {
//                        final List<Person> allPeople = dbDao.getAllPeople();
                    ++failCount;
                }
            } while ((person != null || failCount < 2) && currentType == TYPE_PEOPLE);
        }
    }

    // S04M03-7 write a method to retrieve all the data
    private void getData(int type) {
        currentType = type;
        switch (type) {
            case TYPE_PEOPLE:
                for(int i = 0; i < 5; ++i) {
                    new Thread(new PeopleGetter(i+1, 5)).start();
                }
                break;
            case TYPE_PLANETS:
                int counter = 1;
                SwApiDao.getPlanet(counter++, new SwApiDao.SwApiCallback() {
                    @Override
                    public void processObject(SwApiObject object) {
                        if (object != null) {
                            swApiObjects.add(object);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    viewAdapter.notifyItemChanged(swApiObjects.size() - 1);
                                }
                            });
                        }
                    }
                });
                break;
            case TYPE_STARSHIPS:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Starship starship;
                        int         counter   = 1;
                        int         failCount = 0;
                        do {
                            starship = SwApiDao.getStarship(counter++);
                            if (starship != null) {
                                swApiObjects.add(starship);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewAdapter.notifyItemChanged(swApiObjects.size() - 1);
                                    }
                                });
                                failCount = 0;
                            } else {
                                ++failCount;
                            }
                        } while (starship != null || failCount < 5);
                    }
                }).start();
                break;
        }
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     *
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
//    S09M02-7 add our menu to the toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param menuItem The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    //    S09M02-8 add logic to listener
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.menu_sort) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                swApiObjects.sort(null);
                swApiObjects.sort(new Comparator<SwApiObject>() {
                    @Override
                    public int compare(SwApiObject o1, SwApiObject o2) {
//                        return o2.getName().compareTo(o1.getName());
                        return SwApiObject.compareNames(o1, o2);
                    }
                });
                viewAdapter.notifyDataSetChanged();
            }
        }
        return true;
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity  mParentActivity;
        private final List<SwApiObject> mValues;
        private final boolean           mTwoPane;

        // S04M03-16 set the position value
        private int lastPosition = -1;

        /*private final View.OnClickListener         mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // S04M03-17 update click listener to pass our object
                SwApiObject item = (SwApiObject) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));  // put object in intent
                    arguments.putSerializable(ItemDetailFragment.ARG_ITEM_ID, item);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.item_detail_container, fragment)
                                   .commit();
                } else {
                    Context context = view.getContext();
                    Intent  intent  = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);  // put object in intent

                    // S04M03-22 add options to make transition appear
                    Bundle options = ActivityOptions.makeSceneTransitionAnimation(
                            (Activity)view.getContext(),

                                                                                 ).toBundle();

                    context.startActivity(intent, options);
                }
            }
        };*/

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<SwApiObject> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // S04M03-10 convert id to string to display
            final SwApiObject swApiObject = mValues.get(position);
            holder.mIdView.setText(String.valueOf(swApiObject.getId()));
            holder.mNameView.setText(swApiObject.getName());

//        S04M03-13 bind data to new views
            holder.mCategoryView.setText(swApiObject.getCategory());
            holder.mImageView.setImageDrawable(
                    holder.mImageView.getContext().getDrawable(
                            DrawableResolver.getDrawableId(
                                    swApiObject.getCategory(),
                                    swApiObject.getId())));

            holder.itemView.setTag(swApiObject);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // S04M03-17 update click listener to pass our object
                    SwApiObject item = (SwApiObject) view.getTag();
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));  // put object in intent
                        arguments.putSerializable(ItemDetailFragment.ARG_ITEM_ID, item);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.item_detail_container, fragment)
                                       .commit();
                    } else {
                        Context context = view.getContext();
                        Intent  intent  = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);  // put object in intent

                        // S04M03-22 add options to make transition appear
//                        Bundle options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle();
                        // S04M03-24 change constructor to allow for shared views
                        Bundle options = ActivityOptions.makeSceneTransitionAnimation(
                                (Activity) view.getContext(),
                                holder.mImageView,
                                ViewCompat.getTransitionName(holder.mImageView)
                                                                                     ).toBundle();

                        context.startActivity(intent, options);
                    }
                }
            });

            // S04M03-15 call animation method
            setEnterAnimation(holder.parentView, position);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        // S04M03-14 writing a method to set animation
        private void setEnterAnimation(View viewToAnimate, int position) {
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

        //        S04M03-12 add new views to viewholder
        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mNameView, mCategoryView;
            final ImageView mImageView;
            final View      parentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mNameView = (TextView) view.findViewById(R.id.name);
                mCategoryView = view.findViewById(R.id.category);
                mImageView = view.findViewById(R.id.image_view);
                parentView = view.findViewById(R.id.parent_view);
            }
        }
    }
}

package jordanzimmittidevelopers.com.communityservicelogger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

// UsersView Class Created By Jordan Zimmitti 1-21-17//
public class UsersView extends AppCompatActivity {

    //<editor-fold desc="Variables">

    //<editor-fold desc="Extra">

    // Define Variable UsersDatabase usersDatabase//
    private UsersDatabase usersDatabase;

    // Define Variable Vibrator Vibe//
    private Vibrator vibe;

    //</editor-fold>

    //<editor-fold desc="Widgets">

    // Define Variable ListView usersListViews//
    private ListView usersListView;

    //</editor-fold>

    //</editor-fold>

    // What Happens When Activity Starts//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Starts UI For Activity//
        setContentView(R.layout.users_view_ui);

        // Initiate InstantiateWidgets Method//
        instantiateWidgets();

        // Initiate databaseOpen Method//
        databaseOpen();

        // Initiate populateListView//
        populateListView();
    }

    // Method That Opens Database//
    private void databaseOpen() {

        // Instantiate Variable UsersDatabase usersDatabase//
        usersDatabase = new UsersDatabase(this);

        // Open Database//
        usersDatabase.open();
    }

    // Method That Instantiates Widgets//
    private void instantiateWidgets() {

        // Instantiate Variable ListView usersListView//
        usersListView = (ListView) findViewById(R.id.usersListView);

        // Instantiate Variable Vibrator vibe//
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    // What Happens When Fab Btn Is Clicked//
    public void onClickFab(View view) {

        // Vibrate For 50m//
        vibe.vibrate(50);

        // Define and Instantiate Variable Intent UsersAdd//
        Intent UsersAdd = new Intent(this, UsersAdd.class);

        // Start Activity UsersAdd//
        startActivity(UsersAdd);

        // Custom Transition//
        overridePendingTransition(R.anim.slid_in, R.anim.slid_out);
    }

    // Method To Populate ListView//
    private void populateListView() {

        // Gets All Rows Added To Database//
        final Cursor cursor = usersDatabase.getAllRows();

        // Code Runs If Android Is Running 5.0 Or Later//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // Puts Rows Stored On Database Into A String Shown//
            final String[] fromFieldNames = new String[]{UsersDatabase.KEY_NAMES, UsersDatabase.KEY_AGE, UsersDatabase.KEY_ORGANIZATION, UsersDatabase.KEY_IMAGE};

            // Takes String From Database And Sends It To Whatever Layout Widget You Want, Will Show Up In The Order String Is Made In//
            int[] toViewIDs = new int[]{R.id.usersViewName, R.id.usersViewAge, R.id.usersViewOrganization, R.id.usersViewCircleImage};

            // Creates ListView Adapter Which Allows ListView Items To Be Seen//
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.users_view_design_ui, cursor, fromFieldNames, toViewIDs, 0);

            // Sets Up Adapter Made Earlier / Shows Content From Database//
            usersListView.setAdapter(simpleCursorAdapter);
        }

    }
}

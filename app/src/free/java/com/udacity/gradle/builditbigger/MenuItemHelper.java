package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Peter on 8/31/2017.
 */

public class MenuItemHelper {
    public static boolean makeBuyData(Context context, MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_purchase_app) {
            Toast.makeText(context, R.string.buy_toast, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

}

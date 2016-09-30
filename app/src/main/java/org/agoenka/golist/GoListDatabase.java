package org.agoenka.golist;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Author: agoenka
 * Created At: 9/29/2016
 * Version: ${VERSION}
 */

@Database(name = GoListDatabase.NAME, version = GoListDatabase.VERSION)
public class GoListDatabase {
    static final String NAME = "GoListDatabase";
    static final int VERSION = 1;
}

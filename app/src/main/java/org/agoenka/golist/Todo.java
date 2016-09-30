package org.agoenka.golist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;


/**
 * Author: agoenka
 * Created At: 9/29/2016
 * Version: ${VERSION}
 */
@Table(database = GoListDatabase.class)
public class Todo extends BaseModel implements Serializable {

    @Column
    @PrimaryKey
    Date createdDateTime;

    @Column
    String description;

    @Column
    Date dueDate;

    @Column
    int priorityCode;

    Priority priority;
}
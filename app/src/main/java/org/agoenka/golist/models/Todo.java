package org.agoenka.golist.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.agoenka.golist.GoListDatabase;

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
    public Date createdDateTime;

    @Column
    public String description;

    @Column
    public Date dueDate;

    @Column
    public int priorityCode;

    public Priority priority;
}
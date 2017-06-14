package com.androidbook.fragments.dialogs;

/*
 * An interface implemented typically by an activity
 * so that a dialog can report back
 * on what happened.
 */
interface OnDialogDoneListener {
    void onDialogDone(String tag, boolean cancelled, CharSequence message);
}

package yb.rssfeedaggregator.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import yb.rssfeedaggregator.R;

public class LoginDialog extends DialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnShowListener {

    private AlertDialog mAlertDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customContent;
        int content = R.layout.login_dialog_custom_content;
        customContent = inflate(content);
        return createDialogFromView(customContent);
    }

    private Dialog createDialogFromView(View content) {
        int button = R.string.login;
        int hSpacing = 0;
        int vSpacing = 0;
        mAlertDialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton(button, this)
                .setView(content, hSpacing, vSpacing, hSpacing, vSpacing)
                .create();
        mAlertDialog.setOnShowListener(this);
        return mAlertDialog;
    }

    private View inflate(int layout) {
        return getActivity().getLayoutInflater().inflate(layout, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.colorPrimaryLight);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onShow(DialogInterface dialog) {
        Button positiveButton = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
    }
}

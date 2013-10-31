package net.animetick.animetick_android.component.newticket;

import android.widget.TextView;

import net.animetick.animetick_android.R;
import net.animetick.animetick_android.component.Button;
import net.animetick.animetick_android.component.OnClickEvent;
import net.animetick.animetick_android.component.TransitionData;

/**
 * Created by kazz on 2013/09/27.
 */
public class WatchButton extends Button {

    public WatchButton(TextView view, WatchMenuComponent component, OnClickEvent event) {
        super(view, component, event, new TransitionData(TransitionData.NULL, 0,
                                                         R.drawable.trans_watch_to_confirm, 150));
        view.setText("Watch");
        view.setBackgroundResource(R.drawable.watch_button);
    }

}
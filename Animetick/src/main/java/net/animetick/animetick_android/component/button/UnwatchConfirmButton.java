package net.animetick.animetick_android.component.button;

import android.widget.TextView;

import net.animetick.animetick_android.R;
import net.animetick.animetick_android.component.Button;
import net.animetick.animetick_android.component.MenuComponent;
import net.animetick.animetick_android.component.OnClickEvent;
import net.animetick.animetick_android.component.TransitionData;

/**
 * Created by kazz on 2013/09/27.
 */
public class UnwatchConfirmButton extends Button {

    public UnwatchConfirmButton(TextView view, MenuComponent component, OnClickEvent event) {
        super(view, component, event, new TransitionData(R.drawable.trans_confirm_to_unwatch, DURATION_TIME,
                                                         R.drawable.trans_confirm_to_watch, DURATION_TIME));
        view.setText("Unwatch?");
    }

}

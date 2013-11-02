package net.animetick.animetick_android.model.ticket;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.animetick.animetick_android.R;
import net.animetick.animetick_android.component.ticket.TicketMenuComponent;
import net.animetick.animetick_android.model.EpisodeAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kazz on 2013/08/11.
 */
public class TicketAdapter extends EpisodeAdapter<Ticket> {

    public TicketAdapter(Context context) {
        super(context);
        resourceId = R.layout.ticket;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        if (convertView == null) {
            return null;
        }
        Ticket ticket = getItem(position);
        setChannel(convertView, ticket);
        setStartAt(convertView, ticket);
        setWatchButton(convertView, ticket);
        return convertView;
    }

    private void setChannel(View convertView, Ticket ticket) {
        TextView channel = (TextView) convertView.findViewById(R.id.ticket_channel);
        String ticketChannel = ticket.getChName();
        if (ticketChannel != null) {
            int ticketChannelNum = ticket.getChNum();
            if (ticketChannelNum > 0) {
                ticketChannel += " / " + String.valueOf(ticketChannelNum) + "ch";
            }
            channel.setText(ticketChannel);
        }
    }

    private void setStartAt(View convertView, Ticket ticket) {
        TextView startAt = (TextView) convertView.findViewById(R.id.ticket_start_at);
        Date ticketStartAt = ticket.getStartAt();
        if (ticketStartAt != null) {
            DateFormat df = new SimpleDateFormat("MM/dd HH:mm ~");
            String startAtStr = df.format(ticketStartAt);
            String relativeDay = getRelativeDay(ticketStartAt);
            if (relativeDay != null) {
                startAtStr = relativeDay + " " + startAtStr;
            }
            startAt.setText(startAtStr);
        }
    }

    public String getRelativeDay(Date startAt) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar today = shiftDate(now);
        Calendar yesterday = (Calendar) today.clone();
        yesterday.add(Calendar.DATE, -1);
        Calendar tomorrow = (Calendar) today.clone();
        tomorrow.add(Calendar.DATE, 1);
        Calendar startAtCal = Calendar.getInstance();
        startAtCal.setTime(startAt);
        startAtCal = shiftDate(startAtCal);
        if (yesterday.equals(startAtCal)) {
            return "昨晩";
        } else if (today.equals(startAtCal)) {
            return "今晩";
        } else if (tomorrow.equals(startAtCal)) {
            return "翌晩";
        }
        return null;
    }

    private Calendar shiftDate(Calendar original) {
        Calendar calendar = (Calendar) original.clone();
        calendar.add(Calendar.HOUR, -5);
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    @Override
    protected void setWatchButton(View convertView, final Ticket ticket) {
        TextView watchButton = (TextView) convertView.findViewById(R.id.ticket_watch_button);
        ImageView tweetButton = (ImageView) convertView.findViewById(R.id.ticket_tweet_button);
        List<View> panelViewList = TicketMenuComponent.createPanelViewList(tweetButton);
        new TicketMenuComponent(watchButton, panelViewList, ticket, density, menuManager);
    }

}

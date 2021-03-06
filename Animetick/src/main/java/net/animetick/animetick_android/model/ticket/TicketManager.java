package net.animetick.animetick_android.model.ticket;

import android.view.View;
import android.widget.ListView;

import net.animetick.animetick_android.model.Authentication;
import net.animetick.animetick_android.model.EpisodeAdapter;
import net.animetick.animetick_android.model.EpisodeManager;
import net.animetick.animetick_android.model.EpisodeResult;
import net.animetick.animetick_android.model.TicketHash;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazz on 2013/09/13.
 */
public class TicketManager extends EpisodeManager<Ticket> {

    public TicketManager(EpisodeAdapter<Ticket> adapter, Authentication authentication,
                         ListView listView, View footerView) {
        super(adapter, authentication, listView, footerView);
    }

    @Override
    protected EpisodeResult<Ticket> createAnimeEpisodeList(InputStream is) throws IOException {
        TicketListFactory ticketListFactory = new TicketListFactory();
        return ticketListFactory.createTicketList(is);
    }

    @Override
    protected String getRequestPath() {
        int watchedNum = TicketHash.getInstance().getWatchedNum();
        int offset = page * 30 - watchedNum;
        return "/ticket/list.json?offset=" + offset;
    }

    @Override
    protected List<Ticket> getUniqueEpisodes(List<Ticket> episodeList) {
        ArrayList<Ticket> resultEpisodeList = new ArrayList<Ticket>();
        for (Ticket episode : episodeList) {
            ArrayList<Integer> key = new ArrayList<Integer>();
            key.add(episode.getTitleId());
            key.add(episode.getCount());

            Ticket existEpisode = TicketHash.getInstance().getHash().putIfAbsent(key, episode);
            if (existEpisode == null) {
                resultEpisodeList.add(episode);
            }
        }
        return resultEpisodeList;
    }

    @Override
    protected void reset() {
        super.reset();
        TicketHash.getInstance().reset();
    }

}

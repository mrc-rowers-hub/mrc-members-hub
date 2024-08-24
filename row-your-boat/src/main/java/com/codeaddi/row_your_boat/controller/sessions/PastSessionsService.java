package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.controller.util.DateUtil;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PastSessionsService {

    public static List<String> getUpcomingSessionDates(List<PastSession> sessions){
        List<String> formattedDates = new ArrayList<>();
        for(Date date : sessions.stream().map(PastSession::getDate).toList() ){
            formattedDates.add(DateUtil.formatDate(date));
        }
        return formattedDates;
    }
}

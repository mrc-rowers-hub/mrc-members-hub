package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionsService {

    public static List<RowingSessions> mapRowingSessionToSessions(List<RowingSession> rowingSessionList){
        Map<RowingSessionGrouper.RowingSessionKey, List<RowingSession>> sessionsWithSameTimes = RowingSessionGrouper.groupSessions(rowingSessionList);
        List<RowingSessions> rowingSessionsToReturn = new ArrayList<>();

        for (RowingSessionGrouper.RowingSessionKey key : sessionsWithSameTimes.keySet()) {
            List<Squad> squads = new ArrayList<>();
            List<RowerLevel> rowerLevels = new ArrayList<>();

            for(RowingSession rowingSession : sessionsWithSameTimes.get(key) ){
                if(!squads.contains(rowingSession.getSquad())){
                    squads.add(rowingSession.getSquad());

                }
                if(!rowerLevels.contains(rowingSession.getLevel())){
                    rowerLevels.add(rowingSession.getLevel());

                }
            }

           rowingSessionsToReturn.add(RowingSessions.builder().day(key.getDay()).sessionType(key.getSessionType()).startTime(key.getStartTime()).endTime(key.getEndTime()).squads(squads).levels(rowerLevels).build());
        }
        return rowingSessionsToReturn;
    }

//    Todo - order the sessions Monday -> Friday now

}

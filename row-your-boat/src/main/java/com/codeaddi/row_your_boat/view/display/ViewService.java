package com.codeaddi.row_your_boat.view.display;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.controller.http.SchedulerClient;
import com.codeaddi.row_your_boat.controller.sessions.AvailabilityService;
import com.codeaddi.row_your_boat.controller.sessions.SessionsService;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailability;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ViewService {
    // Todo remove the availability group key bits

    @Autowired
    private SchedulerClient schedulerClient;
    @Autowired
    private AvailabilityClient availabilityClient;

    public List<RowingSession> getAllSessions() {
        List<RowingSession> sessionsToReturn = schedulerClient.getAllSessions();

        sessionsToReturn.sort(Comparator.comparing(RowingSession::getStartTime));

        return sessionsToReturn;
    }

    public Map<Squad, List<RowingSessions>> getAllStandardSessionsToDisplay() {
        List<RowingSession> rowingSessions = schedulerClient.getAllSessions();
        List<RowingSessions> groupedSessions =
                SessionsService.mapRowingSessionToSessions(rowingSessions);
        Map<Squad, List<RowingSessions>> standardSessions =
                SessionsService.getRowingSessionsPerSquad(groupedSessions);

        standardSessions.forEach(
                (key, value) -> value.sort(Comparator.comparing(RowingSessions::getStartTime)));

        standardSessions.forEach(
                (key, value) ->
                        value.sort(Comparator.comparing(rowingSession -> rowingSession.getDay().ordinal())));

        return standardSessions;
    }

    public Long getMaxId() {
        List<RowingSession> rowingSessions = schedulerClient.getAllSessions();

        return rowingSessions.stream()
                .max(Comparator.comparingLong(RowingSession::getId))
                .map(RowingSession::getId)
                .orElse(0L);
    }

    public Map<Squad, List<UpcomingAvailabilityDTO>> getAvailabilitySessions() {
        List<UpcomingAvailabilityDTO> upcomingSessions = availabilityClient.getAllSessions();

        List<UpcomingAvailabilityDTO> sessionsWithDays =
                AvailabilityService.addWeekday(upcomingSessions);

        Map<Squad, List<UpcomingAvailabilityDTO>> toReturn =
                AvailabilityService.mapUpcomingSessionsToSquads(sessionsWithDays);

        toReturn.forEach(
                (key, value) -> value.sort(Comparator.comparing(UpcomingAvailabilityDTO::getDate)));

        return toReturn;
    }

    // Todo should this live in viewService?
    public Map<Squad, List<UpcomingAvailabilityDTO>> addAvailabilityForThisUser(Long rowerId, Squad rowerSquad, Map<Squad, List<UpcomingAvailabilityDTO>> allUpcomingSessions) {
        List<UpcomingSessionAvailability> rowersUpcomingAvailability = availabilityClient.getUpcomingAvailabilityForRower(rowerId);
        List<Long> rowersAvailableSessions = rowersUpcomingAvailability.stream().map(UpcomingSessionAvailability::getUpcomingSessionId)
                .toList();

        List<UpcomingAvailabilityDTO> upcomingSessionsForThisSquad = allUpcomingSessions.get(rowerSquad);
        List<UpcomingAvailabilityDTO> upcomingSessionsForThisSquadWithAvailability = new ArrayList<>();

        for(UpcomingAvailabilityDTO upcomingAvailabilityDTO : upcomingSessionsForThisSquad){
          if(rowersAvailableSessions.contains(upcomingAvailabilityDTO.getUpcomingSessionId())){
            upcomingAvailabilityDTO.setRowerIsAvailable(true);
          }

          upcomingSessionsForThisSquadWithAvailability.add(upcomingAvailabilityDTO);

        }

        allUpcomingSessions.replace(rowerSquad, upcomingSessionsForThisSquadWithAvailability);

        return allUpcomingSessions;
    }
}

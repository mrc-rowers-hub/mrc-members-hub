package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.availability.AvailabilityGroup;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AvailabilityService {

    public static List<AvailabilityGroup> mapToUpcomingAvailabilityGroups(Map<UpcomingSessionsGrouper.UpcomingSessionKey, List<UpcomingAvailabilityDTO>> upcomingSessionKeyListMap){
        List<AvailabilityGroup> availabilityGroups = new ArrayList<>();

        for(UpcomingSessionsGrouper.UpcomingSessionKey upcomingSessionKey: upcomingSessionKeyListMap.keySet()){
            List<UpcomingAvailabilityDTO> upcomingSessions = upcomingSessionKeyListMap.get(upcomingSessionKey);

            List<RowerLevel> rowerLevels = upcomingSessionKeyListMap.get(upcomingSessionKey)
                    .stream()
                    .map(UpcomingAvailabilityDTO::getLevel)
                    .distinct().toList();

            List<Long> upcomingSessionIds = upcomingSessionKeyListMap.get(upcomingSessionKey)
                    .stream()
                    .map(UpcomingAvailabilityDTO::getUpcomingSessionId)
                    .toList();

            AvailabilityGroup availabilityGroup = AvailabilityGroup.builder()
                    .date(upcomingSessionKey.getDate())
                    .startTime(upcomingSessionKey.getStartTime())
                    .endTime(upcomingSessionKey.getEndTime())
                    .sessionType(upcomingSessionKey.getSessionType())
                    .squad(upcomingSessionKey.getSquad())
                    .levels(rowerLevels).upcomingSessionIds(upcomingSessionIds).build();
            availabilityGroups.add(availabilityGroup);
        }

        return availabilityGroups;
    }

    public static Map<Squad, List<AvailabilityGroup>> mapAvailabilityGroupsToSquads(Map<UpcomingSessionsGrouper.UpcomingSessionKey,
            List<UpcomingAvailabilityDTO>> upcomingSessionKeyListMap){

            List<AvailabilityGroup> availabilityGroups = mapToUpcomingAvailabilityGroups(upcomingSessionKeyListMap);



            // Use Stream API to group by Squad
            return availabilityGroups.stream()
                    .collect(Collectors.groupingBy(AvailabilityGroup::getSquad));
        }
}

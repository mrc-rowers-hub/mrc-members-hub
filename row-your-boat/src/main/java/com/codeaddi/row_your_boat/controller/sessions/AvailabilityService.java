package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.availability.AvailabilityGroup;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AvailabilityService {

    public static List<AvailabilityGroup> mapToUpcomingAvailabilityGroups(Map<UpcomingSessionsGrouper.UpcomingSessionKey, List<UpcomingAvailabilityDTO>> upcomingSessionKeyListMap){
        List<AvailabilityGroup> availabilityGroups = new ArrayList<>();

        for(UpcomingSessionsGrouper.UpcomingSessionKey upcomingSessionKey: upcomingSessionKeyListMap.keySet()){
            List<UpcomingAvailabilityDTO> upcomingSessions = upcomingSessionKeyListMap.get(upcomingSessionKey);

            List<RowerLevel> rowerLevels = upcomingSessionKeyListMap.get(upcomingSessionKey)
                    .stream()
                    .map(UpcomingAvailabilityDTO::getLevel)
                    .toList();

            List<Long> upcomingSessionIds = upcomingSessionKeyListMap.get(upcomingSessionKey)
                    .stream()
                    .map(UpcomingAvailabilityDTO::getUpcomingSessionId)
                    .toList();

            AvailabilityGroup availabilityGroup = AvailabilityGroup.builder().upcomingSessionKey(upcomingSessionKey).levels(rowerLevels).upcomingSessionIds(upcomingSessionIds).build();
            availabilityGroups.add(availabilityGroup);
        }

        return availabilityGroups;
    }
}

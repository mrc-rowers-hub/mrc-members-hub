package com.codeaddi.row_your_boat.view.display;

import com.codeaddi.row_your_boat.TestData;
import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.controller.http.RowerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ViewServiceTests {
    @Mock
    AvailabilityClient availabilityClient;

    @Mock
    RowerClient rowerClient;

    @InjectMocks
    ViewService viewService;

    // Todo continue with these tests

//    @Test
//    void getAllAvailableRowersForDate_validDateAndAvailableRowers_returnsNamesOfOnlyTheAvailableRowers(){
//        when(availabilityClient.getAllUpcomingPastSessions()).thenReturn(List.of(TestData.pastSession1, TestData.pastSession2));
//        when(availabilityClient.getAllUpcomingPastSessionAvailability()).thenReturn(List.of(TestData.pastSessionAvailability1, TestData.pastSessionAvailability3));
//        when(rowerClient.getAllRowers()).thenReturn(List.of(TestData.rower1, TestData.rower3, TestData.rower2));
//
//        viewService.getAllAvailableRowersForDate(TestData.formattedDate);
//    }
}

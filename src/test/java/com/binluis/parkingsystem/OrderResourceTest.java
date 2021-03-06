package com.binluis.parkingsystem;

import com.binluis.parkingsystem.domain.ParkingOrder;
import com.binluis.parkingsystem.domain.ParkingOrderRepository;
import com.binluis.parkingsystem.models.CreateParkingOrderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;

import static com.binluis.parkingsystem.WebTestUtil.asJsonString;
import static com.binluis.parkingsystem.WebTestUtil.getContentAsObject;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class OrderResourceTest {
    @Autowired
    ParkingOrderRepository parkingOrderRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser
    public void should_get_all_orders() throws Exception {
        ParkingOrder parkingOrder = new ParkingOrder("car2","park","accepted");
        parkingOrderRepository.save(parkingOrder);
        parkingOrderRepository.flush();

        MvcResult result=this.mvc.perform(get("/orders")).andExpect(status().isOk()).andReturn();
        final ParkingOrder[] parkingOrders = getContentAsObject(result, ParkingOrder[].class);


        assertEquals(1,parkingOrders.length);
        assertEquals("car2",parkingOrders[0].getCarNumber());
        assertEquals("park",parkingOrders[0].getRequestType());
        assertEquals("accepted",parkingOrders[0].getStatus());
    }


    //Given a parking boy {"carNumber": "string","requestType":"string","status":"string"},
    //When POST /orders, Return 201
//    @Test
//    public void should_create_parking_order() throws Exception {
//        // Given
//        final ParkingOrder boy = new ParkingOrder("car1","park","pending");
//
//        // When
//        final MvcResult result = mvc.perform(post("/orders").content(asJsonString(boy)).contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        // Then
//        assertEquals(201, result.getResponse().getStatus());
//
//        final ParkingOrder createdOrder = parkingOrderRepository.findAll().get(0);
//        entityManager.clear();
//
//        assertEquals("car1", createdOrder.getCarNumber());
//        assertEquals("park",createdOrder.getRequestType());
//        assertEquals("pending",createdOrder.getStatus());
//    }

    @Test
    @WithMockUser
    public void should_create_parking_order_v1() throws Exception {
        // Given
        CreateParkingOrderRequest request = new CreateParkingOrderRequest().create("A123");

        // When
        mvc.perform(post("/orders")
                .content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        // Then
        final ParkingOrder createdOrder = parkingOrderRepository.findAll().get(0);
        entityManager.clear();

        assertEquals("A123", createdOrder.getCarNumber());
        assertEquals("parking",createdOrder.getRequestType());
        assertEquals("pendingParking",createdOrder.getStatus());

        ParkingOrder[] parkingOrders = getContentAsObject(
                mvc.perform(get("/orders")).andReturn(),ParkingOrder[].class);
        assertEquals(1, parkingOrders.length);
        assertEquals("A123", parkingOrders[0].getCarNumber());
        assertEquals("parking",parkingOrders[0].getRequestType());
        assertEquals("pendingParking",parkingOrders[0].getStatus());

    }

    @Test
    @WithMockUser
    public void should_make_car_fetching_request() throws Exception {
        // Given
        ParkingOrder parkingOrderWithCarParked = new ParkingOrder("ABC", "parking", "parked");
        parkingOrderRepository.saveAndFlush(parkingOrderWithCarParked);
        CreateParkingOrderRequest request=new CreateParkingOrderRequest("ABC","pendingFetching");
        // When
        mvc.perform(patch("/orders/"+parkingOrderWithCarParked.getId()).content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        //Then
        assertEquals("fetching",parkingOrderRepository.findAll().get(0).getRequestType());
        assertEquals("pendingFetching",parkingOrderRepository.findAll().get(0).getStatus());



    }

}



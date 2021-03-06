import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void beforeEachTest() {
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("10:00:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);
        boolean checkRestaurantStatus = spyRestaurant.isRestaurantOpen();
        assertTrue(checkRestaurantStatus);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.parse("23:00:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);
        boolean checkRestaurantStatus = spyRestaurant.isRestaurantOpen();
        assertFalse(checkRestaurantStatus);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //add another method that returns the order value, given the name of the items in <String> format
    //Pass the names of the items as parameters to the method
    //return ordertotal

    @Test
    public void return_total_order_value_of_selected_items_in_menu(){
        ArrayList<String> itemNames = new ArrayList<String>();
        itemNames.add("Sweet corn soup");
        itemNames.add("Vegetable lasagne");
        int orderTotal = restaurant.orderValue(itemNames);
        assertEquals(388,orderTotal);
    }
}
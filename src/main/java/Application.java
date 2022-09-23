import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.zipcode.Place;
import com.kenzie.app.data.zipcode.ZipCodeDTO;
import com.kenzie.app.format.AddressFormatUtil;
import com.kenzie.app.http.HttpUtil;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        //declare variables
        try {


        String BASE_URL = "https://api.zippopotam.us/us/";
        Scanner scanner = new Scanner(System.in);
        String recipientName;
        String streetAddress;
        String city;
        String state;
        String zipCode;



        //read in user input - scanner
        System.out.println("Enter recipient name: ");
        recipientName = scanner.nextLine();
        System.out.println("Enter street address: ");
        streetAddress = scanner.nextLine();
        System.out.println("Enter city: ");
        city = scanner.nextLine();
        System.out.println("Enter state: ");
        state = scanner.nextLine();


        //clean city - Los Angeles
        String tempCity = city.replace(" ", "%20");


        //format URL with user city and state
        String finalURL  = BASE_URL + state + "/" + tempCity;
        //System.out.println(finalURL);



        //Call GET
        String httpResponse = HttpUtil.makeGETRequest(finalURL);
        //System.out.println(httpResponse);

        //if return string contains 404, don't object map
       if (httpResponse.contains("GET request failed")) {
            System.out.println("No zip code found");
        }
       else  {

           //ObjectMapper to retrieve zip code
           //1.  Instantiate ObjetMapper
           ObjectMapper objectMapper = new ObjectMapper();
           //2.  Declare DTO
           ZipCodeDTO zipObj;
           //3.  Set DTO to objectMapper.readValue()
           zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

           zipObj.getPlaces().get(0).getPostCode();
           //if 1 zipCode returned, set zipCode
           if (zipObj.getPlaces().size() == 1) {
               zipCode = zipObj.getPlaces().get(0).getPostCode();
           }
           else  //(zipObj.getPlaces().size() == 1 > 1)
           {
               for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                   System.out.println(i + ") " + zipObj.getPlaces().get(i).getPostCode());
               }
           }

           //else loop and display all zipcodes

           //prompt user to select zipcode
           System.out.println("Choose a zipcode");
           int choice = scanner.nextInt();
           scanner.nextLine();
           zipCode = zipObj.getPlaces().get(choice).getPostCode();

           //set zipCode based on the selection


        //print out final address
            System.out.println(recipientName);
            System.out.println(streetAddress);
            System.out.println(city + "," + state + " " + zipCode);
       }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e);;
        }

    }

    //URL: https://api.zippopotam.us/us/mo/saint%20louis
    public static void main_backup(String[] args) {
        try {

            String URL = "https://api.zippopotam.us/us/mo/saint%20louis";

            String httpResponse;

            httpResponse = HttpUtil.makeGETRequest(URL);

            System.out.println(httpResponse);

            //JSON Object mapping
            //1.  instantiate ObjectMapper
            //2.  create DTO (in order to map data into)
            //3.  readValue()

            ObjectMapper objectMapper = new ObjectMapper();
            ZipCodeDTO zipObj;
            zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

            //print out state, city(place name), zipcode(post code)
            System.out.println("State: " + zipObj.getState());
            System.out.println("City: " + zipObj.getPlaces().get(0).getPlace_name());
            System.out.println("Zip Code: " + zipObj.getPlaces().get(0).getPostCode());

            // if( there's only on item, set zipCode to that> {

        //}
            //else if (<list size is greater than 1>)

//            if (zipObj.getPlaces().size() == 1){
//                System.out.println("Only one zip code: " + zipObj.getPlaces().get(0).getPostCode());
//
//                //<ist size is greater than one>
//            } else if (zipObj.getPlaces().size() > 1) {
//                for (int i = 0; i < zipObj.getPlaces().size(); i++) {
//                    System.out.println("Zone " + i);
//                    System.out.println("State: " + zipObj.getState());
//                    System.out.println("City: " + zipObj.getPlaces().get(i).getPlace_name());
//                    System.out.println("Zip Code: " + zipObj.getPlaces().get(i).getPostCode());
//                }
//
//                }

            //call the formatter util from main
            String testStr = "123 Main St.";
            AddressFormatUtil.initCodeTable();
            System.out.println(AddressFormatUtil.replaceAbbreviation(testStr));


        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
        }

    }
}

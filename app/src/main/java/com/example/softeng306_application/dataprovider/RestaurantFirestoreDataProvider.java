package com.example.softeng306_application.dataprovider;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.softeng306_application.entity.Asian;
import com.example.softeng306_application.entity.Cafe;
import com.example.softeng306_application.entity.European;
import com.example.softeng306_application.entity.FastFood;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFirestoreDataProvider {

    public RestaurantFirestoreDataProvider() {

    }

    // Add number documents to Firestore
    public void addRestaurantToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Restaurant> restaurantsList = getRestaurants();
        for (Restaurant restaurant : restaurantsList) {
            db.collection("restaurants").document("restaurant " + restaurant.getRestaurantID()).set(restaurant).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("Numbers Collection Add", "number " + restaurant.getRestaurantID() + " added.");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.w("Numbers Collection Add", "number " + restaurant.getRestaurantID() + " NOT added.");
                }
            });
        }
    }

    public List<Review> getReviews() {
        List<Review> reviewList = new ArrayList<>();
        Review r1 = new Review("Luke", "I like this");
        Review r2 = new Review("Kai", "I hate this");
        Review r3 = new Review("Naren", "I want to kill myself");
        reviewList.add(r1);
        reviewList.add(r2);
        reviewList.add(r3);
        return reviewList;
    }

    private List<Restaurant> getRestaurants() {
        List<Restaurant> restaurantsArrayList = new ArrayList<>();

        List<String> r0Images = new ArrayList<>();
        r0Images.add("back0_1");
        r0Images.add("back0_2");
        r0Images.add("back0_3");

        List<String> r3Images = new ArrayList<>();
        r3Images.add("back3_1");
        r3Images.add("back3_2");
        r3Images.add("back3_3");

        List<String> r4Images = new ArrayList<>();
        r4Images.add("back4_1");
        r4Images.add("back4_2");
        r4Images.add("back4_3");

        List<String> r5Images = new ArrayList<>();
        r5Images.add("back5_1");
        r5Images.add("back5_2");
        r5Images.add("back5_3");

        List<String> r6Images = new ArrayList<>();
        r6Images.add("back6_1");
        r5Images.add("back6_2");
        r6Images.add("back6_3");

        List<String> r7Images = new ArrayList<>();
        r7Images.add("back7_1");
        r7Images.add("back7_2");
        r7Images.add("back7_3");

        List<String> r8Images = new ArrayList<>();
        r8Images.add("back8_1");
        r8Images.add("back8_2");
        r8Images.add("back8_3");

        List<String> r9Images = new ArrayList<>();
        r9Images.add("back8_1");
        r9Images.add("back8_2");
        r9Images.add("back8_3");

        List<String> r10Images = new ArrayList<>();
        r10Images.add("back10_1");
        r10Images.add("back10_2");
        r10Images.add("back10_3");

        List<String> r11Images = new ArrayList<>();
        r11Images.add("back11_1");
        r11Images.add("back11_2");
        r11Images.add("back11_3");

        List<String> r12Images = new ArrayList<>();
        r12Images.add("back12_1");
        r12Images.add("back12_2");
        r12Images.add("back12_3");

        List<String> r13Images = new ArrayList<>();
        r13Images.add("back13_1");
        r13Images.add("back13_2");
        r13Images.add("back13_3");

        List<String> r14Images = new ArrayList<>();
        r14Images.add("back14_1");
        r14Images.add("back14_2");
        r14Images.add("back14_3");

        List<String> r15Images = new ArrayList<>();
        r15Images.add("back15_1");
        r15Images.add("back15_2");
        r15Images.add("back15_3");

        List<String> r16Images = new ArrayList<>();
        r16Images.add("back16_1");
        r16Images.add("back16_2");
        r16Images.add("back16_3");

        List<String> r17Images = new ArrayList<>();
        r17Images.add("back17_1");
        r17Images.add("back17_2");
        r17Images.add("back17_3");

        List<String> r18Images = new ArrayList<>();
        r18Images.add("back18_1");
        r18Images.add("back18_2");
        r18Images.add("back18_3");

        List<String> r19Images = new ArrayList<>();
        r19Images.add("back19_1");
        r19Images.add("back19_2");
        r19Images.add("back19_3");

        List<String> r20Images = new ArrayList<>();
        r20Images.add("back20_1");
        r20Images.add("back20_2");
        r20Images.add("back20_3");

        List<String> r21Images = new ArrayList<>();
        r21Images.add("back21_1");
        r21Images.add("back21_2");
        r21Images.add("back21_3");


        List<String> r22Images = new ArrayList<>();
        r22Images.add("back22_1");
        r22Images.add("back22_2");
        r22Images.add("back22_3");


        List<String> r23Images = new ArrayList<>();
        r23Images.add("back23_1");
        r23Images.add("back23_2");
        r23Images.add("back23_3");

        List<String> r24Images = new ArrayList<>();
        r24Images.add("back24_1");
        r24Images.add("back24_2");
        r24Images.add("back24_3");

        List<String> r25Images = new ArrayList<>();
        r25Images.add("back25_1");
        r25Images.add("back25_2");
        r25Images.add("back25_3");

        List<String> r26Images = new ArrayList<>();
        r26Images.add("back26_1");
        r26Images.add("back26_2");
        r26Images.add("back26_3");

        List<String> r27Images = new ArrayList<>();
        r27Images.add("back27_1");
        r27Images.add("back27_2");
        r27Images.add("back27_3");

        List<String> r28Images = new ArrayList<>();
        r28Images.add("back28_1");
        r28Images.add("back28_2");
        r28Images.add("back28_3");

        List<String> r29Images = new ArrayList<>();
        r29Images.add("back29_1");
        r29Images.add("back29_2");
        r29Images.add("back29_3");

        List<String> r30Images = new ArrayList<>();
        r30Images.add("back30_1");
        r30Images.add("back30_2");
        r30Images.add("back30_3");

        List<String> r31Images = new ArrayList<>();
        r31Images.add("back31_1");
        r31Images.add("back31_2");
        r31Images.add("back31_3");

        List<String> r32Images = new ArrayList<>();
        r32Images.add("back32_1");
        r32Images.add("back32_2");
        r32Images.add("back32_3");

        List<String> r33Images = new ArrayList<>();
        r33Images.add("back33_1");
        r33Images.add("back33_2");
        r33Images.add("back33_3");

        List<String> r34Images = new ArrayList<>();
        r34Images.add("back34_1");
        r34Images.add("back34_2");
        r34Images.add("back34_3");

        List<String> r35Images = new ArrayList<>();
        r35Images.add("back35_1");
        r35Images.add("back35_2");
        r35Images.add("back35_3");

        List<String> r36Images = new ArrayList<>();
        r36Images.add("back36_1");
        r36Images.add("back36_2");
        r36Images.add("back36_3");

        List<String> r37Images = new ArrayList<>();
        r37Images.add("back37_1");
        r37Images.add("back37_2");
        r37Images.add("back37_3");

        List<String> r38Images = new ArrayList<>();
        r38Images.add("back38_1");
        r38Images.add("back38_2");
        r38Images.add("back38_3");

        List<String> r39Images = new ArrayList<>();
        r39Images.add("back39_1");
        r39Images.add("back39_2");
        r39Images.add("back39_3");


        List<Review> chipotleReviews = new ArrayList<>();
        chipotleReviews.add(new Review("user1", "Delicious flavors, great atmosphere, a must-visit for food enthusiasts!"));
        chipotleReviews.add(new Review("user2", "Exquisite presentation, impeccable service, left me craving for more."));
        chipotleReviews.add(new Review("user3", "Innovative menu, bold combinations, a culinary adventure in every bite."));
        chipotleReviews.add(new Review("user4", "Cozy ambiance, comfort food that feels like a warm embrace."));
        chipotleReviews.add(new Review("user5", "Authentic tastes, genuine hospitality, a hidden gem worth discovering."));


        List<Review> seoulNightReviews = new ArrayList<>();
        seoulNightReviews.add(new Review("user1", "Fresh ingredients, creative twists, a feast for the senses here."));
        seoulNightReviews.add(new Review("user2", "Exceptional service, diverse menu, an experience that truly satisfies."));
        seoulNightReviews.add(new Review("user3", "Mouthwatering dishes, exceeded expectations, a taste of perfection."));
        seoulNightReviews.add(new Review("user4", "Charming decor, vibrant vibes, a delightful fusion of flavors and cultures."));
        seoulNightReviews.add(new Review("user5", "Impeccable attention to detail, made each dish a masterpiece."));

        List<Review> bestieCafeReviews = new ArrayList<>();
        bestieCafeReviews.add(new Review("user1", "Simple elegance, farm-to-table ethos, a breath of fresh culinary air."));
        bestieCafeReviews.add(new Review("user2", "Unpretentious charm, indulgent treats, a haven for food lovers."));
        bestieCafeReviews.add(new Review("user3", "Exemplary service, unforgettable dishes, raised the bar for dining out."));

        Restaurant r0 = new Restaurant("1", r0Images, "Chipotle", "Lovely Mexican Food", "Auckland", new FastFood(), "restaurant0", "$", chipotleReviews);
        Restaurant r1 = new Restaurant("2",r0Images, "McDonalds", "Junk Food", "Auckland", new FastFood(), "restaurant1", "$$");
        Restaurant r2 = new Restaurant("3", r0Images,"Wendy's", "A well-known international fast-food restaurant chain specializing in fresh, high-quality burgers, chicken sandwiches, and salads", "Auckland", new FastFood(), "restaurant2", "$");
        Restaurant r3 = new Restaurant("4", r3Images , "Domino's Pizza", "Who does not love pizzas", "Wellington", new FastFood(), "restaurant3", "$");
        Restaurant r4 = new Restaurant("5", r4Images, "Pizza Hut", "The better pizza place", "Auckland", new FastFood(), "restaurant4", "$");
        Restaurant r5 = new Restaurant("6", r5Images, "Subway", "A global fast-food chain recognized for its made-to-order sandwiches and salads, providing a customizable and healthier dining option.", "Christchurch", new FastFood(), "restaurant5", "$");
        Restaurant r6 = new Restaurant("7", r6Images, "Burger King", "The best burgers in town", "Auckland", new FastFood(), "restaurant6", "$");
        Restaurant r7 = new Restaurant("8", r7Images, "Paname Social", "combines culinary excellence with a vibrant social atmosphere, offering a diverse menu inspired by global flavors.", "Auckland", new European(), "restaurant7", "$$$");
        Restaurant r8 = new Restaurant("9",r8Images, "Pappa Rich", "A contemporary Malaysian restaurant known for its diverse menu featuring authentic flavors and cultural richness.", "Auckland", new Asian(), "restaurant8", "$$");
        Restaurant r9 = new Restaurant("10", r9Images, "KFC", "Kentucky Fried Chicken", "Auckland", new FastFood(), "restaurant9", "$");
        Restaurant r10 = new Restaurant("11", r10Images,"Shake Shack",  "Savor juicy burgers, crinkle-cut fries, and creamy shakes at Shake Shack—a paradise for burger aficionados seeking gourmet flavor with a side of laid-back vibes.", "Auckland", new FastFood(), "restaurant10", "$");
        Restaurant r11 = new Restaurant("12", r11Images,"Taco Bell", "Embrace the tasty chaos at Taco Bell, where zesty tacos, cheesy burritos, and crave-worthy nachos redefine fast-food fiestas with every bite.", "Auckland", new FastFood(), "restaurant11", "$");
        Restaurant r12 = new Restaurant("13",r12Images, "Amano", "Indulge in artisanal flavors at Amano, where rustic Italian cuisine meets modern flair, creating a culinary journey of refined tastes.", "Auckland", new European(), "restaurant12", "$$");
        Restaurant r13 = new Restaurant("14",r13Images, "Berlinchen", "Discover Berlin-inspired delights at Berlinchen, a cozy eatery where German authenticity and heartwarming dishes unite for a one-of-a-kind experience", "Auckland", new European(), "restaurant13", "$$$");
        Restaurant r14 = new Restaurant("15", r14Images,"Gerome", "Experience elegance and innovation at Gerome, where New Zealand's finest ingredients transform into modern Mediterranean masterpieces, igniting the palate", "Auckland", new European(), "restaurant14", "$$");
        Restaurant r15 = new Restaurant("16",r15Images, "Hammer & Tongs", "Get ready to savor the bold, South African flavors of Hammer & Tongs, where open-fire grilling meets authentic taste adventures.", "Auckland", new European(), "restaurant15", "$");
        Restaurant r16 = new Restaurant("17",r16Images, "La Petite Fourchette", "Delight in Parisian charm at La Petite Fourchette, a haven of delicate pastries and exquisite French creations, offering a taste of France in Auckland.", "Auckland", new European(), "restaurant16", "$$");
        Restaurant r17 = new Restaurant("18",r17Images, "Le Paris French Eatery", "Journey to France at Le Paris French Eatery, a culinary haven where classic French cuisine and Parisian ambiance enchant every bite.", "Auckland", new European(), "restaurant17", "$$");
        Restaurant r18 = new Restaurant("19",r18Images, "Mamma Rosa", "Taste the love in every bite at Mamma Rosa, a welcoming Italian eatery crafting cherished family recipes for authentic Italian indulgence.", "Auckland", new European(), "restaurant18", "$$");
        Restaurant r19 = new Restaurant("20",r19Images, "Tasca", "Embark on a tapas journey at Tasca, where Spanish flair and vibrant flavors converge, inviting you to savor the art of sharing.", "Auckland", new European(), "restaurant19", "$");
        Restaurant r20 = new Restaurant("21",r20Images, "The Grove", "Elevate your dining experience at The Grove, an award-winning restaurant where contemporary New Zealand cuisine meets sophistication and innovation.", "Auckland", new European(), "restaurant20", "$$");
        Restaurant r21 = new Restaurant("22", r21Images, "Butter Chicken Factory", "Indulge in rich Indian flavors at the Butter Chicken Factory, where aromatic spices and creamy curries bring authentic comfort to your plate.", "Auckland", new Asian(), "restaurant21", "$");
        Restaurant r22 = new Restaurant("23", r22Images, "Cafe BBQ Duck", "Satisfy your cravings at Cafe BBQ Duck, where the aroma of Cantonese roast meats and delectable Chinese fare fills the air.", "Auckland", new Asian(), "restaurant22", "$");
        Restaurant r23 = new Restaurant("24", r23Images, "Fantasy KBBQ", "Unleash your inner grill master at Fantasy KBBQ, where Korean BBQ dreams come true with sizzling meats and endless flavor possibilities.", "Auckland", new Asian(), "restaurant23", "$$");
        Restaurant r24 = new Restaurant("25", r24Images, "Gogo Music Cafe", "Dine and groove at Gogo Music Cafe, a vibrant spot where Asian fusion cuisine and live music create a perfect harmony of enjoyment.", "Auckland", new Asian(), "restaurant24", "$$");
        Restaurant r25 = new Restaurant("26", r25Images, "Hawker Roll", "Explore the vibrant tastes of Southeast Asia at Hawker Roll, where bold flavors and fresh ingredients come together in delightful rolls", "Auckland", new Asian(), "restaurant25", "$");
        Restaurant r26 = new Restaurant("27", r26Images, "Mr Hao", "Experience modern Asian delights at Mr Hao, where traditional recipes are reimagined, delivering a fusion of tastes that captivate the senses.", "Auckland", new Asian(), "restaurant26", "$$");
        Restaurant r27 = new Restaurant("28", r27Images, "Obar", "Immerse yourself in Korean nightlife at Obar, where pulsating beats, savory bites, and lively atmosphere merge for an unforgettable dining and entertainment experience.", "Auckland", new Asian(), "restaurant27", "$$");
        Restaurant r28 = new Restaurant("29", r28Images,"Seoul Night", "Step into the heart of Korea at Seoul Night, a vibrant spot where Korean cuisine and culture come alive in every dish.", "Auckland", new Asian(), "restaurant28", "$$",seoulNightReviews);
        Restaurant r29 = new Restaurant("30",r29Images, "Yoshizawa", "Savor authentic Japanese cuisine at Yoshizawa, where meticulous craftsmanship and the finest ingredients create an unforgettable dining experience.", "Auckland", new Asian(), "restaurant29", "$$");
        Restaurant r30 = new Restaurant("31", r30Images, "Bestie Cafe", "Find comfort at Bestie Cafe, a cozy haven where every cup of coffee and bite of food feels like a warm embrace.", "Auckland", new Cafe(), "restaurant30", "$", bestieCafeReviews);
        Restaurant r31 = new Restaurant("32", r31Images, "Catroux", "Discover culinary artistry at Catroux, a charming cafe where gourmet offerings and creative flavors create a delightful sensory experience.", "Auckland", new Cafe(), "restaurant31", "$");
        Restaurant r32 = new Restaurant("33", r32Images, "Circus Circus", "Step into a whimsical world at Circus Circus, a vibrant cafe where playful dishes and a carnival atmosphere enchant both young and old.", "Auckland", new Cafe(), "restaurant32", "$");
        Restaurant r33 = new Restaurant("34",r33Images, "EightThirty", "Elevate your coffee journey at EightThirty, where each sip is a masterpiece, carefully brewed to awaken your senses and delight your palate.", "Auckland", new Cafe(), "restaurant33", "$");
        Restaurant r34 = new Restaurant("35", r34Images, "Fields Cafe", "Escape to serenity at Fields Cafe, a tranquil oasis where farm-to-table freshness and soothing ambience invite you to unwind.", "Auckland", new Cafe(), "restaurant34", "$");
        Restaurant r35 = new Restaurant("36", r35Images, "Frasers",  "Experience urban elegance at Frasers, a sophisticated cafe where gourmet cuisine and artistic presentation redefine dining in style.", "Auckland", new Cafe(), "restaurant35", "$");
        Restaurant r36 = new Restaurant("37", r36Images, "Goodness Gracious", "Embrace goodness at Goodness Gracious, a cafe where wholesome ingredients and hearty flavors come together for a nourishing treat.", "Auckland", new Cafe(), "restaurant36", "$");
        Restaurant r37 = new Restaurant("38", r37Images, "Honey Cafe",  "Indulge in sweet moments at Honey Cafe, where delectable treats and welcoming vibes create a haven for dessert lovers.", "Auckland", new Cafe(), "restaurant37", "$");
        Restaurant r38 = new Restaurant("39", r38Images, "Ozone Coffee",  "Embark on a coffee journey at Ozone Coffee, a cafe where passion for quality beans and expert brewing techniques result in a truly satisfying cup.", "Auckland", new Cafe(), "restaurant38", "$");
        Restaurant r39 = new Restaurant("40", r39Images, "Slowlane", "Escape the rush at Slowlane, a serene cafe where mindfulness and exceptional coffee meet, inviting you to savor life's simple pleasures.", "Auckland", new Cafe(), "restaurant39", "$");


        restaurantsArrayList.add(r0);
        restaurantsArrayList.add(r1);
        restaurantsArrayList.add(r2);
        restaurantsArrayList.add(r3);
        restaurantsArrayList.add(r4);
        restaurantsArrayList.add(r5);
        restaurantsArrayList.add(r6);
        restaurantsArrayList.add(r7);
        restaurantsArrayList.add(r8);
        restaurantsArrayList.add(r9);
        restaurantsArrayList.add(r10);
        restaurantsArrayList.add(r11);
        restaurantsArrayList.add(r12);
        restaurantsArrayList.add(r13);
        restaurantsArrayList.add(r14);
        restaurantsArrayList.add(r15);
        restaurantsArrayList.add(r16);
        restaurantsArrayList.add(r17);
        restaurantsArrayList.add(r18);
        restaurantsArrayList.add(r19);
        restaurantsArrayList.add(r20);
        restaurantsArrayList.add(r21);
        restaurantsArrayList.add(r22);
        restaurantsArrayList.add(r23);
        restaurantsArrayList.add(r24);
        restaurantsArrayList.add(r25);
        restaurantsArrayList.add(r26);
        restaurantsArrayList.add(r27);
        restaurantsArrayList.add(r28);
        restaurantsArrayList.add(r29);
        restaurantsArrayList.add(r30);
        restaurantsArrayList.add(r31);
        restaurantsArrayList.add(r32);
        restaurantsArrayList.add(r33);
        restaurantsArrayList.add(r34);
        restaurantsArrayList.add(r35);
        restaurantsArrayList.add(r36);
        restaurantsArrayList.add(r37);
        restaurantsArrayList.add(r38);
        restaurantsArrayList.add(r39);

        return restaurantsArrayList;
    }

}
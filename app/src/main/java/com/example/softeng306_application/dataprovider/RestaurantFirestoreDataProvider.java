package com.example.softeng306_application.dataprovider;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
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

        Restaurant r0 = new Restaurant("1", "Chipotle", "Lovely Mexican Food", "Auckland", new FastFood(), "restaurant0", "$", chipotleReviews);
        Restaurant r1 = new Restaurant("2", "McDonalds", "Junk Food", "Auckland", new FastFood(), "restaurant1", "$$");
        Restaurant r2 = new Restaurant("3", "Wendy's", "A well-known international fast-food restaurant chain specializing in fresh, high-quality burgers, chicken sandwiches, and salads", "Auckland", new FastFood(), "restaurant2", "$");
        Restaurant r3 = new Restaurant("4", "Domino's Pizza", "Who does not love pizzas", "Wellington", new FastFood(), "restaurant3", "$");
        Restaurant r4 = new Restaurant("5", "Pizza Hut", "The better pizza place", "Auckland", new FastFood(), "restaurant4", "$");
        Restaurant r5 = new Restaurant("6", "Subway", "A global fast-food chain recognized for its made-to-order sandwiches and salads, providing a customizable and healthier dining option.", "Christchurch", new FastFood(), "restaurant5", "$");
        Restaurant r6 = new Restaurant("7", "Burger King", "The best burgers in town", "Auckland", new FastFood(), "restaurant6", "$");
        Restaurant r7 = new Restaurant("8", "Paname Social", "combines culinary excellence with a vibrant social atmosphere, offering a diverse menu inspired by global flavors.", "Auckland", new European(), "restaurant7", "$$$");
        Restaurant r8 = new Restaurant("9", "Pappa Rich", "A contemporary Malaysian restaurant known for its diverse menu featuring authentic flavors and cultural richness.", "Auckland", new Asian(), "restaurant8", "$$");
        Restaurant r9 = new Restaurant("10", "KFC", "Kentucky Fried Chicken", "Auckland", new FastFood(), "restaurant9", "$");
        Restaurant r10 = new Restaurant("11", "Shake Shack",  "Savor juicy burgers, crinkle-cut fries, and creamy shakes at Shake Shack—a paradise for burger aficionados seeking gourmet flavor with a side of laid-back vibes.", "Auckland", new FastFood(), "restaurant10", "$");
        Restaurant r11 = new Restaurant("12", "Taco Bell", "Embrace the tasty chaos at Taco Bell, where zesty tacos, cheesy burritos, and crave-worthy nachos redefine fast-food fiestas with every bite.", "Auckland", new FastFood(), "restaurant11", "$");
        Restaurant r12 = new Restaurant("13", "Amano", "Indulge in artisanal flavors at Amano, where rustic Italian cuisine meets modern flair, creating a culinary journey of refined tastes.", "Auckland", new European(), "restaurant12", "$$");
        Restaurant r13 = new Restaurant("14", "Berlinchen", "Discover Berlin-inspired delights at Berlinchen, a cozy eatery where German authenticity and heartwarming dishes unite for a one-of-a-kind experience", "Auckland", new European(), "restaurant13", "$$$");
        Restaurant r14 = new Restaurant("15", "Gerome", "Experience elegance and innovation at Gerome, where New Zealand's finest ingredients transform into modern Mediterranean masterpieces, igniting the palate", "Auckland", new European(), "restaurant14", "$$");
        Restaurant r15 = new Restaurant("16", "Hammer & Tongs", "Get ready to savor the bold, South African flavors of Hammer & Tongs, where open-fire grilling meets authentic taste adventures.", "Auckland", new European(), "restaurant15", "$");
        Restaurant r16 = new Restaurant("17", "La Petite Fourchette", "Delight in Parisian charm at La Petite Fourchette, a haven of delicate pastries and exquisite French creations, offering a taste of France in Auckland.", "Auckland", new European(), "restaurant16", "$$");
        Restaurant r17 = new Restaurant("18", "Le Paris French Eatery", "Journey to France at Le Paris French Eatery, a culinary haven where classic French cuisine and Parisian ambiance enchant every bite.", "Auckland", new European(), "restaurant17", "$$");
        Restaurant r18 = new Restaurant("19", "Mamma Rosa", "Taste the love in every bite at Mamma Rosa, a welcoming Italian eatery crafting cherished family recipes for authentic Italian indulgence.", "Auckland", new European(), "restaurant18", "$$");
        Restaurant r19 = new Restaurant("20", "Tasca", "Embark on a tapas journey at Tasca, where Spanish flair and vibrant flavors converge, inviting you to savor the art of sharing.", "Auckland", new European(), "restaurant19", "$");
        Restaurant r20 = new Restaurant("21", "The Grove", "Elevate your dining experience at The Grove, an award-winning restaurant where contemporary New Zealand cuisine meets sophistication and innovation.", "Auckland", new European(), "restaurant20", "$$");
        Restaurant r21 = new Restaurant("22", "Butter Chicken Factory", "Indulge in rich Indian flavors at the Butter Chicken Factory, where aromatic spices and creamy curries bring authentic comfort to your plate.", "Auckland", new Asian(), "restaurant21", "$");
        Restaurant r22 = new Restaurant("23", "Cafe BBQ Duck", "Satisfy your cravings at Cafe BBQ Duck, where the aroma of Cantonese roast meats and delectable Chinese fare fills the air.", "Auckland", new Asian(), "restaurant22", "$");
        Restaurant r23 = new Restaurant("24", "Fantasy KBBQ", "Unleash your inner grill master at Fantasy KBBQ, where Korean BBQ dreams come true with sizzling meats and endless flavor possibilities.", "Auckland", new Asian(), "restaurant23", "$$");
        Restaurant r24 = new Restaurant("25", "Gogo Music Cafe", "Dine and groove at Gogo Music Cafe, a vibrant spot where Asian fusion cuisine and live music create a perfect harmony of enjoyment.", "Auckland", new Asian(), "restaurant24", "$$");
        Restaurant r25 = new Restaurant("26", "Hawker Roll", "Explore the vibrant tastes of Southeast Asia at Hawker Roll, where bold flavors and fresh ingredients come together in delightful rolls", "Auckland", new Asian(), "restaurant25", "$");
        Restaurant r26 = new Restaurant("27", "Mr Hao", "Experience modern Asian delights at Mr Hao, where traditional recipes are reimagined, delivering a fusion of tastes that captivate the senses.", "Auckland", new Asian(), "restaurant26", "$$");
        Restaurant r27 = new Restaurant("28", "Obar", "Immerse yourself in Korean nightlife at Obar, where pulsating beats, savory bites, and lively atmosphere merge for an unforgettable dining and entertainment experience.", "Auckland", new Asian(), "restaurant27", "$$");
        Restaurant r28 = new Restaurant("29", "Seoul Night", "Step into the heart of Korea at Seoul Night, a vibrant spot where Korean cuisine and culture come alive in every dish.", "Auckland", new Asian(), "restaurant28", "$$",seoulNightReviews);
        Restaurant r29 = new Restaurant("30", "Yoshizawa", "Savor authentic Japanese cuisine at Yoshizawa, where meticulous craftsmanship and the finest ingredients create an unforgettable dining experience.", "Auckland", new Asian(), "restaurant29", "$$");
        Restaurant r30 = new Restaurant("31", "Bestie Cafe", "Find comfort at Bestie Cafe, a cozy haven where every cup of coffee and bite of food feels like a warm embrace.", "Auckland", new Cafe(), "restaurant30", "$", bestieCafeReviews);
        Restaurant r31 = new Restaurant("32", "Catroux", "Discover culinary artistry at Catroux, a charming cafe where gourmet offerings and creative flavors create a delightful sensory experience.", "Auckland", new Cafe(), "restaurant31", "$");
        Restaurant r32 = new Restaurant("33", "Circus Circus", "Step into a whimsical world at Circus Circus, a vibrant cafe where playful dishes and a carnival atmosphere enchant both young and old.", "Auckland", new Cafe(), "restaurant32", "$");
        Restaurant r33 = new Restaurant("34", "EightThirty", "Elevate your coffee journey at EightThirty, where each sip is a masterpiece, carefully brewed to awaken your senses and delight your palate.", "Auckland", new Cafe(), "restaurant33", "$");
        Restaurant r34 = new Restaurant("35", "Fields Cafe", "Escape to serenity at Fields Cafe, a tranquil oasis where farm-to-table freshness and soothing ambience invite you to unwind.", "Auckland", new Cafe(), "restaurant34", "$");
        Restaurant r35 = new Restaurant("36", "Frasers",  "Experience urban elegance at Frasers, a sophisticated cafe where gourmet cuisine and artistic presentation redefine dining in style.", "Auckland", new Cafe(), "restaurant35", "$");
        Restaurant r36 = new Restaurant("37", "Goodness Gracious", "Embrace goodness at Goodness Gracious, a cafe where wholesome ingredients and hearty flavors come together for a nourishing treat.", "Auckland", new Cafe(), "restaurant36", "$");
        Restaurant r37 = new Restaurant("38", "Honey Cafe",  "Indulge in sweet moments at Honey Cafe, where delectable treats and welcoming vibes create a haven for dessert lovers.", "Auckland", new Cafe(), "restaurant37", "$");
        Restaurant r38 = new Restaurant("39", "Ozone Coffee",  "Embark on a coffee journey at Ozone Coffee, a cafe where passion for quality beans and expert brewing techniques result in a truly satisfying cup.", "Auckland", new Cafe(), "restaurant38", "$");
        Restaurant r39 = new Restaurant("40", "Slowlane", "Escape the rush at Slowlane, a serene cafe where mindfulness and exceptional coffee meet, inviting you to savor life's simple pleasures.", "Auckland", new Cafe(), "restaurant39", "$");


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
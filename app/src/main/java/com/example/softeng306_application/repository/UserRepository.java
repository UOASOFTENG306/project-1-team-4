package com.example.softeng306_application.repository;

import com.example.softeng306_application.entity.Favourites;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * This class will be a singleton that is responsible for all fields within the user's database and authentication
 */

public class UserRepository implements IUserRepository {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static UserRepository instance;

    public static UserRepository getInstance(){
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    /**
     * This method would get the user collection from firestore based of the user's id
     * @return a Document Snapshot of the user's document
     */

    public Task<DocumentSnapshot> getAllUserInformation() {
        Task<DocumentSnapshot> docRef = db.collection("users").document(this.getCurrentUserById()).get();
        return docRef;
    }

    /**
     * This method would get the current id of the user
     * @return UserId in the form of a string
     */

    @Override
    public String getCurrentUserById() {
        FirebaseUser currentUser = this.getUser();
        if (currentUser != null) {
            return currentUser.getUid();
        }
        return null;
    }

    /**
     * This method would utilise Firebase Authentication to sign a user in using email and password
     * @param email
     * @param password
     * @return An authentication result
     */

    @Override
    public Task<AuthResult> signIn(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    /**
     * This method would utilise Firebase Authentication to register a new user
     * @param email
     * @param password
     * @param username
     * @return An authentication result
     */

    @Override
    public Task<AuthResult> register(String email, String password, String username) {
        Task<AuthResult> newUser = mAuth.createUserWithEmailAndPassword(email, password);
        return newUser;
    }


    /**
     * This method would write information about the newly registered user to Firebase Firestore
     * @param email
     * @param password
     * @param username
     */

    @Override
    public void addUserToDb(String email, String password, String username) {
        User user = new User(mAuth.getUid(),email,password,username);
        db.collection("users").document(mAuth.getUid()).set(user);
    }

    /**
     * This method would utilise Firebase Authentication method of getting a current user
     * @return the current user
     */

    @Override
    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

    /**
     * This method would allows the currently authenticated user to sign out of the application
     */

    @Override
    public void logout() {
        mAuth.signOut();
    }


    /**
     * This method would retrieve the favourite restaurants of a specified user
     * @return a Document snapshot of all the favourite restaurants
     */

    @Override
    public Task<DocumentSnapshot> getFavourites() {
        String userID = getCurrentUserById();
        Task <DocumentSnapshot> task = db.collection("users").document(userID).get();
        return task;
    }


    /**
     * This method would add favourites to the favourites field inside of the specified user's document
     * @param restaurant the restaurant that the user hearted
     */

    @Override
    public void addFavourite(Restaurant restaurant) {
        DocumentReference documentRef  = db.collection("users").document(this.getCurrentUserById());
        documentRef.update("favourites.favouriteRestaurants", FieldValue.arrayUnion(restaurant));
    }

    /**
     * This method would delete the favourite restaurant from the favourites field inside of the specified user's document
     * @param restaurant the restaurant that the user unhearted
     */

    @Override
    public void removeFavourite(Restaurant restaurant) {
        DocumentReference documentRef = db.collection("users").document(this.getCurrentUserById());
        documentRef.update("favourites.favouriteRestaurants", FieldValue.arrayRemove(restaurant));
    }
}


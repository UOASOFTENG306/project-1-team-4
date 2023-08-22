package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Favourites;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public Task<DocumentSnapshot> getAllUserInformation() {
        Task<DocumentSnapshot> docRef = db.collection("users").document(this.getCurrentUserById()).get();
        return docRef;
    }

    @Override
    public String getCurrentUserById() {
        FirebaseUser currentUser = this.getUser();
        if (currentUser != null) {
            return currentUser.getUid();
        }
        return null;
    }

    @Override
    public Task<AuthResult> signIn(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public Task<AuthResult> register(String email, String password, String username) {
        Task<AuthResult> newUser = mAuth.createUserWithEmailAndPassword(email, password);
        return newUser;
    }

    @Override
    public void addUserToDb(String email, String password, String username) {
        User user = new User(mAuth.getUid(),email,password,username);
        db.collection("users").document(mAuth.getUid()).set(user);
    }

    @Override
    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

    @Override
    public void logout() {
        mAuth.signOut();
    }

    @Override
    public Task<DocumentSnapshot> getFavourites() {
        String userID = getCurrentUserById();
        Task <DocumentSnapshot> task = db.collection("users").document(userID).get();
        return task;
    }

    @Override
    public Favourites checkFavourite(String userID, String restaurantID) {
        return null;
    }

    @Override
    public void addFavourite(Restaurant restaurant) {
        DocumentReference documentRef  = db.collection("users").document(this.getCurrentUserById());
        documentRef.update("favourites", FieldValue.arrayUnion(restaurant));
    }

    @Override
    public void deleteFavourite(Restaurant restaurant) {
        DocumentReference documentRef = db.collection("users").document(this.getCurrentUserById());
        documentRef.update("favourite", FieldValue.arrayRemove(restaurant));
    }
}


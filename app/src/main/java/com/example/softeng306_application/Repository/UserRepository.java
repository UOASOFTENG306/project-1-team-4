package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Favourites;
import com.example.softeng306_application.Entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.atomic.AtomicReference;

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

    public Task<DocumentSnapshot> getAllUserInformation(String userID) {
        Task<DocumentSnapshot> docRef = db.collection("users").document(userID).get();
        return docRef;
    }

    public String getUserName() {
        Task<DocumentSnapshot> docRef = this.getAllUserInformation(this.getCurrentUserById());
        AtomicReference<String> username = new AtomicReference<>("YOLO");
        docRef
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Get the value of a specific field
                        username.set(documentSnapshot.getString("username"));
                    }
                });
        return username.get();
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
    public Favourites getFavourites(String userID) {
        return null;
    }

    @Override
    public Favourites checkFavourite(String userID, String restaurantID) {
        return null;
    }
}


package emre.turhal.go4lunch.firebase.repository;

import android.content.Context;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import emre.turhal.go4lunch.models.Workmate;


public final class UserRepository {

    private static volatile UserRepository instance;
    private static final String COLLECTION_WORKMATE = "Workmate";

    public static CollectionReference getWorkmateCollection(){
            return FirebaseFirestore.getInstance().collection(COLLECTION_WORKMATE);
    }


    public static UserRepository getInstance() {
        UserRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized(UserRepository.class) {
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }
    }

    public FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void createUser(){
        FirebaseUser user = getCurrentUser();
        if (user != null){
            String urlPicture = (user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null;
            String name = (user.getDisplayName());
            String uid =(user.getUid());

            Workmate userToCreate = new Workmate(urlPicture, name, uid);

            Task<DocumentSnapshot> userData = getUserData();

            userData.addOnSuccessListener(documentSnapshot -> getWorkmateCollection().document(uid).set(userToCreate));
        }
    }

    public Task<DocumentSnapshot> getUserData(){
        String uid = getCurrentUser().getUid();
        return getWorkmateCollection().document(uid).get();
    }

    public Task<DocumentSnapshot> getWorkmate(String uid){
        return UserRepository.getWorkmateCollection().document(uid).get();
    }

    public Task<Void> signOut(Context context){
        return AuthUI.getInstance().signOut(context);

    }

    public static void updateUserSettings(String userId, boolean notification) {
        UserRepository.getWorkmateCollection().document(userId).update("notification", notification);
    }



}

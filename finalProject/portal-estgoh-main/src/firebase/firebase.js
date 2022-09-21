import Firebase from "firebase/compat/app";
import {
  getAuth,
  signInWithRedirect,
  signInWithPopup,
  GoogleAuthProvider,
  createUserWithEmailAndPassword,
  signInWithEmailAndPassword,
  onAuthStateChanged,
  signOut,
  updateEmail,
} from "firebase/auth";
import {
  getFirestore,
  doc,
  getDoc,
  setDoc,
  collection,
  writeBatch,
  query,
  getDocs,
  addDoc,
  orderBy,
  updateDoc,
  arrayUnion,
  arrayRemove,
  increment,
  deleteDoc,
  where
} from "firebase/firestore";
import { getStorage, ref, getDownloadURL, uploadBytes } from "firebase/storage";

const config = {
  apiKey: process.env.REACT_APP_FIREBASE_API_KEY,
  authDomain:  process.env.REACT_APP_FIREBASE_AUTO_DOMAIN,
  projectId: process.env.REACT_APP_FIREBASE_PROJECT_ID,
  storageBucket: process.env.REACT_APP_FIREBASE_STORAGE_BUCKET,
  messagingSenderId: process.env.REACT_APP_FIREBASE_MESSAGING_SENDER_ID,
  appId: process.env.REACT_APP_FIREBASE_APP_ID,
};

const firebase = Firebase.initializeApp(config);
export const db = getFirestore();

const provider = new GoogleAuthProvider();

provider.setCustomParameters({
  prompt: "select_account",
});

export const auth = getAuth();
export const signInWithGooglePopup = () => signInWithPopup(auth, provider);
export const signInWithGoogleRedirect = () =>
  signInWithRedirect(auth, provider);
const storage = getStorage();

export const addCollectionAndDocuments = async (
  collectionKey,
  objectsToAdd,
  titleKey
) => {
  if(objectsToAdd.companyName){
    objectsToAdd.companyPic = await getDownloadURL(ref(storage, objectsToAdd.companyName));
  }
  const collectionRef = collection(db, collectionKey);
  if (titleKey === undefined) {
    addDoc(collectionRef, objectsToAdd);
  } else {
    const batch = writeBatch(db);
    const docRef = doc(collectionRef, titleKey);
    batch.set(docRef, objectsToAdd);
    await batch.commit();
  }
};

export const updateDocUsers = async (formFields, userID) => {
  const followerRef = doc(db, "users", userID);
  formFields.profilePic = await getDownloadURL(ref(storage, userID));
  await updateDoc(followerRef, formFields);
    updateEmail(auth.currentUser, formFields.email).then(() => {
    }).catch((error) => {
      if(error === "auth/email-already-in-use")
        alert("Esse email já é utilizado")
    });

};

export const updateFollowers = async (
  followerUserId,
  loggedIn,
  isFollowingThisPerson
) => {
  const followerRef = doc(db, "users", followerUserId);
  await updateDoc(followerRef, {
    followers: isFollowingThisPerson
      ? arrayRemove(loggedIn)
      : arrayUnion(loggedIn),
  });
  const followingRef = doc(db, "users", loggedIn);
  await updateDoc(followingRef, {
    following: isFollowingThisPerson
      ? arrayRemove(followerUserId)
      : arrayUnion(followerUserId),
  });
};

export const isFollowingThisPerson = async(currentUserId, suggestedFriendUserId) => {
  const followingRef = doc(db, "users", currentUserId);
  const docSnapshot = await getDoc(followingRef);
  return docSnapshot.data().following.includes(suggestedFriendUserId)
}

export const getDocActualUser = async (userID) => {
  const docRef = doc(db, "users", userID);
  const docSnapshot = await getDoc(docRef);
  return docSnapshot.data();
};

export const getPostsAndDocuments = async () => {
  const collectionRef = collection(db, "POST");
  const q = query(collectionRef, orderBy("currentTime", "desc"));
  const querySnapshot = await getDocs(q);
  const postMap = querySnapshot.docs.reduce((acc, docSnapshot) => {
    acc[docSnapshot.id] = docSnapshot.data();
    return acc;
  }, {});
  return postMap;
};

export const getOffersAndDocuments = async () => {
  const collectionRef = collection(db, "Offer");
  const q = query(collectionRef, orderBy("currentTime", "desc"));
  const querySnapshot = await getDocs(q);
  const offerMap = querySnapshot.docs.reduce((acc, docSnapshot) => {
    acc[docSnapshot.id] = docSnapshot.data();
    return acc;
  }, {});
  return offerMap;
};

export const getComment = async (docID) => {
  const collectionRef = collection(db, "comments");
  const q = query(collectionRef, orderBy("currentTime", "desc"));
  const querySnapshot = await getDocs(q);
  const commentMap = querySnapshot.docs.reduce((acc, docSnapshot) => {
    acc[docSnapshot.id] = docSnapshot.data();
    return acc;
  }, {});
  var results = Object.keys(commentMap).reduce(function(acc, val) {
    if(commentMap[val].postid === docID)  acc[val] = commentMap[val];
      return acc;
    }, {});
  return results;
};

export const getNotifications = async (userID) => {
  const collectionRef = collection(db, "notification");
  const q = query(collectionRef, orderBy("currentTime", "desc"));
  const querySnapshot = await getDocs(q);
  const notificationMap = querySnapshot.docs.reduce((acc, docSnapshot) => {
    acc[docSnapshot.id] = docSnapshot.data();
    return acc;
  }, {});
  var results = Object.keys(notificationMap).reduce(function(acc, val) {
    if(notificationMap[val].postOwner === userID)  acc[val] = notificationMap[val];
      return acc;
    }, {});
  return results;
};


export const checkIfUsersAlreadyLikedIt = async (userID, collection ,docID) => {
  const docRef = doc(db, collection, docID);
  const docSnapshot = await getDoc(docRef);
  const result = docSnapshot.data().usersWhoLikedIt.includes(userID);
  return result;
};

export const checkIfAlreadyNotified = async (userID, docID) => {
  let value = false;
  const collectionRef = collection(db, "notification");
  const q = query(collectionRef, where("check", "==", false));
  const querySnapshot = await getDocs(q);
  querySnapshot.forEach((doc) => {
      if(doc.data().actualUser === userID && doc.data().docId === docID){
        value = true;
      } 
    });
    return value
}

export const updateLikes = async (docID, isAlreadyLiked, isComment ,userID) => {
  const followerRef = doc(db, isComment, docID);
  if (isAlreadyLiked) {
    await updateDoc(followerRef, {
      likes: increment(-1),
      usersWhoLikedIt: arrayRemove(userID),
    });
  } else {
    await updateDoc(followerRef, {
      likes: increment(+1),
      usersWhoLikedIt: arrayUnion(userID),
    });
  }
};

export const changeCheck = async (docID) => {
  const followerRef = doc(db, "notification", docID);
    await updateDoc(followerRef, {
      check: true,
    });
};

export const removePost = async (docID, type) => {
  await deleteDoc(doc(db, type, docID))
}

export const uploadPhoto = async (file, currentUserUID) => {
  const fileRef = ref(storage, currentUserUID);
  const snapshot = await uploadBytes(fileRef, file);
  return snapshot;
};

export const getSuggestedFriends = async () => {
  const collectionRef = collection(db, "users");
  const q = query(collectionRef);
  const querySnapshot = await getDocs(q);
  const suggestedFriends = querySnapshot.docs.reduce((acc, docSnapshot) => {
    acc[docSnapshot.id] = docSnapshot.data();
    return acc;
  }, {});
  return suggestedFriends;
};

export const createUserDocumentFromAuth = async (
  userAuth,
  additionalInformation = {}
) => {
  if (!userAuth) return;

  const userDocRef = doc(db, "users", userAuth.uid);

  const userSnapshot = await getDoc(userDocRef);

  if (!userSnapshot.exists()) {
    const { displayName, email } = userAuth;
    const createdAt = new Date();
    const profilePicRef = ref(storage, "default-profile.jpeg");
    const profilePic = await getDownloadURL(profilePicRef);
    const followers = [];
    const following = [];
    try {
      await setDoc(userDocRef, {
        displayName,
        email,
        createdAt,
        profilePic,
        followers,
        following,
        ...additionalInformation,
      });
    } catch (error) {
      console.log("error creating the user", error.message);
    }
  }
  return userDocRef;
};

export const createAuthUserWithEmailAndPassword = async (email, password) => {
  if (!email || !password) return;

  return await createUserWithEmailAndPassword(auth, email, password);
};

export const signInUserWithEmailAndPassword = async (email, password) => {
  if (!email || !password) return;

  return await signInWithEmailAndPassword(auth, email, password);
};

export const onAuthStateChangedListener = (callback) =>
  onAuthStateChanged(auth, callback);

export const signOutUser = async () => await signOut(auth);

export { firebase };

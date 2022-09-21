import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import App from "./App";
import FirebaseContext from "./context/firebase";
import { firebase } from "./firebase/firebase";
import { UserProvider } from "./context/user.context";
import { PostProvider } from "./context/posts.context";
import { SuggestedFriendsProvider } from "./context/suggestedFriends.context";
import {OfferProvider} from "./context/offers.context"

ReactDOM.render(
  <React.StrictMode>
    <FirebaseContext.Provider value={{ firebase }}>
      <UserProvider>
        <BrowserRouter>
          <SuggestedFriendsProvider>
            <OfferProvider>
              <PostProvider>
                <App />
              </PostProvider>
            </OfferProvider>
          </SuggestedFriendsProvider>
        </BrowserRouter>
      </UserProvider>
    </FirebaseContext.Provider>
  </React.StrictMode>,

  document.getElementById("root")
);

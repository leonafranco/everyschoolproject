import { createContext, useEffect, useState } from "react";

import { getSuggestedFriends } from "../firebase/firebase";

export const SuggestedFriendsContext = createContext({
  suggestedFriendsMap: [],
});

export const SuggestedFriendsProvider = ({ children }) => {
  const [suggestedFriendsMap, setsuggestedFriendsMap] = useState([]);
  useEffect(() => {
    const getsuggestedFriends = async () => {
      const suggestedFriendsMap = await getSuggestedFriends();
      setsuggestedFriendsMap(suggestedFriendsMap);
    };
    getsuggestedFriends();
  }, []);

  const value = { suggestedFriendsMap };
  return (
    <SuggestedFriendsContext.Provider value={value}>
      {children}
    </SuggestedFriendsContext.Provider>
  );
};

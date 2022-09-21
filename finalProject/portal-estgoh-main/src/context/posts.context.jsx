import { createContext, useEffect, useState } from "react";

import { getPostsAndDocuments } from "../firebase/firebase";

export const PostContext = createContext({
  postsMap: [],
});

export const PostProvider = ({ children }) => {
  const [postsMap, setPostsMap] = useState([]);
  useEffect(() => {
    const getPosts = async () => {
      const postMap = await getPostsAndDocuments();
      setPostsMap(postMap);
    };
    getPosts();
  }, []);

  const value = { postsMap };
  return (
    <PostContext.Provider value={value}> {children} </PostContext.Provider>
  );
};

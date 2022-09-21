import { createContext, useEffect, useState } from "react";

import { getOffersAndDocuments } from "../firebase/firebase";

export const OfferContext = createContext({
  offersMap: [],
});

export const OfferProvider = ({ children }) => {
  const [offersMap, setOffersMap] = useState([]);
  useEffect(() => {
    const getOffers = async () => {
      const offerMap = await getOffersAndDocuments();
      setOffersMap(offerMap);
    };
    getOffers();
  }, []);

  const value = { offersMap };
  return (
    <OfferContext.Provider value={value}> {children} </OfferContext.Provider>
  );
};

import React, { Fragment, useContext } from "react";
import MenuItem from "../job-listing-card/job-listing-card.component";

import { OfferContext } from "../../context/offers.context";

const ConvertOffersMap = ({setOfferToView}) => {
  const { offersMap } = useContext(OfferContext);
  return (
    <Fragment>
      {Object.keys(offersMap).map((key) => (
        <Fragment key={key}>
          <div className="directory-pub">
            <MenuItem
                offer ={offersMap[key]}
               setOfferToView={setOfferToView}
               postID={key}
            />
          </div>
        </Fragment>
      ))}
    </Fragment>
  );
};

export default ConvertOffersMap;

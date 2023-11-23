import { Sidebar } from "../components/Sidebar"
import { PeepForm } from "../components/PeepForm"
import { PeepCard } from "../components/PeepCard"

import PeepModel from "../utils/Peep.model"

export const ChitterHome = ({ signedIn, signedOut, peeps, handleAddPeeps, user }) => {
    return (
        <div className="container">
            <div className="row mt-2">
                <div className="col-3">
                    <Sidebar signedIn={signedIn} signedOut={signedOut} user={user} />
                </div>
                <div className="col-9">
                    <h5 className="border-bottom pb-2 mt-3">Home</h5>
                    {signedIn && <PeepForm handleAddPeeps={handleAddPeeps} user={user} />}
                    <h6 className="my-3">Latest peeps</h6>
                    <PeepCard peeps={peeps} />
                </div>
            </div>
        </div>
    )
}
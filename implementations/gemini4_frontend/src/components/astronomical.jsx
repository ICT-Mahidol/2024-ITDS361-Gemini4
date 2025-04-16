import React, { use } from "react";
import BackButton from "./backButton";
import Cookies from "js-cookie";
import axios from "axios";
import { useEffect, useState } from "react";

function Astronomical() {
    const [planno, setPlanno] = React.useState("")
    const [isFetching, setIsFetching] = useState(false)
    const [images, setImages] = useState([])
    const [sciencePlan, setSciencePlan] = useState(null);

    const user = Cookies.get("user_name")
    const role = Cookies.get("user_role")
    console.log("User: " + user)
    console.log("Role: " + role)

    const handleChange = async () => {
        setIsFetching(true); // Start loading
        if (!planno.trim()) {
            alert("Please fill out the Science plan No.")
            return;
        }

        try {
            const response = await axios.post(`http://localhost:8080/api/getastronomical`, {
                sciplan_no: planno,
            }, {
                withCredentials: true,
            });
            console.log(response.data);
            if (response.data?.images && Array.isArray(response.data.images)) {
                setSciencePlan(response.data.sciencePlan);
                setImages(response.data.images)
            } else {
                alert("No images found in response.")
            }
            alert("Access astronomical data successfully")
        } catch (error) {
            console.error(error);
            alert("No validated science plans found.")
            window.location.reload();
        }
        setIsFetching(false); // Stop loading
        setPlanno("");
        
    };

    if (isFetching) {
        return (
        <div className="flex h-screen items-center justify-center" role="status">
            <svg aria-hidden="true" class="w-8 h-8 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="currentColor"/>
                <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentFill"/>
            </svg>
            <span class="sr-only">Loading...</span>
        </div>
        )
    }

    return (
        <>
        <BackButton onClick="/home" />
        <div className="mx-auto mt-2 p-6 bg-white rounded-xl shadow-lg space-y-6">
            <h1 className="text-2xl font-bold text-gray-800 text-center">Access Astronomical Data</h1>
        </div>

        <div className="flex items-center justify-center bg-gray-100 p-4">
        <input
          type="text"
          value={planno}
          onChange={(e) => setPlanno(e.target.value)}
          placeholder="Enter Science plan No."
          className="p-2 border w-120"
        />
        <button onClick={handleChange} className="rounded-lg ml-2 px-4 py-2 bg-indigo-600 hover:bg-indigo-800 text-white">Send</button>
      </div>

        <div className="bg-gray-100 min-h-screen p-4">
                {sciencePlan && (
            <div className="flex flex-col bg-gray-100 max-w-2xl mx-auto mt-10 p-6 bg-white rounded-xl shadow-lg space-y-4">
                <h1 className="text-2xl font-bold text-gray-800 text-center">Science Plan</h1>
                <p className="text-gray-800"><strong>Plan No:</strong> {sciencePlan.planNo}</p>
                <p className="text-gray-800"><strong>Creator:</strong> {sciencePlan.creator}</p>
                <p className="text-gray-800"><strong>Submitter:</strong> {sciencePlan.submitter}</p>
                <p className="text-gray-800"><strong>Funding (USD):</strong> {sciencePlan.fundingInUSD}</p>
                <p className="text-gray-800"><strong>Objectives:</strong> {sciencePlan.objectives}</p>
                <p className="text-gray-800"><strong>Star System:</strong> {sciencePlan.starSystem}</p>
                <p className="text-gray-800"><strong>Start:</strong> {sciencePlan.startDate}</p>
                <p className="text-gray-800"><strong>End:</strong> {sciencePlan.endDate}</p>
                <p className="text-gray-800"><strong>Location:</strong> {sciencePlan.telescopeLocation}</p>
                <p className="text-gray-800"><strong>Status:</strong> {sciencePlan.status}</p>
            </div>
        )}
                <div className="flex flex-wrap justify-center">
                        {images.length > 0 ? (
                    images.map((url, index) => (
                    <div key={index} className="bg-white mx-8 p-4 rounded-lg shadow-md w-1/3 mt-24 transition delay-150 duration-300 ease-in-out hover:-translate-y-1 hover:scale-110 border-2 border-solid hover:border-indigo-800">
                        <img src={url} alt={`astronomy-${index}`} className="w-full h-full object-cover"/>
                    </div>
                    ))
                ) : ( <p className="text-center text-gray-600 mt-12">No astronomical data to display yet.</p>)}
                </div>
            </div>
        </>
    );
}

export default Astronomical;
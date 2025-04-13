import React from "react"
import { Link } from "react-router-dom"
import telescope from "../assets/telescope.svg"
import configuration from "../assets/configuration.svg"
import astronomical from "../assets/astronomical.svg"
function Home(){
    return (
        <>
        <nav>
        <div className="flex justify-between items-center bg-white p-6 text-black">
            <h1 className="text-xl text-indigo-500 font-bold">Gemini 4</h1>
            <div className="mx-8">
            <Link className="font-bold text-indigo hover:text-indigo-800" to="/">Logout</Link>
            </div>
        </div>
        </nav>
        
        <div className="bg-gray-100 min-h-screen p-4">
        <div className="flex flex-row justify-center">
            <Link to="/home/cli" className="bg-white mx-8 p-4 rounded-lg shadow-md w-1/3 mt-24 transition delay-150 duration-300 ease-in-out hover:-translate-y-1 hover:scale-110 border-2 border-solid hover:border-indigo-800">
                <img src={telescope} alt="astronomy" className="w-full h-40"/>
                <h1 className="text-black-600 font-bold">Command Line Control</h1>
                <h1 className="text-black-600">Command Line Control for Telescope Operator</h1>
            </Link>

            <Link className="bg-white mx-8 p-4 rounded-lg shadow-md w-1/3 mt-24 transition delay-150 duration-300 ease-in-out hover:-translate-y-1 hover:scale-110 border-2 border-solid hover:border-indigo-800">
                <img src={configuration} alt="astronomy" className="w-full h-40"/>
                <h1 className="text-black-600 font-bold">Install New Configurations</h1>
                <h1 className="text-black-600">Install New Configurations for Suppport</h1>
            </Link>

            <Link className="bg-white mx-8 p-4 rounded-lg shadow-md w-1/3 mt-24 transition delay-150 duration-300 ease-in-out hover:-translate-y-1 hover:scale-110 border-2 border-solid hover:border-indigo-800">
                <img src={astronomical} alt="astronomy" className="w-full h-40"/>
                <h1 className="text-black-600 font-bold">Access collected astronomical data</h1>
                <h1 className="text-black-600">Access collected astronomical data for Astronomer</h1>
            </Link>
        </div>
        </div>
        </>
    )
}

export default Home
import React from "react";
import BackButton from "./backButton";

function Cli(){
    return (
        <>
        <BackButton className="" onClick="/home" />
        <div className="bg-black text-green-500 font-mono p-4 rounded-xl shadow-lg max-w-2xl mx-auto mt-10 h-full">
        <div className="mb-2">
          <span className="text-green-400">user@myapp</span>:<span className="text-blue-400">~</span>$ ls
        </div>
        <div className="mb-2">
          <span>README.md</span>&nbsp;&nbsp;<span>src</span>&nbsp;&nbsp;<span>public</span>
        </div>
        <div className="mb-2">
          <span className="text-green-400">user@myapp</span>:<span className="text-blue-400">~</span>$ npm start
        </div>
        <div className="text-yellow-300">
           Starting development server...
        </div>
      </div>
      </>
    )
}

export default Cli;
import React from "react";

function installConfig(props) {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
            <h1 className="text-2xl font-bold mb-4">Install New Configurations</h1>
            <p className="mb-4">Please follow the instructions to install new configurations.</p>
            <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={props.onClick}>
                Install Configurations
            </button>
        </div>
    );
}

export default installConfig;
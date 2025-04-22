import React, { useEffect, useState } from "react";
import BackButton from "./backButton";
import Cookies from "js-cookie";
import axios from "axios";

function Configuration() {
  const [configName, setConfigName] = useState("")
  const [configNo, setConfigNo] = useState("")
  const [configData, setConfigData] = useState("")
  const [showModal, setShowModal] = useState(false);
  const [tempConfigName, setTempConfigName] = useState("");

  const user = Cookies.get("user_name")
  const role = Cookies.get("user_role")

  console.log("User: " + user)
  console.log("Role: " + role)

  // Fetch current configuration
  useEffect(() => {
    axios.get("http://localhost:8080/api/getconfig")
      .then((res) => {
        setConfigData(res.data);
        console.log("Current Config:", res.data)
      })
      .catch((err) => {
        console.error("Failed to fetch configuration:", err)
      })
  }, [])

  // Handle install config
  const handleInstall = () => {
    axios.post("http://localhost:8080/api/installconfig", { config_name: configName })
      .then((res) => {
        if (res.data == "Configuration name must not be null or empty."){
            alert("Please fill out the configuration name")
            return;
        }
        else if (res.data) {
            alert("Configuration installed successfully")
            console.log(res.data)
            window.location.reload();
        } 
        else alert("Installation failed")
      })
      .catch((err) => {
        console.error("Install error:", err)
      })
      
  }

  // Handle remove config
  const handleRemove = () => {
    axios.post(`http://localhost:8080/api/removeconfig/${configNo}`)
      .then((res) => {
        if (res.data){
            console.log(res.data) 
            alert("Configuration removed successfully")
            window.location.reload();
        }
        else alert("Removal failed");
      })
      .catch((err) => {
        console.error("Remove error:", err)
      })
  }

  
  const downloadJsonFile = (data, filename) => {
    const jsonStr = JSON.stringify(data, null, 2);
    const blob = new Blob([jsonStr], {
       type: "application/json" }
      );
    const url = URL.createObjectURL(blob)

    const link = document.createElement("a")
    link.href = url;
    link.download = filename || "data.json"
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
    URL.revokeObjectURL(url)
  }


  return (
    <>
      <BackButton onClick="/home" />
      <div className="max-w-2xl mx-auto mt-10 p-6 bg-white rounded-xl shadow-lg space-y-6">
        <h1 className="text-2xl font-bold text-gray-800 text-center">Current Operating Tables</h1>
        <div className="space-y-2"></div>
        {Array.isArray(configData) && configData.map((config, index) => (
    <div key={index}>
      {config.id}: {config.name}
    </div>
  ))}


        {/* Install Configuration */}
        <div className="flex flex-col sm:flex-row gap-4 items-center">
          {/* <input
            type="text"
            placeholder="Enter new configuration"
            value={configName}
            onChange={(e) => setConfigName(e.target.value)}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
          <button
            onClick={handleInstall}
            className="px-6 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-all">
            Install new configuration
          </button> */}
          <div className="flex justify-start">
          <button onClick={() => setShowModal(true)} className="px-6 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-all">Install new configuration
          </button>
            {showModal && (
            <div className="fixed inset-0 bg-white bg-opacity-50 flex items-center justify-center z-50">
              <div className="bg-white rounded-lg shadow-xl p-6 w-full max-w-md">
                <h2 className="text-xl font-semibold mb-4">Enter configuration name</h2>
                <input
                  type="text"
                  placeholder="Configuration name"
                  value={configName}
                  onChange={(e) => setConfigName(e.target.value)}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md mb-4 focus:outline-none focus:ring-2 focus:ring-green-500"
                />
                <div className="flex justify-end gap-4">
                  <button onClick={handleInstall} className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700">OK </button>
                  <button onClick={() => { setConfigName(""); setShowModal(false);}} className="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400">Cancel</button>
                </div>
              </div>
            </div>
          )}
        </div>
        </div>

        {/* Download */}
        <div className="flex flex-col sm:flex-row gap-4 items-center">
          <button onClick={() => downloadJsonFile(configData, "config.json")} className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-all">
            Download current configuration
          </button>
        </div>
        
        </div>
    </>
  );
}

export default Configuration;

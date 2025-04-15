import React, { useEffect, useState } from "react";
import BackButton from "./backButton";
import Cookies from "js-cookie";
import axios from "axios";

function Configuration() {
  const [configName, setConfigName] = useState("")
  const [configNo, setConfigNo] = useState("")
  const [configData, setConfigData] = useState("")

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
          <input
            type="text"
            placeholder="Enter new configuration"
            value={configName}
            onChange={(e) => setConfigName(e.target.value)}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
          <button
            onClick={handleInstall}
            className="px-6 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-all">
            Install new configuration
          </button>
        </div>

        {/* Remove Configuration */}
        <div className="flex flex-col sm:flex-row gap-4 items-center">
          <input type="text" placeholder="Configuration No." value={configNo} onChange={(e) => setConfigNo(e.target.value)} className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-red-500"/>
          <button onClick={handleRemove} className="px-6 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-all">
            Remove configuration
          </button>
        </div>
        </div>
    </>
  );
}

export default Configuration;

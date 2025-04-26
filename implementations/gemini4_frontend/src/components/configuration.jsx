import React, { useEffect, useState } from "react";
import BackButton from "./backButton";
import Cookies from "js-cookie";

function Configuration() {
  const [loading, setLoading] = useState(false);
  const [configData, setConfigData] = useState(null);
  const [error, setError] = useState("");

  const user = Cookies.get("user_name");
  const role = Cookies.get("user_role");
  console.log("Role:", role);

  // Fetch configuration on page load
  useEffect(() => {
    fetchConfig()
  }, []);

  const fetchConfig = async () => {
    setLoading(true);
    setError(""); // Reset error
    try {
      const response = await fetch("http://localhost:8080/api/get_default_config", {
        credentials: 'include',
      });

      if (!response.ok) {
        throw new Error("Failed to fetch configuration.")
      }

      const data = await response.json()
      setConfigData(data);
      console.log("Fetched config:", data)
    } catch (error) {
      console.error(error)
      setError("Failed to load configuration.")
    } finally {
      setLoading(false)
    }
  };

  // Download current configuration
  const handleDownload = () => {
    if (!configData) {
      console.error("No configuration to download!")
      return;
    }

    const jsonString = JSON.stringify(configData, null, 2)
    const blob = new Blob([jsonString], { type: "application/json" })
    const url = URL.createObjectURL(blob)

    const link = document.createElement('a')
    link.href = url;
    link.setAttribute('download', 'gemini_config_current.json');
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)

    URL.revokeObjectURL(url); // Clean up
  };

  // Upload and replace configuration
  const handleFileChange = (event) => {
    const file = event.target.files[0]
    if (!file) return;

    const reader = new FileReader()
    reader.onload = (e) => {
      try {
        const parsedJson = JSON.parse(e.target.result)
        setConfigData(parsedJson)
        setError("")
        console.log("Uploaded and replaced configuration:", parsedJson)
      } catch (error) {
        console.error("Invalid JSON file:", error)
        setError("The provided Gemini configuration is in a wrong format. Please reupload a correctly formatted file.")
      }
    };
    reader.readAsText(file)
  };

  return (
    <>
      <BackButton onClick="/home" />
      <div className="max-w-2xl mx-auto mt-10 p-6 bg-white rounded-xl shadow-lg space-y-6">
        <div className="flex flex-col space-y-4 mt-6">
          <button
            onClick={handleDownload}
            disabled={!configData}
            className={`px-6 py-2 text-white rounded-lg transition-all ${
              configData ? "bg-green-600 hover:bg-green-700" : "bg-gray-400 cursor-not-allowed"
            }`}>
            Download Current Configuration
          </button>

          <label className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-all text-center cursor-pointer">
            Install New Configuration
            <input
              type="file"
              accept=".json"
              onChange={handleFileChange}
              className="hidden" />
          </label>
        </div>

        <h1 className="text-2xl font-bold text-gray-800 text-center">Current Configuration</h1>

        {loading ? (
          <p className="text-center text-gray-500">Loading...</p>
        ) : error ? (
          <p className="text-center text-red-500">{error}</p>
        ) : configData ? (
          <pre className="bg-gray-100 p-4 rounded-lg overflow-x-auto">
            {JSON.stringify(configData, null, 2)}
          </pre>
        ) : (
          <p className="text-center text-gray-500">No configuration loaded yet.</p>
        )}
      </div>
    </>
  );
}

export default Configuration;

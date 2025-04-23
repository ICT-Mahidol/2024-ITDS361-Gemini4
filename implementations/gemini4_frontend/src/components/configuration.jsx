import React, { useEffect, useState } from "react";
import BackButton from "./backButton";
import Cookies from "js-cookie";
import axios from "axios";

function Configuration() {
  const [configData, setConfigData] = useState([])
  const [showModal, setShowModal] = useState(false)
  const [configInput, setConfigInput] = useState("")
  const [selectedConfigIds, setSelectedConfigIds] = useState([])

  const user = Cookies.get("user_name")
  const role = Cookies.get("user_role")
  console.log("Role: " + role)

  useEffect(() => {
    axios.get("http://localhost:8080/api/getconfig")
      .then((res) => {
        setConfigData(res.data);
      })
      .catch((err) => {
        console.error("Failed to fetch configuration:", err);
      });
  }, []);

  const handleManualInstall = () => {
    if (!configInput.trim()) {
      alert("Please enter a configuration name.");
      return;
    }

    axios.post("http://localhost:8080/api/installconfig", { config_name: configInput })
      .then((res) => {
        if (res.data === "Configuration name must not be null or empty.") {
          alert("Configuration name cannot be empty.");
        } else if (res.data) {
          alert("Configuration installed successfully.");
          setShowModal(false);
          window.location.reload();
        } else {
          alert("Installation failed.");
        }
      })
      .catch((err) => {
        console.error("Install error:", err);
      });
  };

  const handleRemove = () => {
    if (selectedConfigIds.length !== 1) {
      alert("Please select exactly one configuration to remove.");
      return;
    }

    const idToRemove = selectedConfigIds[0];
    axios.post(`http://localhost:8080/api/removeconfig/${idToRemove}`)
      .then((res) => {
        if (res.data) {
          alert("Configuration removed successfully.");
          window.location.reload();
        } else {
          alert("Removal failed.");
        }
      })
      .catch((err) => {
        console.error("Remove error:", err);
      });
  };

  const downloadJsonFile = (data, filename) => {
    const jsonStr = JSON.stringify(data, null, 2);
    const blob = new Blob([jsonStr], { type: "application/json" });
    const url = URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = url;
    link.download = filename || "data.json";
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  };

  return (
    <>
      <BackButton onClick="/home" />
      <div className="max-w-2xl mx-auto mt-10 p-6 bg-white rounded-xl shadow-lg space-y-6">
        <h1 className="text-2xl font-bold text-gray-800 text-center">Current Operating Tables</h1>

        {Array.isArray(configData) && configData.map((config, index) => (
          <div key={index} className="p-4 bg-gray-100 rounded-lg shadow-md mb-2 flex items-center">
            <input
              type="checkbox"
              value={config.id}
              checked={selectedConfigIds.includes(config.id)}
              onChange={(e) => {
                const id = config.id;
                if (e.target.checked) {
                  setSelectedConfigIds(prev => [...prev, id]);
                } else {
                  setSelectedConfigIds(prev => prev.filter(item => item !== id));
                }
              }}
              className="w-4 h-4 mr-4 text-blue-600 bg-gray-100 border-gray-300 rounded-sm"
            />
            <p>{config.id}: {config.name}</p>
          </div>
        ))}

        {/* Install New Configuration (manual input via modal) */}
        <div className="flex flex-col sm:flex-row gap-4 items-center">
          <button
            onClick={() => setShowModal(true)}
            className="mt-4 px-6 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-all"
          >
            Install new configuration
          </button>

          {showModal && (
            <div className="fixed inset-0 bg-white bg-opacity-50 flex items-center justify-center z-50">
              <div className="bg-white rounded-lg shadow-xl p-6 w-full max-w-md">
                <h2 className="text-xl font-semibold mb-4">Enter configuration name</h2>
                <input
                  type="text"
                  placeholder="Configuration name"
                  value={configInput}
                  onChange={(e) => setConfigInput(e.target.value)}
                  className="w-full px-4 py-2 border border-gray-300 rounded-md mb-4 focus:outline-none focus:ring-2 focus:ring-green-500"
                />
                <div className="flex justify-end gap-4">
                  <button onClick={handleManualInstall} className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700">OK</button>
                  <button onClick={() => { setConfigInput(""); setShowModal(false); }} className="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400">Cancel</button>
                </div>
              </div>
            </div>
          )}
        </div>

        {/* Download Selected Configuration(s) */}
        <div className="flex flex-col sm:flex-row gap-4 items-center">
          <button
            onClick={() => {
              const selectedConfigs = configData.filter(cfg => selectedConfigIds.includes(cfg.id));
              if (selectedConfigs.length === 0) {
                alert("Please select at least one configuration to download.");
                return;
              }
              downloadJsonFile(selectedConfigs, "selected-config.json");
            }}
            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-all"
          >
            Download selected configuration
          </button>
        </div>

        {/* Remove Selected Configuration */}
        <div className="flex flex-col sm:flex-row gap-4 items-center">
          <button
            onClick={handleRemove}
            className="px-6 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-all"
          >
            Remove configuration
          </button>
        </div>
      </div>
    </>
  );
}

export default Configuration;

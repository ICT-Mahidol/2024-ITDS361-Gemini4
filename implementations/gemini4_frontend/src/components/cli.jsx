import React, { useState } from "react";
import BackButton from "./backButton";
import axios from "axios";
import Cookies from "js-cookie";
import { useEffect } from "react";

function Cli() {
  const [TelescopeId, setTelescopeId] = useState("");
  const [telescopes, setTelescopes] = useState([]);
  const [command, setCommand] = useState("");
  const [history, setHistory] = useState([]); 
  var user = Cookies.get('user_name')
  var role = Cookies.get('user_role')
  console.log("User: " + user)
  console.log("Role: " + role)

  useEffect(() => {
    const fetchTelescopes = async () => {
      try {
        const res = await axios.get("http://localhost:8080/telescopes",
        { withCredentials: true });
        setTelescopes(res.data);
        console.log(res.data);
        if (res.data.length > 0) {
          setTelescopeId(res.data[0].id); // Set default telescope ID to the first one
        }
      } catch (err) {
        console.error("Error fetching telescopes:", err);
      }
    };

    fetchTelescopes();
  }
  , []);

  const sendCommand = async () => {
    if (!command.trim() || !TelescopeId) return;
  
    try {
      const res = await axios.post(
        "http://localhost:8080/execute",
        {
          command: command.trim(),
          id: TelescopeId
        },
        { withCredentials: true }
      );
  
      const newEntry = {
        cmd: command,
        out: res.data
      };
  
      setHistory((prev) => [...prev, newEntry]);
      setCommand("");
    } catch (err) {
      const newEntry = {
        cmd: command,
        out: "Error: " + err.message
      };
      setHistory((prev) => [...prev, newEntry]);
      setCommand("");
    }
  };
  

  return (
    <>
      <BackButton onClick="/home" />
      <div className="flex items-center justify-center bg-gray-100 p-4">
          <select value={TelescopeId} onChange={(e) => setTelescopeId(e.target.value)} className="p-2 border rounded">
          {telescopes.map((tele) => (
            <option key={tele.id} value={tele.id}>
              Telescope #{tele.id}
            </option>
          ))}
        </select>
      </div>
      <div className="bg-black text-green-500 p-4 mt-4 font-mono min-h-screen">
      {history.map((entry, index) => (
          <div key={index} className="mb-2">
            <span className="text-green-400">{user}@{role}</span>:<span className="text-blue-400">~</span>$ {entry.cmd}
            <div className="ml-4 whitespace-pre-wrap">{entry.out}</div>
          </div>
        ))}
      <span className="text-green-400">{user}@{role}</span>:<span className="text-blue-400">~</span>$
        <input
          type="text"
          value={command}
          onChange={(e) => setCommand(e.target.value)}
          placeholder="Enter command..."
          className="p-2 text-green-500 border border-black rounded focus:outline-none"
          onKeyDown={(e) => e.key === "Enter" && sendCommand()}
        />
      </div>
    </>
  );
}

export default Cli;
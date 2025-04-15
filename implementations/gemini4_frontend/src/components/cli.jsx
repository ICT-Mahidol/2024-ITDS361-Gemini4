import React, { useState } from "react";
import BackButton from "./backButton";
import axios from "axios";
import Cookies from "js-cookie";

function Cli() {
  const [command, setCommand] = useState("");
  const [history, setHistory] = useState([]); 
  var user = Cookies.get('user_name')
  var role = Cookies.get('user_role')
  console.log("User: " + user)
  console.log("Role: " + role)

  const sendCommand = async () => {
    if (!command.trim()) return

    try {
      const res = await axios.post(
        "http://localhost:8080/api/execute",
        { command: command },
        { withCredentials: true }
      )

      const newEntry = {
        cmd: command,
        out: res.data
      }

      setHistory((prev) => [...prev, newEntry]); 
      setCommand("")
    } catch (err) {
      const newEntry = {
        cmd: command,
        out: "Error: " + err.message
      };
      setHistory((prev) => [...prev, newEntry]);
      setCommand("")
    }
  };

  return (
    <>
      <BackButton onClick="/home" />
      <div className="flex items-center justify-center bg-gray-100 p-4">
        <input
          type="text"
          value={command}
          onChange={(e) => setCommand(e.target.value)}
          placeholder="Enter command..."
          className="p-2 border w-120"
          onKeyDown={(e) => e.key === "Enter" && sendCommand()}
        />
        <button onClick={sendCommand} className="rounded-lg ml-2 px-4 py-2 bg-indigo-600 hover:bg-indigo-800 text-white">Send</button>
      </div>

      <div className="bg-black text-green-500 p-4 mt-4 font-mono min-h-screen">
        {history.map((entry, index) => (
          <div key={index} className="mb-2">
            <span className="text-green-400">{user}@{role}</span>:<span className="text-blue-400">~</span>$ {entry.cmd}
            <div className="ml-4 whitespace-pre-wrap">{entry.out}</div>
          </div>
        ))}
      </div>
    </>
  );
}

export default Cli;

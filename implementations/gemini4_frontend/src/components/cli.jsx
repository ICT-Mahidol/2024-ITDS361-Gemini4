import React from "react";
import BackButton from "./backButton";
import axios from "axios";
import { useState } from 'react'
import { useEffect } from 'react'



function Cli(){
  const [command, setCommand] = useState("")
  const [output, setOutput] = useState("")
  const sendCommand = async () => {
    try {
      const res = await axios.post("http://localhost:8080/executecli", {
        command: command,
      }, {
        withCredentials: true,
      })
      setOutput(res.data)
    } catch (err) {
      console.error(err)
      setOutput(err)
    }
  }

    return (
        <>
        <BackButton onClick="/home" />
        <div className="flex items-center justify-center bg-gray-100 p-4">
          <input type="text" value={command} onChange={(e) => setCommand(e.target.value)} placeholder="Enter command..." className="p-2 border w-120"/>
          <button onClick={sendCommand} className="rounded-lg ml-2 px-4 py-2 bg-indigo-600 hover:bg-indigo-800 text-white">Send </button>
        </div>
        <div className="bg-black text-green-500 p-4 mt-4">{output}</div>
      </>
    )
}

export default Cli;